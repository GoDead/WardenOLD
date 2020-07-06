package net.tntwars.warden.detection.movement.flight;

import io.github.retrooper.packetevents.event.impl.BukkitMoveEvent;
import net.tntwars.warden.Main;
import net.tntwars.warden.check.api.PublicCheck;
import net.tntwars.warden.check.api.data.Category;
import net.tntwars.warden.events.PublicCheckEvent;
import net.tntwars.warden.playerdata.PlayerData;
import net.tntwars.warden.utils.ConfigManager;
import org.bukkit.entity.Player;

public class FlightD extends PublicCheck {
	public FlightD() {
		super("Flight", 'D', Category.MOVEMENT);
	}

	@Override
	public PublicCheckEvent onCheck(PublicCheckEvent event) {
		if (!ConfigManager.getInstance().isFlightDEnabled()) return event;
		if (event.getCauseEvent() instanceof BukkitMoveEvent) {
			BukkitMoveEvent event1 = (BukkitMoveEvent) event.getCauseEvent();
			final double offsetY = event1.getTo().getY() - event1.getFrom().getY();
			Player player = event1.getPlayer();
			PlayerData user = Main.getPlayerDataManager().find(((BukkitMoveEvent) event.getCauseEvent()).getPlayer().getUniqueId());
			if (String.valueOf(offsetY).contains("E") && !player.getLocation().getBlock().isLiquid()) {
				flag(user);
			}
		}
		return event;
	}
}
