package net.warden.spigot.detection.player.badpackets;

import net.warden.spigot.Main;
import net.warden.spigot.check.api.PublicCheck;
import net.warden.spigot.check.api.data.Category;
import net.warden.spigot.events.PublicCheckEvent;
import net.warden.spigot.playerdata.PlayerData;
import net.warden.spigot.utils.Compatibility;
import net.warden.spigot.utils.ConfigManager;
import net.warden.spigot.utils.XMaterial;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BadPacketsC extends PublicCheck implements Listener {
	public BadPacketsC() {
		super("BadPackets", 'C', Category.PLAYER);
	}

	@Override
	public PublicCheckEvent onCheck(PublicCheckEvent e) {
		return e;
	}

	@EventHandler
	public void blockPlace(BlockPlaceEvent event) {
		if (!ConfigManager.getInstance().isBadPacketsCEnabled()) return;
		if (Compatibility.isInSpectator(event.getPlayer())) return;
		Block underBlock = event.getBlockPlaced().getRelative(0, -1, 0);
		Block topBlock = event.getBlockPlaced().getRelative(0, 1, 0);

		Block infrontBlock = event.getBlockPlaced().getRelative(1, 0, 0);
		Block behindBlock = event.getBlockPlaced().getRelative(-1, 0, 0);

		Block leftBlock = event.getBlockPlaced().getRelative(0, 0, 1);
		Block rightBlock = event.getBlockPlaced().getRelative(0, 0, -1);
		PlayerData user = Main.getPlayerDataManager().find(event.getPlayer().getUniqueId());
		if (underBlock.getType() == XMaterial.AIR.parseMaterial() && topBlock.getType() == XMaterial.AIR.parseMaterial() && infrontBlock.getType() == XMaterial.AIR.parseMaterial() && behindBlock.getType() == XMaterial.AIR.parseMaterial() && leftBlock.getType() == XMaterial.AIR.parseMaterial() && rightBlock.getType() == XMaterial.AIR.parseMaterial()) {
			flag(user);
		} else if (event.getBlockAgainst().isLiquid() || event.getBlockAgainst().getType() == XMaterial.AIR.parseMaterial()) {
			flag(user);
		} else if (event.getBlockAgainst() == event.getBlockPlaced()) {
			flag(user);
		} else if ((underBlock.isLiquid() && infrontBlock.isLiquid() && behindBlock.isLiquid() && leftBlock.isLiquid() && rightBlock.isLiquid()) &&
				(topBlock.getType() == XMaterial.AIR.parseMaterial() || topBlock.isLiquid())) {
			flag(user);
		}
	}
}
