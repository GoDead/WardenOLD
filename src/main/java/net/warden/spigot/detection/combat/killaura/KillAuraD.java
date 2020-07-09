package net.warden.spigot.detection.combat.killaura;

import io.github.retrooper.packetevents.enums.minecraft.EntityUseAction;
import io.github.retrooper.packetevents.event.impl.PacketReceiveEvent;
import io.github.retrooper.packetevents.packet.PacketType;
import io.github.retrooper.packetevents.packetwrappers.in.useentity.WrappedPacketInUseEntity;
import net.warden.spigot.check.api.PrivateCheck;
import net.warden.spigot.check.api.data.Category;
import net.warden.spigot.events.PrivateCheckEvent;
import net.warden.spigot.playerdata.PlayerData;
import net.warden.spigot.utils.Compatibility;
import net.warden.spigot.utils.ConfigManager;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.ArrayList;
import java.util.List;

public class KillAuraD extends PrivateCheck implements Listener {
	public KillAuraD(PlayerData data) {
		super(data, "KillAura", 'D', Category.COMBAT);
	}

	private double vl;
	private double multiplier = Math.pow(2.0, 24.0);
	private float previous;
	private List<Float> previousYaws = new ArrayList<>();

	private long timeSinceFlag;

	boolean complied;

	@Override
	public PrivateCheckEvent onCheck(PrivateCheckEvent e) {
		if (!(ConfigManager.getInstance().isKillAuraDEnabled())) return e;
		if (e.getCauseEvent() instanceof PacketReceiveEvent) {
			if (Compatibility.isInSpectator(((PacketReceiveEvent) e.getCauseEvent()).getPlayer())) return e;
			if (((PacketReceiveEvent) e.getCauseEvent()).getPacketId() == PacketType.Client.USE_ENTITY) {
				WrappedPacketInUseEntity packet = new WrappedPacketInUseEntity(((PacketReceiveEvent) e.getCauseEvent()).getNMSPacket());
				if (packet.getAction() != EntityUseAction.ATTACK) return e;
				//Common.broadcast(" &c" + timeSinceFlag + " &e" + System.currentTimeMillis());
				long time = System.currentTimeMillis() - getPlayerData().getTimeSinceFlagD();
				//Common.broadcast(time + " &c" /*+ timeSinceFlag + " &e" + System.currentTimeMillis()*/);
				if (time < 100 && time > 0) {
					flag();
				} else {
					if (getVL() > 0)
						comply();
				}
			}
		}
		return e;
	}

	//Edit from open source Artemis fixed optifine zoom false positive
	@EventHandler
	public void moveEvent(PlayerMoveEvent event) {
		Location to = event.getTo();
		Location from = event.getFrom();
		float pitchChange = Math.abs(to.getPitch() - from.getPitch());
		float yawChange = Math.abs(to.getYaw() - from.getYaw());
		long a = (long) ((double) pitchChange * multiplier);
		long b = (long) ((double) previous * multiplier);
		long gcd = gcd(16384L, a, b);
		if ((double) yawChange > 0.9 && (double) pitchChange < 15.0 && gcd < 131072L) {
			if ((double) yawChange < 9.7 && (double) pitchChange > 0.05) {
				previousYaws.add(yawChange);
				if (vl++ > 8.0) {
					if (averageFloat(previousYaws) > 0.0f) {
						getPlayerData().setTimeSinceFlagD(System.currentTimeMillis());
					} else {
						vl = 0.0;
						previousYaws.clear();
					}
				}
			}
		} else if (vl > 0.0) {
			vl -= 1.0;
		}
		previous = pitchChange;
	}

	public static long gcd(long limit, long a, long b) {
		return b <= limit ? a : gcd(limit, b, a % b);
	}

	public static float averageFloat(List<Float> list) {
		float avg = 0.0f;
		for (float value : list) {
			avg += value;
		}
		if (list.size() > 0) {
			return avg / (float) list.size();
		}
		return 0.0f;
	}
}
