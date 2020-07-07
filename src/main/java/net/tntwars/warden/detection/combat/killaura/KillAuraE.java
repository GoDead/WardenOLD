package net.tntwars.warden.detection.combat.killaura;

import io.github.retrooper.packetevents.enums.minecraft.EntityUseAction;
import io.github.retrooper.packetevents.event.impl.PacketReceiveEvent;
import io.github.retrooper.packetevents.packet.PacketType;
import io.github.retrooper.packetevents.packetwrappers.in.useentity.WrappedPacketInUseEntity;
import net.tntwars.warden.check.api.PrivateCheck;
import net.tntwars.warden.check.api.data.Category;
import net.tntwars.warden.events.PrivateCheckEvent;
import net.tntwars.warden.playerdata.PlayerData;
import net.tntwars.warden.utils.ConfigManager;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class KillAuraE extends PrivateCheck {
	public KillAuraE(PlayerData data) {
		super(data, "KillAura", 'E', Category.COMBAT);
	}

	@Override
	public PrivateCheckEvent onCheck(PrivateCheckEvent e) {
		if (!(ConfigManager.getInstance().isKillAuraBEnabled())) return e;
		if (e.getCauseEvent() instanceof PacketReceiveEvent) {
			if (((PacketReceiveEvent) e.getCauseEvent()).getPacketId() == PacketType.Client.USE_ENTITY) {
				WrappedPacketInUseEntity packet = new WrappedPacketInUseEntity(((PacketReceiveEvent) e.getCauseEvent()).getNMSPacket());
				if (packet.getAction() != EntityUseAction.ATTACK) return e;
				Player player = ((PacketReceiveEvent) e.getCauseEvent()).getPlayer();
				Entity entity = packet.getEntity();
				if (!getNotLookingAt(player, entity)) {
					flag();
				}

			}
		}
		return e;
	}

	private boolean getNotLookingAt(Player player, Entity player1) {
		Vector eye = player.getEyeLocation().getDirection();
		Vector toEntity = player1.getLocation().toVector().subtract(player.getEyeLocation().toVector());
		double dot = toEntity.normalize().dot(eye.normalize());
		//player.sendMessage(dot + "");

		return dot > 0D;
	}
}
