package net.warden.spigot.check.api;

import net.warden.spigot.Main;
import net.warden.spigot.check.api.data.Category;
import net.warden.spigot.events.CheckEvent;
import net.warden.spigot.events.PrivateCheckEvent;
import net.warden.spigot.playerdata.PlayerData;
import net.warden.spigot.utils.Flag;
import org.bukkit.Bukkit;

public abstract class PrivateCheck extends Check {
	private final PlayerData playerData;

	public PrivateCheck(final PlayerData data, String name, char checkType, Category category) {
		super(name, checkType, category);
		this.playerData = data;
	}

	@Override
	public CheckEvent onPreCheck(final CheckEvent checkEvent) {
		if (checkEvent instanceof PrivateCheckEvent) {
			onCheck((PrivateCheckEvent) checkEvent);
		}
		return checkEvent;
	}


	public PrivateCheckEvent onCheck(final PrivateCheckEvent e) {
		return e;
	}

	public void flag() {

		Bukkit.getScheduler().runTask(Main.getInstance(), () -> Flag.flag(getPlayerData(), this));
	}

	public void comply() {
		getPlayerData().decrementVL(this);
	}

	public float getVL() {
		return getPlayerData().getVLCount(this);
	}

	public void clearVL() {
		getPlayerData().clearVL(this);
	}

	public void flag(final String message) {
		System.out.println(message);
	}

	public final PlayerData getPlayerData() {
		return playerData;
	}
}
