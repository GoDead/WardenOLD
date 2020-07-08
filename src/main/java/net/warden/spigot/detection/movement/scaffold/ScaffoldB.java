package net.warden.spigot.detection.movement.scaffold;

import io.github.retrooper.packetevents.event.impl.PacketReceiveEvent;
import io.github.retrooper.packetevents.packet.PacketType;
import io.github.retrooper.packetevents.packetwrappers.in.blockplace.WrappedPacketInBlockPlace;
import net.warden.spigot.check.api.PublicCheck;
import net.warden.spigot.check.api.data.Category;
import net.warden.spigot.events.PublicCheckEvent;
import net.warden.spigot.utils.ConfigManager;

public class ScaffoldB extends PublicCheck {
	public ScaffoldB() {
		super("Scaffold", 'B', Category.WORLD);
	}

	@Override
	public PublicCheckEvent onCheck(PublicCheckEvent e) {
		if (!ConfigManager.getInstance().isScaffoldBEnabled()) return e;
		if (!(e.getCauseEvent() instanceof PacketReceiveEvent)) return e;
		if ((((PacketReceiveEvent) e.getCauseEvent()).getPacketId()) == PacketType.Client.BLOCK_PLACE) {
			WrappedPacketInBlockPlace packet = new WrappedPacketInBlockPlace(((PacketReceiveEvent) e.getCauseEvent()).getPlayer(), ((PacketReceiveEvent) e.getCauseEvent()).getNMSPacket());

		}
		return e;
	}
}
