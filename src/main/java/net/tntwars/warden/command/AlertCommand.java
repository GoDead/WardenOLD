package net.tntwars.warden.command;

import net.tntwars.warden.Main;
import net.tntwars.warden.playerdata.PlayerData;
import org.bukkit.entity.Player;
import org.mineacademy.fo.Common;
import org.mineacademy.fo.command.SimpleCommand;

public class AlertCommand extends SimpleCommand {
	public AlertCommand() {
		super("alerts");
		setPermission("warden.alerts");
	}

	@Override
	protected void onCommand() {
		checkConsole();
		Player player = (Player) sender;
		PlayerData user = Main.getPlayerDataManager().find(player.getUniqueId());
		user.alerts = !user.alerts;
		player.sendMessage(Common.colorize("§8[§cWarden§8] &7Your alerts are now &r" + (user.alerts ? "&aon" : "&coff")));
	}
}