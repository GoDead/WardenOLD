package net.warden.spigot.playerdata;

import io.github.retrooper.packetevents.enums.ClientVersion;
import net.warden.spigot.check.api.Check;
import net.warden.spigot.utils.XMaterial;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.mineacademy.fo.region.Region;

import java.util.*;

public final class PlayerData {
	private final UUID uuid;

	public boolean alerts = true;

	public PlayerData(final UUID uuid) {
		this.uuid = uuid;
	}

	//EXAMPLE VARIABLES, APPEND OR REMOVE if you like

	private Location location, lastLocation, to, from;
	private ClientVersion clientVersion;
	private double deltaXZ, deltaY, lastDeltaXZ, lastDeltaY;
	private boolean isSprinting, isSneaking, onGround;
	private long timeSinceJoin;
	private long timeSinceDamage;
	private long previousPreviousHit, previousHit, currentHit;
	private long lastFlight, lastInWater, lastGameModeSwitch, lastLevitation, lastTeleport, lastGlide;
	private long timeSinceFlagD;
	private long lastToggleFlight, lastExplosionDamage, lastVehicleAction, lastNearSlime, lastOnGround;
	private int hitsInARow;
	private int successHits, unsuccessHits;

	private final Map<Class<?>, Integer> violations = new HashMap<>();


	public Player getPlayer() {
		return Bukkit.getPlayer(getUniqueId());
	}

	public void setVLCount(final Check check, final int value) {
		violations.put(check.getClass(), value);
	}

	public int getVLCount(final Check check) {
		return violations.getOrDefault(check.getClass(), 0);
	}

	public void addVL(final Check check, final int val) {
		setVLCount(check, getVLCount(check) + val);
	}

	public void subtractVL(final Check check, final int val) {
		addVL(check, -val);
	}

	public void incrementVL(final Check check) {
		addVL(check, 1);
	}

	public void decrementVL(final Check check) {
		subtractVL(check, 1);
	}

	public void clearVL(final Check check) {
		setVLCount(check, 0);
	}

	public Map<Class<?>, Integer> getViolationsMap() {
		return violations;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Location getLastLocation() {
		return lastLocation;
	}

	public void setLastLocation(Location lastLocation) {
		this.lastLocation = lastLocation;
	}

	public Location getTo() {
		return to;
	}

	public void setTo(Location to) {
		this.to = to;
	}

	public Location getFrom() {
		return from;
	}

	public void setFrom(Location from) {
		this.from = from;
	}

	public ClientVersion getClientVersion() {
		return clientVersion;
	}

	public void setClientVersion(ClientVersion clientVersion) {
		this.clientVersion = clientVersion;
	}

	public double getDeltaXZ() {
		return deltaXZ;
	}

	public void setDeltaXZ(double deltaXZ) {
		this.deltaXZ = deltaXZ;
	}

	public double getDeltaY() {
		return deltaY;
	}

	public void setDeltaY(double deltaY) {
		this.deltaY = deltaY;
	}

	public boolean isSprinting() {
		return isSprinting;
	}

	public void setSprinting(boolean sprinting) {
		isSprinting = sprinting;
	}

	public boolean isSneaking() {
		return isSneaking;
	}

	public void setSneaking(boolean sneaking) {
		isSneaking = sneaking;
	}

	public boolean isOnGround() {
		return onGround;
	}

	public void setOnGround(boolean onGround) {
		this.onGround = onGround;
	}

	public long getTimeSinceJoin() {
		return timeSinceJoin;
	}

	public void setTimeSinceJoin(long timeSinceJoin) {
		this.timeSinceJoin = timeSinceJoin;
	}

	public long getTimeSinceDamage() {
		return timeSinceDamage;
	}

	public void setTimeSinceDamage(long timeSinceDamage) {
		this.timeSinceDamage = timeSinceDamage;
	}

	public long getPreviousHit() {
		return previousHit;
	}

	public void setPreviousHit(long previousHit) {
		this.previousHit = previousHit;
	}

	public long getPreviousPreviousHit() {
		return previousPreviousHit;
	}

	public void setPreviousPreviousHit(long previousPreviousHit) {
		this.previousPreviousHit = previousPreviousHit;
	}


	public long getCurrentHit() {
		return currentHit;
	}

	public void setCurrentHit(long currentHit) {
		this.currentHit = currentHit;
	}

	public long getLastFlight() {
		return lastFlight;
	}

	public void setLastFlight(long lastFlight) {
		this.lastFlight = lastFlight;
	}

	public long getLastInWater() {
		return lastInWater;
	}

	public void setLastInWater(long lastInWater) {
		this.lastInWater = lastInWater;
	}

	public long getTimeSinceFlagD() {
		return timeSinceFlagD;
	}

	public void setTimeSinceFlagD(long timeSinceFlagD) {
		this.timeSinceFlagD = timeSinceFlagD;
	}

	public long getLastToggleFlight() {
		return lastToggleFlight;
	}

	public void setLastToggleFlight(long lastToggleFlight) {
		this.lastToggleFlight = lastToggleFlight;
	}

	public long getLastNearSlime() {
		return lastNearSlime;
	}

	public void setLastNearSlime(long lastNearSlime) {
		this.lastNearSlime = lastNearSlime;
	}

	public long getLastVehicleAction() {
		return lastVehicleAction;
	}

	public void setLastVehicleAction(long lastVehicleAction) {
		this.lastVehicleAction = lastVehicleAction;
	}

	public long getLastExplosionDamage() {
		return lastExplosionDamage;
	}

	public void setLastExplosionDamage(long lastExplosionDamage) {
		this.lastExplosionDamage = lastExplosionDamage;
	}

	public long getLastGameModeSwitch() {
		return lastGameModeSwitch;
	}

	public void setLastGameModeSwitch(long lastGameModeSwitch) {
		this.lastGameModeSwitch = lastGameModeSwitch;
	}

	public long getLastLevitation() {
		return lastLevitation;
	}

	public void setLastLevitation(long lastLevitation) {
		this.lastLevitation = lastLevitation;
	}

	public long getLastTeleport() {
		return lastTeleport;
	}

	public void setLastTeleport(long lastTeleport) {
		this.lastTeleport = lastTeleport;
	}

	public int getHitsInARow() {
		return hitsInARow;
	}

	public void setHitsInARow(int hitsInARow) {
		this.hitsInARow = hitsInARow;
	}

	public void addHitsInARow(int amount) {
		setHitsInARow(getHitsInARow() + amount);
	}

	public int getSuccessHits() {
		return successHits;
	}

	public void setSuccessHits(int successHits) {
		this.successHits = successHits;
	}

	public int getUnsuccessHits() {
		return unsuccessHits;
	}

	public void setUnsuccessHits(int unsuccessHits) {
		this.unsuccessHits = unsuccessHits;
	}

	public double getLastDeltaXZ() {
		return lastDeltaXZ;
	}

	public void setLastDeltaXZ(double lastDeltaXZ) {
		this.lastDeltaXZ = lastDeltaXZ;
	}

	public double getLastDeltaY() {
		return lastDeltaY;
	}

	public void setLastDeltaY(double lastDeltaY) {
		this.lastDeltaY = lastDeltaY;
	}

	public long getLastOnGround() {
		return lastOnGround;
	}

	public void setLastOnGround(long lastOnGround) {
		this.lastOnGround = lastOnGround;
	}

	public long getLastGlide() {
		return lastGlide;
	}

	public void setLastGlide(long lastGlide) {
		this.lastGlide = lastGlide;
	}

	public boolean isNear(XMaterial material) {
		if (!material.isSupported()) return false;
		List<Boolean> materialList = new ArrayList<>();
		if (getTo() == null) return false;
		Region region = new Region(getTo().clone().add(1, -1, 1), getTo().clone().add(-1, 1, -1));
		region.getBlocks().forEach(block -> {
			if (block.getType() == material.parseMaterial()) {
				materialList.add(true);
			}
		});
		return !materialList.isEmpty();
	}

	public boolean isNearStairs(Location location) {
		List<Boolean> stairs = new ArrayList<>();
		Region region = new Region(location.clone().add(1, -0.5, 1), location.clone().add(-1, 0.5, -1));
		List<Block> blocks = region.getBlocks();
		blocks.forEach(block -> {
			if (block.toString().toLowerCase().contains("stair")) {
				stairs.add(true);
			}
		});
		return !stairs.isEmpty();
	}

	public boolean isNearSlabs(Location location) {
		List<Boolean> stairs = new ArrayList<>();
		Region region = new Region(location.clone().add(1, -0.5, 1), location.clone().add(-1, 0.5, -1));
		List<Block> blocks = region.getBlocks();
		blocks.forEach(block -> {
			if (block.toString().toLowerCase().contains("slab")) {
				stairs.add(true);
			}
		});
		return !stairs.isEmpty();
	}

	public UUID getUniqueId() {
		return uuid;
	}
}
