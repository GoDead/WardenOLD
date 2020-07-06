package net.tntwars.warden.detection.movement.flight;

import io.github.retrooper.packetevents.event.impl.PacketReceiveEvent;
import io.github.retrooper.packetevents.packet.PacketType;
import net.tntwars.warden.Main;
import net.tntwars.warden.check.api.PrivateCheck;
import net.tntwars.warden.check.api.data.Category;
import net.tntwars.warden.events.PrivateCheckEvent;
import net.tntwars.warden.playerdata.PlayerData;
import net.tntwars.warden.utils.ConfigManager;
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

	@Override
	public PrivateCheckEvent onCheck(PrivateCheckEvent event) {
		if (!ConfigManager.getInstance().isFlightCEnabled()) return event;
		if (event.getCauseEvent() instanceof PacketReceiveEvent) {
			if (PacketType.Client.Util.isInstanceOfFlying(((PacketReceiveEvent) event.getCauseEvent()).getPacketId())) {
				PlayerData user = Main.getPlayerDataManager().find(((PacketReceiveEvent) event.getCauseEvent()).getPlayer().getUniqueId());
				if (user.getFrom() == null) return event;
				double distY = user.getTo().getY() - user.getFrom().getY();
				double lastDistY = this.lastDistY;
				this.lastDistY = distY;

				double predictedDist = (lastDistY - 0.08D) * 0.9800000190734863D;


				boolean onGround = isNearGround(user.getTo());


				boolean lastOnGround = this.lastOnGround;
				this.lastOnGround = onGround;

				boolean lastLastOnGround = this.lastLastOnGround;
				this.lastLastOnGround = lastOnGround;
				Player player = ((PacketReceiveEvent) event.getCauseEvent()).getPlayer();
				if (player.isInsideVehicle() || player.isDead() || player.getAllowFlight() || player.getLocation().getBlock().isLiquid())
					return event;
				if (!onGround && !lastOnGround && !lastLastOnGround && Math.abs(predictedDist) >= 0.005D && ((PacketReceiveEvent) event.getCauseEvent()).getPlayer().getVelocity().length() > 0.3D) {
					if (!isRoughlyEqual(distY, predictedDist)) {
						flag();
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
		}
		return event;
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
