package net.warden.spigot.command.wardencommand;

import net.warden.spigot.utils.SettingsManager;
import org.mineacademy.fo.command.SimpleCommandGroup;
import org.mineacademy.fo.command.SimpleSubCommand;

public class World extends SimpleSubCommand {

	public World(final SimpleCommandGroup parent) {
		super(parent, "world");
		setPermission("warden.world");
		setDescription("Exclude or Include a world from checks");
		setUsage("[add|remove] [worldname]");
		setMinArguments(2);
	}

	@Override
	protected void onCommand() {
		String param1 = args[0];
		String param2 = args[1];
		tabComplete();
		if (param1.equalsIgnoreCase("add")) {
			if (SettingsManager.getInstance().addWorld(param2.toLowerCase()))
				tell("&cWorld already in list!");
			else
				tell("&cWarden &8» &7" + "&7" + args[1] + " &7will now be checked.");
		} else if (param1.equalsIgnoreCase("remove")) {
			if (SettingsManager.getInstance().removeWorld(param2.toLowerCase()))
				tell("&cWorld not in list!");
			else
				tell("&cWarden &8» &7" + "&7" + args[1] + " &7will no longer be checked.");
		} else {
			tell(usageMessage);
		}
	}
}