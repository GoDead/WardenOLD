package net.warden.spigot.detection.movement.flight;

import io.github.retrooper.packetevents.event.impl.BukkitMoveEvent;
import net.warden.spigot.Main;
import net.warden.spigot.check.api.PrivateCheck;
import net.warden.spigot.check.api.data.Category;
import net.warden.spigot.events.PrivateCheckEvent;
import net.warden.spigot.playerdata.PlayerData;
import net.warden.spigot.utils.Compatibility;
import net.warden.spigot.utils.ConfigManager;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class FlightC extends PrivateCheck {

	private boolean lastOnGround, lastLastOnGround;
	private double lastDistY;

	public FlightC(PlayerData data) {
		super(data, "Flight", 'C', Category.MOVEMENT);
	}

	int every = 0;

	//https://www.youtube.com/watch?v=U6mYAyThFcE
	@Override
	public PrivateCheckEvent onCheck(PrivateCheckEvent e) {
		if (!ConfigManager.getInstance().isFlightCEnabled()) return e;
		if (e.getCauseEvent() instanceof BukkitMoveEvent) {
			if (Compatibility.isInSpectator(((BukkitMoveEvent) e.getCauseEvent()).getPlayer())) return e;
			BukkitMoveEvent event = (BukkitMoveEvent) e.getCauseEvent();
			PlayerData user = Main.getPlayerDataManager().find(((BukkitMoveEvent) e.getCauseEvent()).getPlayer().getUniqueId());
			Player player = user.getPlayer();
			long time = System.currentTimeMillis() - user.getTimeSinceDamage();
			if (time < 5000) return e;
			long flight = System.currentTimeMillis() - user.getLastFlight();
			if (flight < 5000) return e;
			if (Compatibility.isLegitVersion(player)) return e;
			double distY = event.getTo().getY() - event.getFrom().getY();
			double lastDistY = this.lastDistY;
			this.lastDistY = distY;
			double predictedDist = (lastDistY - 0.08D) * 0.9800000190734863D;

			boolean onGround = isNearGround(user.getTo());


			boolean lastOnGround = this.lastOnGround;
			this.lastOnGround = onGround;

			boolean lastLastOnGround = this.lastLastOnGround;
			this.lastLastOnGround = lastOnGround;
			if (player.isInsideVehicle() || player.isDead() || player.getAllowFlight() || player.getLocation().getBlock().isLiquid() || player.getLocation().clone().add(0, -1, 0).getBlock().isLiquid())
				return e;
			if (!onGround && !lastOnGround && !lastLastOnGround && Math.abs(predictedDist) >= 0.005D && ((BukkitMoveEvent) e.getCauseEvent()).getPlayer().getVelocity().length() > 0.3D) {
				if (!isRoughlyEqual(distY, predictedDist)) {
					//flag();
				}
			} else {
				every++;
				if (every >= 30) {
					every = 0;
					if (user.getVLCount(this) <= 0)
						user.setVLCount(this, 0);
					if (user.getVLCount(this) > 0)
						user.decrementVL(this);
				}
			}
		}
		return e;
	}

	private boolean isRoughlyEqual(double d1, double d2) {
		return Math.abs(d1 - d2) < 0.00000000001;
	}

	private boolean isNearGround(Location location) {
		double expand = 0.3;
		for (double x = -expand; x <= expand; x += expand) {
			for (double z = -expand; z <= expand; z += expand) {
				if (location.clone().add(x, -0.5001, z).getBlock().getType() != Material.AIR) {
					return true;
				}
			}
		}
		return false;
	}
}
