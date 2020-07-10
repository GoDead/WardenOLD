package net.warden.spigot.detection.movement.highjump;

import io.github.retrooper.packetevents.event.impl.BukkitMoveEvent;
import net.warden.spigot.Main;
import net.warden.spigot.check.api.PublicCheck;
import net.warden.spigot.check.api.data.Category;
import net.warden.spigot.events.PublicCheckEvent;
import net.warden.spigot.playerdata.PlayerData;
import net.warden.spigot.utils.Compatibility;
import net.warden.spigot.utils.ConfigManager;
import org.bukkit.potion.PotionEffectType;

public class HighJumpA extends PublicCheck {
	public HighJumpA() {
		super("HighJump", 'A', Category.MOVEMENT);
	}

	@Override
	public PublicCheckEvent onCheck(PublicCheckEvent e) {
		if (!ConfigManager.getInstance().isHighJumpAEnabled()) return e;
		if (e.getCauseEvent() instanceof BukkitMoveEvent) {
			if (Compatibility.isInSpectator(((BukkitMoveEvent) e.getCauseEvent()).getPlayer())) return e;
			BukkitMoveEvent event = (BukkitMoveEvent) e.getCauseEvent();
			PlayerData user = Main.getPlayerDataManager().find(event.getPlayer().getUniqueId());
			if (Compatibility.isLegitVersion(((BukkitMoveEvent) e.getCauseEvent()).getPlayer()))
				return e;
			if (event.getPlayer().getLocation().getBlock().isLiquid()) return e;
			if (event.getPlayer().hasPotionEffect(PotionEffectType.JUMP)) return e;
			final double deltaY = event.getTo().getY() - event.getFrom().getY();
			if (event.getPlayer().isOnGround()) return e;
			if (event.getPlayer().getAllowFlight()) return e;
			assert user != null;
			long explosion = System.currentTimeMillis() - user.getLastExplosionDamage();
			if (explosion < 5000) return e;
			long slime = System.currentTimeMillis() - user.getLastNearSlime();
			if (slime < 8000) return e;
			long levitation = System.currentTimeMillis() - user.getLastLevitation();
			if (levitation < 5000) return e;
			if (deltaY > 0.55) {
				if (!Compatibility.isLegitVersion(event.getPlayer()))
					flag(user);
			} else {
				if (user.getVLCount(this) <= 0)
					user.setVLCount(this, 0);
				if (user.getVLCount(this) > 0)
					user.decrementVL(this);
			}
		}
		return e;
	}
}