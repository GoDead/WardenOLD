package net.warden.spigot.detection.movement.flight;

import io.github.retrooper.packetevents.event.impl.PacketReceiveEvent;
import io.github.retrooper.packetevents.packet.PacketType;
import net.warden.spigot.Main;
import net.warden.spigot.check.api.PrivateCheck;
import net.warden.spigot.check.api.data.Category;
import net.warden.spigot.events.PrivateCheckEvent;
import net.warden.spigot.playerdata.PlayerData;
import net.warden.spigot.utils.Compatibility;
import net.warden.spigot.utils.ConfigManager;
import net.warden.spigot.utils.XMaterial;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.mineacademy.fo.region.Region;

import java.util.ArrayList;
import java.util.List;

public class FlightG extends PrivateCheck {

	public FlightG(PlayerData data) {
		super(data, "Flight", 'G', Category.MOVEMENT);
	}


	@Override
	public PrivateCheckEvent onCheck(PrivateCheckEvent event) {
		if (!ConfigManager.getInstance().isFlightGEnabled()) return event;
		if (event.getCauseEvent() instanceof PacketReceiveEvent) {
			if (Compatibility.isInSpectator(((PacketReceiveEvent) event.getCauseEvent()).getPlayer())) return event;
			if (PacketType.Client.Util.isInstanceOfFlying(((PacketReceiveEvent) event.getCauseEvent()).getPacketId())) {
				PlayerData user = Main.getPlayerDataManager().find(((PacketReceiveEvent) event.getCauseEvent()).getPlayer().getUniqueId());
				Player player = ((PacketReceiveEvent) event.getCauseEvent()).getPlayer();
				assert user != null;
				if (user.isOnGround()) {
					user.setLastOnGround(System.currentTimeMillis());
					return event;
				}
				long glide = System.currentTimeMillis() - user.getLastGlide();
				if (glide < 4000) return event;
				if (player.isFlying()) return event;
				if (user.isNear(XMaterial.LADDER) || user.isNear(XMaterial.VINE) || user.isNear(XMaterial.TWISTING_VINES_PLANT) || user.isNear(XMaterial.WEEPING_VINES_PLANT))
					return event;
				long gm = System.currentTimeMillis() - user.getLastGameModeSwitch();
				if (gm < 4000) return event;
				long time = System.currentTimeMillis() - user.getTimeSinceDamage();
				if (time < 2000) return event;
				long explosion = System.currentTimeMillis() - user.getLastExplosionDamage();
				if (explosion < 10000) return event;
				if (Compatibility.isLegitVersion(player))
					return event;
				long lastOnGround = System.currentTimeMillis() - user.getLastOnGround();
				//Common.broadcast(lastOnGround + "");
				if (lastOnGround < 3000) return event;
				if (isInAirCube(player) && user.getDeltaY() > 0) {
					flag();
				}
			}
		}
		return event;
	}

	private boolean isInAirCube(Player player) {
		List<Boolean> areAir = new ArrayList<>();
		Location location = player.getLocation();
		Region region = new Region(location.clone().add(1, -1, 1), location.clone().add(-1, 1, -1));
		region.getBlocks().forEach(block -> {
			if (block.getType() == XMaterial.AIR.parseMaterial()) {
				areAir.add(true);
			} else {
				areAir.clear();
			}
		});
		return areAir.size() >= 3 * 3 * 3;
	}
}