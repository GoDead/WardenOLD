package net.warden.spigot.detection.movement.invalidmovement;

import io.github.retrooper.packetevents.event.impl.PacketReceiveEvent;
import io.github.retrooper.packetevents.packet.PacketType;
import net.warden.spigot.Main;
import net.warden.spigot.check.api.PublicCheck;
import net.warden.spigot.check.api.data.Category;
import net.warden.spigot.events.PublicCheckEvent;
import net.warden.spigot.playerdata.PlayerData;
import net.warden.spigot.utils.Compatibility;
import net.warden.spigot.utils.ConfigManager;
import net.warden.spigot.utils.XMaterial;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

public class InvalidMovementC extends PublicCheck {
	public InvalidMovementC() {
		super("InvalidMovement", 'C', Category.MOVEMENT);
	}

	@Override
	public PublicCheckEvent onCheck(PublicCheckEvent e) {
		if (!ConfigManager.getInstance().isInvalidMovementCEnabled()) return e;
		if (e.getCauseEvent() instanceof PacketReceiveEvent) {
			if (PacketType.Client.Util.isInstanceOfFlying((((PacketReceiveEvent) e.getCauseEvent()).getPacketId()))) {
				if (Compatibility.isInSpectator(((PacketReceiveEvent) e.getCauseEvent()).getPlayer())) return e;
				PlayerData user = Main.getPlayerDataManager().find(((PacketReceiveEvent) e.getCauseEvent()).getPlayer().getUniqueId());
				Player player = ((PacketReceiveEvent) e.getCauseEvent()).getPlayer();
				if (Compatibility.isInSpectator(player)) return e;
				if (player.isFlying()) return e;
				if (!inWebs(player)) return e;
				assert user != null;
				if (user.getDeltaXZ() != 0.0D) {
					//Common.broadcast(user.getDeltaXZ() + "");
				}
				if (!player.hasPotionEffect(PotionEffectType.SPEED)) {
					if (user.getDeltaXZ() > 0.08821935836306238) {
						flag(user);
					}
				} else {
					player.getActivePotionEffects().forEach(potion -> {
						if (potion.getType() == PotionEffectType.SPEED) {
							switch (potion.getAmplifier()) {
								case 1:
									if (user.getDeltaXZ() > 0.10089838509101791) {
										flag(user);
									}
									break;
								case 2:
									if (user.getDeltaXZ() > 0.10732982495119359) {
										flag(user);
									}
									break;
								case 3:
									if (user.getDeltaXZ() > 0.11361005600830437) {
										flag(user);
									}
									break;
								case 4:
									if (user.getDeltaXZ() > 0.12006894114125544) {
										flag(user);
									}
									break;
								case 5:
									if (user.getDeltaXZ() > 0.12007000482553407) {
										flag(user);
									}
									break;
								default:
									break;
							}
						}
					});
				}
			}
		}
		return e;
	}

	private boolean inWebs(Player player) {
		if ((player.getLocation().getBlock() != null && player.getLocation().getBlock().getType() == XMaterial.COBWEB.parseMaterial())
				|| (player.getLocation().getBlock().getRelative(BlockFace.UP) != null && player.getLocation().getBlock().getRelative(BlockFace.UP).getType() == XMaterial.COBWEB.parseMaterial()))
			return true;
		return false;
	}
}