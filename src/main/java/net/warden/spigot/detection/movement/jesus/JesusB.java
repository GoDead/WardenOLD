package net.warden.spigot.detection.movement.jesus;

import io.github.retrooper.packetevents.event.impl.PacketReceiveEvent;
import io.github.retrooper.packetevents.packet.PacketType;
import net.warden.spigot.check.api.PrivateCheck;
import net.warden.spigot.check.api.data.Category;
import net.warden.spigot.events.PrivateCheckEvent;
import net.warden.spigot.playerdata.PlayerData;
import net.warden.spigot.utils.Compatibility;
import org.bukkit.entity.Player;

public class JesusB extends PrivateCheck {
	public JesusB(PlayerData data) {
		super(data, "Jesus", 'B', Category.MOVEMENT);
	}

	@Override
	public PrivateCheckEvent onCheck(PrivateCheckEvent e) {
		if (e.getCauseEvent() instanceof PacketReceiveEvent) {
			if (Compatibility.isInSpectator(((PacketReceiveEvent) e.getCauseEvent()).getPlayer())) return e;
			if (PacketType.Client.Util.isInstanceOfFlying(((PacketReceiveEvent) e.getCauseEvent()).getPacketId())) {
				Player player = ((PacketReceiveEvent) e.getCauseEvent()).getPlayer();
				if (player.getLocation().getBlock().isLiquid()) {
					//Common.broadcast(player.getLocation().getY() + "");
				}
			}
		}
		return e;
	}
}
