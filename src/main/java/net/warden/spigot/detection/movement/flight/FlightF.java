package net.warden.spigot.detection.movement.flight;

import io.github.retrooper.packetevents.event.impl.PacketReceiveEvent;
import io.github.retrooper.packetevents.packet.PacketType;
import net.warden.spigot.Main;
import net.warden.spigot.check.api.PrivateCheck;
import net.warden.spigot.check.api.data.Category;
import net.warden.spigot.events.PrivateCheckEvent;
import net.warden.spigot.playerdata.PlayerData;
import net.warden.spigot.utils.Compatibility;
import net.warden.spigot.utils.XMaterial;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.mineacademy.fo.region.Region;

import java.util.List;

public class FlightF extends PrivateCheck {

	public FlightF(PlayerData data) {
		super(data, "Flight", 'F', Category.MOVEMENT);
	}

	final double velocity = 0.0784D;
	final double velocityExact = 0.0784000015258789D;
	int every = 0;

	@Override
	public PrivateCheckEvent onCheck(PrivateCheckEvent event) {
		if (event.getCauseEvent() instanceof PacketReceiveEvent) {
			if (PacketType.Client.Util.isInstanceOfFlying(((PacketReceiveEvent) event.getCauseEvent()).getPacketId())) {
				PlayerData user = Main.getPlayerDataManager().find(((PacketReceiveEvent) event.getCauseEvent()).getPlayer().getUniqueId());
				Player player = ((PacketReceiveEvent) event.getCauseEvent()).getPlayer();
				long time = System.currentTimeMillis() - user.getTimeSinceDamage();
				if (time < 2000) return event;
				if (Compatibility.isLegitVersion(player))
					return event;
				if (user == null) return event;
				if (!user.getPlayer().isOnGround() && !user.getPlayer().getLocation().getBlock().isLiquid() && user.getPlayer().getLocation().getBlock().getType() != Material.LADDER && user.getPlayer().getLocation().getBlock().getType() != Material.VINE) {
					if (isVeryNearGround(user.getPlayer().getLocation())) {
						if (isNearGround(user.getPlayer().getLocation())) {
							if (isRoughlyEqual(user.getPlayer().getVelocity().length(), velocityExact))
								if (!isNearSlime(user.getPlayer().getLocation()) && !isNearWeb(user.getPlayer().getLocation()))
									if (!isNearStairs(user.getPlayer().getLocation()) && !isNearSlab(user.getPlayer().getLocation()))
										flag();
						}
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

	private boolean isVeryNearGround(Location location) {
		double expand = 0.3;
		for (double x = -expand; x <= expand; x += expand) {
			for (double z = -expand; z <= expand; z += expand) {
				if (location.clone().add(x, -0.1001, z).getBlock().getType() != Material.AIR) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean isNearGround(Location location) {
		double expand = 0.3;
		for (double x = -expand; x <= expand; x += expand) {
			for (double z = -expand; z <= expand; z += expand) {
				if (location.clone().add(x, -0.3001, z).getBlock().getType() != Material.AIR) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean isNearStairs(Location location) {
		Region region = new Region(location.clone().add(1, -1, 1), location.clone().add(-1, 1, -1));
		List<Block> blocks = region.getBlocks();
		blocks.removeIf(block -> !block.getType().toString().toLowerCase().contains("stair"));
		return !blocks.isEmpty();
	}

	private boolean isNearSlab(Location location) {
		Region region = new Region(location.clone().add(1, -1, 1), location.clone().add(-1, 1, -1));
		List<Block> blocks = region.getBlocks();
		blocks.removeIf(block -> !block.getType().toString().toLowerCase().contains("slab"));
		return !blocks.isEmpty();
	}

	private boolean isNearSlime(Location location) {
		Region region = new Region(location.clone().add(1, -1, 1), location.clone().add(-1, 1, -1));
		List<Block> blocks = region.getBlocks();
		blocks.removeIf(block -> block.getType() != Material.SLIME_BLOCK);
		return !blocks.isEmpty();
	}

	private boolean isNearWeb(Location location) {
		Region region = new Region(location.clone().add(1, -1, 1), location.clone().add(-1, 1, -1));
		List<Block> blocks = region.getBlocks();
		blocks.removeIf(block -> block.getType() != XMaterial.COBWEB.parseMaterial());
		return !blocks.isEmpty();
	}
}
