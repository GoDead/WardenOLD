package net.warden.spigot.detection.combat.killaura;

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
import net.warden.spigot.utils.Cooldown;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class KillAuraG extends PrivateCheck {
	public KillAuraG(PlayerData data) {
		super(data, "KillAura", 'G', Category.COMBAT);
	}

	private List<Long> times = new ArrayList<>();

	@Override
	public PrivateCheckEvent onCheck(PrivateCheckEvent e) {
		if (ServerVersion.getVersion().isLowerThan(ServerVersion.v_1_9)) return e;
		if (!(ConfigManager.getInstance().isKillAuraGEnabled())) return e;
		if (e.getCauseEvent() instanceof PacketReceiveEvent) {
			if (Compatibility.isInSpectator(((PacketReceiveEvent) e.getCauseEvent()).getPlayer())) return e;
			if (((PacketReceiveEvent) e.getCauseEvent()).getPacketId() != PacketType.Client.USE_ENTITY) return e;
			PlayerData user = Main.getPlayerDataManager().find(((PacketReceiveEvent) e.getCauseEvent()).getPlayer().getUniqueId());
			WrappedPacketInUseEntity packet = new WrappedPacketInUseEntity(((PacketReceiveEvent) e.getCauseEvent()).getNMSPacket());
			if (packet.getAction() != EntityUseAction.ATTACK) return e;
			Player player = ((PacketReceiveEvent) e.getCauseEvent()).getPlayer();
			Cooldown.hit(player);
			if (isHacking(times)) {
				flag();
			}
			//player.sendMessage("" + times.toString());
			if (times.size() < 5) {
				times.add(user.getCurrentHit());
			} else if (times.size() == 5) {
				times.remove(0);
				times.add(user.getCurrentHit());
			} else {
				System.out.println("CLEARED");
				times.clear();
			}
		}
		return e;
	}

	private boolean isHacking(List<Long> list) {
		if (list.isEmpty()) return false;
		if (list.size() < 5) return false;
		if (isSimilar(list.get(4), list.get(3)))
			if (isSimilar(list.get(3), list.get(2)))
				if (isSimilar(list.get(1), list.get(1)))
					if (list.get(4) > 400 && list.get(3) > 400 && list.get(2) > 400 && list.get(1) > 400)
						return true;
		return false;
	}

	private boolean isSimilar(long one, long two) {
		long result = one - two;
		return Math.abs(result) < 6;
	}
}
