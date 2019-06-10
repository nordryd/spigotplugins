package com.nordryd.enums;

import org.bukkit.plugin.java.JavaPlugin;

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
	DO_LOW_HEALTH_EFFECTS("doLowHealthEffects", true);

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
	 *        {@link JavaPlugin} to add config values to.
	 */
	public static void addDefaultConfig(JavaPlugin plugin) {
		for (Config configDefault : Config.values()) {
			plugin.getConfig().addDefault(configDefault.getKey(), configDefault.getDefault());
		}

		plugin.getConfig().options().copyDefaults(true);
		plugin.saveConfig();
	}
}
