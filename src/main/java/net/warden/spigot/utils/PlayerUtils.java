package net.warden.spigot.utils;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class PlayerUtils {

	public static boolean onGround(Player player) {
		Location location = player.getLocation();
		double expand = 0.3;
		for (double x = -expand; x <= expand; x += expand) {
			for (double z = -expand; z <= expand; z += expand) {
				if (location.clone().add(x, -0.1, z).getBlock().getType() != XMaterial.AIR.parseMaterial() || location.clone().add(x, -0.5001, z).getBlock().getType() != XMaterial.AIR.parseMaterial()) {
					return true;
				}
			}

		}
		return false;
	}
}
