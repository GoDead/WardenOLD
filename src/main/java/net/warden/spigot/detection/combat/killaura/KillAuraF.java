package net.warden.spigot.detection.combat.killaura;

import io.github.retrooper.packetevents.enums.minecraft.EntityUseAction;
import io.github.retrooper.packetevents.event.impl.PacketReceiveEvent;
import io.github.retrooper.packetevents.packet.PacketType;
import io.github.retrooper.packetevents.packetwrappers.in.useentity.WrappedPacketInUseEntity;
import net.warden.spigot.check.api.PrivateCheck;
import net.warden.spigot.check.api.data.Category;
import net.warden.spigot.events.PrivateCheckEvent;
import net.warden.spigot.playerdata.PlayerData;
import net.warden.spigot.utils.Compatibility;
import net.warden.spigot.utils.ConfigManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.Set;

public class KillAuraF extends PrivateCheck {
	public KillAuraF(PlayerData data) {
		super(data, "KillAura", 'F', Category.COMBAT);
	}

	private boolean wasLastDig;

	@Override
	public PrivateCheckEvent onCheck(PrivateCheckEvent e) {
		if (!(ConfigManager.getInstance().isKillAuraFEnabled())) return e;
		if (e.getCauseEvent() instanceof PacketReceiveEvent) {
			if (Compatibility.isInSpectator(((PacketReceiveEvent) e.getCauseEvent()).getPlayer())) return e;
			int packet = ((PacketReceiveEvent) e.getCauseEvent()).getPacketId();
			Player player = ((PacketReceiveEvent) e.getCauseEvent()).getPlayer();
			if (packet == PacketType.Client.USE_ENTITY) {
				WrappedPacketInUseEntity packetId = new WrappedPacketInUseEntity(((PacketReceiveEvent) e.getCauseEvent()).getNMSPacket());
				if (packetId.getAction() != EntityUseAction.ATTACK) return e;
				if (wasLastDig) {
					flag();
				} else {
					if (player.getTargetBlock((Set<Material>) null, 4) != null) {

					} else {

					}
				}
			} else if (PacketType.Client.Util.isInstanceOfFlying(packet)) {
				wasLastDig = false;
			} else if (packet == PacketType.Client.BLOCK_DIG || packet == PacketType.Client.BLOCK_PLACE) {
				wasLastDig = true;
			}
		}
		return e;
	}


}
