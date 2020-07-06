package net.tntwars.warden.detection.movement.nofall;

import io.github.retrooper.packetevents.PacketEvents;
import io.github.retrooper.packetevents.event.impl.BukkitMoveEvent;
import net.tntwars.warden.Main;
import net.tntwars.warden.check.api.PublicCheck;
import net.tntwars.warden.check.api.data.Category;
import net.tntwars.warden.events.PublicCheckEvent;
import net.tntwars.warden.playerdata.PlayerData;
import net.tntwars.warden.utils.ConfigManager;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class GroundSpoofB extends PublicCheck {
	public GroundSpoofB() {
		super("GroundSpoof", 'B', Category.MOVEMENT);
	}

	@Override
	public PublicCheckEvent onCheck(PublicCheckEvent e) {
		if (!ConfigManager.getInstance().isGroundSpoofBEnabled()) return e;
		if (e.getCauseEvent() instanceof BukkitMoveEvent) {
			BukkitMoveEvent event = (BukkitMoveEvent) e.getCauseEvent();
			PlayerData user = Main.getPlayerDataManager().find(event.getPlayer().getUniqueId());
			Player player = event.getPlayer();
			if (player.getGameMode().equals(GameMode.CREATIVE) || player.isInsideVehicle() || player.isDead() || (event.getFrom().getX() == event.getTo().getX() && event.getFrom().getY() == event.getTo().getY() && event.getFrom().getZ() == event.getTo().getZ()))
				return e;
			if (!(PacketEvents.getAPI().getPlayerUtilities().getPlayerPing(player) > 100 || player.getNoDamageTicks() != 0 || player.getVehicle() != null || player.isDead() || player.getGameMode().equals((Object) GameMode.CREATIVE))) {
				double yDiff = event.getFrom().getY() - event.getTo().getY();
				if (player.isOnGround() && yDiff > 0.8) {
					flag(user);
				}
			}
		}
		return e;
	}

}