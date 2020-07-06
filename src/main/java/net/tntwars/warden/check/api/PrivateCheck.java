package net.tntwars.warden.check.api;

import net.tntwars.warden.check.api.data.Category;
import net.tntwars.warden.events.CheckEvent;
import net.tntwars.warden.events.PrivateCheckEvent;
import net.tntwars.warden.playerdata.PlayerData;
import net.tntwars.warden.utils.Flag;

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
		Flag.flag(getPlayerData(), this);
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
