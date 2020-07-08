package net.warden.spigot.utils.nms.boundingbox;

import net.minecraft.server.v1_10_R1.AxisAlignedBB;
import org.bukkit.craftbukkit.v1_10_R1.entity.CraftEntity;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;

public class V_1_10_R1 {

	public Vector max;
	public Vector min;

	V_1_10_R1(Vector min, Vector max) {
		this.max = max;
		this.min = min;
	}

	public V_1_10_R1(Entity entity) {
		AxisAlignedBB bb = ((CraftEntity) entity).getHandle().getBoundingBox();
		min = new Vector(bb.a, bb.b, bb.c);
		max = new Vector(bb.d, bb.e, bb.f);
	}

	public V_1_10_R1(AxisAlignedBB bb) {
		min = new Vector(bb.a, bb.b, bb.c);
		max = new Vector(bb.d, bb.e, bb.f);
	}

	public Vector midPoint() {
		return max.clone().add(min).multiply(0.5);
	}

}
