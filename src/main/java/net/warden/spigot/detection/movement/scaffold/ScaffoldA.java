package net.warden.spigot.detection.movement.scaffold;

import io.github.retrooper.packetevents.event.impl.PacketReceiveEvent;
import io.github.retrooper.packetevents.packet.PacketType;
import net.warden.spigot.Main;
import net.warden.spigot.check.api.PublicCheck;
import net.warden.spigot.check.api.data.Category;
import net.warden.spigot.events.PublicCheckEvent;
import net.warden.spigot.playerdata.PlayerData;
import net.warden.spigot.utils.Compatibility;
import net.warden.spigot.utils.ConfigManager;
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
		if (Compatibility.isInSpectator(((PacketReceiveEvent) e.getCauseEvent()).getPlayer())) return e;
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
