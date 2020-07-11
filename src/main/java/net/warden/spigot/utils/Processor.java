package net.warden.spigot.utils;

import io.github.retrooper.packetevents.annotations.PacketHandler;
import io.github.retrooper.packetevents.enums.minecraft.PlayerAction;
import io.github.retrooper.packetevents.event.PacketListener;
import io.github.retrooper.packetevents.event.impl.PacketReceiveEvent;
import io.github.retrooper.packetevents.packet.PacketType;
import io.github.retrooper.packetevents.packetwrappers.in.entityaction.WrappedPacketInEntityAction;
import net.warden.spigot.Main;
import net.warden.spigot.playerdata.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.vehicle.VehicleEnterEvent;
import org.mineacademy.fo.region.Region;

import java.util.List;

public class Processor implements PacketListener, Listener {

	@PacketHandler
	public static void process(PacketReceiveEvent event) {
		PlayerData data = Main.getPlayerDataManager().find(event.getPlayer().getUniqueId());
		if (data != null) {
			Bukkit.getScheduler().runTask(Main.getInstance(), () -> {
				if (PlayerUtils.onGround(event.getPlayer())) {
					data.setOnGround(true);
				} else data.setOnGround(false);
			});

			data.setLastLocation(data.getLocation() != null ? data.getLocation() : event.getPlayer().getLocation());
			data.setLocation(event.getPlayer().getLocation());

			if (isNearSlime(data.getLocation())) {
				data.setLastNearSlime(System.currentTimeMillis());
			}


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

	private static boolean isNearSlime(Location location) {
		Region region = new Region(location.clone().add(1, -0.5, 1), location.clone().add(-1, -0.5, -1));
		List<Block> blocks = region.getBlocks();
		Material slime = Material.SLIME_BLOCK;
		if (blocks.size() != 9) return false;
		return (blocks.get(0).getType() == slime ||
				blocks.get(1).getType() == slime ||
				blocks.get(2).getType() == slime ||
				blocks.get(3).getType() == slime ||
				blocks.get(4).getType() == slime ||
				blocks.get(5).getType() == slime ||
				blocks.get(6).getType() == slime ||
				blocks.get(7).getType() == slime ||
				blocks.get(8).getType() == slime
		);
	}


	@EventHandler
	public void moveEvent(PlayerMoveEvent event) {
		PlayerData data = Main.getPlayerDataManager().find(event.getPlayer().getUniqueId());
		if (data == null) return;
		data.setTo(event.getTo());
		data.setFrom(event.getFrom());
		if (event.getFrom().getBlock().isLiquid() || event.getTo().getBlock().isLiquid()) {
			data.setLastInWater(System.currentTimeMillis());
		}
	}

	@EventHandler
	public void toggleFlight(PlayerToggleFlightEvent event) {
		PlayerData data = Main.getPlayerDataManager().find(event.getPlayer().getUniqueId());
		if (data == null) return;
		if (!event.isFlying()) {
			data.setLastFlight(System.currentTimeMillis());
		}
	}


	@EventHandler
	public void playerDamage(EntityDamageEvent event) {
		if (event.getEntity() instanceof Player) {
			Player player = (Player) event.getEntity();
			PlayerData data = Main.getPlayerDataManager().find(player.getUniqueId());
			if (data == null) return;
			data.setTimeSinceDamage(System.currentTimeMillis());

			if (event.getCause() == EntityDamageEvent.DamageCause.ENTITY_EXPLOSION || event.getCause() == EntityDamageEvent.DamageCause.BLOCK_EXPLOSION) {
				data.setLastExplosionDamage(System.currentTimeMillis());
			}
		}
	}

	@EventHandler
	public void exit(VehicleEnterEvent event) {
		if (event.getEntered() instanceof Player) {
			Player player = (Player) event.getEntered();
			PlayerData data = Main.getPlayerDataManager().find(player.getUniqueId());
			if (data == null) return;
			data.setLastVehicleAction(System.currentTimeMillis());
		}
	}

	@EventHandler
	public void bed(PlayerBedEnterEvent event) {
		Player player = event.getPlayer();
		PlayerData data = Main.getPlayerDataManager().find(player.getUniqueId());
		if (data == null) return;
		data.setLastVehicleAction(System.currentTimeMillis());
	}

	@EventHandler
	public void bedExit(PlayerBedLeaveEvent event) {
		Player player = event.getPlayer();
		PlayerData data = Main.getPlayerDataManager().find(player.getUniqueId());
		if (data == null) return;
		data.setLastVehicleAction(System.currentTimeMillis());
	}

	@EventHandler
	public void teleport(PlayerTeleportEvent event) {
		Player player = event.getPlayer();
		PlayerData data = Main.getPlayerDataManager().find(player.getUniqueId());
		if (data == null) return;
		data.setLastTeleport(System.currentTimeMillis());
	}
}