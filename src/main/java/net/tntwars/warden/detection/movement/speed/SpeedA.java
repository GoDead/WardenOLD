package net.tntwars.warden.detection.movement.speed;

import io.github.retrooper.packetevents.event.impl.BukkitMoveEvent;
import net.tntwars.warden.Main;
import net.tntwars.warden.check.api.PublicCheck;
import net.tntwars.warden.check.api.data.Category;
import net.tntwars.warden.events.PublicCheckEvent;
import net.tntwars.warden.playerdata.PlayerData;
import net.tntwars.warden.utils.Compatibility;
import net.tntwars.warden.utils.ConfigManager;
import net.tntwars.warden.utils.Distance;
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
