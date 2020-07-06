package net.tntwars.warden.detection.movement.flight;

import io.github.retrooper.packetevents.event.impl.BukkitMoveEvent;
import net.tntwars.warden.check.api.PrivateCheck;
import net.tntwars.warden.check.api.data.Category;
import net.tntwars.warden.events.PrivateCheckEvent;
import net.tntwars.warden.playerdata.PlayerData;
import net.tntwars.warden.utils.ConfigManager;
import org.bukkit.Location;
import org.bukkit.Material;

import java.text.DecimalFormat;

public class FlightA extends PrivateCheck {

	public FlightA(PlayerData data) {
		super(data, "Flight", 'A', Category.MOVEMENT);
	}

	private double previousFly = 0;

	@Override
	public PrivateCheckEvent onCheck(final PrivateCheckEvent event) {
		if (!ConfigManager.getInstance().isFlightAEnabled()) return event;
		if (event.getCauseEvent() instanceof BukkitMoveEvent) {
			BukkitMoveEvent moveEvent = (BukkitMoveEvent) event.getCauseEvent();
			Location to = moveEvent.getTo();
			Location from = moveEvent.getFrom();
			if (!ConfigManager.getInstance().isFlightAEnabled()) return event;
			if (moveEvent.getPlayer().getVelocity().length() < 0.6) return event;
			if (from.getY() > to.getY()) return event;
			if (from.getBlock() == to.getBlock()) return event;
			if (from.getBlock().getLocation().add(0, -1, 0).getBlock().getType() != Material.AIR && to.getBlock().getLocation().add(0, -1, 0).getBlock().getType() != Material.AIR) {
				return event;
			} else {
				if (from.getBlock().getLocation().add(0, -2, 0).getBlock().getType() != Material.AIR && to.getBlock().getLocation().add(0, -2, 0).getBlock().getType() != Material.AIR) {
					return event;
				}
			}

			double deltaY = to.getY() - from.getY();
			DecimalFormat df = new DecimalFormat("###.###");
			double formatted = Double.parseDouble(df.format(deltaY));
			if (Math.abs(previousFly) == Math.abs(formatted) && Math.abs(previousFly) != Math.abs(000.000)) {
				flag();
				if (Math.abs(formatted) == Math.abs(0.375) || Math.abs(formatted) == Math.abs(0.374)) {
					flag();
				}


			}
		}
		return event;
	}
}
