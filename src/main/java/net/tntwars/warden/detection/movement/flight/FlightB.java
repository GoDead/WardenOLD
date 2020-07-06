package net.tntwars.warden.detection.movement.flight;

import io.github.retrooper.packetevents.event.impl.BukkitMoveEvent;
import net.tntwars.warden.Main;
import net.tntwars.warden.check.api.PublicCheck;
import net.tntwars.warden.check.api.data.Category;
import net.tntwars.warden.events.PublicCheckEvent;
import net.tntwars.warden.playerdata.PlayerData;
import net.tntwars.warden.utils.ConfigManager;
import org.bukkit.Location;
import org.bukkit.Material;

public class FlightB extends PublicCheck {
	public FlightB() {
		super("Flight", 'B', Category.MOVEMENT);
	}

	@Override
	public PublicCheckEvent onCheck(PublicCheckEvent event) {
		if (!ConfigManager.getInstance().isFlightBEnabled()) return event;
		if (event.getCauseEvent() instanceof BukkitMoveEvent) {
			BukkitMoveEvent moveEvent = (BukkitMoveEvent) event.getCauseEvent();
			PlayerData user = Main.getPlayerDataManager().find(moveEvent.getPlayer().getUniqueId());
			Location to = moveEvent.getTo();
			Location from = moveEvent.getFrom();
			if (!ConfigManager.getInstance().isFlightBEnabled()) return event;
			if (moveEvent.getPlayer().getVelocity().length() < 0.8) return event;
			if (from.getBlock() == to.getBlock()) return event;
			if (from.getBlock().getLocation().add(0, -1, 0).getBlock().getType() == Material.AIR && to.getBlock().getLocation().add(0, -1, 0).getBlock().getType() == Material.AIR) {
				if (from.getBlock().getLocation().add(0, -2, 0).getBlock().getType() == Material.AIR && to.getBlock().getLocation().add(0, -2, 0).getBlock().getType() == Material.AIR) {
					if (to.getY() == from.getY()) {
						flag(user);
					}
				}
			}
		}
		return event;
	}
}
