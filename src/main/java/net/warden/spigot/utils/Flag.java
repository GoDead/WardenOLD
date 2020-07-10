package net.warden.spigot.utils;

import net.warden.spigot.Main;
import net.warden.spigot.check.api.Check;
import net.warden.spigot.check.api.customevent.WardenFlagEvent;
import net.warden.spigot.check.api.customevent.WardenPunishEvent;
import net.warden.spigot.playerdata.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import static org.bukkit.Bukkit.getOnlinePlayers;

public class Flag {

	public static void flag(PlayerData data, Check check) {
		long time = System.currentTimeMillis() - data.getTimeSinceJoin();
		if (time < 8000) {
			return;
		}
		String name = check.getName();
		char type = check.getCheckType();
		int vl = data.getVLCount(check);
		if (data == null) return;
		Player player = data.getPlayer();
		if (SettingsManager.getInstance().getWorlds() != null && !SettingsManager.getInstance().getWorlds().isEmpty()) {
			if (SettingsManager.getInstance().getWorlds().contains(player.getWorld().getName().toLowerCase())) {
				return;
			}
		}
		WardenFlagEvent event = new WardenFlagEvent(player, name, type, vl);
		Bukkit.getPluginManager().callEvent(event);
		if (event.isCancelled()) {
			return;
		}
		punish(data, name, type, vl);
		if (isMultipleof5(vl)) {
			boolean isBungee = bungeeAlert(player, name, String.valueOf(type), vl);

			if (!isBungee) {
				getOnlinePlayers().stream().filter(staff -> staff.hasPermission("warden.alerts")).forEach(staff -> {
					PlayerData user = Main.getPlayerDataManager().find(staff.getUniqueId());
					if (user.alerts) {
						if (data == null) return;
						staff.sendMessage("§8[§cWarden§8] §f" + data.getPlayer().getName() + " §7is suspected of " + (false ? "§d§o(exp) " : "§e") + name + " §7(Type " + type + ") §cx" + (int) vl);
					}
				});
			}
		}
		data.incrementVL(check);
	}

	private static boolean bungeeAlert(Player player, String check, String type, int vl) {
		if (!SettingsManager.getInstance().isBungeeCordAlerts()) return false;
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(b);
		try {
			out.writeUTF(player.getName());
			out.writeUTF(check);
			out.writeUTF(type);
			out.writeInt(vl);
		} catch (IOException e) {
			e.printStackTrace();
		}
		player.sendPluginMessage(Main.getInstance(), "warden:alerts", b.toByteArray());
		return true;
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
		if (user == null) return;
		if (user.getPlayer() == null) return;
		String name = user.getPlayer().getName();
		if (user.getPlayer().hasPermission("warden.bypass")) return;
		if (cheat.contains("Flight")) {
			switch (type) {
				case 'A':
					if (ConfigManager.getInstance().getFlightAMaxVL() == vl && ConfigManager.getInstance().isFlightABannable()) {
						WardenPunishEvent event = new WardenPunishEvent(user.getPlayer(), cheat, type, vl);
						Bukkit.getPluginManager().callEvent(event);
						if (event.isCancelled()) {
							return;
						}
						ConfigManager.getInstance().getFlightAPunish().forEach(command -> {
							Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command.replace("%player%", name));
						});
					}
					break;
				case 'B':
					if (ConfigManager.getInstance().getFlightBMaxVL() == vl && ConfigManager.getInstance().isFlightBBannable()) {
						WardenPunishEvent event = new WardenPunishEvent(user.getPlayer(), cheat, type, vl);
						Bukkit.getPluginManager().callEvent(event);
						if (event.isCancelled()) {
							return;
						}
						ConfigManager.getInstance().getFlightBPunish().forEach(command -> {
							Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command.replace("%player%", name));
						});
					}
					break;
				case 'C':
					if (ConfigManager.getInstance().getFlightCMaxVL() == vl && ConfigManager.getInstance().isFlightCBannable()) {
						WardenPunishEvent event = new WardenPunishEvent(user.getPlayer(), cheat, type, vl);
						Bukkit.getPluginManager().callEvent(event);
						if (event.isCancelled()) {
							return;
						}
						ConfigManager.getInstance().getFlightCPunish().forEach(command -> {
							Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command.replace("%player%", name));
						});
					}
					break;
				case 'D':
					if (ConfigManager.getInstance().getFlightDMaxVL() == vl && ConfigManager.getInstance().isFlightDBannable()) {
						WardenPunishEvent event = new WardenPunishEvent(user.getPlayer(), cheat, type, vl);
						Bukkit.getPluginManager().callEvent(event);
						if (event.isCancelled()) {
							return;
						}
						ConfigManager.getInstance().getFlightDPunish().forEach(command -> {
							Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command.replace("%player%", name));
						});
					}
					break;
				case 'E':
					if (ConfigManager.getInstance().getFlightEMaxVL() == vl && ConfigManager.getInstance().isFlightEBannable()) {
						WardenPunishEvent event = new WardenPunishEvent(user.getPlayer(), cheat, type, vl);
						Bukkit.getPluginManager().callEvent(event);
						if (event.isCancelled()) {
							return;
						}
						ConfigManager.getInstance().getFlightEPunish().forEach(command -> {
							Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command.replace("%player%", name));
						});
					}
					break;

				case 'F':
					if (ConfigManager.getInstance().getFlightFMaxVL() == vl && ConfigManager.getInstance().isFlightFBannable()) {
						WardenPunishEvent event = new WardenPunishEvent(user.getPlayer(), cheat, type, vl);
						Bukkit.getPluginManager().callEvent(event);
						if (event.isCancelled()) {
							return;
						}
						ConfigManager.getInstance().getFlightFPunish().forEach(command -> {
							Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command.replace("%player%", name));
						});
					}
					break;
			}
		} else if (cheat.contains("Speed")) {
			switch (type) {
				case 'A':
					if (ConfigManager.getInstance().getSpeedAMaxVL() == vl && ConfigManager.getInstance().isSpeedABannable()) {
						WardenPunishEvent event = new WardenPunishEvent(user.getPlayer(), cheat, type, vl);
						Bukkit.getPluginManager().callEvent(event);
						if (event.isCancelled()) {
							return;
						}
						ConfigManager.getInstance().getSpeedAPunish().forEach(command -> {
							Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command.replace("%player%", name));
						});
					}
					break;
				case 'B':
					if (ConfigManager.getInstance().getSpeedBMaxVL() == vl && ConfigManager.getInstance().isSpeedBBannable()) {
						WardenPunishEvent event = new WardenPunishEvent(user.getPlayer(), cheat, type, vl);
						Bukkit.getPluginManager().callEvent(event);
						if (event.isCancelled()) {
							return;
						}
						ConfigManager.getInstance().getSpeedBPunish().forEach(command -> {
							Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command.replace("%player%", name));
						});
					}
					break;
			}
		} else if (cheat.contains("GroundSpoof")) {
			switch (type) {
				case 'A':
					if (ConfigManager.getInstance().getGroundSpoofAMaxVL() == vl && ConfigManager.getInstance().isGroundSpoofABannable()) {
						WardenPunishEvent event = new WardenPunishEvent(user.getPlayer(), cheat, type, vl);
						Bukkit.getPluginManager().callEvent(event);
						if (event.isCancelled()) {
							return;
						}
						ConfigManager.getInstance().getGroundSpoofAPunish().forEach(command -> {
							Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command.replace("%player%", name));
						});
					}
					break;
				case 'B':
					if (ConfigManager.getInstance().getGroundSpoofBMaxVL() == vl && ConfigManager.getInstance().isGroundSpoofBBannable()) {
						WardenPunishEvent event = new WardenPunishEvent(user.getPlayer(), cheat, type, vl);
						Bukkit.getPluginManager().callEvent(event);
						if (event.isCancelled()) {
							return;
						}
						ConfigManager.getInstance().getGroundSpoofBPunish().forEach(command -> {
							Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command.replace("%player%", name));
						});
					}
					break;
			}
		} else if (cheat.contains("Scaffold")) {
			switch (type) {
				case 'A':
					if (ConfigManager.getInstance().getScaffoldAMaxVL() == vl && ConfigManager.getInstance().isScaffoldABannable()) {
						WardenPunishEvent event = new WardenPunishEvent(user.getPlayer(), cheat, type, vl);
						Bukkit.getPluginManager().callEvent(event);
						if (event.isCancelled()) {
							return;
						}
						ConfigManager.getInstance().getScaffoldAPunish().forEach(command -> {
							Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command.replace("%player%", name));
						});
					}
					break;
				case 'B':
					if (ConfigManager.getInstance().getScaffoldBMaxVL() == vl && ConfigManager.getInstance().isScaffoldBBannable()) {
						WardenPunishEvent event = new WardenPunishEvent(user.getPlayer(), cheat, type, vl);
						Bukkit.getPluginManager().callEvent(event);
						if (event.isCancelled()) {
							return;
						}
						ConfigManager.getInstance().getScaffoldBPunish().forEach(command -> {
							Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command.replace("%player%", name));
						});
					}
					break;
			}
		} else if (cheat.contains("HighJump")) {
			switch (type) {
				case 'A':
					if (ConfigManager.getInstance().getHighJumpAMaxVL() == vl && ConfigManager.getInstance().isHighJumpABannable()) {
						WardenPunishEvent event = new WardenPunishEvent(user.getPlayer(), cheat, type, vl);
						Bukkit.getPluginManager().callEvent(event);
						if (event.isCancelled()) {
							return;
						}
						ConfigManager.getInstance().getHighJumpAPunish().forEach(command -> {
							Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command.replace("%player%", name));
						});
					}
					break;
			}
		} else if (cheat.contains("KillAura")) {
			switch (type) {
				case 'A':
					if (ConfigManager.getInstance().getKillAuraAMaxVL() == vl && ConfigManager.getInstance().isKillAuraABannable()) {
						WardenPunishEvent event = new WardenPunishEvent(user.getPlayer(), cheat, type, vl);
						Bukkit.getPluginManager().callEvent(event);
						if (event.isCancelled()) {
							return;
						}
						ConfigManager.getInstance().getKillAuraAPunish().forEach(command -> {
							Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command.replace("%player%", name));
						});
					}
					break;
				case 'B':
					if (ConfigManager.getInstance().getKillAuraBMaxVL() == vl && ConfigManager.getInstance().isKillAuraBBannable()) {
						WardenPunishEvent event = new WardenPunishEvent(user.getPlayer(), cheat, type, vl);
						Bukkit.getPluginManager().callEvent(event);
						if (event.isCancelled()) {
							return;
						}
						ConfigManager.getInstance().getKillAuraBPunish().forEach(command -> {
							Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command.replace("%player%", name));
						});
					}
					break;
				case 'C':
					if (ConfigManager.getInstance().getKillAuraCMaxVL() == vl && ConfigManager.getInstance().isKillAuraCBannable()) {
						WardenPunishEvent event = new WardenPunishEvent(user.getPlayer(), cheat, type, vl);
						Bukkit.getPluginManager().callEvent(event);
						if (event.isCancelled()) {
							return;
						}
						ConfigManager.getInstance().getKillAuraCPunish().forEach(command -> {
							Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command.replace("%player%", name));
						});
					}
					break;
				case 'D':
					if (ConfigManager.getInstance().getKillAuraDMaxVL() == vl && ConfigManager.getInstance().isKillAuraDBannable()) {
						WardenPunishEvent event = new WardenPunishEvent(user.getPlayer(), cheat, type, vl);
						Bukkit.getPluginManager().callEvent(event);
						if (event.isCancelled()) {
							return;
						}
						ConfigManager.getInstance().getKillAuraDPunish().forEach(command -> {
							Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command.replace("%player%", name));
						});
					}
					break;
				case 'E':
					if (ConfigManager.getInstance().getKillAuraEMaxVL() == vl && ConfigManager.getInstance().isKillAuraEBannable()) {
						WardenPunishEvent event = new WardenPunishEvent(user.getPlayer(), cheat, type, vl);
						Bukkit.getPluginManager().callEvent(event);
						if (event.isCancelled()) {
							return;
						}
						ConfigManager.getInstance().getKillAuraEPunish().forEach(command -> {
							Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command.replace("%player%", name));
						});
					}
					break;
				case 'F':
					if (ConfigManager.getInstance().getKillAuraFMaxVL() == vl && ConfigManager.getInstance().isKillAuraFBannable()) {
						WardenPunishEvent event = new WardenPunishEvent(user.getPlayer(), cheat, type, vl);
						Bukkit.getPluginManager().callEvent(event);
						if (event.isCancelled()) {
							return;
						}
						ConfigManager.getInstance().getKillAuraFPunish().forEach(command -> {
							Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command.replace("%player%", name));
						});
					}
					break;
				case 'G':
					if (ConfigManager.getInstance().getKillAuraGMaxVL() == vl && ConfigManager.getInstance().isKillAuraGBannable()) {
						WardenPunishEvent event = new WardenPunishEvent(user.getPlayer(), cheat, type, vl);
						Bukkit.getPluginManager().callEvent(event);
						if (event.isCancelled()) {
							return;
						}
						ConfigManager.getInstance().getKillAuraGPunish().forEach(command -> {
							Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command.replace("%player%", name));
						});
					}
					break;
				case 'H':
					if (ConfigManager.getInstance().getKillAuraHMaxVL() == vl && ConfigManager.getInstance().isKillAuraHBannable()) {
						WardenPunishEvent event = new WardenPunishEvent(user.getPlayer(), cheat, type, vl);
						Bukkit.getPluginManager().callEvent(event);
						if (event.isCancelled()) {
							return;
						}
						ConfigManager.getInstance().getKillAuraHPunish().forEach(command -> {
							Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command.replace("%player%", name));
						});
					}
					break;
			}
		} else if (cheat.contains("Criticals")) {
			switch (type) {
				case 'A':
					if (ConfigManager.getInstance().getCriticalsAMaxVL() == vl && ConfigManager.getInstance().isCriticalsABannable()) {
						WardenPunishEvent event = new WardenPunishEvent(user.getPlayer(), cheat, type, vl);
						Bukkit.getPluginManager().callEvent(event);
						if (event.isCancelled()) {
							return;
						}
						ConfigManager.getInstance().getCriticalsAPunish().forEach(command -> {
							Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command.replace("%player%", name));
						});
					}
					break;
				case 'B':
					if (ConfigManager.getInstance().getCriticalsBMaxVL() == vl && ConfigManager.getInstance().isCriticalsBBannable()) {
						WardenPunishEvent event = new WardenPunishEvent(user.getPlayer(), cheat, type, vl);
						Bukkit.getPluginManager().callEvent(event);
						if (event.isCancelled()) {
							return;
						}
						ConfigManager.getInstance().getCriticalsBPunish().forEach(command -> {
							Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command.replace("%player%", name));
						});
					}
					break;
			}
		} else if (cheat.contains("BadPackets")) {
			switch (type) {
				case 'A':
					if (ConfigManager.getInstance().getBadPacketsAMaxVL() == vl && ConfigManager.getInstance().isBadPacketsABannable()) {
						WardenPunishEvent event = new WardenPunishEvent(user.getPlayer(), cheat, type, vl);
						Bukkit.getPluginManager().callEvent(event);
						if (event.isCancelled()) {
							return;
						}
						ConfigManager.getInstance().getBadPacketsAPunish().forEach(command -> {
							Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command.replace("%player%", name));
						});
					}
					break;
				case 'B':
					if (ConfigManager.getInstance().getBadPacketsBMaxVL() == vl && ConfigManager.getInstance().isBadPacketsBBannable()) {
						WardenPunishEvent event = new WardenPunishEvent(user.getPlayer(), cheat, type, vl);
						Bukkit.getPluginManager().callEvent(event);
						if (event.isCancelled()) {
							return;
						}
						ConfigManager.getInstance().getBadPacketsBPunish().forEach(command -> {
							Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command.replace("%player%", name));
						});
					}
					break;
				case 'C':
					if (ConfigManager.getInstance().getBadPacketsCMaxVL() == vl && ConfigManager.getInstance().isBadPacketsCBannable()) {
						WardenPunishEvent event = new WardenPunishEvent(user.getPlayer(), cheat, type, vl);
						Bukkit.getPluginManager().callEvent(event);
						if (event.isCancelled()) {
							return;
						}
						ConfigManager.getInstance().getBadPacketsCPunish().forEach(command -> {
							Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command.replace("%player%", name));
						});
					}
					break;
			}
		} else if (cheat.contains("FastBow")) {
			switch (type) {
				case 'A':
					if (ConfigManager.getInstance().getFastBowAMaxVL() == vl && ConfigManager.getInstance().isFastBowABannable()) {
						WardenPunishEvent event = new WardenPunishEvent(user.getPlayer(), cheat, type, vl);
						Bukkit.getPluginManager().callEvent(event);
						if (event.isCancelled()) {
							return;
						}
						ConfigManager.getInstance().getFastBowAPunish().forEach(command -> {
							Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command.replace("%player%", name));
						});
					}
					break;
				case 'B':
					if (ConfigManager.getInstance().getFastBowBMaxVL() == vl && ConfigManager.getInstance().isFastBowBBannable()) {
						WardenPunishEvent event = new WardenPunishEvent(user.getPlayer(), cheat, type, vl);
						Bukkit.getPluginManager().callEvent(event);
						if (event.isCancelled()) {
							return;
						}
						ConfigManager.getInstance().getFastBowBPunish().forEach(command -> {
							Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command.replace("%player%", name));
						});
					}
					break;
			}
		} else if (cheat.contains("Timer")) {
			switch (type) {
				case 'A':
					if (ConfigManager.getInstance().getTimerAMaxVL() == vl && ConfigManager.getInstance().isTimerABannable()) {
						WardenPunishEvent event = new WardenPunishEvent(user.getPlayer(), cheat, type, vl);
						Bukkit.getPluginManager().callEvent(event);
						if (event.isCancelled()) {
							return;
						}
						ConfigManager.getInstance().getTimerAPunish().forEach(command -> {
							Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command.replace("%player%", name));
						});
					}
					break;
				case 'B':
					if (ConfigManager.getInstance().getTimerBMaxVL() == vl && ConfigManager.getInstance().isTimerBBannable()) {
						WardenPunishEvent event = new WardenPunishEvent(user.getPlayer(), cheat, type, vl);
						Bukkit.getPluginManager().callEvent(event);
						if (event.isCancelled()) {
							return;
						}
						ConfigManager.getInstance().getTimerBPunish().forEach(command -> {
							Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command.replace("%player%", name));
						});
					}
					break;
			}
		} else if (cheat.contains("Velocity")) {
			switch (type) {
				case 'A':
					if (ConfigManager.getInstance().getVelocityAMaxVL() == vl && ConfigManager.getInstance().isVelocityABannable()) {
						WardenPunishEvent event = new WardenPunishEvent(user.getPlayer(), cheat, type, vl);
						Bukkit.getPluginManager().callEvent(event);
						if (event.isCancelled()) {
							return;
						}
						ConfigManager.getInstance().getVelocityAPunish().forEach(command -> {
							Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command.replace("%player%", name));
						});
					}
					break;
				case 'B':
					if (ConfigManager.getInstance().getVelocityBMaxVL() == vl && ConfigManager.getInstance().isVelocityBBannable()) {
						WardenPunishEvent event = new WardenPunishEvent(user.getPlayer(), cheat, type, vl);
						Bukkit.getPluginManager().callEvent(event);
						if (event.isCancelled()) {
							return;
						}
						ConfigManager.getInstance().getVelocityBPunish().forEach(command -> {
							Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command.replace("%player%", name));
						});
					}
					break;
			}
		} else if (cheat.contains("Jesus")) {
			switch (type) {
				case 'A':
					if (ConfigManager.getInstance().getJesusAMaxVL() == vl && ConfigManager.getInstance().isJesusABannable()) {
						WardenPunishEvent event = new WardenPunishEvent(user.getPlayer(), cheat, type, vl);
						Bukkit.getPluginManager().callEvent(event);
						if (event.isCancelled()) {
							return;
						}
						ConfigManager.getInstance().getJesusAPunish().forEach(command -> {
							Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command.replace("%player%", name));
						});
					}
					break;
				case 'B':
					if (ConfigManager.getInstance().getJesusBMaxVL() == vl && ConfigManager.getInstance().isJesusBBannable()) {
						WardenPunishEvent event = new WardenPunishEvent(user.getPlayer(), cheat, type, vl);
						Bukkit.getPluginManager().callEvent(event);
						if (event.isCancelled()) {
							return;
						}
						ConfigManager.getInstance().getJesusBPunish().forEach(command -> {
							Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command.replace("%player%", name));
						});
					}
					break;
			}
		} else if (cheat.contains("Reach")) {
			switch (type) {
				case 'A':
					if (ConfigManager.getInstance().getReachAMaxVL() == vl && ConfigManager.getInstance().isReachABannable()) {
						WardenPunishEvent event = new WardenPunishEvent(user.getPlayer(), cheat, type, vl);
						Bukkit.getPluginManager().callEvent(event);
						if (event.isCancelled()) {
							return;
						}
						ConfigManager.getInstance().getReachAPunish().forEach(command -> {
							Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command.replace("%player%", name));
						});
					}
					break;
			}
		} else if (cheat.contains("InvalidMovement")) {
			switch (type) {
				case 'A':
					if (ConfigManager.getInstance().getInvalidMovementAMaxVL() == vl && ConfigManager.getInstance().isInvalidMovementABannable()) {
						WardenPunishEvent event = new WardenPunishEvent(user.getPlayer(), cheat, type, vl);
						Bukkit.getPluginManager().callEvent(event);
						if (event.isCancelled()) {
							return;
						}
						ConfigManager.getInstance().getInvalidMovementAPunish().forEach(command -> {
							Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command.replace("%player%", name));
						});
					}
					break;
				case 'B':
					if (ConfigManager.getInstance().getInvalidMovementBMaxVL() == vl && ConfigManager.getInstance().isInvalidMovementBBannable()) {
						WardenPunishEvent event = new WardenPunishEvent(user.getPlayer(), cheat, type, vl);
						Bukkit.getPluginManager().callEvent(event);
						if (event.isCancelled()) {
							return;
						}
						ConfigManager.getInstance().getInvalidMovementBPunish().forEach(command -> {
							Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command.replace("%player%", name));
						});
					}
					break;
			}
		}
	}
}
