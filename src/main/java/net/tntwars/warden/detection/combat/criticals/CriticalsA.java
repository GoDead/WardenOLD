package net.tntwars.warden.detection.combat.criticals;

import io.github.retrooper.packetevents.event.impl.PacketReceiveEvent;
import io.github.retrooper.packetevents.packet.PacketType;
import net.tntwars.warden.Main;
import net.tntwars.warden.check.api.PublicCheck;
import net.tntwars.warden.check.api.data.Category;
import net.tntwars.warden.events.PublicCheckEvent;
import net.tntwars.warden.playerdata.PlayerData;
import net.tntwars.warden.utils.ConfigManager;
import org.bukkit.GameMode;
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
			if (((PacketReceiveEvent) e.getCauseEvent()).getPacketId() == PacketType.Client.USE_ENTITY) {
				Player player = ((PacketReceiveEvent) e.getCauseEvent()).getPlayer();
				PlayerData user = Main.getPlayerDataManager().find(player.getUniqueId());
				if (player.isInsideVehicle())
					return e;
				if (!player.getGameMode().equals(GameMode.SURVIVAL) && !player.getGameMode().equals(GameMode.ADVENTURE))
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
