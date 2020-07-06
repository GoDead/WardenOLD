package net.tntwars.warden.detection.player.fastbow;

import net.tntwars.warden.check.api.PrivateCheck;
import net.tntwars.warden.check.api.data.Category;
import net.tntwars.warden.playerdata.PlayerData;
import net.tntwars.warden.utils.ConfigManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class FastBowA extends PrivateCheck implements Listener {
	public FastBowA(PlayerData data) {
		super(data, "FastBow", 'A', Category.PLAYER);
	}

	long fastBowTime = 0;

	@EventHandler
	public void interactEvent(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		if ((event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) && (player.getItemInHand().getType() == Material.BOW)) {
			fastBowTime = System.currentTimeMillis();
		}
	}

	@EventHandler
	public void shoot(EntityShootBowEvent event) {
		if (event.getEntity() instanceof Player) {
			double force = event.getForce();
			if (fastBowTime == 0) {
				return;
			}
			if ((System.currentTimeMillis() - fastBowTime) / force < 950.0 && force > 0.85) {
				if (!(ConfigManager.getInstance().isFastBowAEnabled())) return;
				flag();
			}
		}
	}

}
