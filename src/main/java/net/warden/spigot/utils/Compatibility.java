package net.warden.spigot.utils;

import io.github.retrooper.packetevents.enums.ServerVersion;
import net.warden.spigot.Main;
import net.warden.spigot.playerdata.PlayerData;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;
import org.mineacademy.fo.region.Region;

import java.util.ArrayList;
import java.util.List;

public class Compatibility {

	public static class V_1_9 {
		public static boolean isGliding(Player player) {
			if (ServerVersion.getVersion().isLowerThan(ServerVersion.v_1_9)) return false;
			if (player.isGliding()) {
				PlayerData user = Main.getPlayerDataManager().find(player.getUniqueId());
				user.setLastFlight(System.currentTimeMillis());
			}
			return player.isGliding();
		}

		public static boolean isNearShulkerBox(Location location) {
			if (ServerVersion.getVersion().isLowerThan(ServerVersion.v_1_9)) return false;
			if (!XMaterial.SHULKER_BOX.isSupported()) return false;
			Region region = new Region(location.clone().add(1, -1, 1), location.clone().add(-1, 1, -1));
			List<Block> blocks = region.getBlocks();
			blocks.removeIf(block -> block.getType() != XMaterial.SHULKER_BOX.parseMaterial());
			return !blocks.isEmpty();
		}
	}

	public static class V_1_13 {
		public static boolean hasLevitation(Player player) {
			if (ServerVersion.getVersion().isLowerThan(ServerVersion.v_1_13)) return false;
			if (player.hasPotionEffect(PotionEffectType.LEVITATION)) {
				PlayerData user = Main.getPlayerDataManager().find(player.getUniqueId());
				user.setLastLevitation(System.currentTimeMillis());
			}
			return player.hasPotionEffect(PotionEffectType.LEVITATION);
		}

		public static boolean isRiptiding(Player player) {
			if (ServerVersion.getVersion().isLowerThan(ServerVersion.v_1_13)) return false;
			return player.isRiptiding();
		}

		public static boolean isSwimming(Player player) {
			if (ServerVersion.getVersion().isLowerThan(ServerVersion.v_1_13)) return false;
			return player.isSwimming();
		}
	}

	public static class V_1_14 {
		public static boolean isNearBerryBush(Location location) {
			if (ServerVersion.getVersion().isLowerThan(ServerVersion.v_1_15)) return false;
			Region region = new Region(location.clone().add(1, -1, 1), location.clone().add(-1, 1, -1));
			List<Block> blocks = region.getBlocks();
			blocks.removeIf(block -> block.getType() != XMaterial.SWEET_BERRY_BUSH.parseMaterial());
			return !blocks.isEmpty();
		}
	}

	public static class V_1_15 {
		public static boolean isNearHoney(Location location) {
			if (ServerVersion.getVersion().isLowerThan(ServerVersion.v_1_15)) return false;
			if (!XMaterial.HONEY_BLOCK.isSupported()) return false;
			Region region = new Region(location.clone().add(1, -1, 1), location.clone().add(-1, 1, -1));
			List<Block> blocks = region.getBlocks();
			blocks.removeIf(block -> block.getType() != XMaterial.HONEY_BLOCK.parseMaterial());
			return !blocks.isEmpty();
		}
	}

	public static class V_1_16 {
		public static boolean hasSoulSpeed(Player player) {
			if (ServerVersion.getVersion().isLowerThan(ServerVersion.v_1_16_1)) return false;
			if (player.getInventory().getBoots() == null) return false;
			if (player.getInventory().getBoots().containsEnchantment(Enchantment.SOUL_SPEED))
				return true;
			return false;
		}
	}

	public static boolean isLegitVersion(Player player) {
		List<Boolean> list = new ArrayList<>();
		list.add(V_1_9.isGliding(player));
		list.add(V_1_9.isNearShulkerBox(player.getLocation()));
		list.add(V_1_13.hasLevitation(player));
		list.add(V_1_13.isRiptiding(player));
		list.add(V_1_13.isSwimming(player));
		list.add(V_1_14.isNearBerryBush(player.getLocation()));
		list.add(V_1_15.isNearHoney(player.getLocation()));
		list.add(V_1_16.hasSoulSpeed(player));
		//player.sendMessage(Common.colorize("&c" + !list.contains(true)));
		return list.contains(true);
	}

	public static boolean isInSpectator(Player player) {
		if (ServerVersion.getVersion().isLowerThan(ServerVersion.v_1_8)) return false;
		if (player.getGameMode() == GameMode.SPECTATOR) {
			return true;
		}
		return false;
	}
}
