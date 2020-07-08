package net.warden.bungeecord;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class Main extends Plugin {

	public static Main instance;
	public List<ProxiedPlayer> alertsOn = new ArrayList<>();

	@Override
	public void onEnable() {
		instance = this;
		getProxy().getPluginManager().registerCommand(this, new AlertCommand());
		getProxy().registerChannel("WardenAlerts");
		getProxy().getPluginManager().registerListener(this, new JoinEvent());
		getProxy().getPluginManager().registerListener(this, new Alert());
	}

	public static Main getInstance() {
		return instance;
	}
}