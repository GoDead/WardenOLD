package net.warden.spigot.detection.movement.nofall;

import io.github.retrooper.packetevents.PacketEvents;
import io.github.retrooper.packetevents.event.impl.BukkitMoveEvent;
import net.warden.spigot.Main;
import net.warden.spigot.check.api.PublicCheck;
import net.warden.spigot.check.api.data.Category;
import net.warden.spigot.events.PublicCheckEvent;
import net.warden.spigot.playerdata.PlayerData;
import net.warden.spigot.utils.Compatibility;
import net.warden.spigot.utils.ConfigManager;
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
			if (Compatibility.isInSpectator(((BukkitMoveEvent) e.getCauseEvent()).getPlayer())) return e;
			BukkitMoveEvent event = (BukkitMoveEvent) e.getCauseEvent();
			PlayerData user = Main.getPlayerDataManager().find(event.getPlayer().getUniqueId());
			Player player = event.getPlayer();
			if (player.getGameMode() == GameMode.CREATIVE || player.isInsideVehicle() || player.isDead() || (event.getFrom().getX() == event.getTo().getX() && event.getFrom().getY() == event.getTo().getY() && event.getFrom().getZ() == event.getTo().getZ()))
				return e;
			if (!(PacketEvents.getAPI().getPlayerUtilities().getPlayerPing(player) > 100 || player.getNoDamageTicks() != 0 || player.getVehicle() != null || player.isDead())) {
				double deltaY = event.getFrom().getY() - event.getTo().getY();
				if (player.isOnGround() && deltaY > 0.8) {
					flag(user);
				}
			}
		}
		return e;
	}

}