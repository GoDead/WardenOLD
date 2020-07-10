package net.warden.spigot.detection.movement.flight;

import io.github.retrooper.packetevents.event.impl.BukkitMoveEvent;
import net.warden.spigot.check.api.PrivateCheck;
import net.warden.spigot.check.api.data.Category;
import net.warden.spigot.events.PrivateCheckEvent;
import net.warden.spigot.playerdata.PlayerData;
import net.warden.spigot.utils.Compatibility;
import net.warden.spigot.utils.ConfigManager;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

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
			if (Compatibility.isInSpectator(((BukkitMoveEvent) event.getCauseEvent()).getPlayer())) return event;
			BukkitMoveEvent moveEvent = (BukkitMoveEvent) event.getCauseEvent();
			Location to = moveEvent.getTo();
			Location from = moveEvent.getFrom();
			Player player = moveEvent.getPlayer();
			if (Compatibility.isLegitVersion(player)) return event;
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
			if (String.valueOf(deltaY).contains(",")) {
				flag();
				return event;
			}
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
