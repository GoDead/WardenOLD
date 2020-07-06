package net.tntwars.warden.command.wardencommand;

import net.tntwars.warden.Main;
import net.tntwars.warden.playerdata.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.mineacademy.fo.command.SimpleCommandGroup;
import org.mineacademy.fo.command.SimpleSubCommand;

public class ClearVL extends SimpleSubCommand {

	public ClearVL(final SimpleCommandGroup parent) {
		super(parent, "clear");
		setPermission("warden.clearvl");
		setUsage("&c[player]");
		setMinArguments(1);
	}

	@Override
	protected void onCommand() {
		Player player = Bukkit.getPlayer(args[0]);
		if (player == null) {
			tell("&cWarden &8» &7" + args[0] + " &7doesn't exist or is not online!");
			return;
		}
		if (!player.isOnline()) {
			tell("&cWarden &8» &7" + player.getName() + " &7is not online!");
			return;
		}
		PlayerData user = Main.getPlayerDataManager().find(player.getUniqueId());
		if (user.getViolationsMap().isEmpty()) {
			tell("&cWarden &8» &7" + player.getName() + " &7doesn't have any violations!");
			return;
		}
		user.getViolationsMap().clear();
		tell("&cWarden &8» &7" + "&7Successfully cleared " + player.getName() + "'s Violations!");
	}

}

