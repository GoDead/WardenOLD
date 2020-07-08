package net.warden.spigot.detection.combat.criticals;

import io.github.retrooper.packetevents.event.impl.PacketReceiveEvent;
import io.github.retrooper.packetevents.packet.PacketType;
import net.warden.spigot.Main;
import net.warden.spigot.check.api.PublicCheck;
import net.warden.spigot.check.api.data.Category;
import net.warden.spigot.events.PublicCheckEvent;
import net.warden.spigot.playerdata.PlayerData;
import net.warden.spigot.utils.ConfigManager;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.HashMap;
import java.util.Map;

public class CriticalsB extends PublicCheck implements Listener {
	public CriticalsB() {
		super("Criticals", 'B', Category.COMBAT);
	}

	@Override
	public PublicCheckEvent onCheck(PublicCheckEvent e) {
		if (!(ConfigManager.getInstance().isCriticalsAEnabled())) return e;
		if (e.getCauseEvent() instanceof PacketReceiveEvent) {
			if (((PacketReceiveEvent) e.getCauseEvent()).getPacketId() == PacketType.Client.USE_ENTITY) {
				Player player = ((PacketReceiveEvent) e.getCauseEvent()).getPlayer();
				PlayerData user = Main.getPlayerDataManager().find(player.getUniqueId());
				if (player.isInsideVehicle())
					return e;

				if (!player.getGameMode().equals(GameMode.SURVIVAL) && !player.getGameMode().equals(GameMode.ADVENTURE))
					return e;
				if (!player.isOnGround() && user.isOnGround()) {
					if (isOnGround.containsKey(user)) {
						if (isOnGround.get(user)) {
							flag(user);
						}
					}
				}
			}
		}
		return e;
	}

	public Map<Player, Boolean> isOnGround = new HashMap<>();

	@EventHandler
	public void onMove(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		if (!player.getGameMode().equals(GameMode.SURVIVAL) && !player.getGameMode().equals(GameMode.ADVENTURE))
			return;
		if (event.getTo().getY() == event.getFrom().getY()) {
			if (isOnGround.containsKey(player)) {
				isOnGround.replace(player, true);
			} else {
				isOnGround.putIfAbsent(player, true);
			}
		} else {
			if (isOnGround.containsKey(player)) {
				isOnGround.replace(player, false);
			} else {
				isOnGround.putIfAbsent(player, false);
			}
		}
	}
}
