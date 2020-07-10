package net.warden.spigot.detection.movement.invalidmovement;

import io.github.retrooper.packetevents.enums.ServerVersion;
import io.github.retrooper.packetevents.event.impl.PacketReceiveEvent;
import io.github.retrooper.packetevents.packet.PacketType;
import net.warden.spigot.Main;
import net.warden.spigot.check.api.PublicCheck;
import net.warden.spigot.check.api.data.Category;
import net.warden.spigot.events.PublicCheckEvent;
import net.warden.spigot.playerdata.PlayerData;
import net.warden.spigot.utils.Compatibility;
import net.warden.spigot.utils.ConfigManager;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class InvalidMovementB extends PublicCheck {
	public InvalidMovementB() {
		super("InvalidMovement", 'B', Category.MOVEMENT);
	}

	@Override
	public PublicCheckEvent onCheck(PublicCheckEvent e) {
		if (!ServerVersion.getVersion().isLowerThan(ServerVersion.v_1_9)) return e;
		if (!ConfigManager.getInstance().isInvalidMovementBEnabled()) return e;
		if (e.getCauseEvent() instanceof PacketReceiveEvent) {
			if (PacketType.Client.Util.isInstanceOfFlying((((PacketReceiveEvent) e.getCauseEvent()).getPacketId()))) {
				if (Compatibility.isInSpectator(((PacketReceiveEvent) e.getCauseEvent()).getPlayer())) return e;
				PlayerData user = Main.getPlayerDataManager().find(((PacketReceiveEvent) e.getCauseEvent()).getPlayer().getUniqueId());
				Player player = ((PacketReceiveEvent) e.getCauseEvent()).getPlayer();
				if (Compatibility.isLegitVersion(player)) return e;
				assert user != null;
				if (user.getDeltaY() < 0.005 && user.getDeltaY() > 0 && !isNearBoat(user.getPlayer().getLocation())) {//funkemunky
					flag(user);
				}
			}
		}
		return e;
	}

	private boolean isNearBoat(Location location) {
		List<Entity> ent = getEntitiesByLocation(location, 10);
		//Common.broadcast(ent.toString());
		if (ent.toString().contains("Boat") || ent.toString().contains("Minecart"))
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
