package net.warden.spigot.utils;

import lombok.Getter;
import org.mineacademy.fo.collection.SerializedMap;
import org.mineacademy.fo.settings.YamlConfig;

public class MessagesManager extends YamlConfig {
	@Getter
	public static final MessagesManager instance = new MessagesManager();

	String alertMessage;


	public MessagesManager() {
		setHeader("Warden Settings Configuration");
		loadConfiguration(NO_DEFAULT, "settings.yml");
	}

	public void reloadConfig() {
		reload();
	}


	@Override
	protected void onLoadFinish() {
		/*bungeeCordAlerts = getOrSetDefault("BungeeCordAlerts", false);
		worlds = getStringList("Excluded-Worlds");*/
	}

	@Override
	public SerializedMap serialize() {
		return SerializedMap.ofArray(
				/*"BungeeCordAlerts", bungeeCordAlerts,
				"Excluded-Worlds", worlds*/
		);
	}
}