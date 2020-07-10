package net.warden.spigot.command.wardencommand;

import net.warden.spigot.Main;
import net.warden.spigot.detection.combat.criticals.CriticalsA;
import net.warden.spigot.detection.combat.criticals.CriticalsB;
import net.warden.spigot.detection.combat.killaura.*;
import net.warden.spigot.detection.movement.flight.*;
import net.warden.spigot.detection.movement.highjump.HighJumpA;
import net.warden.spigot.detection.movement.invalidmovement.InvalidMovementA;
import net.warden.spigot.detection.movement.invalidmovement.InvalidMovementB;
import net.warden.spigot.detection.movement.invalidmovement.InvalidMovementC;
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
import net.warden.spigot.playerdata.PlayerData;
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
		setDescription("Get a player's violations");
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
		if (getInvalidMovementVL(user) != 0)
			tell(" &f- &cInvalidMovement: &f" + getInvalidMovementVL(user));
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
				+ getVelocityVL(user)
				+ getInvalidMovementVL(user);
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
				+ user.getViolationsMap().getOrDefault(KillAuraE.class, 0)
				+ user.getViolationsMap().getOrDefault(KillAuraF.class, 0)
				+ user.getViolationsMap().getOrDefault(KillAuraG.class, 0)
				+ user.getViolationsMap().getOrDefault(KillAuraH.class, 0);
		return VL;
	}

	private int getCriticalsVL(PlayerData user) {
		int VL = user.getViolationsMap().getOrDefault(CriticalsA.class, 0)
				+ user.getViolationsMap().getOrDefault(CriticalsB.class, 0);
		return VL;
	}

	private int getBadPacketsVL(PlayerData user) {
		int VL = user.getViolationsMap().getOrDefault(BadPacketsA.class, 0)
				+ user.getViolationsMap().getOrDefault(BadPacketsB.class, 0)
				+ user.getViolationsMap().getOrDefault(BadPacketsC.class, 0);
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

	private int getInvalidMovementVL(PlayerData user) {
		int VL = user.getViolationsMap().getOrDefault(InvalidMovementA.class, 0)
				+ user.getViolationsMap().getOrDefault(InvalidMovementB.class, 0)
				+ user.getViolationsMap().getOrDefault(InvalidMovementC.class, 0);
		return VL;
	}
}
