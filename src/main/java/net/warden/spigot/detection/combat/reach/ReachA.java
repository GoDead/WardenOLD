package net.warden.spigot.detection.combat.reach;

import io.github.retrooper.packetevents.PacketEvents;
import io.github.retrooper.packetevents.event.impl.PacketReceiveEvent;
import io.github.retrooper.packetevents.packet.PacketType;
import io.github.retrooper.packetevents.packetwrappers.in.useentity.WrappedPacketInUseEntity;
import net.warden.spigot.check.api.PrivateCheck;
import net.warden.spigot.check.api.data.Category;
import net.warden.spigot.events.PrivateCheckEvent;
import net.warden.spigot.playerdata.PlayerData;
import net.warden.spigot.utils.Compatibility;
import net.warden.spigot.utils.ConfigManager;
import org.bukkit.GameMode;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

public class ReachA extends PrivateCheck {
	public ReachA(PlayerData data) {
		super(data, "Reach", 'A', Category.COMBAT);
	}

	@Override
	public PrivateCheckEvent onCheck(PrivateCheckEvent e) {
		if (!(ConfigManager.getInstance().isReachAEnabled())) return e;
		if (e.getCauseEvent() instanceof PacketReceiveEvent) {
			if (Compatibility.isInSpectator(((PacketReceiveEvent) e.getCauseEvent()).getPlayer())) return e;
			if (((PacketReceiveEvent) e.getCauseEvent()).getPacketId() == PacketType.Client.USE_ENTITY) {
				WrappedPacketInUseEntity packet = new WrappedPacketInUseEntity(((PacketReceiveEvent) e.getCauseEvent()).getNMSPacket());
				Entity victim = packet.getEntity();
				if (!(victim instanceof Player)) return e;
				Player player = ((PacketReceiveEvent) e.getCauseEvent()).getPlayer();
				Vector attackerPos;
				if (PacketEvents.getAPI().getPlayerUtilities().getPing(player) > 80 || PacketEvents.getAPI().getPlayerUtilities().getPing((Player) victim) > 80)
					return e;
				if (player.isInsideVehicle()) {
					attackerPos = player.getLocation().toVector();
					attackerPos.setY(attackerPos.getY() + player.getEyeHeight());
				} else {
					attackerPos = player.getEyeLocation().toVector();
				}
				double maxReach = player.getGameMode() == GameMode.CREATIVE ? 6.0 : 4.4;
				if (attackerPos == null) return e;
				if (victim.getLocation() == null) return e;
				double dist = distanceToPosition(attackerPos, victim.getLocation().toVector());
				if (dist > maxReach) {
					if (!player.hasPotionEffect(PotionEffectType.SPEED))
						flag();
				}
			}
		}
		return e;
	}

	//edit from hawk
	public double distanceToPosition(Vector player, Vector victim) {
		double distX = Math.max(victim.getX() - player.getX(), Math.max(0, player.getX() - victim.getX()));
		double distY = Math.max(victim.getY() - player.getY(), Math.max(0, player.getY() - victim.getY()));
		double distZ = Math.max(victim.getZ() - player.getZ(), Math.max(0, player.getZ() - victim.getZ()));
		return Math.sqrt(distX * distX + distY * distY + distZ * distZ);
	}
}