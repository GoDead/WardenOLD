package net.warden.spigot.utils;

import net.warden.spigot.Main;
import net.warden.spigot.playerdata.PlayerData;
import org.bukkit.entity.Player;

public class Cooldown {

	private static final long SWORD_COOLDOWN = 12;
	private static final long PICKAXE_COOLDOWN = 17;
	private static final long SHOVEL_COOLDOWN = 20;
	private static final long TRIDENT_COOLDOWN = 18;

	private static final long NETHERITE_AXE_COOLDOWN = 20;
	private static final long DIAMOND_AXE_COOLDOWN = 20;
	private static final long GOLD_AXE_COOLDOWN = 20;
	private static final long IRON_AXE_COOLDOWN = 22;
	private static final long STONE_AXE_COOLDOWN = 25;
	private static final long WOODEN_AXE_COOLDOWN = 25;

	private static final long NETHERITE_HOE_COOLDOWN = 5;
	private static final long DIAMOND_HOE_COOLDOWN = 5;
	private static final long GOLD_HOE_COOLDOWN = 20;
	private static final long IRON_HOE_COOLDOWN = 7;
	private static final long STONE_HOE_COOLDOWN = 10;
	private static final long WOODEN_HOE_COOLDOWN = 20;

	private static final long OTHER = 5;

	public static void hit(Player player) {
		/*if (ServerVersion.getVersion().isLowerThan(ServerVersion.v_1_9)) return;
		if (ServerVersion.getVersion().isLowerThan(ServerVersion.v_1_16_1)) {
			startLegacy(player);
		} else if (ServerVersion.getVersion().isHigherThan(ServerVersion.v_1_15)) {
			startLegacy(player);
		}*/
		startLegacy(player);
	}

	private static void startLegacy(Player player) {
		PlayerData user = Main.getPlayerDataManager().find(player.getUniqueId());
		long previous = user.getPreviousHit();
		user.setCurrentHit(System.currentTimeMillis() - user.getPreviousPreviousHit());
		user.setPreviousPreviousHit(previous);
		user.setPreviousHit(System.currentTimeMillis());
	}
}


