package net.warden.spigot.detection.movement.flight;

import io.github.retrooper.packetevents.event.impl.PacketReceiveEvent;
import io.github.retrooper.packetevents.packet.PacketType;
import net.warden.spigot.Main;
import net.warden.spigot.check.api.PrivateCheck;
import net.warden.spigot.check.api.data.Category;
import net.warden.spigot.events.PrivateCheckEvent;
import net.warden.spigot.playerdata.PlayerData;
import net.warden.spigot.utils.Compatibility;
import net.warden.spigot.utils.ConfigManager;
import net.warden.spigot.utils.XMaterial;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class FlightH extends PrivateCheck {
	public FlightH(PlayerData data) {
		super(data, "Flight", 'H', Category.MOVEMENT);
	}

	float lastDeltaY;
	float verbose;
	float lastAccel;

	//funkemunky
	@Override
	public PrivateCheckEvent onCheck(PrivateCheckEvent e) {
		if (!ConfigManager.getInstance().isFlightHEnabled()) return e;
		if (e.getCauseEvent() instanceof PacketReceiveEvent) {
			if (Compatibility.isInSpectator(((PacketReceiveEvent) e.getCauseEvent()).getPlayer())) return e;
			if (PacketType.Client.Util.isInstanceOfFlying(((PacketReceiveEvent) e.getCauseEvent()).getPacketId())) {
				PlayerData user = Main.getPlayerDataManager().find(((PacketReceiveEvent) e.getCauseEvent()).getPlayer().getUniqueId());
				Player player = ((PacketReceiveEvent) e.getCauseEvent()).getPlayer();
				if (player.isInsideVehicle() || player.isDead() || player.isFlying() || player.getLocation().getBlock().isLiquid() || player.getLocation().clone().add(0, -1, 0).getBlock().isLiquid())
					return e;
				if (isNearBoat(player.getLocation())) return e;
				assert user != null;
				if (user.isOnGround()) return e;
				if (user.isNearStairs(player.getLocation())) return e;
				if (user.isNearSlabs(player.getLocation())) return e;
				if (user.isNear(XMaterial.LADDER) || user.isNear(XMaterial.VINE) || user.isNear(XMaterial.TWISTING_VINES_PLANT) || user.isNear(XMaterial.WEEPING_VINES_PLANT))
					return e;
				if (user.getTo() == null) return e;
				float deltaY = (float) (user.getTo().getY() - user.getFrom().getY());

				long gm = System.currentTimeMillis() - user.getLastGameModeSwitch();
				if (gm < 4000) return e;
				long time = System.currentTimeMillis() - user.getTimeSinceDamage();
				if (time < 2000) return e;
				long explosion = System.currentTimeMillis() - user.getLastExplosionDamage();
				if (explosion < 10000) return e;
				if (Compatibility.isLegitVersion(player))
					return e;
				long lastOnGround = System.currentTimeMillis() - user.getLastOnGround();
				user.setLastDeltaY(lastDeltaY);
				lastDeltaY = deltaY;
				if (Math.abs(lastOnGround) > 700 && deltaY > user.getLastDeltaY()) {
					if (verbose++ > 2)
						flag();
				} else {
					verbose += verbose > 0 ? 0.1f : 0;
				}
				float accel = (float) (deltaY - user.getLastDeltaY());
				if (Math.abs(lastOnGround) > 700 && Math.abs(accel) < 0.01) {
					if (verbose++ > 3) {
						flag();
					}
				} else
					verbose += verbose > 0 ? 0.2f : 0;
				lastAccel = accel;
				lastDeltaY = deltaY;
			}
		}
		return e;
	}

	private boolean isNearBoat(Location location) {
		List<Entity> ent = getEntitiesByLocation(location, 10);
		if (ent.toString().toLowerCase().contains("boat") || ent.toString().toLowerCase().contains("minecart"))
			return true;
		return false;
	}

	private List<Entity> getEntitiesByLocation(Location loc, float r) {
		List<Entity> ent = new ArrayList<>();
		for (Entity e : loc.getWorld().getEntities()) {
			if (e.getLocation().distanceSquared(loc) <= r)
				ent.add(e);
		}
		return ent;
	}
}
