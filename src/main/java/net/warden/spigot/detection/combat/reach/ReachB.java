package net.warden.spigot.detection.combat.reach;

import io.github.retrooper.packetevents.enums.ServerVersion;
import io.github.retrooper.packetevents.enums.minecraft.EntityUseAction;
import io.github.retrooper.packetevents.event.impl.PacketReceiveEvent;
import io.github.retrooper.packetevents.packet.PacketType;
import io.github.retrooper.packetevents.packetwrappers.in.useentity.WrappedPacketInUseEntity;
import net.warden.spigot.Main;
import net.warden.spigot.check.api.PrivateCheck;
import net.warden.spigot.check.api.data.Category;
import net.warden.spigot.events.PrivateCheckEvent;
import net.warden.spigot.playerdata.PlayerData;
import net.warden.spigot.utils.nms.RayTrace;
import net.warden.spigot.utils.nms.boundingbox.*;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.ArrayList;

public class ReachB extends PrivateCheck {
	public ReachB(PlayerData data) {
		super(data, "Reach", 'B', Category.COMBAT);
	}


	@Override
	public PrivateCheckEvent onCheck(PrivateCheckEvent e) {
		//if (!(ConfigManager.getInstance().isReachEnabled())) return e;
		if (e.getCauseEvent() instanceof PacketReceiveEvent) {
			if (((PacketReceiveEvent) e.getCauseEvent()).getPacketId() != PacketType.Client.USE_ENTITY) return e;
			PlayerData user = Main.getPlayerDataManager().find(((PacketReceiveEvent) e.getCauseEvent()).getPlayer().getUniqueId());
			WrappedPacketInUseEntity packet = new WrappedPacketInUseEntity(((PacketReceiveEvent) e.getCauseEvent()).getNMSPacket());
			if (packet.getAction() != EntityUseAction.ATTACK) return e;
			Player player = ((PacketReceiveEvent) e.getCauseEvent()).getPlayer();
			RayTrace rayTrace = new RayTrace(player.getEyeLocation().toVector(), player.getEyeLocation().getDirection());
			ArrayList<Vector> positions = rayTrace.traverse(5, 0.01);
			for (int i = 0; i < positions.size(); i++) {

				Location position = positions.get(i).toLocation(player.getWorld());
				Block block = player.getWorld().getBlockAt(position);
				Entity entity = packet.getEntity();

				if (ServerVersion.getVersion().equals(ServerVersion.v_1_8))
					if (entity != null && /*block.getType() != Material.AIR &&*/ rayTrace.intersects(new V_1_8_R1(entity), 5, 0.01)) {
						flagSafe(false);
						break;
					} else {
						flagSafe(true);
						break;
					}
				if (ServerVersion.getVersion().equals(ServerVersion.v_1_8_3))
					if (entity != null && rayTrace.intersects(new V_1_8_R2(entity), 5, 0.01)) {
						flagSafe(false);
						break;
					} else {
						flagSafe(true);
						break;
					}
				else if (ServerVersion.getVersion().equals(ServerVersion.v_1_8_8))
					if (entity != null && rayTrace.intersects(new V_1_8_R3(entity), 5, 0.01)) {
						flagSafe(false);
						break;
					} else {
						flagSafe(true);
						break;
					}
				else if (ServerVersion.getVersion().equals(ServerVersion.v_1_9))
					if (entity != null && rayTrace.intersects(new V_1_9_R1(entity), 5, 0.01)) {
						flagSafe(false);
						break;
					} else {
						flagSafe(true);
						break;
					}
				else if (ServerVersion.getVersion().equals(ServerVersion.v_1_9_4))
					if (entity != null && rayTrace.intersects(new V_1_9_R2(entity), 5, 0.01)) {
						flagSafe(false);
						break;
					} else {
						flagSafe(true);
						break;
					}
				else if (ServerVersion.getVersion().equals(ServerVersion.v_1_10))
					if (entity != null && rayTrace.intersects(new V_1_10_R1(entity), 5, 0.01)) {
						flagSafe(false);
						break;
					} else {
						flagSafe(true);
						break;
					}
				else if (ServerVersion.getVersion().equals(ServerVersion.v_1_11))
					if (entity != null && rayTrace.intersects(new V_1_11_R1(entity), 5, 0.01)) {
						flagSafe(false);
						break;
					} else {
						flagSafe(true);
						break;
					}
				else if (ServerVersion.getVersion().equals(ServerVersion.v_1_12))
					if (entity != null && rayTrace.intersects(new V_1_12_R1(entity), 5, 0.01)) {
						flagSafe(false);
						break;
					} else {
						flagSafe(true);
						break;
					}
				else if (ServerVersion.getVersion().equals(ServerVersion.v_1_13))
					if (entity != null && rayTrace.intersects(new V_1_13_R1(entity), 5, 0.01)) {
						flagSafe(false);
						break;
					} else {
						flagSafe(true);
						break;
					}
				else if (ServerVersion.getVersion().equals(ServerVersion.v_1_13_2))
					if (entity != null && rayTrace.intersects(new V_1_13_R2(entity), 5, 0.01)) {
						flagSafe(false);
						break;
					} else {
						flagSafe(true);
						break;
					}
				else if (ServerVersion.getVersion().equals(ServerVersion.v_1_14))
					if (entity != null && rayTrace.intersects(new V_1_14_R1(entity), 5, 0.01)) {
						flagSafe(false);
						break;
					} else {
						flagSafe(true);
						break;
					}
				else if (ServerVersion.getVersion().equals(ServerVersion.v_1_15))
					if (entity != null && rayTrace.intersects(new V_1_15_R1(entity), 5, 0.01)) {
						flagSafe(false);
						break;
					} else {
						flagSafe(true);
						break;
					}
				else if (ServerVersion.getVersion().equals(ServerVersion.v_1_16_1))
					if (entity != null && rayTrace.intersects(new V_1_16_R1(entity), 5, 0.01)) {
						flagSafe(false);
						break;
					} else {
						flagSafe(true);
						break;
					}
			}
		}
		return e;
	}

	private void flagSafe(boolean bool) {
		boolean previousBool = bool;
		boolean previousPreviousBool = previousBool;
		if (previousPreviousBool && bool) {
			flag();
		}
	}
}


