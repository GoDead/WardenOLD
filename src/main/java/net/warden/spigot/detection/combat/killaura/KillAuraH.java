package net.warden.spigot.detection.combat.killaura;

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
import net.warden.spigot.utils.Compatibility;
import net.warden.spigot.utils.ConfigManager;
import net.warden.spigot.utils.nms.RayTrace;
import net.warden.spigot.utils.nms.boundingbox.*;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class KillAuraH extends PrivateCheck {
	public KillAuraH(PlayerData data) {
		super(data, "KillAura", 'H', Category.COMBAT);
	}

	private List<Boolean> list = new ArrayList<>();

	@Override
	public PrivateCheckEvent onCheck(PrivateCheckEvent e) {
		if (!(ConfigManager.getInstance().isKillAuraHEnabled())) return e;
		if (e.getCauseEvent() instanceof PacketReceiveEvent) {
			if (Compatibility.isInSpectator(((PacketReceiveEvent) e.getCauseEvent()).getPlayer())) return e;
			if (((PacketReceiveEvent) e.getCauseEvent()).getPacketId() != PacketType.Client.USE_ENTITY) return e;
			PlayerData user = Main.getPlayerDataManager().find(((PacketReceiveEvent) e.getCauseEvent()).getPlayer().getUniqueId());
			WrappedPacketInUseEntity packet = new WrappedPacketInUseEntity(((PacketReceiveEvent) e.getCauseEvent()).getNMSPacket());
			if (packet.getAction() != EntityUseAction.ATTACK) return e;
			Player player = ((PacketReceiveEvent) e.getCauseEvent()).getPlayer();
			RayTrace rayTrace = new RayTrace(player.getEyeLocation().toVector(), player.getEyeLocation().getDirection());
			assert user != null;
			ArrayList<Vector> positions = rayTrace.traverse(5, 0.01);
			for (int i = 0; i < positions.size(); i++) {

				Location position = positions.get(i).toLocation(player.getWorld());
				Block block = player.getWorld().getBlockAt(position);
				Entity entity = packet.getEntity();
				if (entity instanceof EnderDragon) return e;
				if (entity.getLocation().toVector().distance(player.getLocation().toVector()) < 1) return e;
				if (ServerVersion.getVersion().equals(ServerVersion.v_1_7_10))
					if (entity != null && rayTrace.intersects(new V_1_7_R4(entity), 5, 0.01)) {
						flagSafe(false);
						user.addHitsInARow(1);
						break;
					} else {
						flagSafe(true);
						user.setHitsInARow(0);
						break;
					}
				else if (ServerVersion.getVersion().equals(ServerVersion.v_1_8))
					if (entity != null && /*block.getType() != Material.AIR &&*/ rayTrace.intersects(new V_1_8_R1(entity), 5, 0.01)) {
						flagSafe(false);
						user.addHitsInARow(1);
						break;
					} else {
						flagSafe(true);
						user.setHitsInARow(0);
						break;
					}
				else if (ServerVersion.getVersion().equals(ServerVersion.v_1_8_3))
					if (entity != null && rayTrace.intersects(new V_1_8_R2(entity), 5, 0.01)) {
						flagSafe(false);
						user.addHitsInARow(1);
						break;
					} else {
						flagSafe(true);
						user.setHitsInARow(0);
						break;
					}
				else if (ServerVersion.getVersion().equals(ServerVersion.v_1_8_8))
					if (entity != null && rayTrace.intersects(new V_1_8_R3(entity), 5, 0.01)) {
						flagSafe(false);
						user.addHitsInARow(1);
						break;
					} else {
						flagSafe(true);
						user.setHitsInARow(0);
						break;
					}
				else if (ServerVersion.getVersion().equals(ServerVersion.v_1_9))
					if (entity != null && rayTrace.intersects(new V_1_9_R1(entity), 5, 0.01)) {
						flagSafe(false);
						user.addHitsInARow(1);
						break;
					} else {
						flagSafe(true);
						user.setHitsInARow(0);
						break;
					}
				else if (ServerVersion.getVersion().equals(ServerVersion.v_1_9_4))
					if (entity != null && rayTrace.intersects(new V_1_9_R2(entity), 5, 0.01)) {
						flagSafe(false);
						user.addHitsInARow(1);
						break;
					} else {
						flagSafe(true);
						user.setHitsInARow(0);
						break;
					}
				else if (ServerVersion.getVersion().equals(ServerVersion.v_1_10))
					if (entity != null && rayTrace.intersects(new V_1_10_R1(entity), 5, 0.01)) {
						flagSafe(false);
						user.addHitsInARow(1);
						break;
					} else {
						flagSafe(true);
						user.setHitsInARow(0);
						break;
					}
				else if (ServerVersion.getVersion().equals(ServerVersion.v_1_11))
					if (entity != null && rayTrace.intersects(new V_1_11_R1(entity), 5, 0.01)) {
						flagSafe(false);
						user.addHitsInARow(1);
						break;
					} else {
						flagSafe(true);
						user.setHitsInARow(0);
						break;
					}
				else if (ServerVersion.getVersion().equals(ServerVersion.v_1_12))
					if (entity != null && rayTrace.intersects(new V_1_12_R1(entity), 5, 0.01)) {
						flagSafe(false);
						user.addHitsInARow(1);
						break;
					} else {
						flagSafe(true);
						user.setHitsInARow(0);
						break;
					}
				else if (ServerVersion.getVersion().equals(ServerVersion.v_1_13))
					if (entity != null && rayTrace.intersects(new V_1_13_R1(entity), 5, 0.01)) {
						flagSafe(false);
						user.addHitsInARow(1);
						break;
					} else {
						flagSafe(true);
						user.setHitsInARow(0);
						break;
					}
				else if (ServerVersion.getVersion().equals(ServerVersion.v_1_13_2))
					if (entity != null && rayTrace.intersects(new V_1_13_R2(entity), 5, 0.01)) {
						flagSafe(false);
						user.addHitsInARow(1);
						break;
					} else {
						flagSafe(true);
						user.setHitsInARow(0);
						break;
					}
				else if (ServerVersion.getVersion().equals(ServerVersion.v_1_14))
					if (entity != null && rayTrace.intersects(new V_1_14_R1(entity), 5, 0.01)) {
						flagSafe(false);
						user.addHitsInARow(1);
						break;
					} else {
						flagSafe(true);
						user.setHitsInARow(0);
						break;
					}
				else if (ServerVersion.getVersion().equals(ServerVersion.v_1_15))
					if (entity != null && rayTrace.intersects(new V_1_15_R1(entity), 5, 0.01)) {
						flagSafe(false);
						user.addHitsInARow(1);
						break;
					} else {
						flagSafe(true);
						user.setHitsInARow(0);
						break;
					}
				else if (ServerVersion.getVersion().equals(ServerVersion.v_1_16_1))
					if (entity != null && rayTrace.intersects(new V_1_16_R1(entity), 5, 0.01)) {
						flagSafe(false);
						user.addHitsInARow(1);
						break;
					} else {
						flagSafe(true);
						user.setHitsInARow(0);
						break;
					}
			}
		}
		return e;
	}

	boolean previousBool;
	boolean previousPreviousBool;
	boolean previousPreviousPreviousBool;

	private void flagSafe(boolean bool) {
		if (!bool) {
			list.clear();
		}
		if (list.size() < 5) {
			list.add(bool);
		} else if (list.size() == 5) {
			list.remove(0);
			list.add(bool);

		} else {
			System.out.println("CLEARED");
			list.clear();
		}
		//Common.broadcast(list.toString());
		if (isHacking(list))
			flag();
	}

	private boolean isHacking(List<Boolean> list) {
		if (list.isEmpty()) return false;
		if (list.size() < 5) return false;
		if (/*list.get(7) && list.get(6) && list.get(5) &&*/ list.get(4) && list.get(3) && list.get(2) && list.get(1) && list.get(0))
			return true;
		else if (/*!list.get(7) && !list.get(6) && !list.get(5) &&*/ !list.get(4) && !list.get(3) && !list.get(2) && !list.get(1) && list.get(0)) {
			if (getVL() > 0)
				comply();
			return false;
		}
		return false;

	}
}

