package net.warden.spigot.detection.movement.nofall;

import io.github.retrooper.packetevents.PacketEvents;
import io.github.retrooper.packetevents.event.impl.PacketReceiveEvent;
import io.github.retrooper.packetevents.packet.PacketType;
import net.warden.spigot.Main;
import net.warden.spigot.check.api.PublicCheck;
import net.warden.spigot.check.api.data.Category;
import net.warden.spigot.events.PublicCheckEvent;
import net.warden.spigot.playerdata.PlayerData;
import net.warden.spigot.utils.Compatibility;
import net.warden.spigot.utils.ConfigManager;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class GroundSpoofB extends PublicCheck {
	public GroundSpoofB() {
		super("GroundSpoof", 'B', Category.MOVEMENT);
	}


	@Override
	public PublicCheckEvent onCheck(PublicCheckEvent e) {
		if (!ConfigManager.getInstance().isGroundSpoofBEnabled()) return e;
		if (e.getCauseEvent() instanceof PacketReceiveEvent) {
			if (PacketType.Client.Util.isInstanceOfFlying(((PacketReceiveEvent) e.getCauseEvent()).getPacketId())) {
				if (Compatibility.isInSpectator(((PacketReceiveEvent) e.getCauseEvent()).getPlayer())) return e;
				PlayerData user = Main.getPlayerDataManager().find(((PacketReceiveEvent) e.getCauseEvent()).getPlayer().getUniqueId());
				Player player = ((PacketReceiveEvent) e.getCauseEvent()).getPlayer();
				assert user != null;
				if (user.getTo() == null) return e;
				if (player.getGameMode() == GameMode.CREATIVE || player.isInsideVehicle() || player.isDead() || (user.getFrom().getX() == user.getTo().getX() && user.getFrom().getY() == user.getTo().getY() && user.getFrom().getZ() == user.getTo().getZ()))
					return e;
				if (!(PacketEvents.getAPI().getPlayerUtilities().getPing(player) > 100 || player.getNoDamageTicks() != 0 || player.isInsideVehicle() || player.isDead())) {
					double deltaY = user.getFrom().getY() - user.getTo().getY();
					if (player.isOnGround() && deltaY > 0.8) {
						flag(user);
					}
				}
			}
		}
		return e;
	}
}