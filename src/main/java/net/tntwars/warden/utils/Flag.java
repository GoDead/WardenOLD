package net.tntwars.warden.utils;

import net.tntwars.warden.Main;
import net.tntwars.warden.check.api.Check;
import net.tntwars.warden.check.api.customevent.WardenFlagEvent;
import net.tntwars.warden.check.api.customevent.WardenPunishEvent;
import net.tntwars.warden.playerdata.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import static org.bukkit.Bukkit.getOnlinePlayers;

public class Flag {

	public static void flag(PlayerData data, Check check) {
		long time = System.currentTimeMillis() - data.getTimeSinceJoin();
		if (time < 1000) {
			return;
		}
		String name = check.getName();
		char type = check.getCheckType();
		int vl = data.getVLCount(check);
		Player player = data.getPlayer();
		WardenFlagEvent event = new WardenFlagEvent(player, name, type, vl);
		Bukkit.getPluginManager().callEvent(event);
		if (event.isCancelled()) {
			return;
		}
		punish(data, name, type, vl);
		getOnlinePlayers().stream().filter(staff -> staff.hasPermission("warden.alerts")).forEach(staff -> {
			if (data.alerts) {
				if (isMultipleof5(vl)) {
					staff.sendMessage("§8[§cWarden§8] §f" + data.getPlayer().getName() + " §7is suspected of " + (false ? "§d§o(exp) " : "§e") + name + " §7(Type " + type + ") §cx" + (int) vl);
				}
			}
		});
		data.incrementVL(check);
	}

	private static boolean isMultipleof5(float n) {
		if (n == 0)
			return false;
		while (n > 0)
			n = n - 5;

		if (n == 0)
			return true;

		return false;
	}

	public static void punish(PlayerData user, String cheat, char type, int vl) {
		if (user.getPlayer().hasPermission("warden.bypass")) return;
		if (cheat.contains("Flight")) {
			switch (type) {
				case 'A':
					if (ConfigManager.getInstance().getFlightAMaxVL() <= vl && ConfigManager.getInstance().isFlightABannable()) {
						WardenPunishEvent event = new WardenPunishEvent(user.getPlayer(), cheat, type, vl);
						Bukkit.getPluginManager().callEvent(event);
						if (event.isCancelled()) {
							return;
						}
						Bukkit.getScheduler().runTask(Main.getInstance(), () -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), ConfigManager.getInstance().getFlightAPunish().replace("%player%", user.getPlayer().getName())));
					}
					break;
				case 'B':
					if (ConfigManager.getInstance().getFlightBMaxVL() <= vl && ConfigManager.getInstance().isFlightBBannable()) {
						WardenPunishEvent event = new WardenPunishEvent(user.getPlayer(), cheat, type, vl);
						Bukkit.getPluginManager().callEvent(event);
						if (event.isCancelled()) {
							return;
						}
						Bukkit.getScheduler().runTask(Main.getInstance(), () -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), ConfigManager.getInstance().getFlightBPunish().replace("%player%", user.getPlayer().getName())));
					}
					break;
				case 'C':
					if (ConfigManager.getInstance().getFlightCMaxVL() <= vl && ConfigManager.getInstance().isFlightCBannable()) {
						WardenPunishEvent event = new WardenPunishEvent(user.getPlayer(), cheat, type, vl);
						Bukkit.getPluginManager().callEvent(event);
						if (event.isCancelled()) {
							return;
						}
						Bukkit.getScheduler().runTask(Main.getInstance(), () -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), ConfigManager.getInstance().getFlightCPunish().replace("%player%", user.getPlayer().getName())));
					}
					break;
				case 'D':
					if (ConfigManager.getInstance().getFlightDMaxVL() <= vl && ConfigManager.getInstance().isFlightDBannable()) {
						WardenPunishEvent event = new WardenPunishEvent(user.getPlayer(), cheat, type, vl);
						Bukkit.getPluginManager().callEvent(event);
						if (event.isCancelled()) {
							return;
						}
						Bukkit.getScheduler().runTask(Main.getInstance(), () -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), ConfigManager.getInstance().getFlightDPunish().replace("%player%", user.getPlayer().getName())));
					}
					break;
				case 'E':
					if (ConfigManager.getInstance().getFlightEMaxVL() <= vl && ConfigManager.getInstance().isFlightEBannable()) {
						WardenPunishEvent event = new WardenPunishEvent(user.getPlayer(), cheat, type, vl);
						Bukkit.getPluginManager().callEvent(event);
						if (event.isCancelled()) {
							return;
						}
						Bukkit.getScheduler().runTask(Main.getInstance(), () -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), ConfigManager.getInstance().getFlightEPunish().replace("%player%", user.getPlayer().getName())));
					}
					break;

				case 'F':
					if (ConfigManager.getInstance().getFlightFMaxVL() <= vl && ConfigManager.getInstance().isFlightFBannable()) {
						WardenPunishEvent event = new WardenPunishEvent(user.getPlayer(), cheat, type, vl);
						Bukkit.getPluginManager().callEvent(event);
						if (event.isCancelled()) {
							return;
						}
						Bukkit.getScheduler().runTask(Main.getInstance(), () -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), ConfigManager.getInstance().getFlightFPunish().replace("%player%", user.getPlayer().getName())));
					}
					break;
			}
		} else if (cheat.contains("Speed")) {
			switch (type) {
				case 'A':
					if (ConfigManager.getInstance().getSpeedAMaxVL() <= vl && ConfigManager.getInstance().isSpeedABannable()) {
						WardenPunishEvent event = new WardenPunishEvent(user.getPlayer(), cheat, type, vl);
						Bukkit.getPluginManager().callEvent(event);
						if (event.isCancelled()) {
							return;
						}
						Bukkit.getScheduler().runTask(Main.getInstance(), () -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), ConfigManager.getInstance().getSpeedAPunish().replace("%player%", user.getPlayer().getName())));
					}
					break;
				case 'B':
					if (ConfigManager.getInstance().getSpeedBMaxVL() <= vl && ConfigManager.getInstance().isSpeedBBannable()) {
						WardenPunishEvent event = new WardenPunishEvent(user.getPlayer(), cheat, type, vl);
						Bukkit.getPluginManager().callEvent(event);
						if (event.isCancelled()) {
							return;
						}
						Bukkit.getScheduler().runTask(Main.getInstance(), () -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), ConfigManager.getInstance().getSpeedBPunish().replace("%player%", user.getPlayer().getName())));
					}
					break;
			}
		} else if (cheat.contains("GroundSpoof")) {
			switch (type) {
				case 'A':
					if (ConfigManager.getInstance().getGroundSpoofAMaxVL() <= vl && ConfigManager.getInstance().isGroundSpoofABannable()) {
						WardenPunishEvent event = new WardenPunishEvent(user.getPlayer(), cheat, type, vl);
						Bukkit.getPluginManager().callEvent(event);
						if (event.isCancelled()) {
							return;
						}
						Bukkit.getScheduler().runTask(Main.getInstance(), () -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), ConfigManager.getInstance().getGroundSpoofAPunish().replace("%player%", user.getPlayer().getName())));
					}
					break;
				case 'B':
					if (ConfigManager.getInstance().getGroundSpoofBMaxVL() <= vl && ConfigManager.getInstance().isGroundSpoofBBannable()) {
						WardenPunishEvent event = new WardenPunishEvent(user.getPlayer(), cheat, type, vl);
						Bukkit.getPluginManager().callEvent(event);
						if (event.isCancelled()) {
							return;
						}
						Bukkit.getScheduler().runTask(Main.getInstance(), () -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), ConfigManager.getInstance().getGroundSpoofBPunish().replace("%player%", user.getPlayer().getName())));
					}
					break;
			}
		} else if (cheat.contains("Scaffold")) {
			switch (type) {
				case 'A':
					if (ConfigManager.getInstance().getScaffoldAMaxVL() <= vl && ConfigManager.getInstance().isScaffoldABannable()) {
						WardenPunishEvent event = new WardenPunishEvent(user.getPlayer(), cheat, type, vl);
						Bukkit.getPluginManager().callEvent(event);
						if (event.isCancelled()) {
							return;
						}
						Bukkit.getScheduler().runTask(Main.getInstance(), () -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), ConfigManager.getInstance().getScaffoldAPunish().replace("%player%", user.getPlayer().getName())));
					}
					break;
				case 'B':
					if (ConfigManager.getInstance().getScaffoldBMaxVL() <= vl && ConfigManager.getInstance().isScaffoldBBannable()) {
						WardenPunishEvent event = new WardenPunishEvent(user.getPlayer(), cheat, type, vl);
						Bukkit.getPluginManager().callEvent(event);
						if (event.isCancelled()) {
							return;
						}
						Bukkit.getScheduler().runTask(Main.getInstance(), () -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), ConfigManager.getInstance().getScaffoldBPunish().replace("%player%", user.getPlayer().getName())));
					}
					break;
			}
		} else if (cheat.contains("HighJump")) {
			switch (type) {
				case 'A':
					if (ConfigManager.getInstance().getHighJumpAMaxVL() <= vl && ConfigManager.getInstance().isHighJumpABannable()) {
						WardenPunishEvent event = new WardenPunishEvent(user.getPlayer(), cheat, type, vl);
						Bukkit.getPluginManager().callEvent(event);
						if (event.isCancelled()) {
							return;
						}
						Bukkit.getScheduler().runTask(Main.getInstance(), () -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), ConfigManager.getInstance().getHighJumpAPunish().replace("%player%", user.getPlayer().getName())));
					}
					break;
			}
		} else if (cheat.contains("KillAura")) {
			switch (type) {
				case 'A':
					if (ConfigManager.getInstance().getKillAuraAMaxVL() <= vl && ConfigManager.getInstance().isKillAuraABannable()) {
						WardenPunishEvent event = new WardenPunishEvent(user.getPlayer(), cheat, type, vl);
						Bukkit.getPluginManager().callEvent(event);
						if (event.isCancelled()) {
							return;
						}
						Bukkit.getScheduler().runTask(Main.getInstance(), () -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), ConfigManager.getInstance().getKillAuraAPunish().replace("%player%", user.getPlayer().getName())));
					}
					break;
				case 'B':
					if (ConfigManager.getInstance().getKillAuraBMaxVL() <= vl && ConfigManager.getInstance().isKillAuraBBannable()) {
						WardenPunishEvent event = new WardenPunishEvent(user.getPlayer(), cheat, type, vl);
						Bukkit.getPluginManager().callEvent(event);
						if (event.isCancelled()) {
							return;
						}
						Bukkit.getScheduler().runTask(Main.getInstance(), () -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), ConfigManager.getInstance().getKillAuraBPunish().replace("%player%", user.getPlayer().getName())));
					}
					break;
				case 'C':
					if (ConfigManager.getInstance().getKillAuraCMaxVL() <= vl && ConfigManager.getInstance().isKillAuraCBannable()) {
						WardenPunishEvent event = new WardenPunishEvent(user.getPlayer(), cheat, type, vl);
						Bukkit.getPluginManager().callEvent(event);
						if (event.isCancelled()) {
							return;
						}
						Bukkit.getScheduler().runTask(Main.getInstance(), () -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), ConfigManager.getInstance().getKillAuraCPunish().replace("%player%", user.getPlayer().getName())));
					}
					break;
				case 'D':
					if (ConfigManager.getInstance().getKillAuraDMaxVL() <= vl && ConfigManager.getInstance().isKillAuraDBannable()) {
						WardenPunishEvent event = new WardenPunishEvent(user.getPlayer(), cheat, type, vl);
						Bukkit.getPluginManager().callEvent(event);
						if (event.isCancelled()) {
							return;
						}
						Bukkit.getScheduler().runTask(Main.getInstance(), () -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), ConfigManager.getInstance().getKillAuraDPunish().replace("%player%", user.getPlayer().getName())));
					}
					break;
				case 'E':
					if (ConfigManager.getInstance().getKillAuraEMaxVL() <= vl && ConfigManager.getInstance().isKillAuraEBannable()) {
						WardenPunishEvent event = new WardenPunishEvent(user.getPlayer(), cheat, type, vl);
						Bukkit.getPluginManager().callEvent(event);
						if (event.isCancelled()) {
							return;
						}
						Bukkit.getScheduler().runTask(Main.getInstance(), () -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), ConfigManager.getInstance().getKillAuraEPunish().replace("%player%", user.getPlayer().getName())));
					}
					break;
			}
		} else if (cheat.contains("Criticals")) {
			switch (type) {
				case 'A':
					if (ConfigManager.getInstance().getCriticalsAMaxVL() <= vl && ConfigManager.getInstance().isCriticalsABannable()) {
						WardenPunishEvent event = new WardenPunishEvent(user.getPlayer(), cheat, type, vl);
						Bukkit.getPluginManager().callEvent(event);
						if (event.isCancelled()) {
							return;
						}
						Bukkit.getScheduler().runTask(Main.getInstance(), () -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), ConfigManager.getInstance().getCriticalsAPunish().replace("%player%", user.getPlayer().getName())));
					}
					break;
				case 'B':
					if (ConfigManager.getInstance().getCriticalsBMaxVL() <= vl && ConfigManager.getInstance().isCriticalsBBannable()) {
						WardenPunishEvent event = new WardenPunishEvent(user.getPlayer(), cheat, type, vl);
						Bukkit.getPluginManager().callEvent(event);
						if (event.isCancelled()) {
							return;
						}
						Bukkit.getScheduler().runTask(Main.getInstance(), () -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), ConfigManager.getInstance().getCriticalsBPunish().replace("%player%", user.getPlayer().getName())));
					}
					break;
			}
		} else if (cheat.contains("BadPackets")) {
			switch (type) {
				case 'A':
					if (ConfigManager.getInstance().getBadPacketsAMaxVL() <= vl && ConfigManager.getInstance().isBadPacketsABannable()) {
						WardenPunishEvent event = new WardenPunishEvent(user.getPlayer(), cheat, type, vl);
						Bukkit.getPluginManager().callEvent(event);
						if (event.isCancelled()) {
							return;
						}
						Bukkit.getScheduler().runTask(Main.getInstance(), () -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), ConfigManager.getInstance().getBadPacketsAPunish().replace("%player%", user.getPlayer().getName())));
					}
					break;
				case 'B':
					if (ConfigManager.getInstance().getBadPacketsBMaxVL() <= vl && ConfigManager.getInstance().isBadPacketsBBannable()) {
						WardenPunishEvent event = new WardenPunishEvent(user.getPlayer(), cheat, type, vl);
						Bukkit.getPluginManager().callEvent(event);
						if (event.isCancelled()) {
							return;
						}
						Bukkit.getScheduler().runTask(Main.getInstance(), () -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), ConfigManager.getInstance().getBadPacketsBPunish().replace("%player%", user.getPlayer().getName())));
					}
					break;
			}
		} else if (cheat.contains("FastBow")) {
			switch (type) {
				case 'A':
					if (ConfigManager.getInstance().getFastBowAMaxVL() <= vl && ConfigManager.getInstance().isFastBowABannable()) {
						WardenPunishEvent event = new WardenPunishEvent(user.getPlayer(), cheat, type, vl);
						Bukkit.getPluginManager().callEvent(event);
						if (event.isCancelled()) {
							return;
						}
						Bukkit.getScheduler().runTask(Main.getInstance(), () -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), ConfigManager.getInstance().getFastBowAPunish().replace("%player%", user.getPlayer().getName())));
					}
					break;
				case 'B':
					if (ConfigManager.getInstance().getFastBowBMaxVL() <= vl && ConfigManager.getInstance().isFastBowBBannable()) {
						WardenPunishEvent event = new WardenPunishEvent(user.getPlayer(), cheat, type, vl);
						Bukkit.getPluginManager().callEvent(event);
						if (event.isCancelled()) {
							return;
						}
						Bukkit.getScheduler().runTask(Main.getInstance(), () -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), ConfigManager.getInstance().getFastBowBPunish().replace("%player%", user.getPlayer().getName())));
					}
					break;
			}
		} else if (cheat.contains("Timer")) {
			switch (type) {
				case 'A':
					if (ConfigManager.getInstance().getTimerAMaxVL() <= vl && ConfigManager.getInstance().isTimerABannable()) {
						WardenPunishEvent event = new WardenPunishEvent(user.getPlayer(), cheat, type, vl);
						Bukkit.getPluginManager().callEvent(event);
						if (event.isCancelled()) {
							return;
						}
						Bukkit.getScheduler().runTask(Main.getInstance(), () -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), ConfigManager.getInstance().getTimerAPunish().replace("%player%", user.getPlayer().getName())));
					}
					break;
				case 'B':
					if (ConfigManager.getInstance().getTimerBMaxVL() <= vl && ConfigManager.getInstance().isTimerBBannable()) {
						WardenPunishEvent event = new WardenPunishEvent(user.getPlayer(), cheat, type, vl);
						Bukkit.getPluginManager().callEvent(event);
						if (event.isCancelled()) {
							return;
						}
						Bukkit.getScheduler().runTask(Main.getInstance(), () -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), ConfigManager.getInstance().getTimerBPunish().replace("%player%", user.getPlayer().getName())));
					}
					break;
			}
		} else if (cheat.contains("Velocity")) {
			switch (type) {
				case 'A':
					if (ConfigManager.getInstance().getVelocityAMaxVL() <= vl && ConfigManager.getInstance().isVelocityABannable()) {
						WardenPunishEvent event = new WardenPunishEvent(user.getPlayer(), cheat, type, vl);
						Bukkit.getPluginManager().callEvent(event);
						if (event.isCancelled()) {
							return;
						}
						Bukkit.getScheduler().runTask(Main.getInstance(), () -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), ConfigManager.getInstance().getVelocityAPunish().replace("%player%", user.getPlayer().getName())));
					}
					break;
				case 'B':
					if (ConfigManager.getInstance().getVelocityBMaxVL() <= vl && ConfigManager.getInstance().isVelocityBBannable()) {
						WardenPunishEvent event = new WardenPunishEvent(user.getPlayer(), cheat, type, vl);
						Bukkit.getPluginManager().callEvent(event);
						if (event.isCancelled()) {
							return;
						}
						Bukkit.getScheduler().runTask(Main.getInstance(), () -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), ConfigManager.getInstance().getVelocityBPunish().replace("%player%", user.getPlayer().getName())));
					}
					break;
			}
		} else if (cheat.contains("Jesus")) {
			switch (type) {
				case 'A':
					if (ConfigManager.getInstance().getJesusAMaxVL() <= vl && ConfigManager.getInstance().isJesusABannable()) {
						WardenPunishEvent event = new WardenPunishEvent(user.getPlayer(), cheat, type, vl);
						Bukkit.getPluginManager().callEvent(event);
						if (event.isCancelled()) {
							return;
						}
						Bukkit.getScheduler().runTask(Main.getInstance(), () -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), ConfigManager.getInstance().getJesusAPunish().replace("%player%", user.getPlayer().getName())));
					}
					break;
				case 'B':
					if (ConfigManager.getInstance().getJesusBMaxVL() <= vl && ConfigManager.getInstance().isJesusBBannable()) {
						WardenPunishEvent event = new WardenPunishEvent(user.getPlayer(), cheat, type, vl);
						Bukkit.getPluginManager().callEvent(event);
						if (event.isCancelled()) {
							return;
						}
						Bukkit.getScheduler().runTask(Main.getInstance(), () -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), ConfigManager.getInstance().getJesusBPunish().replace("%player%", user.getPlayer().getName())));
					}
					break;
			}
		}
	}
}
