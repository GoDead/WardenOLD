package net.warden.spigot.detection.movement.nofall;

import io.github.retrooper.packetevents.event.impl.BukkitMoveEvent;
import net.warden.spigot.Main;
import net.warden.spigot.check.api.PublicCheck;
import net.warden.spigot.check.api.data.Category;
import net.warden.spigot.events.PublicCheckEvent;
import net.warden.spigot.playerdata.PlayerData;
import net.warden.spigot.utils.Compatibility;
import net.warden.spigot.utils.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class GroundSpoofA extends PublicCheck {
	public GroundSpoofA() {
		super("GroundSpoof", 'A', Category.MOVEMENT);
	}

	@Override
	public PublicCheckEvent onCheck(PublicCheckEvent e) {
		//if (!ServerVersion.getVersion().isLowerThan(ServerVersion.v_1_9)) return e;
		if (!ConfigManager.getInstance().isGroundSpoofAEnabled()) return e;
		if (e.getCauseEvent() instanceof BukkitMoveEvent) {
			if (Compatibility.isInSpectator(((BukkitMoveEvent) e.getCauseEvent()).getPlayer())) return e;
			BukkitMoveEvent event = (BukkitMoveEvent) e.getCauseEvent();
			PlayerData user = Main.getPlayerDataManager().find(event.getPlayer().getUniqueId());
			Player player = event.getPlayer();
			if (player.getGameMode() == GameMode.CREATIVE || player.isInsideVehicle() || player.isDead() || (event.getFrom().getX() == event.getTo().getX() && event.getFrom().getY() == event.getTo().getY() && event.getFrom().getZ() == event.getTo().getZ()))
				return e;
			double deltaY = event.getTo().getY() - event.getFrom().getY();
			double deltaYB = Math.abs(event.getFrom().getY() - event.getTo().getY());
			boolean up = (deltaY > 0.0D);
			if (!player.isFlying()) {
				if (!up && deltaYB > 0.4D && player.isOnGround()) {
					Bukkit.getScheduler().runTaskLater(Main.getInstance(), () -> {
						if ((System.currentTimeMillis() - user.getTimeSinceDamage()) > 300)
							flag(user);
					}, 5);
				}
			}
		}
		return e;
	}

}