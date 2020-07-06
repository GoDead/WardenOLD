package net.tntwars.warden.command.wardencommand;

import net.tntwars.warden.Main;
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
import net.tntwars.warden.playerdata.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.mineacademy.fo.command.SimpleCommandGroup;
import org.mineacademy.fo.command.SimpleSubCommand;

public class Violation extends SimpleSubCommand {

	public Violation(final SimpleCommandGroup parent) {
		super(parent, "vl");
		setPermission("warden.checkvl");
		setUsage("&c[player]");
		setMinArguments(1);
	}

	@Override
	protected void onCommand() {
		Player player = Bukkit.getPlayer(args[0]);
		if (player == null) {
			tell("&cWarden &8» &7" + args[0] + " &7doesn't exist or is not online!");
			return;
		}
		
		if (!player.isOnline()) {
			tell("&cWarden &8» &7" + player.getName() + " &7is not online!");
			return;
		}
		PlayerData user = Main.getPlayerDataManager().find(player.getUniqueId());
		if (user.getViolationsMap().isEmpty() || getTotalVL(user) <= 0) {
			tell("&cWarden &8» &7" + player.getName() + " &7doesn't have any violations!");
			return;
		}
		tell("&7§m" + "----------" + " &7« &6" + player.getName() + "'s Violations &7»" + "&7§m" + "----------");
		if (getFlightVL(user) != 0)
			tell(" &f- &cFlight: &f" + getFlightVL(user));
		if (getSpeedVL(user) != 0)
			tell(" &f- &cSpeed: &f" + getSpeedVL(user));
		if (getJesusVL(user) != 0)
			tell(" &f- &cJesus: &f" + getJesusVL(user));
		if (getGroundSpoofVL(user) != 0)
			tell(" &f- &cGroundSpoof: &f" + getGroundSpoofVL(user));
		if (getHighJumpVL(user) != 0)
			tell(" &f- &cHighJump: &f" + getHighJumpVL(user));
		if (getScaffoldVL(user) != 0)
			tell(" &f- &cScaffold: &f" + getScaffoldVL(user));
		if (getKillAuraVL(user) != 0)
			tell(" &f- &cKillAura: &f" + getKillAuraVL(user));
		if (getCriticalsVL(user) != 0)
			tell(" &f- &cCriticals: &f" + getCriticalsVL(user));
		if (getBadPacketsVL(user) != 0)
			tell(" &f- &cBadPackets: &f" + getBadPacketsVL(user));
		if (getFastBowVL(user) != 0)
			tell(" &f- &cFastBow: &f" + getFastBowVL(user));
		if (getTimerVL(user) != 0)
			tell(" &f- &cTimer: &f" + getTimerVL(user));
		if (getVelocityVL(user) != 0)
			tell(" &f- &cVelocity: &f" + getVelocityVL(user));
	}

	private int getTotalVL(PlayerData user) {
		int VL = getFlightVL(user)
				+ getSpeedVL(user)
				+ getJesusVL(user)
				+ getGroundSpoofVL(user)
				+ getHighJumpVL(user)
				+ getScaffoldVL(user)
				+ getKillAuraVL(user)
				+ getCriticalsVL(user)
				+ getBadPacketsVL(user)
				+ getFastBowVL(user)
				+ getTimerVL(user)
				+ getVelocityVL(user);
		return VL;
	}

	private int getFlightVL(PlayerData user) {
		int VL = user.getViolationsMap().getOrDefault(FlightA.class, 0)
				+ user.getViolationsMap().getOrDefault(FlightB.class, 0)
				+ user.getViolationsMap().getOrDefault(FlightC.class, 0)
				+ user.getViolationsMap().getOrDefault(FlightD.class, 0)
				+ user.getViolationsMap().getOrDefault(FlightE.class, 0)
				+ user.getViolationsMap().getOrDefault(FlightF.class, 0);
		return VL;
	}

	private int getSpeedVL(PlayerData user) {
		int VL = user.getViolationsMap().getOrDefault(SpeedA.class, 0)
				+ user.getViolationsMap().getOrDefault(SpeedB.class, 0);
		return VL;
	}

	private int getJesusVL(PlayerData user) {
		int VL = user.getViolationsMap().getOrDefault(JesusA.class, 0);
		return VL;
	}

	private int getGroundSpoofVL(PlayerData user) {
		int VL = user.getViolationsMap().getOrDefault(GroundSpoofA.class, 0)
				+ user.getViolationsMap().getOrDefault(GroundSpoofB.class, 0);
		return VL;
	}

	private int getHighJumpVL(PlayerData user) {
		int VL = user.getViolationsMap().getOrDefault(HighJumpA.class, 0);
		return VL;
	}

	private int getScaffoldVL(PlayerData user) {
		int VL = user.getViolationsMap().getOrDefault(ScaffoldA.class, 0)
				+ user.getViolationsMap().getOrDefault(ScaffoldB.class, 0);
		return VL;
	}

	private int getKillAuraVL(PlayerData user) {
		int VL = user.getViolationsMap().getOrDefault(KillAuraA.class, 0)
				+ user.getViolationsMap().getOrDefault(KillAuraB.class, 0)
				+ user.getViolationsMap().getOrDefault(KillAuraC.class, 0)
				+ user.getViolationsMap().getOrDefault(KillAuraD.class, 0)
				+ user.getViolationsMap().getOrDefault(KillAuraE.class, 0);
		return VL;
	}

	private int getCriticalsVL(PlayerData user) {
		int VL = user.getViolationsMap().getOrDefault(CriticalsA.class, 0)
				+ user.getViolationsMap().getOrDefault(CriticalsB.class, 0);
		return VL;
	}

	private int getBadPacketsVL(PlayerData user) {
		int VL = user.getViolationsMap().getOrDefault(BadPacketsA.class, 0)
				+ user.getViolationsMap().getOrDefault(BadPacketsB.class, 0);
		return VL;
	}

	private int getFastBowVL(PlayerData user) {
		int VL = user.getViolationsMap().getOrDefault(FastBowA.class, 0)
				+ user.getViolationsMap().getOrDefault(FastBowB.class, 0);
		return VL;
	}

	private int getTimerVL(PlayerData user) {
		int VL = user.getViolationsMap().getOrDefault(TimerA.class, 0);
		return VL;
	}

	private int getVelocityVL(PlayerData user) {
		int VL = user.getViolationsMap().getOrDefault(VelocityA.class, 0);
		return VL;
	}
}
