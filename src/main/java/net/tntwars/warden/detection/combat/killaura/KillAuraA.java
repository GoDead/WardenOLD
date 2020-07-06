package net.tntwars.warden.detection.combat.killaura;

import io.github.retrooper.packetevents.enums.minecraft.EntityUseAction;
import io.github.retrooper.packetevents.event.impl.PacketReceiveEvent;
import io.github.retrooper.packetevents.packet.PacketType;
import io.github.retrooper.packetevents.packetwrappers.in.useentity.WrappedPacketInUseEntity;
import net.tntwars.warden.check.api.PrivateCheck;
import net.tntwars.warden.check.api.data.Category;
import net.tntwars.warden.events.PrivateCheckEvent;
import net.tntwars.warden.playerdata.PlayerData;
import net.tntwars.warden.utils.ConfigManager;

public class KillAuraA extends PrivateCheck {
	public KillAuraA(PlayerData data) {
		super(data, "KillAura", 'A', Category.COMBAT);
	}

	long previousTimeStamp = 0;
	long previousDifference = 0;

	@Override
	public PrivateCheckEvent onCheck(PrivateCheckEvent e) {
		if (!(ConfigManager.getInstance().isKillAuraAEnabled())) return e;
		if (e.getCauseEvent() instanceof PacketReceiveEvent) {
			if (((PacketReceiveEvent) e.getCauseEvent()).getPacketId() == PacketType.Client.USE_ENTITY) {
				WrappedPacketInUseEntity packet = new WrappedPacketInUseEntity(((PacketReceiveEvent) e.getCauseEvent()).getNMSPacket());
				if (packet.getAction() != EntityUseAction.ATTACK) return e;
				if (previousTimeStamp == 0) {
					previousTimeStamp = e.getTimestamp();
					return e;
				}
				long difference = e.getTimestamp() - previousTimeStamp;
				if (previousDifference == 0) {
					previousDifference = difference;
					return e;
				}
				if (difference <= 49 && previousDifference <= 49) {
					flag();
				}
				previousTimeStamp = e.getTimestamp();
			}
		}
		return e;
	}
}

