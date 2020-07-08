package net.warden.spigot.detection.player.badpackets;

import io.github.retrooper.packetevents.event.impl.PacketReceiveEvent;
import io.github.retrooper.packetevents.packet.PacketType;
import net.warden.spigot.Main;
import net.warden.spigot.check.api.PublicCheck;
import net.warden.spigot.check.api.data.Category;
import net.warden.spigot.events.PublicCheckEvent;
import net.warden.spigot.playerdata.PlayerData;
import net.warden.spigot.utils.ConfigManager;

public class BadPacketsB extends PublicCheck {
	public BadPacketsB() {
		super("BadPackets", 'B', Category.PLAYER);
	}

	//https://www.youtube.com/watch?v=uCgq8EnNje8&t
	@Override
	public PublicCheckEvent onCheck(PublicCheckEvent e) {
		if (!(ConfigManager.getInstance().isBadPacketsBEnabled())) return e;
		if (e.getCauseEvent() instanceof PacketReceiveEvent) {
			if (PacketType.Client.Util.isInstanceOfFlying(((PacketReceiveEvent) e.getCauseEvent()).getPacketId())) {
				PlayerData user = Main.getPlayerDataManager().find(((PacketReceiveEvent) e.getCauseEvent()).getPlayer().getUniqueId());
				if (Math.abs(((PacketReceiveEvent) e.getCauseEvent()).getPlayer().getLocation().getPitch()) > 90) {
					flag(user);
				}
			}
		}
		return e;
	}
}
