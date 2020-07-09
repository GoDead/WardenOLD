package net.warden.spigot.detection.movement.speed;

import io.github.retrooper.packetevents.event.impl.BukkitMoveEvent;
import net.warden.spigot.Main;
import net.warden.spigot.check.api.PublicCheck;
import net.warden.spigot.check.api.data.Category;
import net.warden.spigot.events.PublicCheckEvent;
import net.warden.spigot.playerdata.PlayerData;
import net.warden.spigot.utils.Compatibility;
import net.warden.spigot.utils.ConfigManager;
import org.bukkit.GameMode;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

public class SpeedB extends PublicCheck {
	public SpeedB() {
		super("Speed", 'B', Category.MOVEMENT);
	}

	@Override
	public PublicCheckEvent onCheck(PublicCheckEvent e) {
		if (!ConfigManager.getInstance().isSpeedBEnabled()) return e;
		if (e.getCauseEvent() instanceof BukkitMoveEvent) {
			if (Compatibility.isInSpectator(((BukkitMoveEvent) e.getCauseEvent()).getPlayer())) return e;
			BukkitMoveEvent event = (BukkitMoveEvent) e.getCauseEvent();
			PlayerData user = Main.getPlayerDataManager().find(event.getPlayer().getUniqueId());
			if (event.getPlayer().hasPotionEffect(PotionEffectType.SPEED)) return e;
			if (event.getPlayer().getGameMode() == GameMode.CREATIVE) return e;
			long time = System.currentTimeMillis() - user.getTimeSinceDamage();
			if (time < 2000) return e;
			long flight = System.currentTimeMillis() - user.getLastFlight();
			if (flight < 5000) return e;
			if (event.getPlayer().isFlying())
				return e;
			if (Compatibility.isLegitVersion(event.getPlayer()))
				return e;
			if (Math.abs(event.getPlayer().getWalkSpeed()) >= Math.abs(0.3)) {
				event.getPlayer().sendMessage(Math.abs(event.getPlayer().getWalkSpeed()) + " " + Math.abs(0.3));
				return e;
			}
			Vector from = event.getFrom().toVector().clone().setY(0);
			Vector to = event.getTo().toVector().clone().setY(0);
			double speedSquared = to.distanceSquared(from);
			if (speedSquared * 10 > 4) {
				if (!event.getPlayer().hasPotionEffect(PotionEffectType.SPEED)) {
					flag(user);
				} else {
					PotionEffect effect = event.getPlayer().getPotionEffect(PotionEffectType.SPEED);
					if (effect.getAmplifier() < 4) {
						if (!event.getPlayer().getLocation().clone().add(0, 2, 0).getBlock().getType().isSolid())
							flag(user);
					}
				}
			}
		}
		return e;
	}
}