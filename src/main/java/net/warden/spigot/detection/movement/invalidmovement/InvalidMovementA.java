package net.warden.spigot.detection.movement.invalidmovement;

import io.github.retrooper.packetevents.event.impl.PacketReceiveEvent;
import io.github.retrooper.packetevents.packet.PacketType;
import net.warden.spigot.Main;
import net.warden.spigot.check.api.PrivateCheck;
import net.warden.spigot.check.api.data.Category;
import net.warden.spigot.events.PrivateCheckEvent;
import net.warden.spigot.playerdata.PlayerData;
import net.warden.spigot.utils.Compatibility;
import org.bukkit.entity.Player;

public class InvalidMovementA extends PrivateCheck {
	public InvalidMovementA(PlayerData data) {
		super(data, "InvalidMovement", 'A', Category.MOVEMENT);
	}

	@Override
	public PrivateCheckEvent onCheck(PrivateCheckEvent e) {
		if (e.getCauseEvent() instanceof PacketReceiveEvent) {
			if (PacketType.Client.Util.isInstanceOfFlying((((PacketReceiveEvent) e.getCauseEvent()).getPacketId()))) {
				if (Compatibility.isInSpectator(((PacketReceiveEvent) e.getCauseEvent()).getPlayer())) return e;
				PlayerData user = Main.getPlayerDataManager().find(((PacketReceiveEvent) e.getCauseEvent()).getPlayer().getUniqueId());
				Player player = ((PacketReceiveEvent) e.getCauseEvent()).getPlayer();
				//Common.broadcast("&6DeltaXZ: " + user.getDeltaXZ());
				//Common.broadcast("&bDeltaY: " + user.getDeltaY());
				if (user.getDeltaY() > 0.42 && user.getDeltaXZ() < 0.3) {
					flag();
				}
			}
		}
		return e;
	}
}
