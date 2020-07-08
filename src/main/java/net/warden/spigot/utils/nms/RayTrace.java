package net.warden.spigot.utils.nms;

import net.warden.spigot.utils.nms.boundingbox.*;
import org.bukkit.Effect;
import org.bukkit.World;
import org.bukkit.util.Vector;

import java.util.ArrayList;

public class RayTrace {

	//origin = start position
	//direction = direction in which the raytrace will go
	Vector origin, direction;

	public RayTrace(Vector origin, Vector direction) {
		this.origin = origin;
		this.direction = direction;
	}

	//get a point on the raytrace at X blocks away
	public Vector getPostion(double blocksAway) {
		return origin.clone().add(direction.clone().multiply(blocksAway));
	}

	//checks if a position is on contained within the position
	public boolean isOnLine(Vector position) {
		double t = (position.getX() - origin.getX()) / direction.getX();
		if (position.getBlockY() == origin.getY() + (t * direction.getY()) && position.getBlockZ() == origin.getZ() + (t * direction.getZ())) {
			return true;
		}
		return false;
	}

	//get all postions on a raytrace
	public ArrayList<Vector> traverse(double blocksAway, double accuracy) {
		ArrayList<Vector> positions = new ArrayList<>();
		for (double d = 0; d <= blocksAway; d += accuracy) {
			positions.add(getPostion(d));
		}
		return positions;
	}

	//intersection detection for current raytrace with return
	public Vector positionOfIntersection(Vector min, Vector max, double blocksAway, double accuracy) {
		ArrayList<Vector> positions = traverse(blocksAway, accuracy);
		for (Vector position : positions) {
			if (intersects(position, min, max)) {
				return position;
			}
		}
		return null;
	}

	//intersection detection for current raytrace
	public boolean intersects(Vector min, Vector max, double blocksAway, double accuracy) {
		ArrayList<Vector> positions = traverse(blocksAway, accuracy);
		for (Vector position : positions) {
			if (intersects(position, min, max)) {
				return true;
			}
		}
		return false;
	}

	public Vector positionOfIntersection(V_1_8_R1 boundingBox, double blocksAway, double accuracy) {
		ArrayList<Vector> positions = traverse(blocksAway, accuracy);
		for (Vector position : positions) {
			if (intersects(position, boundingBox.min, boundingBox.max)) {
				return position;
			}
		}
		return null;
	}

	public boolean intersects(V_1_8_R1 boundingBox, double blocksAway, double accuracy) {
		ArrayList<Vector> positions = traverse(blocksAway, accuracy);
		for (Vector position : positions) {
			if (intersects(position, boundingBox.min, boundingBox.max)) {
				return true;
			}
		}
		return false;
	}

	public Vector positionOfIntersection(V_1_8_R2 boundingBox, double blocksAway, double accuracy) {
		ArrayList<Vector> positions = traverse(blocksAway, accuracy);
		for (Vector position : positions) {
			if (intersects(position, boundingBox.min, boundingBox.max)) {
				return position;
			}
		}
		return null;
	}

	public boolean intersects(V_1_8_R2 boundingBox, double blocksAway, double accuracy) {
		ArrayList<Vector> positions = traverse(blocksAway, accuracy);
		for (Vector position : positions) {
			if (intersects(position, boundingBox.min, boundingBox.max)) {
				return true;
			}
		}
		return false;
	}

	public Vector positionOfIntersection(V_1_8_R3 boundingBox, double blocksAway, double accuracy) {
		ArrayList<Vector> positions = traverse(blocksAway, accuracy);
		for (Vector position : positions) {
			if (intersects(position, boundingBox.min, boundingBox.max)) {
				return position;
			}
		}
		return null;
	}

	public boolean intersects(V_1_8_R3 boundingBox, double blocksAway, double accuracy) {
		ArrayList<Vector> positions = traverse(blocksAway, accuracy);
		for (Vector position : positions) {
			if (intersects(position, boundingBox.min, boundingBox.max)) {
				//Common.broadcast(boundingBox.min + " " + boundingBox.max);
				return true;
			}
		}
		return false;
	}

	public Vector positionOfIntersection(V_1_9_R1 boundingBox, double blocksAway, double accuracy) {
		ArrayList<Vector> positions = traverse(blocksAway, accuracy);
		for (Vector position : positions) {
			if (intersects(position, boundingBox.min, boundingBox.max)) {
				return position;
			}
		}
		return null;
	}

	public boolean intersects(V_1_9_R1 boundingBox, double blocksAway, double accuracy) {
		ArrayList<Vector> positions = traverse(blocksAway, accuracy);
		for (Vector position : positions) {
			if (intersects(position, boundingBox.min, boundingBox.max)) {
				return true;
			}
		}
		return false;
	}

	public Vector positionOfIntersection(V_1_9_R2 boundingBox, double blocksAway, double accuracy) {
		ArrayList<Vector> positions = traverse(blocksAway, accuracy);
		for (Vector position : positions) {
			if (intersects(position, boundingBox.min, boundingBox.max)) {
				return position;
			}
		}
		return null;
	}

	public boolean intersects(V_1_9_R2 boundingBox, double blocksAway, double accuracy) {
		ArrayList<Vector> positions = traverse(blocksAway, accuracy);
		for (Vector position : positions) {
			if (intersects(position, boundingBox.min, boundingBox.max)) {
				return true;
			}
		}
		return false;
	}

	public Vector positionOfIntersection(V_1_10_R1 boundingBox, double blocksAway, double accuracy) {
		ArrayList<Vector> positions = traverse(blocksAway, accuracy);
		for (Vector position : positions) {
			if (intersects(position, boundingBox.min, boundingBox.max)) {
				return position;
			}
		}
		return null;
	}

	public boolean intersects(V_1_10_R1 boundingBox, double blocksAway, double accuracy) {
		ArrayList<Vector> positions = traverse(blocksAway, accuracy);
		for (Vector position : positions) {
			if (intersects(position, boundingBox.min, boundingBox.max)) {
				return true;
			}
		}
		return false;
	}

	public Vector positionOfIntersection(V_1_11_R1 boundingBox, double blocksAway, double accuracy) {
		ArrayList<Vector> positions = traverse(blocksAway, accuracy);
		for (Vector position : positions) {
			if (intersects(position, boundingBox.min, boundingBox.max)) {
				return position;
			}
		}
		return null;
	}

	public boolean intersects(V_1_11_R1 boundingBox, double blocksAway, double accuracy) {
		ArrayList<Vector> positions = traverse(blocksAway, accuracy);
		for (Vector position : positions) {
			if (intersects(position, boundingBox.min, boundingBox.max)) {
				return true;
			}
		}
		return false;
	}

	public Vector positionOfIntersection(V_1_12_R1 boundingBox, double blocksAway, double accuracy) {
		ArrayList<Vector> positions = traverse(blocksAway, accuracy);
		for (Vector position : positions) {
			if (intersects(position, boundingBox.min, boundingBox.max)) {
				return position;
			}
		}
		return null;
	}

	public boolean intersects(V_1_12_R1 boundingBox, double blocksAway, double accuracy) {
		ArrayList<Vector> positions = traverse(blocksAway, accuracy);
		for (Vector position : positions) {
			if (intersects(position, boundingBox.min, boundingBox.max)) {
				return true;
			}
		}
		return false;
	}

	public Vector positionOfIntersection(V_1_13_R1 boundingBox, double blocksAway, double accuracy) {
		ArrayList<Vector> positions = traverse(blocksAway, accuracy);
		for (Vector position : positions) {
			if (intersects(position, boundingBox.min, boundingBox.max)) {
				return position;
			}
		}
		return null;
	}

	public boolean intersects(V_1_13_R1 boundingBox, double blocksAway, double accuracy) {
		ArrayList<Vector> positions = traverse(blocksAway, accuracy);
		for (Vector position : positions) {
			if (intersects(position, boundingBox.min, boundingBox.max)) {
				return true;
			}
		}
		return false;
	}

	public Vector positionOfIntersection(V_1_13_R2 boundingBox, double blocksAway, double accuracy) {
		ArrayList<Vector> positions = traverse(blocksAway, accuracy);
		for (Vector position : positions) {
			if (intersects(position, boundingBox.min, boundingBox.max)) {
				return position;
			}
		}
		return null;
	}

	public boolean intersects(V_1_13_R2 boundingBox, double blocksAway, double accuracy) {
		ArrayList<Vector> positions = traverse(blocksAway, accuracy);
		for (Vector position : positions) {
			if (intersects(position, boundingBox.min, boundingBox.max)) {
				return true;
			}
		}
		return false;
	}

	public Vector positionOfIntersection(V_1_14_R1 boundingBox, double blocksAway, double accuracy) {
		ArrayList<Vector> positions = traverse(blocksAway, accuracy);
		for (Vector position : positions) {
			if (intersects(position, boundingBox.min, boundingBox.max)) {
				return position;
			}
		}
		return null;
	}

	public boolean intersects(V_1_14_R1 boundingBox, double blocksAway, double accuracy) {
		ArrayList<Vector> positions = traverse(blocksAway, accuracy);
		for (Vector position : positions) {
			if (intersects(position, boundingBox.min, boundingBox.max)) {
				return true;
			}
		}
		return false;
	}

	public Vector positionOfIntersection(V_1_15_R1 boundingBox, double blocksAway, double accuracy) {
		ArrayList<Vector> positions = traverse(blocksAway, accuracy);
		for (Vector position : positions) {
			if (intersects(position, boundingBox.min, boundingBox.max)) {
				return position;
			}
		}
		return null;
	}

	public boolean intersects(V_1_15_R1 boundingBox, double blocksAway, double accuracy) {
		ArrayList<Vector> positions = traverse(blocksAway, accuracy);
		for (Vector position : positions) {
			if (intersects(position, boundingBox.min, boundingBox.max)) {
				return true;
			}
		}
		return false;
	}

	public Vector positionOfIntersection(V_1_16_R1 boundingBox, double blocksAway, double accuracy) {
		ArrayList<Vector> positions = traverse(blocksAway, accuracy);
		for (Vector position : positions) {
			if (intersects(position, boundingBox.min, boundingBox.max)) {
				return position;
			}
		}
		return null;
	}

	public boolean intersects(V_1_16_R1 boundingBox, double blocksAway, double accuracy) {
		ArrayList<Vector> positions = traverse(blocksAway, accuracy);
		for (Vector position : positions) {
			if (intersects(position, boundingBox.min, boundingBox.max)) {
				return true;
			}
		}
		return false;
	}

	//general intersection detection
	public static boolean intersects(Vector position, Vector min, Vector max) {
		if (position.getX() < min.getX() || position.getX() > max.getX()) {
			return false;
		} else if (position.getY() < min.getY() || position.getY() > max.getY()) {
			return false;
		} else if (position.getZ() < min.getZ() || position.getZ() > max.getZ()) {
			return false;
		}
		return true;
	}

	//debug / effects
	public void highlight(World world, double blocksAway, double accuracy) {
		for (Vector position : traverse(blocksAway, accuracy)) {
			world.playEffect(position.toLocation(world), Effect.SMOKE, 0);
		}
	}

}