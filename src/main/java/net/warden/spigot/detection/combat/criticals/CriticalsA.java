package net.warden.spigot.detection.combat.criticals;

import io.github.retrooper.packetevents.event.impl.PacketReceiveEvent;
import io.github.retrooper.packetevents.packet.PacketType;
import net.warden.spigot.Main;
import net.warden.spigot.check.api.PublicCheck;
import net.warden.spigot.check.api.data.Category;
import net.warden.spigot.events.PublicCheckEvent;
import net.warden.spigot.playerdata.PlayerData;
import net.warden.spigot.utils.Compatibility;
import net.warden.spigot.utils.ConfigManager;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

public class CriticalsA extends PublicCheck {
	public CriticalsA() {
		super("Criticals", 'A', Category.COMBAT);
	}

	@Override
	public PublicCheckEvent onCheck(PublicCheckEvent e) {
		if (!(ConfigManager.getInstance().isCriticalsAEnabled())) return e;
		if (e.getCauseEvent() instanceof PacketReceiveEvent) {
			if (Compatibility.isInSpectator(((PacketReceiveEvent) e.getCauseEvent()).getPlayer())) return e;
			if (((PacketReceiveEvent) e.getCauseEvent()).getPacketId() == PacketType.Client.USE_ENTITY) {
				Player player = ((PacketReceiveEvent) e.getCauseEvent()).getPlayer();
				PlayerData user = Main.getPlayerDataManager().find(player.getUniqueId());
				if (player.isInsideVehicle())
					return e;
				if (!player.isOnGround() && !player.isFlying()) {
					if (player.getLocation().getY() % 1.0D == 0.0D) {
						if (player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType().isSolid()) {
							flag(user);
						}
					}
				}
			}
		}
		return e;
	}
}
