package net.warden.spigot.command.wardencommand;

import net.warden.spigot.utils.SettingsManager;
import org.mineacademy.fo.command.SimpleCommandGroup;
import org.mineacademy.fo.command.SimpleSubCommand;

public class Bungee extends SimpleSubCommand {

	public Bungee(final SimpleCommandGroup parent) {
		super(parent, "bungee");
		setPermission("warden.bungee");
		setDescription("Toggle BungeeCord alerts");
	}

	@Override
	protected void onCommand() {
		if (SettingsManager.getInstance().isBungeeCordAlerts()) {
			SettingsManager.getInstance().setBungeeCordAlerts(false);
			tell("&cWarden &8» &7" + "&eDisabled &7BungeeCord support!");
		} else {
			SettingsManager.getInstance().setBungeeCordAlerts(true);
			tell("&cWarden &8» &7" + "&eEnabled &7BungeeCord support!");
		}
		SettingsManager.getInstance().reloadConfig();
	}
}