package net.tntwars.warden.detection.combat.killaura;

import io.github.retrooper.packetevents.event.impl.PacketReceiveEvent;
import io.github.retrooper.packetevents.packet.PacketType;
import net.tntwars.warden.check.api.PrivateCheck;
import net.tntwars.warden.check.api.data.Category;
import net.tntwars.warden.events.PrivateCheckEvent;
import net.tntwars.warden.playerdata.PlayerData;

public class KillAuraF extends PrivateCheck {
	public KillAuraF(PlayerData data) {
		super(data, "KillAura", 'E', Category.COMBAT);
	}

	private boolean wasLastDig;

	@Override
	public PrivateCheckEvent onCheck(PrivateCheckEvent e) {
		if (e.getCauseEvent() instanceof PacketReceiveEvent) {
			int packet = ((PacketReceiveEvent) e.getCauseEvent()).getPacketId();
			if (packet == PacketType.Client.USE_ENTITY) {
				if (wasLastDig) {
					flag();
				}
			} else if (PacketType.Client.Util.isInstanceOfFlying(packet)) {
				wasLastDig = false;
			} else if (packet == PacketType.Client.BLOCK_DIG) {
				wasLastDig = true;
			}
		}
		return e;
	}


}
