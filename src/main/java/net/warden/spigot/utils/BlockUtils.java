package net.warden.spigot.utils;

import org.bukkit.block.Block;

public class BlockUtils {

	public static XMaterial getType(Block block) {
		String input = block.toString();
		String extracted;
		if (!input.contains("type=") || !input.contains(",data"))
			return XMaterial.AIR;

		extracted = input.substring(input.indexOf("type="), input.indexOf(",data"));
		extracted = extracted.replace("type=", "");
		//Common.broadcast(XMaterial.matchXMaterial(extracted).toString());
		return XMaterial.valueOf(XMaterial.matchXMaterial(extracted).toString().toUpperCase().replace("OPTIONAL[", "").replace("]", "").replace(" ", "_"));
	}
}
