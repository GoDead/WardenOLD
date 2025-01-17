package net.warden.spigot.detection.player.badpackets;

import io.github.retrooper.packetevents.PacketEvents;
import io.github.retrooper.packetevents.enums.ServerVersion;
import io.github.retrooper.packetevents.enums.minecraft.EntityUseAction;
import io.github.retrooper.packetevents.event.impl.PacketReceiveEvent;
import io.github.retrooper.packetevents.packet.PacketType;
import io.github.retrooper.packetevents.packetwrappers.in.useentity.WrappedPacketInUseEntity;
import net.warden.spigot.Main;
import net.warden.spigot.check.api.PrivateCheck;
import net.warden.spigot.check.api.data.Category;
import net.warden.spigot.events.PrivateCheckEvent;
import net.warden.spigot.playerdata.PlayerData;
import net.warden.spigot.utils.Compatibility;
import net.warden.spigot.utils.ConfigManager;

public class BadPacketsA extends PrivateCheck {
	public BadPacketsA(PlayerData data) {
		super(data, "BadPackets", 'A', Category.PLAYER);
	}

	private boolean wasLastArmAnimation;

	//https://www.youtube.com/watch?v=uCgq8EnNje8&t
	@Override
	public PrivateCheckEvent onCheck(PrivateCheckEvent e) {
		if (!ServerVersion.getVersion().isLowerThan(ServerVersion.v_1_9)) return e;
		if (!(ConfigManager.getInstance().isBadPacketsAEnabled())) return e;
		if (e.getCauseEvent() instanceof PacketReceiveEvent) {
			PlayerData user = Main.getPlayerDataManager().find(((PacketReceiveEvent) e.getCauseEvent()).getPlayer().getUniqueId());
			if (Compatibility.isInSpectator(((PacketReceiveEvent) e.getCauseEvent()).getPlayer())) return e;
			if (((PacketReceiveEvent) e.getCauseEvent()).getPacketId() == PacketType.Client.ARM_ANIMATION) {
				wasLastArmAnimation = true;
			} else if (((PacketReceiveEvent) e.getCauseEvent()).getPacketId() == PacketType.Client.USE_ENTITY) {
				WrappedPacketInUseEntity packet = new WrappedPacketInUseEntity(((PacketReceiveEvent) e.getCauseEvent()).getNMSPacket());
				if (packet.getAction() != EntityUseAction.ATTACK) return e;
				if (!wasLastArmAnimation) {
					if (PacketEvents.getAPI().getPlayerUtilities().getPing(((PacketReceiveEvent) e.getCauseEvent()).getPlayer()) < 30)
						flag();
				} else {
					if (user.getVLCount(this) <= 0)
						user.setVLCount(this, 0);
					if (user.getVLCount(this) > 0)
						user.decrementVL(this);
				}
			} else if (PacketType.Client.Util.isInstanceOfFlying(((PacketReceiveEvent) e.getCauseEvent()).getPacketId())) {
				wasLastArmAnimation = false;
			}
		}
		return e;
	}
}
