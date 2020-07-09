package net.warden.spigot.utils.nms.boundingbox;

import net.minecraft.server.v1_9_R1.AxisAlignedBB;
import org.bukkit.craftbukkit.v1_9_R1.entity.CraftEntity;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;

public class V_1_9_R1 {

	public Vector max;
	public Vector min;

	V_1_9_R1(Vector min, Vector max) {
		this.max = max;
		this.min = min;
	}

	public V_1_9_R1(Entity entity) {
		AxisAlignedBB bb = ((CraftEntity) entity).getHandle().getBoundingBox();
		min = new Vector(bb.a - 0.03, bb.b - 0.3, bb.c - 0.03);
		max = new Vector(bb.d + 0.03, bb.e + 0.4, bb.f + 0.03);
	}

	public V_1_9_R1(AxisAlignedBB bb) {
		min = new Vector(bb.a, bb.b, bb.c);
		max = new Vector(bb.d, bb.e, bb.f);
	}

	public Vector midPoint() {
		return max.clone().add(min).multiply(0.5);
	}

}