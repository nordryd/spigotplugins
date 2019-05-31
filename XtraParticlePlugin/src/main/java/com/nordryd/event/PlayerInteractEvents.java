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
	/* add permissions for these later? */
	public static void onSignInteract(Player player, Sign sign) {
		String str = sign.getLine(1);
		if (str.equals(SignStrings.DING)) {
			player.giveExpLevels(1);
		}
		else if(str.equals(SignStrings.RESET_LEVEL)) {
			player.setLevel(0);
		}
		else if(str.equals(SignStrings.HIT_MEH)) {
			player.damage(2.0);
		}
	}
	
	private static interface SignStrings{
		String DING = "ding", RESET_LEVEL = "reset level", HIT_MEH = "hit meh";
	}
}
