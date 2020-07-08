package net.warden.bungeecord;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

public class Alert implements Listener {


	@EventHandler
	public void onAlert(PluginMessageEvent event) {
		if (event.isCancelled() ||
				!event.getTag().equals("WardenAlerts") ||
				!(event.getSender() instanceof net.md_5.bungee.api.connection.Server))
			return;
		try {
			DataInputStream in = new DataInputStream(new ByteArrayInputStream(event.getData()));
			String player = in.readUTF();
			ProxiedPlayer cheater = ProxyServer.getInstance().getPlayer(player);
			String check = in.readUTF();
			String type = in.readUTF();
			int vl = in.readInt();
			alert(cheater, check, type, vl);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	private void alert(ProxiedPlayer player, String check, String type, int vl) {
		TextComponent component = new TextComponent(ChatColor.DARK_GRAY + "[" + ChatColor.RED + "Warden" + ChatColor.DARK_GRAY + "] " + ChatColor.WHITE + player.getName() + ChatColor.GRAY + " is suspected of " + ChatColor.YELLOW + check + ChatColor.GRAY + " (Type " + type + ")" + ChatColor.RED + "x" + (int) vl + ChatColor.GRAY + " on: " + player.getServer().getInfo().getName().toUpperCase());
		for (ProxiedPlayer proxy : Main.getInstance().getProxy().getPlayers()) {
			if (proxy.hasPermission("warden.alerts")) {
				proxy.sendMessage((BaseComponent) component);
			}
		}
	}
}


