package net.tntwars.warden.detection.combat.killaura;

import io.github.retrooper.packetevents.event.impl.BukkitMoveEvent;
import net.tntwars.warden.check.api.PrivateCheck;
import net.tntwars.warden.check.api.data.Category;
import net.tntwars.warden.events.PrivateCheckEvent;
import net.tntwars.warden.playerdata.PlayerData;
import net.tntwars.warden.utils.ConfigManager;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

public class KillAuraD extends PrivateCheck {
	public KillAuraD(PlayerData data) {
		super(data, "KillAura", 'D', Category.COMBAT);
	}

	private double vl;
	private double multiplier = Math.pow(2.0, 24.0);
	private float previous;
	private List<Float> previousYaws = new ArrayList<>();


	@Override
	public PrivateCheckEvent onCheck(PrivateCheckEvent e) {
		if (!(ConfigManager.getInstance().isKillAuraDEnabled())) return e;
		if (e.getCauseEvent() instanceof BukkitMoveEvent) {
			BukkitMoveEvent event = (BukkitMoveEvent) e.getCauseEvent();
			Location to = event.getTo();
			Location from = event.getFrom();
			float pitchChange = Math.abs(to.getPitch() - from.getPitch());
			float yawChange = Math.abs(to.getYaw() - from.getYaw());
			long a = (long) ((double) pitchChange * multiplier);
			long b = (long) ((double) previous * multiplier);
			long gcd = gcd(16384L, a, b);
			if ((double) yawChange > 0.9 && (double) pitchChange < 15.0 && gcd < 131072L) {
				if ((double) yawChange < 9.7 && (double) pitchChange > 0.05) {
					this.previousYaws.add(yawChange);
					if (vl++ > 5.0) {
						if (averageFloat(this.previousYaws) > 0.0f) {
							flag();
						} else {
							this.vl = 0.0;
							this.previousYaws.clear();
						}
					}
				}
			} else if (this.vl > 0.0) {
				this.vl -= 1.0;
			}
			this.previous = pitchChange;
		}
		return e;
	}

	//From another anticheat
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
