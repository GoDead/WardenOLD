package net.tntwars.warden.command;

import net.tntwars.warden.command.wardencommand.ClearVL;
import net.tntwars.warden.command.wardencommand.Violation;
import org.mineacademy.fo.command.SimpleCommandGroup;

public class WardenCommand extends SimpleCommandGroup {
	@Override
	protected void registerSubcommands() {
		registerSubcommand(new Violation(this));
		registerSubcommand(new ClearVL(this));
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
