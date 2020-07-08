package net.warden.spigot.check.api;

import net.warden.spigot.Main;
import net.warden.spigot.check.api.data.Category;
import net.warden.spigot.events.CheckEvent;
import net.warden.spigot.events.PublicCheckEvent;
import net.warden.spigot.playerdata.PlayerData;
import net.warden.spigot.utils.Flag;
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
		if (data == null)
			return;
		Bukkit.getScheduler().runTask(Main.getInstance(), () -> Flag.flag(data, this));
	}
}
