package net.tntwars.warden.detection.player.badpackets;

import io.github.retrooper.packetevents.event.impl.PacketReceiveEvent;
import io.github.retrooper.packetevents.packet.PacketType;
import net.tntwars.warden.Main;
import net.tntwars.warden.check.api.PublicCheck;
import net.tntwars.warden.check.api.data.Category;
import net.tntwars.warden.events.PublicCheckEvent;
import net.tntwars.warden.playerdata.PlayerData;
import net.tntwars.warden.utils.ConfigManager;

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
