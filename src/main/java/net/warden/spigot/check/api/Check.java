package net.warden.spigot.check.api;

import net.warden.spigot.check.api.data.Category;
import net.warden.spigot.events.CheckEvent;

public abstract class Check {
	private final String name;
	private final char checkType;
	private final Category category;

	public Check(final String name, final char checkType, final Category category) {
		this.name = name;
		this.checkType = checkType;
		this.category = category;
	}

	public CheckEvent onPreCheck(final CheckEvent checkEvent) {
		return checkEvent;
	}

	public final String getName() {
		return name;
	}

	public final char getCheckType() {
		return checkType;
	}

	public final Category getCategory() {
		return category;
	}
}
