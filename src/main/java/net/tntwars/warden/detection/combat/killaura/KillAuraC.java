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

public class KillAuraC extends PrivateCheck {
	public KillAuraC(PlayerData data) {
		super(data, "KillAura", 'C', Category.COMBAT);
	}

	long lastSwing;

	@Override
	public PrivateCheckEvent onCheck(PrivateCheckEvent e) {
		if (!(ConfigManager.getInstance().isKillAuraCEnabled())) return e;
		if (e.getCauseEvent() instanceof PacketReceiveEvent) {
			if (((PacketReceiveEvent) e.getCauseEvent()).getPacketId() == PacketType.Client.USE_ENTITY) {
				if (((PacketReceiveEvent) e.getCauseEvent()).getPacketId() == PacketType.Client.ARM_ANIMATION) {
					lastSwing = System.currentTimeMillis();
				} else if (((PacketReceiveEvent) e.getCauseEvent()).getPacketId() == PacketType.Client.USE_ENTITY) {
					WrappedPacketInUseEntity packetInUseEntity = new WrappedPacketInUseEntity(((PacketReceiveEvent) e.getCauseEvent()).getNMSPacket());
					if (!(packetInUseEntity.getAction().equals(EntityUseAction.ATTACK))) {
						return e;
					}
				}
			}
		}
		return e;
	}
}