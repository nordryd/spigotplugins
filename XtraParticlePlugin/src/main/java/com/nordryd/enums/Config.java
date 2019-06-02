package com.nordryd.enums;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import com.nordryd.util.Values;

/**
 * <p>
 * Enum for all strings and default values for the plugin configuration, as well
 * as handling their initialization.
 * </p>
 * 
 * @author Nordryd
 */
public enum Config
{
	DO_SPARKLE("doSparkles", true),
	DO_DUST("doDust", true),
	DO_SPELLEFFECT("doSpellEffect", true),
	DO_ENCHANTING("doEnchanting", true),
	DO_DRAGONBREATH("doDragonBreath", true),
	DO_ITEMCRACK("doItemCrack", true),
	DO_FLAME("doFlame", true),
	DO_CUSTOM_LOGIN_MESSAGES("doCustomLoginMessages", true),
	DO_CUSTOM_LOGOUT_MESSAGES("doCustomLogoutMessages", true),
	LOGIN_MESSAGES("loginMessages",
			new ArrayList<String>(Arrays.asList(ChatColor.AQUA + Values.PLAYER_NAME_ESCAPE + ChatColor.DARK_AQUA + " has joined the game",
					ChatColor.DARK_AQUA + "Who the hell invited " + ChatColor.AQUA + Values.PLAYER_NAME_ESCAPE + ChatColor.DARK_AQUA + "?"))),
	LOGOUT_MESSAGES("logoutMessages",
			new ArrayList<String>(Arrays.asList(ChatColor.AQUA + Values.PLAYER_NAME_ESCAPE + ChatColor.DARK_AQUA + " has left the game",
					ChatColor.DARK_AQUA + "Thank god " + ChatColor.AQUA + Values.PLAYER_NAME_ESCAPE + ChatColor.DARK_AQUA + " is gone")));

	private final String key;
	private final Object defaultValue;

	private Config(String key, Object defaultValue) {
		this.key = key;
		this.defaultValue = defaultValue;
	}

	public String getKey() {
		return key;
	}

	public Object getDefault() {
		return defaultValue;
	}

	@Override
	public String toString() {
		return this.key;
	}

	/**
	 * Initializes all the configutation values with their respective defaults.
	 * 
	 * @param plugin
	 *            {@code JavaPlugin} to add config values to.
	 */
	public static void addDefaultConfig(JavaPlugin plugin) {
		for (Config configDefault : Config.values()) {
			plugin.getConfig().addDefault(configDefault.getKey(), configDefault.getDefault());
		}

		plugin.getConfig().options().copyDefaults(true);
		plugin.saveConfig();
	}
}
