package com.nordryd.enums;

import org.bukkit.ChatColor;

public enum Role
{
	TANK(ChatColor.LIGHT_PURPLE + "TANK"), HEALER(ChatColor.AQUA + "HEALER"), DPS(ChatColor.RED + "DPS");
	
	private String string;
	
	private Role(String string) {
		this.string = string;
	}

	@Override
	public String toString() {
		return this.string;
	}
}
