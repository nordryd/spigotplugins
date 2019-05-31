package com.nordryd.event.entity.player;

import org.bukkit.block.Sign;
import org.bukkit.entity.Player;

import com.nordryd.util.Values;

/**
 * <p>
 * Class for all {@code PlayerInteractEvent} variants.
 * </p>
 * 
 * @author Nordryd
 */
public interface PlayerInteractEventHandler
{
	/* add permissions for these later? */
	public static void onSignInteract(Player player, Sign sign) {
		String str = sign.getLine(1);
		if (str.equals(Values.SignStrings.DING)) {
			player.giveExpLevels(1);
		}
		else if (str.equals(Values.SignStrings.RESET_LEVEL)) {
			player.setLevel(0);
		}
		else if (str.equals(Values.SignStrings.HIT_MEH)) {
			player.damage(2.0);
		}
	}
}
