package net.warden.spigot.utils;


import io.github.retrooper.packetevents.event.impl.BukkitMoveEvent;
import org.bukkit.Location;

public class Distance {

	private Location from, to;

	private Double xDiff, yDiff, zDiff;

	public Distance(BukkitMoveEvent event) {
		this.from = event.getFrom();
		this.to = event.getTo();
		this.xDiff = (from.getX() > to.getX() ? from.getX() : to.getX()) - (from.getX() < to.getX() ? from.getX() : to.getX());
		this.yDiff = (from.getY() > to.getY() ? from.getY() : to.getY()) - (from.getY() < to.getY() ? from.getY() : to.getY());
		this.zDiff = (from.getZ() > to.getZ() ? from.getZ() : to.getZ()) - (from.getZ() < to.getZ() ? from.getZ() : to.getZ());
	}

	public Location getFrom() {
		return from;
	}

	public void setFrom(Location from) {
		this.from = from;
	}

	public Location getTo() {
		return to;
	}

	public void setTo(Location to) {
		this.to = to;
	}

	public Double getxDiff() {
		return xDiff;
	}

	public void setxDiff(Double xDiff) {
		this.xDiff = xDiff;
	}

	public Double getyDiff() {
		return yDiff;
	}

	public void setyDiff(Double yDiff) {
		this.yDiff = yDiff;
	}

	public Double getzDiff() {
		return zDiff;
	}

	public void setzDiff(Double zDiff) {
		this.zDiff = zDiff;
	}

}