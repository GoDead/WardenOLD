package net.warden.spigot;


import io.github.retrooper.packetevents.PacketEvents;
import io.github.retrooper.packetevents.annotations.PacketHandler;
import io.github.retrooper.packetevents.enums.ServerVersion;
import io.github.retrooper.packetevents.event.PacketEvent;
import io.github.retrooper.packetevents.event.PacketListener;
import io.github.retrooper.packetevents.event.impl.*;
import net.warden.spigot.check.api.PrivateCheck;
import net.warden.spigot.check.api.PublicCheck;
import net.warden.spigot.check.manager.CheckManager;
import net.warden.spigot.command.AlertCommand;
import net.warden.spigot.command.WardenCommand;
import net.warden.spigot.detection.combat.criticals.CriticalsA;
import net.warden.spigot.detection.combat.criticals.CriticalsB;
import net.warden.spigot.detection.combat.killaura.*;
import net.warden.spigot.detection.combat.reach.ReachA;
import net.warden.spigot.detection.movement.flight.*;
import net.warden.spigot.detection.movement.highjump.HighJumpA;
import net.warden.spigot.detection.movement.invalidmovement.InvalidMovementA;
import net.warden.spigot.detection.movement.invalidmovement.InvalidMovementB;
import net.warden.spigot.detection.movement.jesus.JesusA;
import net.warden.spigot.detection.movement.nofall.GroundSpoofA;
import net.warden.spigot.detection.movement.nofall.GroundSpoofB;
import net.warden.spigot.detection.movement.scaffold.ScaffoldA;
import net.warden.spigot.detection.movement.scaffold.ScaffoldB;
import net.warden.spigot.detection.movement.speed.SpeedA;
import net.warden.spigot.detection.movement.speed.SpeedB;
import net.warden.spigot.detection.player.badpackets.BadPacketsA;
import net.warden.spigot.detection.player.badpackets.BadPacketsB;
import net.warden.spigot.detection.player.badpackets.BadPacketsC;
import net.warden.spigot.detection.player.fastbow.FastBowA;
import net.warden.spigot.detection.player.fastbow.FastBowB;
import net.warden.spigot.detection.player.timer.TimerA;
import net.warden.spigot.detection.player.velocity.VelocityA;
import net.warden.spigot.events.CheckEvent;
import net.warden.spigot.events.PrivateCheckEvent;
import net.warden.spigot.events.PublicCheckEvent;
import net.warden.spigot.playerdata.PlayerData;
import net.warden.spigot.playerdata.PlayerDataManager;
import net.warden.spigot.utils.MovementProcessor;
import net.warden.spigot.utils.UpdateChecker;
import net.warden.spigot.utils.Version;
import org.bukkit.plugin.Plugin;
import org.mineacademy.fo.Common;
import org.mineacademy.fo.plugin.SimplePlugin;

import java.util.UUID;

public final class Main extends SimplePlugin implements PacketListener {

	private static CheckManager checkManager;
	private static PlayerDataManager playerDataManager;

	Version version;

	@Override
	protected void onPluginPreStart() {
		Common.log("&6Checking for updates...");
		new UpdateChecker(this, 80393).getVersion(version -> {
			String cVersion = getVersion();
			String lVersion = version;
			cVersion = cVersion.replace("B", "");
			lVersion = lVersion.replace("B", "");
			cVersion = cVersion.replace("R", "");
			lVersion = lVersion.replace("R", "");
			cVersion = cVersion.replace(".", "");
			lVersion = lVersion.replace(".", "");
			int currentVersion = Integer.parseInt(cVersion);
			int latestVersion = Integer.parseInt(lVersion);
			int difference = currentVersion - latestVersion;
			if (difference < 0) {
				Common.log("&c" + Common.consoleLineSmooth());
				Common.log("\n&cWarning!");
				Common.log("\n&fWarden is out of date, a new update is available!");
				Common.log("&fPlease download the new update at:&6 https://www.spigotmc.org/resources/warden-guardian-of-your-server-simple-cheat-detection-1-8-1-16.80393/");
				Common.log("\n&c" + Common.consoleLineSmooth());
				Common.log("\n&bDo not forget to join our Discord server: &fhttps://discord.gg/r4mz3ZX");
			} else if (difference == 0) {
				Common.log("&aWarden is up to date!");
				Common.log("\n&bDo not forget to join our Discord server: &fhttps://discord.gg/r4mz3ZX");
			} else if (difference > 0) {
				Common.log("&6Whhhat are you living in the future? You are " + difference + " versions ahead!");
				Common.log("\n&bDo not forget to join our Discord server: &fhttps://discord.gg/r4mz3ZX");
			}
		});
		String version = getServer().getVersion();
		ServerVersion.getVersion();
		if (version.contains("1.7"))
			this.version = Version.V1_7;
		if (version.contains("1.8"))
			this.version = Version.V1_8;
		else if (version.contains("1.9"))
			this.version = Version.V1_9;
		if (version.contains("1.10"))
			this.version = Version.V1_10;
		else if (version.contains("1.11"))
			this.version = Version.V1_11;
		else if (version.contains("1.12"))
			this.version = Version.V1_12;
		else if (version.contains("1.13"))
			this.version = Version.V1_13;
		else if (version.contains("1.14"))
			this.version = Version.V1_14;
		else if (version.contains("1.15"))
			this.version = Version.V1_15;
		else if (version.contains("1.16"))
			this.version = Version.V1_16;
		else
			this.version = Version.UNKNOWN;
		Common.log("&6Server Version " + this.version.getName());
		Common.log("&6Running Warden B" + getVersion());
	}

	@Override
	protected void onPluginStart() {
		Common.log("&6Enabling Checks...");
		registerEvents(new MovementProcessor());
		getServer().getMessenger().registerOutgoingPluginChannel(this, "warden:alerts");
		registerCommand(new AlertCommand());
		registerCommands("warden", new WardenCommand());
		start(this);
		Common.log("&6Finished Loading.");
	}

	@Override
	protected void onPluginStop() {
		stop();
	}

	public void start(Plugin plugin) {
		PacketEvents.start(plugin);
		PacketEvents.getAPI().getEventManager().registerListener(this);
		PacketEvents.getAPI().getEventManager().registerListener(new MovementProcessor());
		registerEvents(new BadPacketsC());
		getCheckManager().addCheck(new FlightB());
		getCheckManager().addCheck(new FlightD());
		getCheckManager().addCheck(new FlightE());
		getCheckManager().addCheck(new GroundSpoofA());
		getCheckManager().addCheck(new GroundSpoofB());
		getCheckManager().addCheck(new SpeedA());
		getCheckManager().addCheck(new SpeedB());
		getCheckManager().addCheck(new HighJumpA());
		getCheckManager().addCheck(new InvalidMovementB());
		getCheckManager().addCheck(new ScaffoldA());
		getCheckManager().addCheck(new CriticalsA());
		getCheckManager().addCheck(new CriticalsB());
		registerEvents(new CriticalsB());
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
		registerEvents(new KillAuraD(data));
		getCheckManager().addCheck(new KillAuraE(data));
		getCheckManager().addCheck(new KillAuraF(data));
		getCheckManager().addCheck(new KillAuraG(data));
		getCheckManager().addCheck(new KillAuraH(data));
		getCheckManager().addCheck(new ScaffoldB(data));
		registerEvents(new ScaffoldB(data));
		getCheckManager().addCheck(new InvalidMovementA(data));
		getCheckManager().addCheck(new ReachA(data));
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

	public Version getServerVersion() {
		return version;
	}

	/*public static Main getInstance() {
		return instance == null ? instance = new Main() : instance;
	}*/

}
