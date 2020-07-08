package net.warden.spigot.command;

import net.warden.spigot.command.wardencommand.*;
import org.mineacademy.fo.command.SimpleCommandGroup;

public class WardenCommand extends SimpleCommandGroup {
	@Override
	protected void registerSubcommands() {
		registerSubcommand(new Violation(this));
		registerSubcommand(new ClearVL(this));
		registerSubcommand(new Support(this));
		registerSubcommand(new Bungee(this));
		registerSubcommand(new Reload(this));
	}

	@Override
	protected String getCredits() {
		return "Warden Cheat Detection";
	}

	@Override
	protected boolean sendHelpIfNoArgs() {
		return false;
	}
}
