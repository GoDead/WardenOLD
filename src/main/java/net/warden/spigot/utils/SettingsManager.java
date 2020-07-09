package net.warden.spigot.utils;

import lombok.AccessLevel;
import lombok.Getter;
import org.mineacademy.fo.collection.SerializedMap;
import org.mineacademy.fo.settings.YamlConfig;

import java.util.List;


@Getter(AccessLevel.PUBLIC)
public class SettingsManager extends YamlConfig {
	@Getter
	public static final SettingsManager instance = new SettingsManager();


	public void setBungeeCordAlerts(boolean bungeeCordAlerts) {
		this.bungeeCordAlerts = bungeeCordAlerts;
		save("BungeeCordAlerts", bungeeCordAlerts);
	}

	public boolean addWorld(String world) {
		if (this.worlds.contains(world))
			return true;
		this.worlds.add(world);
		save("Excluded-Worlds", worlds);
		return false;
	}

	public boolean removeWorld(String world) {
		if (!this.worlds.contains(world))
			return true;
		this.worlds.remove(world);
		save("Excluded-Worlds", worlds);
		return false;
	}

	private boolean bungeeCordAlerts;
	private List<String> worlds;


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
		worlds = getStringList("Excluded-Worlds");
	}

	@Override
	public SerializedMap serialize() {
		return SerializedMap.ofArray(
				"BungeeCordAlerts", bungeeCordAlerts,
				"Excluded-Worlds", worlds
		);
	}
}