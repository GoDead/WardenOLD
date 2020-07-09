package net.warden.spigot.detection.player.fastbow;

import net.warden.spigot.Main;
import net.warden.spigot.check.api.PrivateCheck;
import net.warden.spigot.check.api.data.Category;
import net.warden.spigot.playerdata.PlayerData;
import net.warden.spigot.utils.Compatibility;
import net.warden.spigot.utils.ConfigManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class FastBowB extends PrivateCheck implements Listener {
	public FastBowB(PlayerData data) {
		super(data, "FastBow", 'B', Category.PLAYER);
	}

	long fastBowTime = 0;

	@EventHandler
	public void interactEvent(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		PlayerData user = Main.getPlayerDataManager().find(player.getUniqueId());
		if (user != getPlayerData()) return;
		if ((event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) && (player.getItemInHand().getType() == Material.BOW)) {
			fastBowTime = System.currentTimeMillis();
		}
	}

	@EventHandler
	public void shoot(EntityShootBowEvent event) {
		if (event.getEntity() instanceof Player) {
			Player player = (Player) event.getEntity();
			PlayerData user = Main.getPlayerDataManager().find(player.getUniqueId());
			if (user != getPlayerData()) return;
			double force = event.getForce();
			if (fastBowTime == 0) {
				return;
			}
			if ((System.currentTimeMillis() - fastBowTime) / force < 700.0 && force > 0.42) {
				if (!(ConfigManager.getInstance().isFastBowBEnabled())) return;
				if (Compatibility.isInSpectator(player)) return;
				flag();
			}
		}
	}

}