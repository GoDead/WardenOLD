package net.warden.spigot.command.wardencommand;

import net.warden.spigot.utils.ConfigManager;
import net.warden.spigot.utils.SettingsManager;
import org.mineacademy.fo.command.SimpleCommandGroup;
import org.mineacademy.fo.command.SimpleSubCommand;

import java.util.ArrayList;
import java.util.Arrays;

public class Reload extends SimpleSubCommand {

	public Reload(final SimpleCommandGroup parent) {
		super(parent, "reload");
		setPermission("warden.reload");
		setDescription("Reload all Warden configuration files");
		setAliases(new ArrayList<>(Arrays.asList("rl")));
	}

	@Override
	protected void onCommand() {
		SettingsManager.getInstance().reloadConfig();
		ConfigManager.getInstance().reloadConfig();
		tell("&cWarden &8» &7" + "&7Successfully reloaded Warden configurations!");
	}
}