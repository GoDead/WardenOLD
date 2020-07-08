package net.warden.spigot.utils.nms.boundingbox;

import net.minecraft.server.v1_14_R1.AxisAlignedBB;
import org.bukkit.craftbukkit.v1_14_R1.entity.CraftEntity;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;

public class V_1_14_R1 {

	public Vector max;
	public Vector min;

	V_1_14_R1(Vector min, Vector max) {
		this.max = max;
		this.min = min;
	}

	public V_1_14_R1(Entity entity) {
		AxisAlignedBB bb = ((CraftEntity) entity).getHandle().getBoundingBox();
		min = new Vector(bb.minX, bb.minY, bb.minZ);
		max = new Vector(bb.maxX, bb.maxY, bb.maxZ);
	}

	public V_1_14_R1(AxisAlignedBB bb) {
		min = new Vector(bb.minX, bb.minY, bb.minZ);
		max = new Vector(bb.maxX, bb.maxY, bb.maxZ);
	}

	public Vector midPoint() {
		return max.clone().add(min).multiply(0.5);
	}

}