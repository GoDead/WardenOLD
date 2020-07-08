package net.warden.spigot.utils;

import net.warden.spigot.Main;
import net.warden.spigot.playerdata.PlayerData;
import org.bukkit.Location;

public class PlayerUtils {

	public static boolean onGround(PlayerData player) {
		Location location = Main.getPlayerDataManager().find(player.getUniqueId()).getLocation();
		double expand = 0.3;
		for (double x = -expand; x <= expand; x += expand) {
			for (double z = -expand; z <= expand; z += expand) {
				if (location.clone().add(x, -0.1, z).getBlock().getType().isSolid() || location.clone().add(x, -0.5001, z).getBlock().getType().isSolid()) {
					return true;
				}
			}

		}
		return false;
	}
}
