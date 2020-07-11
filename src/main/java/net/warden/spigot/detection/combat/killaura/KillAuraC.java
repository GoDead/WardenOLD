package net.warden.spigot.detection.combat.killaura;

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
import org.bukkit.entity.Entity;

public class KillAuraC extends PrivateCheck {
	public KillAuraC(PlayerData data) {
		super(data, "KillAura", 'C', Category.COMBAT);
	}

	long lastSwing;

	@Override
	public PrivateCheckEvent onCheck(PrivateCheckEvent e) {
		if (!ServerVersion.getVersion().isLowerThan(ServerVersion.v_1_9)) return e;
		if (!(ConfigManager.getInstance().isKillAuraCEnabled())) return e;
		if (e.getCauseEvent() instanceof PacketReceiveEvent) {
			if (Compatibility.isInSpectator(((PacketReceiveEvent) e.getCauseEvent()).getPlayer())) return e;
			PlayerData user = Main.getPlayerDataManager().find(((PacketReceiveEvent) e.getCauseEvent()).getPlayer().getUniqueId());
			if (((PacketReceiveEvent) e.getCauseEvent()).getPacketId() == PacketType.Client.ARM_ANIMATION) {
				lastSwing = System.currentTimeMillis();
			} else if (((PacketReceiveEvent) e.getCauseEvent()).getPacketId() == PacketType.Client.USE_ENTITY) {
				WrappedPacketInUseEntity packetInUseEntity = new WrappedPacketInUseEntity(((PacketReceiveEvent) e.getCauseEvent()).getNMSPacket());
				if (!(packetInUseEntity.getAction().equals(EntityUseAction.ATTACK))) {
					return e;
				}
				long time = System.currentTimeMillis() - lastSwing;
				if (time != 0 && time != 1) {
					if (PacketEvents.getAPI().getPlayerUtilities().getPing(((PacketReceiveEvent) e.getCauseEvent()).getPlayer()) < 30
							&& PacketEvents.getAPI().getServerUtilities().getCurrentServerTPS() > 19.5
					) {
						Entity entity = packetInUseEntity.getEntity();
						if (entity.getLocation().toVector().distance(((PacketReceiveEvent) e.getCauseEvent()).getPlayer().getLocation().toVector()) < 1.2)
							return e;
						if (user.getHitsInARow() > 8)
							flag();
					} else {
						comply();
					}
				}
			}
		}
		return e;
	}
}
