package net.warden.spigot.detection.player.timer;

import io.github.retrooper.packetevents.event.impl.PacketReceiveEvent;
import io.github.retrooper.packetevents.event.impl.PacketSendEvent;
import io.github.retrooper.packetevents.packet.PacketType;
import net.warden.spigot.Main;
import net.warden.spigot.check.api.PrivateCheck;
import net.warden.spigot.check.api.data.Category;
import net.warden.spigot.events.PrivateCheckEvent;
import net.warden.spigot.playerdata.PlayerData;
import net.warden.spigot.utils.Compatibility;
import net.warden.spigot.utils.ConfigManager;
import org.bukkit.event.Listener;

public class TimerA extends PrivateCheck implements Listener {
	public TimerA(PlayerData data) {
		super(data, "Timer", 'A', Category.PLAYER);
	}

	private long lastTime;
	private double balance;

	int every = 0;

	@Override
	public PrivateCheckEvent onCheck(PrivateCheckEvent e) {
		if (!ConfigManager.getInstance().isTimerAEnabled()) return e;
		if (e.getCauseEvent() instanceof PacketReceiveEvent) {
			if (Compatibility.isInSpectator(((PacketReceiveEvent) e.getCauseEvent()).getPlayer())) return e;
			int packetId = ((PacketReceiveEvent) e.getCauseEvent()).getPacketId();
			PlayerData user = Main.getPlayerDataManager().find(((PacketReceiveEvent) e.getCauseEvent()).getPlayer().getUniqueId());
			if (PacketType.Client.Util.isInstanceOfFlying(packetId)) {
				long time = System.currentTimeMillis();
				long lastTime = this.lastTime != 0 ? this.lastTime : time - 50;
				this.lastTime = time;

				long rate = time - lastTime;

				balance += 50.0;
				balance -= rate;

				if (balance >= 10.0) {
					balance = 0.0;
					flag();
				} else {
					every++;
					if (every >= 15) {
						every = 0;
						if (user.getVLCount(this) <= 0)
							user.setVLCount(this, 0);
						if (user.getVLCount(this) > 0)
							user.decrementVL(this);
					}
				}
			}
		} else if (e.getCauseEvent() instanceof PacketSendEvent) {
			int packetId = ((PacketSendEvent) e.getCauseEvent()).getPacketId();
			if (packetId == PacketType.Server.POSITION) {
				balance -= 50.0;
			}
		}
		return e;
	}
}
