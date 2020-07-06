package net.tntwars.warden.utils;

import lombok.AccessLevel;
import lombok.Getter;
import org.mineacademy.fo.collection.SerializedMap;
import org.mineacademy.fo.settings.YamlConfig;


@Getter(AccessLevel.PUBLIC)
public class ConfigManager extends YamlConfig {
	@Getter
	public static final ConfigManager instance = new ConfigManager();
	//FLY
	private boolean flightAEnabled;
	private int flightAMaxVL;
	private boolean flightABannable;
	private String flightAPunish;

	private boolean flightBEnabled;
	private int flightBMaxVL;
	private boolean flightBBannable;
	private String flightBPunish;

	private boolean flightCEnabled;
	private int flightCMaxVL;
	private boolean flightCBannable;
	private String flightCPunish;

	private boolean flightDEnabled;
	private int flightDMaxVL;
	private boolean flightDBannable;
	private String flightDPunish;

	private boolean flightEEnabled;
	private int flightEMaxVL;
	private boolean flightEBannable;
	private String flightEPunish;

	private boolean flightFEnabled;
	private int flightFMaxVL;
	private boolean flightFBannable;
	private String flightFPunish;

	//SPEED
	private boolean speedAEnabled;
	private int speedAMaxVL;
	private boolean speedABannable;
	private String speedAPunish;

	private boolean speedBEnabled;
	private int speedBMaxVL;
	private boolean speedBBannable;
	private String speedBPunish;

	//SCAFFOLD
	private boolean scaffoldAEnabled;
	private int scaffoldAMaxVL;
	private boolean scaffoldABannable;
	private String scaffoldAPunish;

	private boolean scaffoldBEnabled;
	private int scaffoldBMaxVL;
	private boolean scaffoldBBannable;
	private String scaffoldBPunish;

	//JESUS
	private boolean jesusAEnabled;
	private int jesusAMaxVL;
	private boolean jesusABannable;
	private String jesusAPunish;

	private boolean jesusBEnabled;
	private int jesusBMaxVL;
	private boolean jesusBBannable;
	private String jesusBPunish;

	//HIGHJUMP
	private boolean highJumpAEnabled;
	private int highJumpAMaxVL;
	private boolean highJumpABannable;
	private String highJumpAPunish;

	//KILLAURA
	private boolean killAuraAEnabled;
	private int killAuraAMaxVL;
	private boolean killAuraABannable;
	private String killAuraAPunish;

	private boolean killAuraBEnabled;
	private int killAuraBMaxVL;
	private boolean killAuraBBannable;
	private String killAuraBPunish;

	private boolean killAuraCEnabled;
	private int killAuraCMaxVL;
	private boolean killAuraCBannable;
	private String killAuraCPunish;

	private boolean killAuraDEnabled;
	private int killAuraDMaxVL;
	private boolean killAuraDBannable;
	private String killAuraDPunish;

	private boolean killAuraEEnabled;
	private int killAuraEMaxVL;
	private boolean killAuraEBannable;
	private String killAuraEPunish;

	//CRITICALS
	private boolean criticalsAEnabled;
	private int criticalsAMaxVL;
	private boolean criticalsABannable;
	private String criticalsAPunish;

	private boolean criticalsBEnabled;
	private int criticalsBMaxVL;
	private boolean criticalsBBannable;
	private String criticalsBPunish;

	//REACH
	private boolean reachAEnabled;
	private int reachAMaxVL;
	private boolean reachABannable;
	private String reachAPunish;

	//FASTBOW
	private boolean fastBowAEnabled;
	private int fastBowAMaxVL;
	private boolean fastBowABannable;
	private String fastBowAPunish;

	private boolean fastBowBEnabled;
	private int fastBowBMaxVL;
	private boolean fastBowBBannable;
	private String fastBowBPunish;

	//BLINK
	private boolean blinkAEnabled;
	private int blinkAMaxVL;
	private boolean blinkABannable;
	private String blinkAPunish;

	//BADPACKETS
	private boolean badPacketsAEnabled;
	private int badPacketsAMaxVL;
	private boolean badPacketsABannable;
	private String badPacketsAPunish;

	private boolean badPacketsBEnabled;
	private int badPacketsBMaxVL;
	private boolean badPacketsBBannable;
	private String badPacketsBPunish;

	//GROUNDSPOOF
	private boolean groundSpoofAEnabled;
	private int groundSpoofAMaxVL;
	private boolean groundSpoofABannable;
	private String groundSpoofAPunish;

	private boolean groundSpoofBEnabled;
	private int groundSpoofBMaxVL;
	private boolean groundSpoofBBannable;
	private String groundSpoofBPunish;

	//TIMER
	private boolean timerAEnabled;
	private int timerAMaxVL;
	private boolean timerABannable;
	private String timerAPunish;

	private boolean timerBEnabled;
	private int timerBMaxVL;
	private boolean timerBBannable;
	private String timerBPunish;

	//VELOCITY
	private boolean velocityAEnabled;
	private int velocityAMaxVL;
	private boolean velocityABannable;
	private String velocityAPunish;

	private boolean velocityBEnabled;
	private int velocityBMaxVL;
	private boolean velocityBBannable;
	private String velocityBPunish;


	public ConfigManager() {
		setHeader("Warden Checks Configuration");
		loadConfiguration(NO_DEFAULT, "checks.yml");
	}

	public void reloadConfig() {
		reload();
	}


	@Override
	protected void onLoadFinish() {
		//FLY
		flightAEnabled = getOrSetDefault("FlightA.enabled", true);
		flightAMaxVL = getOrSetDefault("FlightA.maxVl", 15);
		flightABannable = getOrSetDefault("FlightA.bannable", true);
		flightAPunish = getOrSetDefault("FlightA.punish", "ban %player%");

		flightBEnabled = getOrSetDefault("FlightB.enabled", true);
		flightBMaxVL = getOrSetDefault("FlightB.maxVl", 15);
		flightBBannable = getOrSetDefault("FlightB.bannable", true);
		flightBPunish = getOrSetDefault("FlightB.punish", "ban %player%");

		flightCEnabled = getOrSetDefault("FlightC.enabled", true);
		flightCMaxVL = getOrSetDefault("FlightC.maxVl", 15);
		flightCBannable = getOrSetDefault("FlightC.bannable", true);
		flightCPunish = getOrSetDefault("FlightC.punish", "ban %player%");

		flightDEnabled = getOrSetDefault("FlightD.enabled", true);
		flightDMaxVL = getOrSetDefault("FlightD.maxVl", 15);
		flightDBannable = getOrSetDefault("FlightD.bannable", true);
		flightDPunish = getOrSetDefault("FlightD.punish", "ban %player%");

		flightEEnabled = getOrSetDefault("FlightE.enabled", true);
		flightEMaxVL = getOrSetDefault("FlightE.maxVl", 15);
		flightEBannable = getOrSetDefault("FlightE.bannable", true);
		flightEPunish = getOrSetDefault("FlightE.punish", "ban %player%");

		flightFEnabled = getOrSetDefault("FlightF.enabled", true);
		flightFMaxVL = getOrSetDefault("FlightF.maxVl", 15);
		flightFBannable = getOrSetDefault("FlightF.bannable", true);
		flightFPunish = getOrSetDefault("FlightF.punish", "ban %player%");

		//SPEED
		speedAEnabled = getOrSetDefault("SpeedA.enabled", true);
		speedAMaxVL = getOrSetDefault("SpeedA.maxVl", 15);
		speedABannable = getOrSetDefault("SpeedA.bannable", true);
		speedAPunish = getOrSetDefault("SpeedA.punish", "ban %player%");

		speedBEnabled = getOrSetDefault("SpeedB.enabled", true);
		speedBMaxVL = getOrSetDefault("SpeedB.maxVl", 15);
		speedBBannable = getOrSetDefault("SpeedB.bannable", true);
		speedBPunish = getOrSetDefault("SpeedB.punish", "ban %player%");

		//SCAFFOLD
		scaffoldAEnabled = getOrSetDefault("ScaffoldA.enabled", true);
		scaffoldAMaxVL = getOrSetDefault("ScaffoldA.maxVl", 15);
		scaffoldABannable = getOrSetDefault("ScaffoldA.bannable", true);
		scaffoldAPunish = getOrSetDefault("ScaffoldA.punish", "ban %player%");

		scaffoldBEnabled = getOrSetDefault("ScaffoldB.enabled", true);
		scaffoldBMaxVL = getOrSetDefault("ScaffoldB.maxVl", 15);
		scaffoldBBannable = getOrSetDefault("ScaffoldB.bannable", true);
		scaffoldBPunish = getOrSetDefault("ScaffoldB.punish", "ban %player%");

		//JESUS
		jesusAEnabled = getOrSetDefault("JesusA.enabled", true);
		jesusAMaxVL = getOrSetDefault("JesusA.maxVl", 15);
		jesusABannable = getOrSetDefault("JesusA.bannable", true);
		jesusAPunish = getOrSetDefault("JesusA.punish", "ban %player%");

		jesusBEnabled = getOrSetDefault("JesusB.enabled", true);
		jesusBMaxVL = getOrSetDefault("JesusB.maxVl", 15);
		jesusBBannable = getOrSetDefault("JesusB.bannable", true);
		jesusBPunish = getOrSetDefault("JesusB.punish", "ban %player%");

		//HIGHJUMP
		highJumpAEnabled = getOrSetDefault("ScaffoldA.enabled", true);
		highJumpAMaxVL = getOrSetDefault("ScaffoldA.maxVl", 15);
		highJumpABannable = getOrSetDefault("ScaffoldA.bannable", true);
		highJumpAPunish = getOrSetDefault("ScaffoldA.punish", "ban %player%");

		//KILLAURA
		killAuraAEnabled = getOrSetDefault("KillAuraA.enabled", true);
		killAuraAMaxVL = getOrSetDefault("KillAuraA.maxVl", 15);
		killAuraABannable = getOrSetDefault("KillAuraA.bannable", true);
		killAuraAPunish = getOrSetDefault("KillAuraA.punish", "ban %player%");

		killAuraBEnabled = getOrSetDefault("KillAuraB.enabled", true);
		killAuraBMaxVL = getOrSetDefault("KillAuraB.maxVl", 15);
		killAuraBBannable = getOrSetDefault("KillAuraB.bannable", true);
		killAuraBPunish = getOrSetDefault("KillAuraB.punish", "ban %player%");

		killAuraCEnabled = getOrSetDefault("KillAuraC.enabled", true);
		killAuraCMaxVL = getOrSetDefault("KillAuraC.maxVl", 15);
		killAuraCBannable = getOrSetDefault("KillAuraC.bannable", true);
		killAuraCPunish = getOrSetDefault("KillAuraC.punish", "ban %player%");

		killAuraDEnabled = getOrSetDefault("KillAuraD.enabled", true);
		killAuraDMaxVL = getOrSetDefault("KillAuraD.maxVl", 15);
		killAuraDBannable = getOrSetDefault("KillAuraD.bannable", true);
		killAuraDPunish = getOrSetDefault("KillAuraD.punish", "ban %player%");

		killAuraEEnabled = getOrSetDefault("KillAuraE.enabled", true);
		killAuraEMaxVL = getOrSetDefault("KillAuraE.maxVl", 15);
		killAuraEBannable = getOrSetDefault("KillAuraE.bannable", true);
		killAuraEPunish = getOrSetDefault("KillAuraE.punish", "ban %player%");

		//CRITICALS
		criticalsAEnabled = getOrSetDefault("CriticalsA.enabled", true);
		criticalsAMaxVL = getOrSetDefault("CriticalsA.maxVl", 15);
		criticalsABannable = getOrSetDefault("CriticalsA.bannable", true);
		criticalsAPunish = getOrSetDefault("CriticalsA.punish", "ban %player%");

		criticalsBEnabled = getOrSetDefault("CriticalsB.enabled", true);
		criticalsBMaxVL = getOrSetDefault("CriticalsB.maxVl", 15);
		criticalsBBannable = getOrSetDefault("CriticalsB.bannable", true);
		criticalsBPunish = getOrSetDefault("CriticalsB.punish", "ban %player%");

		//REACH
		reachAEnabled = getOrSetDefault("ReachA.enabled", true);
		reachAMaxVL = getOrSetDefault("ReachA.maxVl", 15);
		reachABannable = getOrSetDefault("ReachA.bannable", true);
		reachAPunish = getOrSetDefault("ReachA.punish", "ban %player%");

		//FASTBOW
		fastBowAEnabled = getOrSetDefault("FastBowA.enabled", true);
		fastBowAMaxVL = getOrSetDefault("FastBowA.maxVl", 15);
		fastBowABannable = getOrSetDefault("FastBowA.bannable", true);
		fastBowAPunish = getOrSetDefault("FastBowA.punish", "ban %player%");

		fastBowBEnabled = getOrSetDefault("FastBowB.enabled", true);
		fastBowBMaxVL = getOrSetDefault("FastBowB.maxVl", 15);
		fastBowBBannable = getOrSetDefault("FastBowB.bannable", true);
		fastBowBPunish = getOrSetDefault("FastBowB.punish", "ban %player%");

		//BLINK
		blinkAEnabled = getOrSetDefault("BlinkA.enabled", true);
		blinkAMaxVL = getOrSetDefault("BlinkA.maxVl", 15);
		blinkABannable = getOrSetDefault("BlinkA.bannable", true);
		blinkAPunish = getOrSetDefault("BlinkA.punish", "ban %player%");

		//BADPACKETS
		badPacketsAEnabled = getOrSetDefault("BadPacketsA.enabled", true);
		badPacketsAMaxVL = getOrSetDefault("BadPacketsA.maxVl", 15);
		badPacketsABannable = getOrSetDefault("BadPacketsA.bannable", true);
		badPacketsAPunish = getOrSetDefault("BadPacketsA.punish", "ban %player%");

		badPacketsBEnabled = getOrSetDefault("BadPacketsB.enabled", true);
		badPacketsBMaxVL = getOrSetDefault("BadPacketsB.maxVl", 15);
		badPacketsBBannable = getOrSetDefault("BadPacketsB.bannable", true);
		badPacketsBPunish = getOrSetDefault("BadPacketsB.punish", "ban %player%");

		//GROUNDSPOOF
		groundSpoofAEnabled = getOrSetDefault("GroundSpoofA.enabled", true);
		groundSpoofAMaxVL = getOrSetDefault("GroundSpoofA.maxVl", 15);
		groundSpoofABannable = getOrSetDefault("GroundSpoofA.bannable", true);
		groundSpoofAPunish = getOrSetDefault("GroundSpoofA.punish", "ban %player%");

		groundSpoofBEnabled = getOrSetDefault("GroundSpoofB.enabled", true);
		groundSpoofBMaxVL = getOrSetDefault("GroundSpoofB.maxVl", 15);
		groundSpoofBBannable = getOrSetDefault("GroundSpoofB.bannable", true);
		groundSpoofBPunish = getOrSetDefault("GroundSpoofB.punish", "ban %player%");

		//TIMER
		timerAEnabled = getOrSetDefault("TimerA.enabled", true);
		timerAMaxVL = getOrSetDefault("TimerA.maxVl", 15);
		timerABannable = getOrSetDefault("TimerA.bannable", true);
		timerAPunish = getOrSetDefault("TimerA.punish", "ban %player%");

		timerBEnabled = getOrSetDefault("TimerB.enabled", true);
		timerBMaxVL = getOrSetDefault("TimerB.maxVl", 15);
		timerBBannable = getOrSetDefault("TimerB.bannable", true);
		timerBPunish = getOrSetDefault("TimerB.punish", "ban %player%");

		//VELOCITY
		velocityAEnabled = getOrSetDefault("VelocityA.enabled", true);
		velocityAMaxVL = getOrSetDefault("VelocityA.maxVl", 15);
		velocityABannable = getOrSetDefault("VelocityA.bannable", true);
		velocityAPunish = getOrSetDefault("VelocityA.punish", "ban %player%");

		velocityBEnabled = getOrSetDefault("VelocityB.enabled", true);
		velocityBMaxVL = getOrSetDefault("VelocityB.maxVl", 15);
		velocityBBannable = getOrSetDefault("VelocityB.bannable", true);
		velocityBPunish = getOrSetDefault("VelocityB.punish", "ban %player%");
	}

	@Override
	public SerializedMap serialize() {
		return SerializedMap.ofArray(
				//FLY
				"FlightA.enabled", flightAEnabled,
				"FlightA.maxVl", flightAMaxVL,
				"FlightA.bannable", flightABannable,
				"FlightA.punish", flightAPunish,

				"FlightB.enabled", flightBEnabled,
				"FlightB.maxVl", flightBMaxVL,
				"FlightB.bannable", flightBBannable,
				"FlightB.punish", flightBPunish,

				"FlightC.enabled", flightCEnabled,
				"FlightC.maxVl", flightCMaxVL,
				"FlightC.bannable", flightCBannable,
				"FlightC.punish", flightCPunish,

				"FlightD.enabled", flightDEnabled,
				"FlightD.maxVl", flightDMaxVL,
				"FlightD.bannable", flightDBannable,
				"FlightD.punish", flightDPunish,

				"FlightE.enabled", flightEEnabled,
				"FlightE.maxVl", flightEMaxVL,
				"FlightE.bannable", flightEBannable,
				"FlightE.punish", flightEPunish,

				"FlightF.enabled", flightFEnabled,
				"FlightF.maxVl", flightFMaxVL,
				"FlightF.bannable", flightFBannable,
				"FlightF.punish", flightFPunish,

				//SPEED
				"SpeedA.enabled", speedAEnabled,
				"SpeedA.maxVl", speedAMaxVL,
				"SpeedA.bannable", speedABannable,
				"SpeedA.punish", speedAPunish,

				"SpeedB.enabled", speedBEnabled,
				"SpeedB.maxVl", speedBMaxVL,
				"SpeedB.bannable", speedBBannable,
				"SpeedB.punish", speedBPunish,

				//SCAFFOLD
				"ScaffoldA.enabled", scaffoldAEnabled,
				"ScaffoldA.maxVl", scaffoldAMaxVL,
				"ScaffoldA.bannable", scaffoldABannable,
				"ScaffoldA.punish", scaffoldAPunish,

				"ScaffoldB.enabled", scaffoldBEnabled,
				"ScaffoldB.maxVl", scaffoldBMaxVL,
				"ScaffoldB.bannable", scaffoldBBannable,
				"ScaffoldB.punish", scaffoldBPunish,

				//JESUS
				"JesusA.enabled", jesusAEnabled,
				"JesusA.maxVl", jesusAMaxVL,
				"JesusA.bannable", jesusABannable,
				"JesusA.punish", jesusAPunish,

				"JesusB.enabled", jesusBEnabled,
				"JesusB.maxVl", jesusBMaxVL,
				"JesusB.bannable", jesusBBannable,
				"JesusB.punish", jesusBPunish,

				//HIGHJUMP
				"HighJumpA.enabled", highJumpAEnabled,
				"HighJumpA.maxVl", highJumpAMaxVL,
				"HighJumpA.bannable", highJumpABannable,
				"HighJumpA.punish", highJumpAPunish,

				//KILLAURA
				"KillAuraA.enabled", killAuraAEnabled,
				"KillAuraA.maxVl", killAuraAMaxVL,
				"KillAuraA.bannable", killAuraABannable,
				"KillAuraA.punish", killAuraAPunish,

				"KillAuraB.enabled", killAuraBEnabled,
				"KillAuraB.maxVl", killAuraBMaxVL,
				"KillAuraB.bannable", killAuraBBannable,
				"KillAuraB.punish", killAuraBPunish,

				"KillAuraC.enabled", killAuraCEnabled,
				"KillAuraC.maxVl", killAuraCMaxVL,
				"KillAuraC.bannable", killAuraCBannable,
				"KillAuraC.punish", killAuraCPunish,

				"KillAuraD.enabled", killAuraDEnabled,
				"KillAuraD.maxVl", killAuraDMaxVL,
				"KillAuraD.bannable", killAuraDBannable,
				"KillAuraD.punish", killAuraDPunish,

				"KillAuraE.enabled", killAuraEEnabled,
				"KillAuraE.maxVl", killAuraEMaxVL,
				"KillAuraE.bannable", killAuraEBannable,
				"KillAuraE.punish", killAuraEPunish,

				//CRITICALS
				"CriticalsA.enabled", criticalsAEnabled,
				"CriticalsA.maxVl", criticalsAMaxVL,
				"CriticalsA.bannable", criticalsABannable,
				"CriticalsA.punish", criticalsAPunish,

				"CriticalsB.enabled", criticalsBEnabled,
				"CriticalsB.maxVl", criticalsBMaxVL,
				"CriticalsB.bannable", criticalsBBannable,
				"CriticalsB.punish", criticalsBPunish,

				//REACH
				"ReachA.enabled", reachABannable,
				"ReachA.maxVl", reachAMaxVL,
				"ReachA.bannable", reachABannable,
				"ReachA.punish", reachAPunish,

				//FASTBOW
				"FastBowA.enabled", fastBowAEnabled,
				"FastBowA.maxVl", fastBowAMaxVL,
				"FastBowA.bannable", fastBowABannable,
				"FastBowA.punish", fastBowAPunish,

				"FastBowB.enabled", fastBowBEnabled,
				"FastBowB.maxVl", fastBowBMaxVL,
				"FastBowB.bannable", fastBowBBannable,
				"FastBowB.punish", fastBowBPunish,

				//BLINK
				"BlinkA.enabled", blinkABannable,
				"BlinkA.maxVl", blinkAMaxVL,
				"BlinkA.bannable", blinkABannable,
				"BlinkA.punish", blinkAPunish,

				//BADPACKETS
				"BadPacketsA.enabled", badPacketsAEnabled,
				"BadPacketsA.maxVl", badPacketsAMaxVL,
				"BadPacketsA.bannable", badPacketsABannable,
				"BadPacketsA.punish", badPacketsAPunish,

				"BadPacketsB.enabled", badPacketsBEnabled,
				"BadPacketsB.maxVl", badPacketsBMaxVL,
				"BadPacketsB.bannable", badPacketsBBannable,
				"BadPacketsB.punish", badPacketsBPunish,

				//GROUNDSPOOF
				"GroundSpoofA.enabled", groundSpoofAEnabled,
				"GroundSpoofA.maxVl", groundSpoofAMaxVL,
				"GroundSpoofA.bannable", groundSpoofABannable,
				"GroundSpoofA.punish", groundSpoofAPunish,

				"GroundSpoofB.enabled", groundSpoofBEnabled,
				"GroundSpoofB.maxVl", groundSpoofBMaxVL,
				"GroundSpoofB.bannable", groundSpoofBBannable,
				"GroundSpoofB.punish", groundSpoofBPunish,

				//TIMER
				"TimerA.enabled", timerAEnabled,
				"TimerA.maxVl", timerAMaxVL,
				"TimerA.bannable", timerABannable,
				"TimerA.punish", timerAPunish,

				"TimerB.enabled", timerBEnabled,
				"TimerB.maxVl", timerBMaxVL,
				"TimerB.bannable", timerBBannable,
				"TimerB.punish", timerBPunish,

				//VELOCITY
				"VelocityA.enabled", velocityAEnabled,
				"VelocityA.maxVl", velocityAMaxVL,
				"VelocityA.bannable", velocityABannable,
				"VelocityA.punish", velocityAPunish,

				"VelocityB.enabled", velocityBEnabled,
				"VelocityB.maxVl", velocityBMaxVL,
				"VelocityB.bannable", velocityBBannable,
				"VelocityB.punish", velocityBPunish

		);
	}
}