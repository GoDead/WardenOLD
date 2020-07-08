package net.warden.spigot.detection.movement.flight;

import io.github.retrooper.packetevents.event.impl.BukkitMoveEvent;
import net.warden.spigot.Main;
import net.warden.spigot.check.api.PublicCheck;
import net.warden.spigot.check.api.data.Category;
import net.warden.spigot.events.PublicCheckEvent;
import net.warden.spigot.playerdata.PlayerData;
import net.warden.spigot.utils.Compatibility;
import net.warden.spigot.utils.ConfigManager;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class FlightE extends PublicCheck {
	public FlightE() {
		super("Flight", 'E', Category.MOVEMENT);
	}

	@Override
	public PublicCheckEvent onCheck(PublicCheckEvent e) {
		if (!ConfigManager.getInstance().isFlightEEnabled()) return e;
		if (e.getCauseEvent() instanceof BukkitMoveEvent) {
			BukkitMoveEvent event = (BukkitMoveEvent) e.getCauseEvent();
			PlayerData user = Main.getPlayerDataManager().find(((BukkitMoveEvent) e.getCauseEvent()).getPlayer().getUniqueId());
			Player player = user.getPlayer();
			if (Compatibility.isLegitVersion(player)) return e;
			if (event.getPlayer().getLocation().getBlock().getType().getData().getName().contains("CARPET")) return e;
			if (event.getPlayer().getLocation().getBlock().getType().getData().getName().contains("CARPET")) return e;
			if (!event.getPlayer().getLocation().getBlock().isLiquid() && !event.getPlayer().isOnGround() && !onGround(event.getPlayer()) && event.getPlayer().getVelocity().length() > 0.5) {
				final double offsetXZ = Math.hypot(event.getTo().getX() - event.getFrom().getX(), event.getTo().getZ() - event.getFrom().getZ());
				final double offsetY = event.getTo().getY() - event.getFrom().getY();
				if (offsetXZ > 0.0 && offsetY == 0.0) {
					flag(user);
				}
			}
		}
		return e;
	}

	public static boolean onGround(Player player) {
		Location location = player.getLocation();
		double expand = 0.3;
		for (double x = -expand; x <= expand; x += expand) {
			for (double z = -expand; z <= expand; z += expand) {
				if (location.clone().add(x, -1.1, z).getBlock().getType().isSolid() || location.clone().add(x, -1.5001, z).getBlock().getType().isSolid()) {
					return true;
				}
			}

		}
		return false;
	}
}
