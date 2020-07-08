package net.warden.spigot.utils;

import lombok.AccessLevel;
import lombok.Getter;
import org.mineacademy.fo.collection.SerializedMap;
import org.mineacademy.fo.settings.YamlConfig;


@Getter(AccessLevel.PUBLIC)
public class SettingsManager extends YamlConfig {
	@Getter
	public static final SettingsManager instance = new SettingsManager();


	public void setBungeeCordAlerts(boolean bungeeCordAlerts) {
		this.bungeeCordAlerts = bungeeCordAlerts;
	}

	private boolean bungeeCordAlerts;


	public SettingsManager() {
		setHeader("Warden Settings Configuration");
		loadConfiguration(NO_DEFAULT, "settings.yml");
	}

	public void reloadConfig() {
		reload();
	}


	@Override
	protected void onLoadFinish() {
		bungeeCordAlerts = getOrSetDefault("BungeeCordAlerts", false);
	}

	@Override
	public SerializedMap serialize() {
		return SerializedMap.ofArray(
				"BungeeCordAlerts", bungeeCordAlerts
		);
	}
}