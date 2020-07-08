package net.warden.bungeecord;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class AlertCommand extends Command {
	public AlertCommand() {
		super("bungeealerts", "warden.alerts");
	}

	@Override
	public void execute(CommandSender commandSender, String[] strings) {
		if (commandSender instanceof ProxiedPlayer) {
			ProxiedPlayer player = (ProxiedPlayer) commandSender;
			boolean contains = (Main.getInstance()).alertsOn.contains(player);
			if (contains) {
				(Main.getInstance()).alertsOn.remove(player);
			} else {
				(Main.getInstance()).alertsOn.add(player);
			}
			player.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', "§8[§cWarden§8] &7Your alerts are now &r" + (Main.getInstance().alertsOn.contains(player) ? "&aon" : "&coff"))));
		}
	}
}