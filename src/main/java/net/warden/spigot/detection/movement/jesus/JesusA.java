package net.warden.spigot.detection.movement.jesus;

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
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.mineacademy.fo.region.Region;

import java.util.ArrayList;
import java.util.List;

public class JesusA extends PrivateCheck {
	public JesusA(PlayerData data) {
		super(data, "Jesus", 'A', Category.MOVEMENT);
	}

	int every = 0;

	@Override
	public PrivateCheckEvent onCheck(PrivateCheckEvent event) {
		if (!ConfigManager.getInstance().isJesusAEnabled()) return event;
		if (event.getCauseEvent() instanceof PacketReceiveEvent) {
			if (Compatibility.isInSpectator(((PacketReceiveEvent) event.getCauseEvent()).getPlayer())) return event;
			if (PacketType.Client.Util.isInstanceOfFlying(((PacketReceiveEvent) event.getCauseEvent()).getPacketId())) {
				PlayerData user = Main.getPlayerDataManager().find(((PacketReceiveEvent) event.getCauseEvent()).getPlayer().getUniqueId());
				assert user != null;
				if (user.getFrom() == null) return event;
				long flight = System.currentTimeMillis() - user.getLastInWater();
				if (flight < 2000) return event;
				Player player = ((PacketReceiveEvent) event.getCauseEvent()).getPlayer();
				if (player.isInsideVehicle() || player.isDead() || player.getAllowFlight())
					return event;
				if (isNearWater(user.getTo()) && !isLilyPad(user.getTo()) && !player.getLocation().getBlock().isLiquid()) {
					if (!isNearBoat(player.getLocation()))
						flag();
				} else {
					every++;
					if (every >= 20) {
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

	private boolean isNearBoat(Location location) {
		List<Entity> ent = getEntitiesByLocation(location, 10);
		//Common.broadcast(ent.toString());
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

	private boolean isNearWater(Location location) {
		Region region = new Region(location.clone().add(1, -0.5, 1), location.clone().add(-1, -0.5, -1));
		List<Block> blocks = region.getBlocks();
		if (blocks.size() != 9) return false;
		return (blocks.get(0).isLiquid() &&
				blocks.get(1).isLiquid() &&
				blocks.get(2).isLiquid() &&
				blocks.get(3).isLiquid() &&
				blocks.get(4).isLiquid() &&
				blocks.get(5).isLiquid() &&
				blocks.get(6).isLiquid() &&
				blocks.get(7).isLiquid() &&
				blocks.get(8).isLiquid()
		);
	}

	private boolean isLilyPad(Location location) {
		Region region = new Region(location.clone().add(1, 0, 1), location.clone().add(-1, 0, -1));
		List<Block> blocks = region.getBlocks();
		if (blocks.size() != 9) return false;
		return (blocks.get(0).getType() == XMaterial.LILY_PAD.parseMaterial() ||
				blocks.get(1).getType() == XMaterial.LILY_PAD.parseMaterial() ||
				blocks.get(2).getType() == XMaterial.LILY_PAD.parseMaterial() ||
				blocks.get(3).getType() == XMaterial.LILY_PAD.parseMaterial() ||
				blocks.get(4).getType() == XMaterial.LILY_PAD.parseMaterial() ||
				blocks.get(5).getType() == XMaterial.LILY_PAD.parseMaterial() ||
				blocks.get(6).getType() == XMaterial.LILY_PAD.parseMaterial() ||
				blocks.get(7).getType() == XMaterial.LILY_PAD.parseMaterial() ||
				blocks.get(8).getType() == XMaterial.LILY_PAD.parseMaterial()
		);
	}
}
