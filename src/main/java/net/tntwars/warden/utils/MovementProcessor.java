package net.tntwars.warden.utils;

import io.github.retrooper.packetevents.annotations.PacketHandler;
import io.github.retrooper.packetevents.enums.minecraft.PlayerAction;
import io.github.retrooper.packetevents.event.PacketListener;
import io.github.retrooper.packetevents.event.impl.PacketReceiveEvent;
import io.github.retrooper.packetevents.packet.PacketType;
import io.github.retrooper.packetevents.packetwrappers.in.entityaction.WrappedPacketInEntityAction;
import net.tntwars.warden.Main;
import net.tntwars.warden.playerdata.PlayerData;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class MovementProcessor implements PacketListener, Listener {

	@PacketHandler
	public static void process(PacketReceiveEvent event) {
		PlayerData data = Main.getPlayerDataManager().find(event.getPlayer().getUniqueId());
		if (data != null) {
			if (PlayerUtils.onGround(data)) {
				data.setOnGround(true);
			} else data.setOnGround(false);

			data.setLastLocation(data.getLocation() != null ? data.getLocation() : event.getPlayer().getLocation());
			data.setLocation(event.getPlayer().getLocation());

			if (PacketType.Client.Util.isInstanceOfFlying((event.getPacketId()))) {
				//data.setDeltaXZ(data.getLocation().toVector().setY(0).distance(data.getLastLocation().toVector()));
				//data.setDeltaY(data.getLocation().getY() - data.getLastLocation().getY());
				data.setDeltaXZ(data.getTo().toVector().setY(0).distance(data.getFrom().toVector()));
				data.setDeltaY(data.getTo().getY() - data.getFrom().getY());
			}

			if (event.getPacketId() == (PacketType.Client.ENTITY_ACTION)) {
				WrappedPacketInEntityAction packet = new WrappedPacketInEntityAction(event.getNMSPacket());
				if (packet.getAction().equals(PlayerAction.START_SNEAKING)) {
					data.setSprinting(true);
				}
				if (packet.getAction().equals(PlayerAction.STOP_SPRINTING)) {
					data.setSprinting(false);
				}
				if (packet.getAction().equals(PlayerAction.START_SNEAKING)) {
					data.setSneaking(true);
				}
				if (packet.getAction().equals(PlayerAction.STOP_SNEAKING)) {
					data.setSneaking(false);
				}
			}
		}
	}

	@EventHandler
	public void moveEvent(PlayerMoveEvent event) {
		PlayerData data = Main.getPlayerDataManager().find(event.getPlayer().getUniqueId());
		data.setTo(event.getTo());
		data.setFrom(event.getFrom());
	}
}