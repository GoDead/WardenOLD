package net.tntwars.warden.detection.movement.scaffold;

import io.github.retrooper.packetevents.event.impl.PacketReceiveEvent;
import io.github.retrooper.packetevents.packet.PacketType;
import net.tntwars.warden.Main;
import net.tntwars.warden.check.api.PublicCheck;
import net.tntwars.warden.check.api.data.Category;
import net.tntwars.warden.events.PublicCheckEvent;
import net.tntwars.warden.playerdata.PlayerData;
import net.tntwars.warden.utils.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ScaffoldA extends PublicCheck {
	public ScaffoldA() {
		super("Scaffold", 'A', Category.WORLD);
	}

	@Override
	public PublicCheckEvent onCheck(PublicCheckEvent e) {
		if (!ConfigManager.getInstance().isScaffoldAEnabled()) return e;
		if (!(e.getCauseEvent() instanceof PacketReceiveEvent)) return e;
		if ((((PacketReceiveEvent) e.getCauseEvent()).getPacketId()) == PacketType.Client.BLOCK_PLACE) {
			Player player = ((PacketReceiveEvent) e.getCauseEvent()).getPlayer();
			PlayerData user = Main.getPlayerDataManager().find(((PacketReceiveEvent) e.getCauseEvent()).getPlayer().getUniqueId());
			final float now = player.getLocation().getPitch();
			Bukkit.getScheduler().runTaskLater(Main.getInstance(), () -> {
				float pitchNow = player.getLocation().getPitch();
				float diff = Math.abs(now - pitchNow);
				if (diff > 20F) {
					flag(user);
				}
			}, 2L);
		}
		return e;
	}
}
