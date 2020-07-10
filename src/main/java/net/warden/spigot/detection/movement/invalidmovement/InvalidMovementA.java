package net.warden.spigot.detection.movement.invalidmovement;

import io.github.retrooper.packetevents.event.impl.PacketReceiveEvent;
import io.github.retrooper.packetevents.packet.PacketType;
import net.warden.spigot.Main;
import net.warden.spigot.check.api.PrivateCheck;
import net.warden.spigot.check.api.data.Category;
import net.warden.spigot.events.PrivateCheckEvent;
import net.warden.spigot.playerdata.PlayerData;
import net.warden.spigot.utils.Compatibility;
import net.warden.spigot.utils.ConfigManager;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;
import org.mineacademy.fo.region.Region;

import java.util.List;

public class InvalidMovementA extends PrivateCheck {
	public InvalidMovementA(PlayerData data) {
		super(data, "InvalidMovement", 'A', Category.MOVEMENT);
	}

	@Override
	public PrivateCheckEvent onCheck(PrivateCheckEvent e) {
		if (!ConfigManager.getInstance().isInvalidMovementAEnabled()) return e;
		if (e.getCauseEvent() instanceof PacketReceiveEvent) {
			if (PacketType.Client.Util.isInstanceOfFlying((((PacketReceiveEvent) e.getCauseEvent()).getPacketId()))) {
				if (Compatibility.isInSpectator(((PacketReceiveEvent) e.getCauseEvent()).getPlayer())) return e;
				PlayerData user = Main.getPlayerDataManager().find(((PacketReceiveEvent) e.getCauseEvent()).getPlayer().getUniqueId());
				Player player = ((PacketReceiveEvent) e.getCauseEvent()).getPlayer();
				if (Compatibility.isInSpectator(player)) return e;
				if (player.isInsideVehicle()) return e;
				assert user != null;
				long teleport = System.currentTimeMillis() - user.getLastTeleport();
				if (teleport < 2000) return e;
				long water = System.currentTimeMillis() - user.getLastInWater();
				if (water < 2000) return e;
				long levitation = System.currentTimeMillis() - user.getLastLevitation();
				if (levitation < 5000) return e;
				long time = System.currentTimeMillis() - user.getLastVehicleAction();
				if (time < 2000) return e;
				if (Compatibility.isLegitVersion(player)) return e;
				long slime = System.currentTimeMillis() - user.getLastNearSlime();
				if (slime < 8000) return e;
				if (player.getAllowFlight()) return e;
				if (isNearStairs(player.getLocation())) return e;
				if (user.getDeltaY() != 0) {
					//Common.broadcast(user.getDeltaY() + "");
				}
				if (!player.hasPotionEffect(PotionEffectType.JUMP)) {
					if (user.getDeltaY() > 0.41999998688697815) {
						flag();
					}
				} else {
					player.getActivePotionEffects().forEach(potion -> {
						if (potion.getType() == PotionEffectType.JUMP && user.getDeltaXZ() < 0.3) {
							switch (potion.getAmplifier()) {
								case 1:
									if (user.getDeltaY() > 0.6199999898672104 && user.getDeltaXZ() < 0.3) {
										flag();
									}
									break;
								case 2:
									if (user.getDeltaY() > 0.7199999988079071 && user.getDeltaXZ() < 0.3) {
										flag();
									}
									break;
								case 3:
									if (user.getDeltaY() > 0.8199999928474426 && user.getDeltaXZ() < 0.3) {
										flag();
									}
									break;
								case 4:
									if (user.getDeltaY() > 0.9199999868869781 && user.getDeltaXZ() < 0.3) {
										flag();
									}
									break;
								case 5:
									if (user.getDeltaY() > 1.020000010728836 && user.getDeltaXZ() < 0.3) {
										flag();
									}
									break;
								default:
									break;
							}
						}
					});
				}
			}

			//NO JUMP: 0.41999998688697815
			//JUMPBOOST 1: 0.6199999898672104
			//JUMPBOOST 2: 0.7199999988079071
			//JUMPBOOST 3: 0.8199999928474426
			//JUMPBOOST 4: 0.9199999868869781
			//JUMPBOOST 5: 1.020000010728836
		}
		return e;
	}

	private boolean isNearStairs(Location location) {
		Region region = new Region(location.clone().add(1, -0.5, 1), location.clone().add(-1, -0.5, -1));
		List<Block> blocks = region.getBlocks();
		if (blocks.size() != 9) return false;
		return (blocks.get(0).toString().toLowerCase().contains("stair") ||
				blocks.get(1).toString().toLowerCase().contains("stair") ||
				blocks.get(2).toString().toLowerCase().contains("stair") ||
				blocks.get(3).toString().toLowerCase().contains("stair") ||
				blocks.get(4).toString().toLowerCase().contains("stair") ||
				blocks.get(5).toString().toLowerCase().contains("stair") ||
				blocks.get(6).toString().toLowerCase().contains("stair") ||
				blocks.get(7).toString().toLowerCase().contains("stair") ||
				blocks.get(8).toString().toLowerCase().contains("stair")
		);
	}
}
