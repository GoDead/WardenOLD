package net.warden.spigot.detection.combat.killaura;

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
import org.bukkit.entity.Player;

public class KillAuraI extends PrivateCheck {
	public KillAuraI(PlayerData data) {
		super(data, "KillAura", 'I', Category.COMBAT);
	}

	float pitchDiff;
	float previousPitchDiff;

	float diffDiff;
	float previousDiffDiff;


	@Override
	public PrivateCheckEvent onCheck(PrivateCheckEvent e) {
		if (!(ConfigManager.getInstance().isKillAuraIEnabled())) return e;
		if (e.getCauseEvent() instanceof PacketReceiveEvent) {
			if (Compatibility.isInSpectator(((PacketReceiveEvent) e.getCauseEvent()).getPlayer())) return e;
			int packet = ((PacketReceiveEvent) e.getCauseEvent()).getPacketId();
			Player player = ((PacketReceiveEvent) e.getCauseEvent()).getPlayer();
			PlayerData user = Main.getPlayerDataManager().find(player.getUniqueId());
			if (packet == PacketType.Client.USE_ENTITY) {
				WrappedPacketInUseEntity packetId = new WrappedPacketInUseEntity(((PacketReceiveEvent) e.getCauseEvent()).getNMSPacket());
				if (packetId.getAction() != EntityUseAction.ATTACK) return e;
				assert user != null;
				float yawDiff = Math.abs(user.getFrom().getYaw() - user.getTo().getYaw());
				float pitchDiff = Math.abs(user.getFrom().getPitch() - user.getTo().getPitch());
				//Common.broadcast("&dY: " + yawDiff);
				//Common.broadcast("&bP: " + pitchDiff);
				double distance = user.getFrom().toVector().distance(user.getTo().toVector());
				previousPitchDiff = this.pitchDiff;
				this.pitchDiff = pitchDiff;
				float difference = Math.abs(Math.abs(previousPitchDiff) - Math.abs(pitchDiff));
				if (String.valueOf(difference).contains("E")) {
					flag();
				}
				previousDiffDiff = this.diffDiff;
				this.diffDiff = difference;
				float diffDifference = Math.abs(Math.abs(previousDiffDiff) - Math.abs(diffDiff));
				if (String.valueOf(diffDifference).contains("E")) {
					flag();
				}
				if (diffDifference > 30 && user.getHitsInARow() > 5) {
					flag();
					//Common.broadcast("&d" + diffDifference + " &f" + distance);
				}
				if (pitchDiff > 15 && user.getHitsInARow() > 12 && distance > 0.3) {
					flag();
					//Common.broadcast("&b" + pitchDiff + " &f" + distance);
				}
			}
		}
		return e;
	}
}
