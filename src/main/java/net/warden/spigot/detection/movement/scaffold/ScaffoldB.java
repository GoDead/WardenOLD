package net.warden.spigot.detection.movement.scaffold;

import net.warden.spigot.Main;
import net.warden.spigot.check.api.PrivateCheck;
import net.warden.spigot.check.api.data.Category;
import net.warden.spigot.events.PrivateCheckEvent;
import net.warden.spigot.playerdata.PlayerData;
import net.warden.spigot.utils.Compatibility;
import net.warden.spigot.utils.ConfigManager;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.util.Vector;

public class ScaffoldB extends PrivateCheck implements Listener {
	public ScaffoldB(PlayerData data) {
		super(data, "Scaffold", 'B', Category.WORLD);
	}

	@Override
	public PrivateCheckEvent onCheck(PrivateCheckEvent e) {
		return e;
	}

	@EventHandler
	public void blockPlaceEvent(BlockPlaceEvent event) {
		if (!ConfigManager.getInstance().isScaffoldBEnabled()) return;
		if (Compatibility.isInSpectator(event.getPlayer())) return;
		Player player = event.getPlayer();
		PlayerData user = Main.getPlayerDataManager().find(player.getUniqueId());
		if (user != getPlayerData()) return;
		Block placed = event.getBlockPlaced();
		Block placedAgainst = event.getBlockAgainst();
		BlockFace placedFace = placed.getFace(placedAgainst);
		final Vector placedVector = new Vector(placedFace.getModX(), placedFace.getModY(), placedFace.getModZ());
		float placedAngle = player.getLocation().getDirection().angle(placedVector);
		if (placedAngle > Math.toRadians(90)) {
			flag();
		}
	}
}
