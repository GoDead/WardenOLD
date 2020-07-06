package net.tntwars.warden.detection.movement.speed;

import io.github.retrooper.packetevents.event.impl.BukkitMoveEvent;
import net.tntwars.warden.Main;
import net.tntwars.warden.check.api.PublicCheck;
import net.tntwars.warden.check.api.data.Category;
import net.tntwars.warden.events.PublicCheckEvent;
import net.tntwars.warden.playerdata.PlayerData;
import net.tntwars.warden.utils.ConfigManager;
import org.bukkit.GameMode;
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
			BukkitMoveEvent event = (BukkitMoveEvent) e.getCauseEvent();
			PlayerData user = Main.getPlayerDataManager().find(event.getPlayer().getUniqueId());
			if (event.getPlayer().hasPotionEffect(PotionEffectType.SPEED)) return e;
			if (event.getPlayer().getGameMode() == GameMode.CREATIVE) return e;
			if (event.getPlayer().getGameMode() == GameMode.SPECTATOR) return e;
			if (event.getPlayer().getAllowFlight())
				return e;

			if (Math.abs(event.getPlayer().getWalkSpeed()) >= Math.abs(0.3)) {
				event.getPlayer().sendMessage(Math.abs(event.getPlayer().getWalkSpeed()) + " " + Math.abs(0.3));
				return e;
			}
			Vector from = event.getFrom().toVector().clone().setY(0);
			Vector to = event.getTo().toVector().clone().setY(0);
			double speed = to.distanceSquared(from);
			if (speed * 10 > 4) {
				flag(user);
			}
		}
		return e;
	}
}