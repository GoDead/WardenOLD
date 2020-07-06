package net.tntwars.warden.events;

import io.github.retrooper.packetevents.event.PacketEvent;

public class CheckEvent extends PacketEvent {
	private final PacketEvent causeEvent;

	public CheckEvent(final PacketEvent causeEvent) {
		this.causeEvent = causeEvent;
	}

	public final PacketEvent getCauseEvent() {
		return causeEvent;
	}
}
