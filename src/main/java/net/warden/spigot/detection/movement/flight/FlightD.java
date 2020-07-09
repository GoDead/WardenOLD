package net.warden.spigot.detection.movement.flight;

import io.github.retrooper.packetevents.event.impl.BukkitMoveEvent;
import net.warden.spigot.Main;
import net.warden.spigot.check.api.PublicCheck;
import net.warden.spigot.check.api.data.Category;
import net.warden.spigot.events.PublicCheckEvent;
import net.warden.spigot.playerdata.PlayerData;
import net.warden.spigot.utils.Compatibility;
import net.warden.spigot.utils.ConfigManager;
import org.bukkit.entity.Player;

public class FlightD extends PublicCheck {
	public FlightD() {
		super("Flight", 'D', Category.MOVEMENT);
	}

	@Override
	public PublicCheckEvent onCheck(PublicCheckEvent event) {
		if (!ConfigManager.getInstance().isFlightDEnabled()) return event;
		if (event.getCauseEvent() instanceof BukkitMoveEvent) {
			if (Compatibility.isInSpectator(((BukkitMoveEvent) event.getCauseEvent()).getPlayer())) return event;
			BukkitMoveEvent event1 = (BukkitMoveEvent) event.getCauseEvent();
			final double offsetY = event1.getTo().getY() - event1.getFrom().getY();
			Player player = event1.getPlayer();
			PlayerData user = Main.getPlayerDataManager().find(((BukkitMoveEvent) event.getCauseEvent()).getPlayer().getUniqueId());
			if (Compatibility.isLegitVersion(player)) return event;
			if (String.valueOf(offsetY).contains("E") && !player.getLocation().getBlock().isLiquid()) {
				flag(user);
			}
		}
		return event;
	}
}
