package net.warden.spigot.playerdata;

import io.github.retrooper.packetevents.enums.ClientVersion;
import net.warden.spigot.check.api.Check;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class PlayerData {
	private final UUID uuid;

	public boolean alerts = true;

	public PlayerData(final UUID uuid) {
		this.uuid = uuid;
	}

	//EXAMPLE VARIABLES, APPEND OR REMOVE if you like

	private Location location, lastLocation, to, from;
	private ClientVersion clientVersion;
	private double deltaXZ, deltaY;
	private boolean isSprinting, isSneaking, onGround;
	private long timeSinceJoin;
	private long timeSinceDamage;
	private long previousPreviousHit, previousHit, currentHit;

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


	public UUID getUniqueId() {
		return uuid;
	}
}
