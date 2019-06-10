package com.nordryd.enums;

import org.bukkit.ChatColor;

/**
 * <p>
 * Different roles that a player can be inside a dungeon. Will be chosen in a
 * lobby with special blocks (or signs?)
 * </p>
 * 
 * @author Nordryd
 */
public enum Role
{
	/**
	 * Protects other party members from taking damage.
	 */
	TANK(ChatColor.LIGHT_PURPLE + "tank"),

	/**
	 * Mends other party members' wounds.
	 */
	HEALER(ChatColor.AQUA + "healer"),

	/**
	 * Deals damage to enemies.
	 */
	DAMAGE(ChatColor.RED + "damage");

	private String string;

	private Role(String string) {
		this.string = string;
	}

	@Override
	public String toString() {
		return this.string;
	}
}
