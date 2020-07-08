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
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

public class SpeedA extends PublicCheck {
	public SpeedA() {
		super("Speed", 'A', Category.MOVEMENT);
	}

	@Override
	public PublicCheckEvent onCheck(PublicCheckEvent e) {
		if (!ConfigManager.getInstance().isSpeedAEnabled()) return e;
		if (e.getCauseEvent() instanceof BukkitMoveEvent) {
			BukkitMoveEvent event = (BukkitMoveEvent) e.getCauseEvent();
			Distance distance = new Distance(event);
			PlayerData user = Main.getPlayerDataManager().find(event.getPlayer().getUniqueId());
			Player player = user.getPlayer();
			long time = System.currentTimeMillis() - user.getTimeSinceDamage();
			if (time < 2000) return e;
			if (Compatibility.isLegitVersion(player))
				return e;
			if (!runCheck(distance, event.getPlayer())) return e;
			if (event.getPlayer().getActivePotionEffects() != PotionEffectType.SPEED) {
				flag(user);
			}
		}
		return e;
	}

	//https://www.youtube.com/watch?v=syliroWqHcs&t
	public boolean runCheck(Distance distance, Player player) {
		Double xz_speed = (distance.getxDiff() > distance.getzDiff() ? distance.getxDiff() : distance.getzDiff());
		if (xz_speed > 0.75D && player.getGameMode() != GameMode.CREATIVE && player.getGameMode() != GameMode.SPECTATOR) {
			return true;
		}
		return false;
	}
}
