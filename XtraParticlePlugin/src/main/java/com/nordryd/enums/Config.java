package com.nordryd.enums;

import org.bukkit.plugin.java.JavaPlugin;

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
	DO_SPARKLE("doSparkles", true), DO_DUST("doDust", true), DO_SPELLEFFECT("doSpellEffect", true);

	private String string;
	private Object defaultValue;

	private Config(String string, Object defaultValue) {
		this.string = string;
		this.defaultValue = defaultValue;
	}

	public String getString() {
		return string;
	}

	public Object getDefault() {
		return defaultValue;
	}

	@Override
	public String toString() {
		return this.string;
	}

	/**
	 * Initializes all the configutation values with their respective defaults.
	 * @param plugin
	 * 			{@code JavaPlugin} to add config values to.
	 */
	public static void addDefaultConfig(JavaPlugin plugin) {
		for (Config configDefault : Config.values()) {
			plugin.getConfig().addDefault(configDefault.getString(), configDefault.getDefault());
		}

		plugin.getConfig().options().copyDefaults(true);
		plugin.saveConfig();
	}
}
