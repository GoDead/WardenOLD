package net.warden.spigot.detection.player.pingspoof;

import io.github.retrooper.packetevents.PacketEvents;
import io.github.retrooper.packetevents.event.impl.PacketReceiveEvent;
import io.github.retrooper.packetevents.event.impl.PacketSendEvent;
import io.github.retrooper.packetevents.packet.PacketType;
import net.warden.spigot.check.api.PrivateCheck;
import net.warden.spigot.check.api.data.Category;
import net.warden.spigot.events.PrivateCheckEvent;
import net.warden.spigot.playerdata.PlayerData;
import net.warden.spigot.utils.ConfigManager;
import org.bukkit.entity.Player;

public class PingSpoofA extends PrivateCheck {
	public PingSpoofA(PlayerData data) {
		super(data, "PingSpoof", 'A', Category.PLAYER);
	}

	long lastKeepAliveSent;

	@Override
	public PrivateCheckEvent onCheck(PrivateCheckEvent e) {
		if (!ConfigManager.getInstance().isPingSpoofAEnabled()) return e;
		if (e.getCauseEvent() instanceof PacketSendEvent) {
			if (((PacketSendEvent) e.getCauseEvent()).getPacketId() == PacketType.Server.KEEP_ALIVE) {
				lastKeepAliveSent = System.currentTimeMillis();
			}
		} else if (e.getCauseEvent() instanceof PacketReceiveEvent) {
			if (((PacketReceiveEvent) e.getCauseEvent()).getPacketId() == PacketType.Client.KEEP_ALIVE) {
				Player player = ((PacketReceiveEvent) e.getCauseEvent()).getPlayer();
				long ping = System.currentTimeMillis() - lastKeepAliveSent;
				if (ping - 100F > PacketEvents.getAPI().getPlayerUtilities().getPing(player)) {
					flag();
				}
			}
		}
		return e;
	}
}
