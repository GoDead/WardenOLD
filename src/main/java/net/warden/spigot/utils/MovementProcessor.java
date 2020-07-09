package net.warden.spigot.utils;

import io.github.retrooper.packetevents.annotations.PacketHandler;
import io.github.retrooper.packetevents.enums.minecraft.PlayerAction;
import io.github.retrooper.packetevents.event.PacketListener;
import io.github.retrooper.packetevents.event.impl.PacketReceiveEvent;
import io.github.retrooper.packetevents.packet.PacketType;
import io.github.retrooper.packetevents.packetwrappers.in.entityaction.WrappedPacketInEntityAction;
import net.warden.spigot.Main;
import net.warden.spigot.playerdata.PlayerData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class MovementProcessor implements PacketListener, Listener {

	@PacketHandler
	public static void process(PacketReceiveEvent event) {
		PlayerData data = Main.getPlayerDataManager().find(event.getPlayer().getUniqueId());
		if (data != null) {
			if (PlayerUtils.onGround(event.getPlayer())) {
				data.setOnGround(true);
			} else data.setOnGround(false);

			data.setLastLocation(data.getLocation() != null ? data.getLocation() : event.getPlayer().getLocation());
			data.setLocation(event.getPlayer().getLocation());

			if (PacketType.Client.Util.isInstanceOfFlying((event.getPacketId()))) {
				data.setDeltaXZ(data.getLocation().toVector().setY(0).distance(data.getLastLocation().toVector().setY(0)));
				data.setDeltaY(data.getLocation().getY() - data.getLastLocation().getY());
				//data.setDeltaXZ(data.getTo().toVector().setY(0).distance(data.getFrom().toVector().setY(0)));
				//data.setDeltaY(data.getTo().getY() - data.getFrom().getY());
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
		if (event.getFrom().getBlock().isLiquid() || event.getTo().getBlock().isLiquid()) {
			data.setLastInWater(System.currentTimeMillis());
		}
	}

	@EventHandler
	public void playerDamage(EntityDamageEvent event) {
		if (event.getEntity() instanceof Player) {
			Player player = (Player) event.getEntity();
			PlayerData data = Main.getPlayerDataManager().find(player.getUniqueId());
			data.setTimeSinceDamage(System.currentTimeMillis());
		}
	}
}