package net.warden.spigot.utils.nms.boundingbox;

import net.minecraft.server.v1_13_R2.AxisAlignedBB;
import org.bukkit.craftbukkit.v1_13_R2.entity.CraftEntity;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;

public class V_1_13_R2 {

	public Vector max;
	public Vector min;

	V_1_13_R2(Vector min, Vector max) {
		this.max = max;
		this.min = min;
	}

	public V_1_13_R2(Entity entity) {
		AxisAlignedBB bb = ((CraftEntity) entity).getHandle().getBoundingBox();
		min = new Vector(bb.minX - 0.03, bb.minY - 0.3, bb.minZ - 0.03);
		max = new Vector(bb.maxX + 0.03, bb.maxY + 0.4, bb.maxZ + 0.03);
	}

	public V_1_13_R2(AxisAlignedBB bb) {
		min = new Vector(bb.minX, bb.minY, bb.minZ);
		max = new Vector(bb.maxX, bb.maxY, bb.maxZ);
	}

	public Vector midPoint() {
		return max.clone().add(min).multiply(0.5);
	}

}
