package net.warden.spigot.detection.player.velocity;

import io.github.retrooper.packetevents.PacketEvents;
import io.github.retrooper.packetevents.event.impl.PacketReceiveEvent;
import io.github.retrooper.packetevents.event.impl.PacketSendEvent;
import io.github.retrooper.packetevents.packet.PacketType;
import net.warden.spigot.check.api.PrivateCheck;
import net.warden.spigot.check.api.data.Category;
import net.warden.spigot.events.PrivateCheckEvent;
import net.warden.spigot.playerdata.PlayerData;
import net.warden.spigot.utils.ConfigManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerVelocityEvent;

public class VelocityA extends PrivateCheck implements Listener {
	public VelocityA(PlayerData data) {
		super(data, "Velocity", 'A', Category.PLAYER);
	}

	private double lastVertical;
	private int ticksSinceSend;
	private boolean hasSent;

	@Override
	public PrivateCheckEvent onCheck(PrivateCheckEvent e) {
		if (!ConfigManager.getInstance().isVelocityAEnabled()) return e;
		if (e.getCauseEvent() instanceof PacketSendEvent) {
			int packet = ((PacketSendEvent) e.getCauseEvent()).getPacketId();
			if (packet == PacketType.Server.ENTITY_VELOCITY) {
				if (((PacketSendEvent) e.getCauseEvent()).getPlayer().getVelocity().length() > 0.2) {
					lastVertical = ((PacketSendEvent) e.getCauseEvent()).getPlayer().getVelocity().length();
					ticksSinceSend = 0;
					hasSent = true;
				}
			}
		} else if (e.getCauseEvent() instanceof PacketReceiveEvent) {
			int packet = ((PacketReceiveEvent) e.getCauseEvent()).getPacketId();
			if (PacketType.Client.Util.isInstanceOfFlying(((PacketReceiveEvent) e.getCauseEvent()).getPacketId())) {
				if (!hasSent) return e;
				ticksSinceSend++;

				int maxTicks = (int) (PacketEvents.getAPI().getPlayerUtilities().getPlayerPing(((PacketReceiveEvent) e.getCauseEvent()).getPlayer()) / 50) + 5;

				if (ticksSinceSend <= maxTicks) {
					//((PacketReceiveEvent) e.getCauseEvent()).getPlayer().sendMessage(ticksSinceSend + "  " + maxTicks);
				}
			}
		}
		return e;
	}

	@EventHandler
	public void playerVelocity(PlayerVelocityEvent event) {
		event.getVelocity().getY();
	}
}
