package net.warden.spigot.utils;

import lombok.AccessLevel;
import lombok.Getter;
import org.mineacademy.fo.collection.SerializedMap;
import org.mineacademy.fo.settings.YamlConfig;

import java.util.List;


@Getter(AccessLevel.PUBLIC)
public class ConfigManager extends YamlConfig {
	@Getter
	public static final ConfigManager instance = new ConfigManager();
	//FLY
	private boolean flightAEnabled;
	private int flightAMaxVL;
	private boolean flightABannable;
	private List<String> flightAPunish;

	private boolean flightBEnabled;
	private int flightBMaxVL;
	private boolean flightBBannable;
	private List<String> flightBPunish;

	private boolean flightCEnabled;
	private int flightCMaxVL;
	private boolean flightCBannable;
	private List<String> flightCPunish;

	private boolean flightDEnabled;
	private int flightDMaxVL;
	private boolean flightDBannable;
	private List<String> flightDPunish;

	private boolean flightEEnabled;
	private int flightEMaxVL;
	private boolean flightEBannable;
	private List<String> flightEPunish;

	private boolean flightFEnabled;
	private int flightFMaxVL;
	private boolean flightFBannable;
	private List<String> flightFPunish;

	private boolean flightGEnabled;
	private int flightGMaxVL;
	private boolean flightGBannable;
	private List<String> flightGPunish;

	private boolean flightHEnabled;
	private int flightHMaxVL;
	private boolean flightHBannable;
	private List<String> flightHPunish;

	//SPEED
	private boolean speedAEnabled;
	private int speedAMaxVL;
	private boolean speedABannable;
	private List<String> speedAPunish;

	private boolean speedBEnabled;
	private int speedBMaxVL;
	private boolean speedBBannable;
	private List<String> speedBPunish;

	//SCAFFOLD
	private boolean scaffoldAEnabled;
	private int scaffoldAMaxVL;
	private boolean scaffoldABannable;
	private List<String> scaffoldAPunish;

	private boolean scaffoldBEnabled;
	private int scaffoldBMaxVL;
	private boolean scaffoldBBannable;
	private List<String> scaffoldBPunish;

	//JESUS
	private boolean jesusAEnabled;
	private int jesusAMaxVL;
	private boolean jesusABannable;
	private List<String> jesusAPunish;

	private boolean jesusBEnabled;
	private int jesusBMaxVL;
	private boolean jesusBBannable;
	private List<String> jesusBPunish;

	//HIGHJUMP
	private boolean highJumpAEnabled;
	private int highJumpAMaxVL;
	private boolean highJumpABannable;
	private List<String> highJumpAPunish;

	//KILLAURA
	private boolean killAuraAEnabled;
	private int killAuraAMaxVL;
	private boolean killAuraABannable;
	private List<String> killAuraAPunish;

	private boolean killAuraBEnabled;
	private int killAuraBMaxVL;
	private boolean killAuraBBannable;
	private List<String> killAuraBPunish;

	private boolean killAuraCEnabled;
	private int killAuraCMaxVL;
	private boolean killAuraCBannable;
	private List<String> killAuraCPunish;

	private boolean killAuraDEnabled;
	private int killAuraDMaxVL;
	private boolean killAuraDBannable;
	private List<String> killAuraDPunish;

	private boolean killAuraEEnabled;
	private int killAuraEMaxVL;
	private boolean killAuraEBannable;
	private List<String> killAuraEPunish;

	private boolean killAuraFEnabled;
	private int killAuraFMaxVL;
	private boolean killAuraFBannable;
	private List<String> killAuraFPunish;

	private boolean killAuraGEnabled;
	private int killAuraGMaxVL;
	private boolean killAuraGBannable;
	private List<String> killAuraGPunish;

	private boolean killAuraHEnabled;
	private int killAuraHMaxVL;
	private boolean killAuraHBannable;
	private List<String> killAuraHPunish;

	private boolean killAuraIEnabled;
	private int killAuraIMaxVL;
	private boolean killAuraIBannable;
	private List<String> killAuraIPunish;

	private boolean killAuraJEnabled;
	private int killAuraJMaxVL;
	private boolean killAuraJBannable;
	private List<String> killAuraJPunish;

	//CRITICALS
	private boolean criticalsAEnabled;
	private int criticalsAMaxVL;
	private boolean criticalsABannable;
	private List<String> criticalsAPunish;

	private boolean criticalsBEnabled;
	private int criticalsBMaxVL;
	private boolean criticalsBBannable;
	private List<String> criticalsBPunish;

	//REACH
	private boolean reachAEnabled;
	private int reachAMaxVL;
	private boolean reachABannable;
	private List<String> reachAPunish;

	//FASTBOW
	private boolean fastBowAEnabled;
	private int fastBowAMaxVL;
	private boolean fastBowABannable;
	private List<String> fastBowAPunish;

	private boolean fastBowBEnabled;
	private int fastBowBMaxVL;
	private boolean fastBowBBannable;
	private List<String> fastBowBPunish;

	//BLINK
	private boolean blinkAEnabled;
	private int blinkAMaxVL;
	private boolean blinkABannable;
	private List<String> blinkAPunish;

	//BADPACKETS
	private boolean badPacketsAEnabled;
	private int badPacketsAMaxVL;
	private boolean badPacketsABannable;
	private List<String> badPacketsAPunish;

	private boolean badPacketsBEnabled;
	private int badPacketsBMaxVL;
	private boolean badPacketsBBannable;
	private List<String> badPacketsBPunish;

	private boolean badPacketsCEnabled;
	private int badPacketsCMaxVL;
	private boolean badPacketsCBannable;
	private List<String> badPacketsCPunish;

	private boolean badPacketsDEnabled;
	private int badPacketsDMaxVL;
	private boolean badPacketsDBannable;
	private List<String> badPacketsDPunish;

	//GROUNDSPOOF
	private boolean groundSpoofAEnabled;
	private int groundSpoofAMaxVL;
	private boolean groundSpoofABannable;
	private List<String> groundSpoofAPunish;

	private boolean groundSpoofBEnabled;
	private int groundSpoofBMaxVL;
	private boolean groundSpoofBBannable;
	private List<String> groundSpoofBPunish;

	//TIMER
	private boolean timerAEnabled;
	private int timerAMaxVL;
	private boolean timerABannable;
	private List<String> timerAPunish;

	private boolean timerBEnabled;
	private int timerBMaxVL;
	private boolean timerBBannable;
	private List<String> timerBPunish;

	//VELOCITY
	private boolean velocityAEnabled;
	private int velocityAMaxVL;
	private boolean velocityABannable;
	private List<String> velocityAPunish;

	private boolean velocityBEnabled;
	private int velocityBMaxVL;
	private boolean velocityBBannable;
	private List<String> velocityBPunish;

	//INVALIDMOVEMENT
	private boolean invalidMovementAEnabled;
	private int invalidMovementAMaxVL;
	private boolean invalidMovementABannable;
	private List<String> invalidMovementAPunish;

	private boolean invalidMovementBEnabled;
	private int invalidMovementBMaxVL;
	private boolean invalidMovementBBannable;
	private List<String> invalidMovementBPunish;

	private boolean invalidMovementCEnabled;
	private int invalidMovementCMaxVL;
	private boolean invalidMovementCBannable;
	private List<String> invalidMovementCPunish;

	//PINGSPOOF
	private boolean pingSpoofAEnabled;
	private int pingSpoofAMaxVL;
	private boolean pingSpoofABannable;
	private List<String> pingSpoofAPunish;


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
		flightAPunish = getStringList("FlightA.punish");

		flightBEnabled = getOrSetDefault("FlightB.enabled", true);
		flightBMaxVL = getOrSetDefault("FlightB.maxVl", 15);
		flightBBannable = getOrSetDefault("FlightB.bannable", true);
		flightBPunish = getStringList("FlightB.punish");

		flightCEnabled = getOrSetDefault("FlightC.enabled", true);
		flightCMaxVL = getOrSetDefault("FlightC.maxVl", 15);
		flightCBannable = getOrSetDefault("FlightC.bannable", true);
		flightCPunish = getStringList("FlightC.punish");

		flightDEnabled = getOrSetDefault("FlightD.enabled", true);
		flightDMaxVL = getOrSetDefault("FlightD.maxVl", 15);
		flightDBannable = getOrSetDefault("FlightD.bannable", true);
		flightDPunish = getStringList("FlightD.punish");

		flightEEnabled = getOrSetDefault("FlightE.enabled", true);
		flightEMaxVL = getOrSetDefault("FlightE.maxVl", 15);
		flightEBannable = getOrSetDefault("FlightE.bannable", true);
		flightEPunish = getStringList("FlightE.punish");

		flightFEnabled = getOrSetDefault("FlightF.enabled", true);
		flightFMaxVL = getOrSetDefault("FlightF.maxVl", 15);
		flightFBannable = getOrSetDefault("FlightF.bannable", true);
		flightFPunish = getStringList("FlightF.punish");

		flightGEnabled = getOrSetDefault("FlightG.enabled", true);
		flightGMaxVL = getOrSetDefault("FlightG.maxVl", 15);
		flightGBannable = getOrSetDefault("FlightG.bannable", true);
		flightGPunish = getStringList("FlightG.punish");

		flightHEnabled = getOrSetDefault("FlightH.enabled", true);
		flightHMaxVL = getOrSetDefault("FlightH.maxVl", 15);
		flightHBannable = getOrSetDefault("FlightH.bannable", true);
		flightHPunish = getStringList("FlightH.punish");

		//SPEED
		speedAEnabled = getOrSetDefault("SpeedA.enabled", true);
		speedAMaxVL = getOrSetDefault("SpeedA.maxVl", 15);
		speedABannable = getOrSetDefault("SpeedA.bannable", true);
		speedAPunish = getStringList("SpeedA.punish");

		speedBEnabled = getOrSetDefault("SpeedB.enabled", true);
		speedBMaxVL = getOrSetDefault("SpeedB.maxVl", 15);
		speedBBannable = getOrSetDefault("SpeedB.bannable", true);
		speedBPunish = getStringList("SpeedB.punish");

		//SCAFFOLD
		scaffoldAEnabled = getOrSetDefault("ScaffoldA.enabled", true);
		scaffoldAMaxVL = getOrSetDefault("ScaffoldA.maxVl", 15);
		scaffoldABannable = getOrSetDefault("ScaffoldA.bannable", true);
		scaffoldAPunish = getStringList("ScaffoldA.punish");

		scaffoldBEnabled = getOrSetDefault("ScaffoldB.enabled", true);
		scaffoldBMaxVL = getOrSetDefault("ScaffoldB.maxVl", 15);
		scaffoldBBannable = getOrSetDefault("ScaffoldB.bannable", true);
		scaffoldBPunish = getStringList("ScaffoldB.punish");

		//JESUS
		jesusAEnabled = getOrSetDefault("JesusA.enabled", true);
		jesusAMaxVL = getOrSetDefault("JesusA.maxVl", 15);
		jesusABannable = getOrSetDefault("JesusA.bannable", true);
		jesusAPunish = getStringList("JesusA.punish");

		jesusBEnabled = getOrSetDefault("JesusB.enabled", true);
		jesusBMaxVL = getOrSetDefault("JesusB.maxVl", 15);
		jesusBBannable = getOrSetDefault("JesusB.bannable", true);
		jesusBPunish = getStringList("JesusB.punish");

		//HIGHJUMP
		highJumpAEnabled = getOrSetDefault("ScaffoldA.enabled", true);
		highJumpAMaxVL = getOrSetDefault("ScaffoldA.maxVl", 15);
		highJumpABannable = getOrSetDefault("ScaffoldA.bannable", true);
		highJumpAPunish = getStringList("ScaffoldA.punish");

		//KILLAURA
		killAuraAEnabled = getOrSetDefault("KillAuraA.enabled", true);
		killAuraAMaxVL = getOrSetDefault("KillAuraA.maxVl", 15);
		killAuraABannable = getOrSetDefault("KillAuraA.bannable", true);
		killAuraAPunish = getStringList("KillAuraA.punish");

		killAuraBEnabled = getOrSetDefault("KillAuraB.enabled", true);
		killAuraBMaxVL = getOrSetDefault("KillAuraB.maxVl", 15);
		killAuraBBannable = getOrSetDefault("KillAuraB.bannable", true);
		killAuraBPunish = getStringList("KillAuraB.punish");

		killAuraCEnabled = getOrSetDefault("KillAuraC.enabled", true);
		killAuraCMaxVL = getOrSetDefault("KillAuraC.maxVl", 15);
		killAuraCBannable = getOrSetDefault("KillAuraC.bannable", true);
		killAuraCPunish = getStringList("KillAuraC.punish");

		killAuraDEnabled = getOrSetDefault("KillAuraD.enabled", true);
		killAuraDMaxVL = getOrSetDefault("KillAuraD.maxVl", 15);
		killAuraDBannable = getOrSetDefault("KillAuraD.bannable", true);
		killAuraDPunish = getStringList("KillAuraD.punish");

		killAuraEEnabled = getOrSetDefault("KillAuraE.enabled", true);
		killAuraEMaxVL = getOrSetDefault("KillAuraE.maxVl", 15);
		killAuraEBannable = getOrSetDefault("KillAuraE.bannable", true);
		killAuraEPunish = getStringList("KillAuraE.punish");

		killAuraFEnabled = getOrSetDefault("KillAuraF.enabled", true);
		killAuraFMaxVL = getOrSetDefault("KillAuraF.maxVl", 15);
		killAuraFBannable = getOrSetDefault("KillAuraF.bannable", true);
		killAuraFPunish = getStringList("KillAuraF.punish");

		killAuraGEnabled = getOrSetDefault("KillAuraG.enabled", true);
		killAuraGMaxVL = getOrSetDefault("KillAuraG.maxVl", 15);
		killAuraGBannable = getOrSetDefault("KillAuraG.bannable", true);
		killAuraGPunish = getStringList("KillAuraG.punish");

		killAuraHEnabled = getOrSetDefault("KillAuraH.enabled", true);
		killAuraHMaxVL = getOrSetDefault("KillAuraH.maxVl", 15);
		killAuraHBannable = getOrSetDefault("KillAuraH.bannable", true);
		killAuraHPunish = getStringList("KillAuraH.punish");

		killAuraIEnabled = getOrSetDefault("KillAuraI.enabled", true);
		killAuraIMaxVL = getOrSetDefault("KillAuraI.maxVl", 15);
		killAuraIBannable = getOrSetDefault("KillAuraI.bannable", true);
		killAuraIPunish = getStringList("KillAuraI.punish");

		killAuraJEnabled = getOrSetDefault("KillAuraJ.enabled", true);
		killAuraJMaxVL = getOrSetDefault("KillAuraJ.maxVl", 15);
		killAuraJBannable = getOrSetDefault("KillAuraJ.bannable", true);
		killAuraJPunish = getStringList("KillAuraJ.punish");

		//CRITICALS
		criticalsAEnabled = getOrSetDefault("CriticalsA.enabled", true);
		criticalsAMaxVL = getOrSetDefault("CriticalsA.maxVl", 15);
		criticalsABannable = getOrSetDefault("CriticalsA.bannable", true);
		criticalsAPunish = getStringList("CriticalsA.punish");

		criticalsBEnabled = getOrSetDefault("CriticalsB.enabled", true);
		criticalsBMaxVL = getOrSetDefault("CriticalsB.maxVl", 15);
		criticalsBBannable = getOrSetDefault("CriticalsB.bannable", true);
		criticalsBPunish = getStringList("CriticalsB.punish");

		//REACH
		reachAEnabled = getOrSetDefault("ReachA.enabled", true);
		reachAMaxVL = getOrSetDefault("ReachA.maxVl", 15);
		reachABannable = getOrSetDefault("ReachA.bannable", true);
		reachAPunish = getStringList("ReachA.punish");

		//FASTBOW
		fastBowAEnabled = getOrSetDefault("FastBowA.enabled", true);
		fastBowAMaxVL = getOrSetDefault("FastBowA.maxVl", 15);
		fastBowABannable = getOrSetDefault("FastBowA.bannable", true);
		fastBowAPunish = getStringList("FastBowA.punish");

		fastBowBEnabled = getOrSetDefault("FastBowB.enabled", true);
		fastBowBMaxVL = getOrSetDefault("FastBowB.maxVl", 15);
		fastBowBBannable = getOrSetDefault("FastBowB.bannable", true);
		fastBowBPunish = getStringList("FastBowB.punish");

		//BLINK
		blinkAEnabled = getOrSetDefault("BlinkA.enabled", true);
		blinkAMaxVL = getOrSetDefault("BlinkA.maxVl", 15);
		blinkABannable = getOrSetDefault("BlinkA.bannable", true);
		blinkAPunish = getStringList("BlinkA.punish");

		//BADPACKETS
		badPacketsAEnabled = getOrSetDefault("BadPacketsA.enabled", true);
		badPacketsAMaxVL = getOrSetDefault("BadPacketsA.maxVl", 15);
		badPacketsABannable = getOrSetDefault("BadPacketsA.bannable", true);
		badPacketsAPunish = getStringList("BadPacketsA.punish");

		badPacketsBEnabled = getOrSetDefault("BadPacketsB.enabled", true);
		badPacketsBMaxVL = getOrSetDefault("BadPacketsB.maxVl", 15);
		badPacketsBBannable = getOrSetDefault("BadPacketsB.bannable", true);
		badPacketsBPunish = getStringList("BadPacketsB.punish");

		badPacketsCEnabled = getOrSetDefault("BadPacketsC.enabled", true);
		badPacketsCMaxVL = getOrSetDefault("BadPacketsC.maxVl", 15);
		badPacketsCBannable = getOrSetDefault("BadPacketsC.bannable", true);
		badPacketsCPunish = getStringList("BadPacketsC.punish");

		badPacketsDEnabled = getOrSetDefault("BadPacketsD.enabled", true);
		badPacketsDMaxVL = getOrSetDefault("BadPacketsD.maxVl", 15);
		badPacketsDBannable = getOrSetDefault("BadPacketsD.bannable", true);
		badPacketsDPunish = getStringList("BadPacketsD.punish");

		//GROUNDSPOOF
		groundSpoofAEnabled = getOrSetDefault("GroundSpoofA.enabled", true);
		groundSpoofAMaxVL = getOrSetDefault("GroundSpoofA.maxVl", 15);
		groundSpoofABannable = getOrSetDefault("GroundSpoofA.bannable", true);
		groundSpoofAPunish = getStringList("GroundSpoofA.punish");

		groundSpoofBEnabled = getOrSetDefault("GroundSpoofB.enabled", true);
		groundSpoofBMaxVL = getOrSetDefault("GroundSpoofB.maxVl", 15);
		groundSpoofBBannable = getOrSetDefault("GroundSpoofB.bannable", true);
		groundSpoofBPunish = getStringList("GroundSpoofB.punish");

		//TIMER
		timerAEnabled = getOrSetDefault("TimerA.enabled", true);
		timerAMaxVL = getOrSetDefault("TimerA.maxVl", 15);
		timerABannable = getOrSetDefault("TimerA.bannable", true);
		timerAPunish = getStringList("TimerA.punish");

		timerBEnabled = getOrSetDefault("TimerB.enabled", true);
		timerBMaxVL = getOrSetDefault("TimerB.maxVl", 15);
		timerBBannable = getOrSetDefault("TimerB.bannable", true);
		timerBPunish = getStringList("TimerB.punish");

		//VELOCITY
		velocityAEnabled = getOrSetDefault("VelocityA.enabled", true);
		velocityAMaxVL = getOrSetDefault("VelocityA.maxVl", 15);
		velocityABannable = getOrSetDefault("VelocityA.bannable", true);
		velocityAPunish = getStringList("VelocityA.punish");

		velocityBEnabled = getOrSetDefault("VelocityB.enabled", true);
		velocityBMaxVL = getOrSetDefault("VelocityB.maxVl", 15);
		velocityBBannable = getOrSetDefault("VelocityB.bannable", true);
		velocityBPunish = getStringList("VelocityB.punish");

		//INVALIDMOVEMENT
		invalidMovementAEnabled = getOrSetDefault("InvalidMovementA.enabled", true);
		invalidMovementAMaxVL = getOrSetDefault("InvalidMovementA.maxVl", 15);
		invalidMovementABannable = getOrSetDefault("InvalidMovementA.bannable", true);
		invalidMovementAPunish = getStringList("InvalidMovementA.punish");

		invalidMovementBEnabled = getOrSetDefault("InvalidMovementB.enabled", true);
		invalidMovementBMaxVL = getOrSetDefault("InvalidMovementB.maxVl", 15);
		invalidMovementBBannable = getOrSetDefault("InvalidMovementB.bannable", true);
		invalidMovementBPunish = getStringList("InvalidMovementB.punish");

		invalidMovementCEnabled = getOrSetDefault("InvalidMovementC.enabled", true);
		invalidMovementCMaxVL = getOrSetDefault("InvalidMovementC.maxVl", 15);
		invalidMovementCBannable = getOrSetDefault("InvalidMovementC.bannable", true);
		invalidMovementCPunish = getStringList("InvalidMovementC.punish");

		//PINGSPOOF
		pingSpoofAEnabled = getOrSetDefault("PingSpoofA.enabled", true);
		pingSpoofAMaxVL = getOrSetDefault("PingSpoofA.maxVl", 15);
		pingSpoofABannable = getOrSetDefault("PingSpoofA.bannable", true);
		pingSpoofAPunish = getStringList("PingSpoofA.punish");
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

				"FlightG.enabled", flightGEnabled,
				"FlightG.maxVl", flightGMaxVL,
				"FlightG.bannable", flightGBannable,
				"FlightG.punish", flightGPunish,

				"FlightH.enabled", flightHEnabled,
				"FlightH.maxVl", flightHMaxVL,
				"FlightH.bannable", flightHBannable,
				"FlightH.punish", flightHPunish,

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

				"KillAuraF.enabled", killAuraFEnabled,
				"KillAuraF.maxVl", killAuraFMaxVL,
				"KillAuraF.bannable", killAuraFBannable,
				"KillAuraF.punish", killAuraFPunish,

				"KillAuraG.enabled", killAuraGEnabled,
				"KillAuraG.maxVl", killAuraGMaxVL,
				"KillAuraG.bannable", killAuraGBannable,
				"KillAuraG.punish", killAuraGPunish,

				"KillAuraH.enabled", killAuraHEnabled,
				"KillAuraH.maxVl", killAuraHMaxVL,
				"KillAuraH.bannable", killAuraHBannable,
				"KillAuraH.punish", killAuraHPunish,

				"KillAuraI.enabled", killAuraIEnabled,
				"KillAuraI.maxVl", killAuraIMaxVL,
				"KillAuraI.bannable", killAuraIBannable,
				"KillAuraI.punish", killAuraIPunish,

				"KillAuraJ.enabled", killAuraJEnabled,
				"KillAuraJ.maxVl", killAuraJMaxVL,
				"KillAuraJ.bannable", killAuraJBannable,
				"KillAuraJ.punish", killAuraJPunish,

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

				"BadPacketsC.enabled", badPacketsCEnabled,
				"BadPacketsC.maxVl", badPacketsCMaxVL,
				"BadPacketsC.bannable", badPacketsCBannable,
				"BadPacketsC.punish", badPacketsCPunish,

				"BadPacketsD.enabled", badPacketsDEnabled,
				"BadPacketsD.maxVl", badPacketsDMaxVL,
				"BadPacketsD.bannable", badPacketsDBannable,
				"BadPacketsD.punish", badPacketsDPunish,

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
				"VelocityB.punish", velocityBPunish,

				//INVALIDMOVEMENT
				"InvalidMovementA.enabled", invalidMovementAEnabled,
				"InvalidMovementA.maxVl", invalidMovementAMaxVL,
				"InvalidMovementA.bannable", invalidMovementABannable,
				"InvalidMovementA.punish", invalidMovementAPunish,

				"InvalidMovementB.enabled", invalidMovementBEnabled,
				"InvalidMovementB.maxVl", invalidMovementBMaxVL,
				"InvalidMovementB.bannable", invalidMovementBBannable,
				"InvalidMovementB.punish", invalidMovementBPunish,

				"InvalidMovementC.enabled", invalidMovementCEnabled,
				"InvalidMovementC.maxVl", invalidMovementCMaxVL,
				"InvalidMovementC.bannable", invalidMovementCBannable,
				"InvalidMovementC.punish", invalidMovementCPunish,

				//PINGSPOOF
				"PingSpoofA.enabled", pingSpoofAEnabled,
				"PingSpoofA.maxVl", pingSpoofAMaxVL,
				"PingSpoofA.bannable", pingSpoofABannable,
				"PingSpoofA.punish", pingSpoofAPunish

		);
	}

}