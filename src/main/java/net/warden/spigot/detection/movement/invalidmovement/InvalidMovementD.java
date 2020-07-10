package net.warden.spigot.detection.movement.invalidmovement;

import net.warden.spigot.check.api.PrivateCheck;
import net.warden.spigot.check.api.data.Category;
import net.warden.spigot.events.PrivateCheckEvent;
import net.warden.spigot.playerdata.PlayerData;

public class InvalidMovementD extends PrivateCheck {
	public InvalidMovementD(PlayerData data) {
		super(data, "InvalidMovement", 'D', Category.MOVEMENT);
	}

	@Override
	public PrivateCheckEvent onCheck(PrivateCheckEvent e) {
		return e;
	}

}
