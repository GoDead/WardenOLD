package net.tntwars.warden.detection.movement.nofall;

import io.github.retrooper.packetevents.event.impl.BukkitMoveEvent;
import net.tntwars.warden.Main;
import net.tntwars.warden.check.api.PublicCheck;
import net.tntwars.warden.check.api.data.Category;
import net.tntwars.warden.events.PublicCheckEvent;
import net.tntwars.warden.playerdata.PlayerData;
import net.tntwars.warden.utils.ConfigManager;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class GroundSpoofA extends PublicCheck {
	public GroundSpoofA() {
		super("GroundSpoof", 'A', Category.MOVEMENT);
	}

	@Override
	public PublicCheckEvent onCheck(PublicCheckEvent e) {
		if (!ConfigManager.getInstance().isGroundSpoofAEnabled()) return e;
		if (e.getCauseEvent() instanceof BukkitMoveEvent) {
			BukkitMoveEvent event = (BukkitMoveEvent) e.getCauseEvent();
			PlayerData user = Main.getPlayerDataManager().find(event.getPlayer().getUniqueId());
			Player player = event.getPlayer();
			if (player.getGameMode().equals(GameMode.CREATIVE) || player.isInsideVehicle() || player.isDead() || (event.getFrom().getX() == event.getTo().getX() && event.getFrom().getY() == event.getTo().getY() && event.getFrom().getZ() == event.getTo().getZ()))
				return e;
			double deltaY = event.getTo().getY() - event.getFrom().getY();
			double deltaYB = Math.abs(event.getFrom().getY() - event.getTo().getY());
			boolean up = (deltaY > 0.0D);
			if (!player.isFlying()) {
				if (!up && deltaYB > 0.4D && player.isOnGround()) {
					flag(user);
				}
			}
		}
		return e;
	}

}