package net.warden.spigot.detection.movement.speed;

import io.github.retrooper.packetevents.event.impl.BukkitMoveEvent;
import net.warden.spigot.Main;
import net.warden.spigot.check.api.PublicCheck;
import net.warden.spigot.check.api.data.Category;
import net.warden.spigot.events.PublicCheckEvent;
import net.warden.spigot.playerdata.PlayerData;
import net.warden.spigot.utils.Compatibility;
import net.warden.spigot.utils.ConfigManager;
import net.warden.spigot.utils.Distance;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;
import org.mineacademy.fo.region.Region;

import java.util.List;

public class SpeedA extends PublicCheck {
	public SpeedA() {
		super("Speed", 'A', Category.MOVEMENT);
	}

	@Override
	public PublicCheckEvent onCheck(PublicCheckEvent e) {
		if (!ConfigManager.getInstance().isSpeedAEnabled()) return e;
		if (e.getCauseEvent() instanceof BukkitMoveEvent) {
			if (Compatibility.isInSpectator(((BukkitMoveEvent) e.getCauseEvent()).getPlayer())) return e;
			BukkitMoveEvent event = (BukkitMoveEvent) e.getCauseEvent();
			Distance distance = new Distance(event);
			PlayerData user = Main.getPlayerDataManager().find(event.getPlayer().getUniqueId());
			Player player = ((BukkitMoveEvent) e.getCauseEvent()).getPlayer();
			if (Compatibility.isLegitVersion(player)) return e;
			if (event.getPlayer().isFlying()) return e;
			assert user != null;
			long slime = System.currentTimeMillis() - user.getLastNearSlime();
			if (slime < 4000) return e;
			long vehicle = System.currentTimeMillis() - user.getLastVehicleAction();
			if (vehicle < 2000) return e;
			long time = System.currentTimeMillis() - user.getTimeSinceDamage();
			if (time < 2000) return e;
			long water = System.currentTimeMillis() - user.getLastInWater();
			if (water < 1000) return e;
			long flight = System.currentTimeMillis() - user.getLastFlight();
			if (flight < 5000) return e;
			if (!runCheck(distance, event.getPlayer())) return e;
			if (player.isInsideVehicle()) return e;
			if (isNearStairs(player.getLocation())) return e;
			if (!event.getPlayer().hasPotionEffect(PotionEffectType.SPEED)) {
				flag(user);
			} else {
				player.getActivePotionEffects().forEach(potion -> {
					if (potion.getType() == PotionEffectType.SPEED) {
						if (potion.getAmplifier() < 4) {
							if (!player.getLocation().clone().add(0, 2, 0).getBlock().getType().isSolid())
								flag(user);
						}
					}
				});

			}
		}
		return e;
	}

	//https://www.youtube.com/watch?v=syliroWqHcs&t
	public boolean runCheck(Distance distance, Player player) {
		Double xz_speed = (distance.getxDiff() > distance.getzDiff() ? distance.getxDiff() : distance.getzDiff());
		if (xz_speed > 0.75D && player.getGameMode() != GameMode.CREATIVE) {
			return true;
		}
		return false;
	}

	private boolean isNearStairs(Location location) {
		Region region = new Region(location.clone().add(1, -0.5, 1), location.clone().add(-1, -0.5, -1));
		List<Block> blocks = region.getBlocks();
		if (blocks.size() != 9) return false;
		return (blocks.get(0).toString().toLowerCase().contains("stair") ||
				blocks.get(1).toString().toLowerCase().contains("stair") ||
				blocks.get(2).toString().toLowerCase().contains("stair") ||
				blocks.get(3).toString().toLowerCase().contains("stair") ||
				blocks.get(4).toString().toLowerCase().contains("stair") ||
				blocks.get(5).toString().toLowerCase().contains("stair") ||
				blocks.get(6).toString().toLowerCase().contains("stair") ||
				blocks.get(7).toString().toLowerCase().contains("stair") ||
				blocks.get(8).toString().toLowerCase().contains("stair")
		);
	}
}
