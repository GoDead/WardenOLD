package net.tntwars.warden.check.api;

import net.tntwars.warden.Main;
import net.tntwars.warden.check.api.data.Category;
import net.tntwars.warden.events.CheckEvent;
import net.tntwars.warden.events.PublicCheckEvent;
import net.tntwars.warden.playerdata.PlayerData;
import net.tntwars.warden.utils.Flag;
import org.bukkit.Bukkit;


public abstract class PublicCheck extends Check {
	public PublicCheck(String name, char checkType, Category category) {
		super(name, checkType, category);
	}

	@Override
	public CheckEvent onPreCheck(final CheckEvent e) {
		if (e instanceof PublicCheckEvent) {
			onCheck((PublicCheckEvent) e);
		}
		return e;
	}

	public PublicCheckEvent onCheck(final PublicCheckEvent e) {
		return e;
	}

	public void flag(PlayerData data) {
		Bukkit.getScheduler().runTask(Main.getInstance(), () -> Flag.flag(data, this));
	}
}
