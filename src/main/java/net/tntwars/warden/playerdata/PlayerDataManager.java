package net.tntwars.warden.playerdata;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public final class PlayerDataManager {
	private final List<PlayerData> playerDataList = new ArrayList<>();

	public PlayerData registerUser(final UUID uuid) {
		final PlayerData data = new PlayerData(uuid);
		playerDataList.add(data);
		return data;
	}

	public void unregisterUser(final UUID uuid) {
		playerDataList.remove(find(uuid));
	}

	public void unregisterUser(final PlayerData data) {
		playerDataList.remove(data);
	}

	public PlayerData find(final UUID uuid) {
		for (final PlayerData data : playerDataList) {
			if (data.getUniqueId() == uuid) {
				return data;
			}
		}
		return null;
	}

	public PlayerData update(final PlayerData data) {
		if (!exists(data.getUniqueId())) {
			registerUser(data.getUniqueId());
			return data;
		} else {
			for (int i = 0; i < playerDataList.size(); i++) {
				final PlayerData d = playerDataList.get(i);
				if (d.getUniqueId() == data.getUniqueId()) {
					playerDataList.set(i, data);
					return data;
				}
			}
			return null;
		}

	}

	public boolean exists(final UUID uuid) {
		return find(uuid) != null;
	}

	public List<PlayerData> getPlayerDataList() {
		return playerDataList;
	}
}
