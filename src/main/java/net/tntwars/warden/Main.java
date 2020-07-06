package net.tntwars.warden;


import io.github.retrooper.packetevents.PacketEvents;
import io.github.retrooper.packetevents.annotations.PacketHandler;
import io.github.retrooper.packetevents.event.PacketEvent;
import io.github.retrooper.packetevents.event.PacketListener;
import io.github.retrooper.packetevents.event.impl.*;
import net.tntwars.warden.check.api.PrivateCheck;
import net.tntwars.warden.check.api.PublicCheck;
import net.tntwars.warden.check.manager.CheckManager;
import net.tntwars.warden.command.AlertCommand;
import net.tntwars.warden.command.WardenCommand;
import net.tntwars.warden.detection.combat.criticals.CriticalsA;
import net.tntwars.warden.detection.combat.criticals.CriticalsB;
import net.tntwars.warden.detection.combat.killaura.*;
import net.tntwars.warden.detection.movement.flight.*;
import net.tntwars.warden.detection.movement.highjump.HighJumpA;
import net.tntwars.warden.detection.movement.jesus.JesusA;
import net.tntwars.warden.detection.movement.nofall.GroundSpoofA;
import net.tntwars.warden.detection.movement.nofall.GroundSpoofB;
import net.tntwars.warden.detection.movement.scaffold.ScaffoldA;
import net.tntwars.warden.detection.movement.scaffold.ScaffoldB;
import net.tntwars.warden.detection.movement.speed.SpeedA;
import net.tntwars.warden.detection.movement.speed.SpeedB;
import net.tntwars.warden.detection.player.badpackets.BadPacketsA;
import net.tntwars.warden.detection.player.badpackets.BadPacketsB;
import net.tntwars.warden.detection.player.fastbow.FastBowA;
import net.tntwars.warden.detection.player.fastbow.FastBowB;
import net.tntwars.warden.detection.player.timer.TimerA;
import net.tntwars.warden.detection.player.velocity.VelocityA;
import net.tntwars.warden.events.CheckEvent;
import net.tntwars.warden.events.PrivateCheckEvent;
import net.tntwars.warden.events.PublicCheckEvent;
import net.tntwars.warden.playerdata.PlayerData;
import net.tntwars.warden.playerdata.PlayerDataManager;
import net.tntwars.warden.utils.MovementProcessor;
import org.bukkit.plugin.Plugin;
import org.mineacademy.fo.plugin.SimplePlugin;

import java.util.UUID;

public final class Main extends SimplePlugin implements PacketListener {

	private static CheckManager checkManager;
	private static PlayerDataManager playerDataManager;

	@Override
	protected void onPluginStart() {
		registerEvents(new MovementProcessor());
		registerCommand(new AlertCommand());
		registerCommands("warden", new WardenCommand());

		start(this);
	}

	@Override
	protected void onPluginStop() {
		stop();
	}

	public void start(Plugin plugin) {
		PacketEvents.start(plugin);
		PacketEvents.getAPI().getEventManager().registerListener(this);
		getCheckManager().addCheck(new FlightB());
		getCheckManager().addCheck(new FlightD());
		getCheckManager().addCheck(new FlightE());
		getCheckManager().addCheck(new GroundSpoofA());
		getCheckManager().addCheck(new GroundSpoofB());
		getCheckManager().addCheck(new SpeedA());
		getCheckManager().addCheck(new SpeedB());
		getCheckManager().addCheck(new HighJumpA());
		getCheckManager().addCheck(new ScaffoldA());
		getCheckManager().addCheck(new ScaffoldB());
		getCheckManager().addCheck(new CriticalsA());
		getCheckManager().addCheck(new CriticalsB());
		getCheckManager().addCheck(new BadPacketsB());
	}


	public static void stop() {
		PacketEvents.stop();
	}

	@PacketHandler
	public void onInject(PlayerInjectEvent event) {
		final PlayerData data = getPlayerDataManager().registerUser(event.getPlayer().getUniqueId());
		data.setTimeSinceJoin(System.currentTimeMillis());
		getCheckManager().addCheck(new FlightA(data));
		getCheckManager().addCheck(new FlightC(data));
		getCheckManager().addCheck(new FlightF(data));
		getCheckManager().addCheck(new JesusA(data));
		getCheckManager().addCheck(new KillAuraA(data));
		getCheckManager().addCheck(new KillAuraB(data));
		getCheckManager().addCheck(new KillAuraC(data));
		getCheckManager().addCheck(new KillAuraD(data));
		getCheckManager().addCheck(new KillAuraE(data));
		getCheckManager().addCheck(new KillAuraF(data));
		getCheckManager().addCheck(new FastBowA(data));
		getCheckManager().addCheck(new FastBowB(data));
		getCheckManager().addCheck(new BadPacketsA(data));
		getCheckManager().addCheck(new TimerA(data));
		getCheckManager().addCheck(new VelocityA(data));

		registerEvents(new FastBowA(data));
		registerEvents(new FastBowB(data));

	}

	@PacketHandler
	public void onUninject(PlayerUninjectEvent event) {
		getPlayerDataManager().unregisterUser(event.getPlayer().getUniqueId());
		getCheckManager().getPrivateChecksMap().remove(event.getPlayer().getUniqueId());
	}

	@PacketHandler
	public void onPacket(final PacketEvent event) {
		//Cause has been fed into the constructor
		final CheckEvent checkEvent = new CheckEvent(event);
		for (final PublicCheck publicCheck : getCheckManager().getPublicChecks()) {
			PublicCheckEvent publicCheckEvent = new PublicCheckEvent(event);
			publicCheck.onPreCheck(publicCheckEvent);
		}
		final UUID uuid;
		if (event instanceof PacketReceiveEvent) {
			PacketReceiveEvent e = (PacketReceiveEvent) event;
			uuid = e.getPlayer().getUniqueId();
		} else if (event instanceof PacketSendEvent) {
			PacketSendEvent e = (PacketSendEvent) event;
			if (e.getPlayer() == null) {
				uuid = null;
			} else {
				uuid = e.getPlayer().getUniqueId();
			}
		} else if (event instanceof PlayerInjectEvent) {
			PlayerInjectEvent e = (PlayerInjectEvent) event;
			uuid = e.getPlayer().getUniqueId();
		} else if (event instanceof PlayerUninjectEvent) {
			PlayerUninjectEvent e = (PlayerUninjectEvent) event;
			uuid = e.getPlayer().getUniqueId();
		} else if (event instanceof BukkitMoveEvent) {
			BukkitMoveEvent e = (BukkitMoveEvent) event;
			uuid = e.getPlayer().getUniqueId();
		} else {
			return;
		}
		if (uuid == null) return;
		if (!getCheckManager().getPrivateChecksMap().containsKey(uuid)) return;
		for (final PrivateCheck privateCheck : getCheckManager().getPrivateChecks(uuid)) {
			PrivateCheckEvent privateCheckEvent = new PrivateCheckEvent(event);
			privateCheck.onPreCheck(privateCheckEvent);
		}

	}

	public static CheckManager getCheckManager() {
		return checkManager == null ? checkManager = new CheckManager() : checkManager;
	}

	public static PlayerDataManager getPlayerDataManager() {
		return playerDataManager == null ? playerDataManager = new PlayerDataManager() : playerDataManager;
	}

	/*public static Main getInstance() {
		return instance == null ? instance = new Main() : instance;
	}*/

}
