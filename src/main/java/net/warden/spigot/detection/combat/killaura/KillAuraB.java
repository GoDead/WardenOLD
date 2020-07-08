package net.warden.spigot.detection.combat.killaura;

import io.github.retrooper.packetevents.PacketEvents;
import io.github.retrooper.packetevents.enums.minecraft.EntityUseAction;
import io.github.retrooper.packetevents.event.impl.PacketReceiveEvent;
import io.github.retrooper.packetevents.packet.PacketType;
import io.github.retrooper.packetevents.packetwrappers.in.useentity.WrappedPacketInUseEntity;
import net.warden.spigot.check.api.PrivateCheck;
import net.warden.spigot.check.api.data.Category;
import net.warden.spigot.events.PrivateCheckEvent;
import net.warden.spigot.playerdata.PlayerData;
import net.warden.spigot.utils.ConfigManager;
import org.bukkit.entity.Player;

public class KillAuraB extends PrivateCheck {
	public KillAuraB(PlayerData data) {
		super(data, "KillAura", 'B', Category.COMBAT);
	}

	float lastYaw = 0;
	long lastFlying = 0;

	@Override
	public PrivateCheckEvent onCheck(PrivateCheckEvent e) {
		if (!(ConfigManager.getInstance().isKillAuraBEnabled())) return e;
		if (e.getCauseEvent() instanceof PacketReceiveEvent) {
			if (((PacketReceiveEvent) e.getCauseEvent()).getPacketId() == PacketType.Client.USE_ENTITY) {
				WrappedPacketInUseEntity packet = new WrappedPacketInUseEntity(((PacketReceiveEvent) e.getCauseEvent()).getNMSPacket());
				if (packet.getAction() != EntityUseAction.ATTACK) return e;
				Player player = ((PacketReceiveEvent) e.getCauseEvent()).getPlayer();
				long elapsed = e.getTimestamp() - lastFlying;
				if (PacketEvents.getAPI().getPlayerUtilities().getPing(player) > 100) return e;
				if (Math.abs(elapsed) < Math.abs(40)) {
					flag();
				}
			} else if (PacketType.Client.Util.isInstanceOfFlying(((PacketReceiveEvent) e.getCauseEvent()).getPacketId())) {
				lastFlying = e.getTimestamp();
			}
		}
		return e;
	}
}
