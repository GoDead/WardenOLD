package net.warden.spigot.command.wardencommand;

import org.mineacademy.fo.command.SimpleCommandGroup;
import org.mineacademy.fo.command.SimpleSubCommand;

public class Support extends SimpleSubCommand {

	public Support(final SimpleCommandGroup parent) {
		super(parent, "support");
		setPermission("warden.support");
		setDescription("Get Discord Support Link");
	}

	@Override
	protected void onCommand() {
		tell("&cWarden &8» &7" + "&7You are Discord server for support:&b https://discord.gg/r4mz3ZX");
	}
}
