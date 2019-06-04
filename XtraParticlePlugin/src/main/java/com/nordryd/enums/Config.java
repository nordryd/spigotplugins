package com.nordryd.enums;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import com.nordryd.util.IValues;

/**
 * <p>
 * Enum for all strings and default values for the plugin configuration, as well
 * as handling their initialization.
 * </p>
 * 
 * <p>
 * TODO Redo to work for specific events (like DO_MOB_AGGRO & DO_MOB_GORE),
 * instead of generalized particles.
 * </p>
 * 
 * @author Nordryd
 */
public enum Config
{
	DO_ENCHANTED_WEAPON_PARTICLES("doEnchantedWeaponParticles", true),
	DO_ENCHANTED_TOOL_PARTICLES("doEnchantedToolParticles", true),
	DO_ORE_BREAK_PARTICLES("doOreBreakParticles", true),
	DO_LEAF_DECAY_PARTICLES("doLeafDecayParticles", true),
	DO_REDSTONE_PARTICLES("doRedstoneParticles", true),
	DO_DING_PARTICLES("doDingParticles", true),
	DO_ENTITY_DEATH_PARTICLES("doEntityDeathParticles", true),
	DO_MOB_AGGRO_PARTICLES("doMobAggroParticles", true),
	DO_MOB_GORE_PARTICLES("doMobGoreParticles", true),
	DO_LOW_HEALTH_EFFECTS("doLowHealthEffects", true),
	DO_CUSTOM_JOIN_MESSAGES("doCustomJoinMessages", true),
	DO_CUSTOM_LEAVE_MESSAGES("doCustomLeaveMessages", true),
	LOGIN_MESSAGES("loginMessages",
			new ArrayList<String>(Arrays.asList(ChatColor.AQUA + IValues.PLAYER_NAME_ESCAPE + ChatColor.DARK_AQUA + " has joined the game",
					ChatColor.DARK_AQUA + "Who the hell invited " + ChatColor.AQUA + IValues.PLAYER_NAME_ESCAPE + ChatColor.DARK_AQUA + "?"))),
	LOGOUT_MESSAGES("logoutMessages",
			new ArrayList<String>(Arrays.asList(ChatColor.AQUA + IValues.PLAYER_NAME_ESCAPE + ChatColor.DARK_AQUA + " has left the game",
					ChatColor.DARK_AQUA + "Thank god " + ChatColor.AQUA + IValues.PLAYER_NAME_ESCAPE + ChatColor.DARK_AQUA + " is gone")));

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
	 *            {@link JavaPlugin} to add config values to.
	 */
	public static void addDefaultConfig(JavaPlugin plugin) {
		for (Config configDefault : Config.values()) {
			plugin.getConfig().addDefault(configDefault.getKey(), configDefault.getDefault());
		}

		plugin.getConfig().options().copyDefaults(true);
		plugin.saveConfig();
	}
}
