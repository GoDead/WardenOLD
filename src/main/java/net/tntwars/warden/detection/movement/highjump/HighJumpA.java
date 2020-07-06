package net.tntwars.warden.detection.movement.highjump;

import io.github.retrooper.packetevents.event.impl.BukkitMoveEvent;
import net.tntwars.warden.Main;
import net.tntwars.warden.check.api.PublicCheck;
import net.tntwars.warden.check.api.data.Category;
import net.tntwars.warden.events.PublicCheckEvent;
import net.tntwars.warden.playerdata.PlayerData;
import net.tntwars.warden.utils.ConfigManager;
import org.bukkit.potion.PotionEffectType;

public class HighJumpA extends PublicCheck {
	public HighJumpA() {
		super("HighJump", 'A', Category.MOVEMENT);
	}

	@Override
	public PublicCheckEvent onCheck(PublicCheckEvent e) {
		if (!ConfigManager.getInstance().isHighJumpAEnabled()) return e;
		if (e.getCauseEvent() instanceof BukkitMoveEvent) {
			BukkitMoveEvent event = (BukkitMoveEvent) e.getCauseEvent();
			PlayerData user = Main.getPlayerDataManager().find(event.getPlayer().getUniqueId());
			if (event.getPlayer().getLocation().getBlock().isLiquid()) return e;
			if (event.getPlayer().hasPotionEffect(PotionEffectType.JUMP)) return e;
			final double offsetY = event.getTo().getY() - event.getFrom().getY();
			if (event.getPlayer().isOnGround()) return e;
			if (offsetY > 0.55) {
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