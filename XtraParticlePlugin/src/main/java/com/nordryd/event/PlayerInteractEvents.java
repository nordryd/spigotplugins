package com.nordryd.event;

import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * <p>
 * Class for all {@link PlayerInteractEvent} variants.
 * </p>
 * 
 * @author Nordryd
 */
public class PlayerInteractEvents
{
	public static void onSignInteract(Player player, Sign sign) {
		String str = sign.getLine(1);
		if (str.equals(SignStrings.DING)) {
			player.giveExpLevels(1);
		}
	}
	
	private interface SignStrings{
		static String DING = "ding";
	}
}
