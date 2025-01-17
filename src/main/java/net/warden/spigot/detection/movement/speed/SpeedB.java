package net.warden.spigot.detection.movement.speed;

import io.github.retrooper.packetevents.event.impl.BukkitMoveEvent;
import net.warden.spigot.Main;
import net.warden.spigot.check.api.PublicCheck;
import net.warden.spigot.check.api.data.Category;
import net.warden.spigot.events.PublicCheckEvent;
import net.warden.spigot.playerdata.PlayerData;
import net.warden.spigot.utils.Compatibility;
import net.warden.spigot.utils.ConfigManager;
import net.warden.spigot.utils.XMaterial;
import org.bukkit.GameMode;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

public class SpeedB extends PublicCheck {
	public SpeedB() {
		super("Speed", 'B', Category.MOVEMENT);
	}

	@Override
	public PublicCheckEvent onCheck(PublicCheckEvent e) {
		if (!ConfigManager.getInstance().isSpeedBEnabled()) return e;
		if (e.getCauseEvent() instanceof BukkitMoveEvent) {
			if (Compatibility.isInSpectator(((BukkitMoveEvent) e.getCauseEvent()).getPlayer())) return e;
			BukkitMoveEvent event = (BukkitMoveEvent) e.getCauseEvent();
			PlayerData user = Main.getPlayerDataManager().find(event.getPlayer().getUniqueId());
			if (event.getPlayer().hasPotionEffect(PotionEffectType.SPEED)) return e;
			if (event.getPlayer().getGameMode() == GameMode.CREATIVE) return e;
			assert user != null;
			long slime = System.currentTimeMillis() - user.getLastNearSlime();
			if (slime < 4000) return e;
			long vehicle = System.currentTimeMillis() - user.getLastVehicleAction();
			if (vehicle < 2000) return e;
			long time = System.currentTimeMillis() - user.getTimeSinceDamage();
			if (time < 2000) return e;
			long flight = System.currentTimeMillis() - user.getLastFlight();
			if (flight < 5000) return e;
			long water = System.currentTimeMillis() - user.getLastInWater();
			if (water < 1000) return e;
			long gm = System.currentTimeMillis() - user.getLastGameModeSwitch();
			if (gm < 4000) return e;
			if (user.isNearStairs(event.getPlayer().getLocation())) return e;
			if (user.isNearSlabs(event.getPlayer().getLocation())) return e;
			if (event.getPlayer().isFlying())
				return e;
			if (Compatibility.isLegitVersion(event.getPlayer()))
				return e;
			if (Math.abs(event.getPlayer().getWalkSpeed()) >= Math.abs(0.3)) {
				event.getPlayer().sendMessage(Math.abs(event.getPlayer().getWalkSpeed()) + " " + Math.abs(0.3));
				return e;
			}
			if (user.isNear(XMaterial.PACKED_ICE) || user.isNear(XMaterial.ICE) || user.isNear(XMaterial.FROSTED_ICE) || user.isNear(XMaterial.BLUE_ICE))
				return e;
			if (event.getPlayer().isInsideVehicle()) return e;
			Vector from = event.getFrom().toVector().clone().setY(0);
			Vector to = event.getTo().toVector().clone().setY(0);
			double speedSquared = to.distanceSquared(from);
			if (speedSquared * 10 > 4) {
				if (!event.getPlayer().hasPotionEffect(PotionEffectType.SPEED)) {
					flag(user);
				} else {
					event.getPlayer().getActivePotionEffects().forEach(potion -> {
						if (potion.getType() == PotionEffectType.SPEED) {
							if (potion.getAmplifier() < 4) {
								if (!event.getPlayer().getLocation().clone().add(0, 2, 0).getBlock().getType().isSolid())
									flag(user);
							}
						}
					});
				}
			}
		}
		return e;
	}

	public static boolean hasDepthStrider(Player player) {
		if (player.getInventory().getBoots() == null) return false;
		if (player.getInventory().getBoots().containsEnchantment(Enchantment.DEPTH_STRIDER))
			return true;
		return false;
	}
}