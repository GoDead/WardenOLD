package net.warden.spigot.detection.movement.flight;

import io.github.retrooper.packetevents.event.impl.PacketReceiveEvent;
import io.github.retrooper.packetevents.packet.PacketType;
import net.warden.spigot.Main;
import net.warden.spigot.check.api.PublicCheck;
import net.warden.spigot.check.api.data.Category;
import net.warden.spigot.events.PublicCheckEvent;
import net.warden.spigot.playerdata.PlayerData;
import net.warden.spigot.utils.Compatibility;
import net.warden.spigot.utils.ConfigManager;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class FlightB extends PublicCheck {
	public FlightB() {
		super("Flight", 'B', Category.MOVEMENT);
	}

	@Override
	public PublicCheckEvent onCheck(PublicCheckEvent event) {
		if (!ConfigManager.getInstance().isFlightBEnabled()) return event;
		if (event.getCauseEvent() instanceof PacketReceiveEvent) {
			if (Compatibility.isInSpectator(((PacketReceiveEvent) event.getCauseEvent()).getPlayer())) return event;
			if (PacketType.Client.Util.isInstanceOfFlying(((PacketReceiveEvent) event.getCauseEvent()).getPacketId())) {
				PlayerData user = Main.getPlayerDataManager().find(((PacketReceiveEvent) event.getCauseEvent()).getPlayer().getUniqueId());
				if (user.getTo() == null || user.getFrom() == null) return event;
				Location to = user.getTo();
				Location from = user.getFrom();
				Player player = user.getPlayer();
				if (Compatibility.isLegitVersion(player)) return event;
				if (((PacketReceiveEvent) event.getCauseEvent()).getPlayer().getVelocity().length() < 0.8) return event;
				if (from.getBlock() == to.getBlock()) return event;
				if (from.getBlock().getLocation().add(0, -1, 0).getBlock().getType() == Material.AIR && to.getBlock().getLocation().add(0, -1, 0).getBlock().getType() == Material.AIR) {
					if (from.getBlock().getLocation().add(0, -2, 0).getBlock().getType() == Material.AIR && to.getBlock().getLocation().add(0, -2, 0).getBlock().getType() == Material.AIR) {
						if (to.getY() == from.getY()) {
							flag(user);
						}
					}
				}
			}
		}
		return event;
	}
}
