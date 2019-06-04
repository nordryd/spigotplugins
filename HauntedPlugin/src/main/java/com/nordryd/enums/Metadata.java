package com.nordryd.enums;

import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * <p>
 * Enum containing all plugin metadata keys.
 * </p>
 * 
 * @author Nordryd
 */
public enum Metadata
{
	;

	private final String key;

	private Metadata(String string) {
		this.key = string;
	}

	/**
	 * @return The metadata's key.
	 */
	public String getKey() {
		return key;
	}

	/**
	 * Get a {@link FixedMetadataValue} based on the plugin and value given.
	 * 
	 * @param jPlugin
	 *            {@link JavaPlugin}
	 * @param value
	 *            Value for this metadata.
	 * @return A {@link FixedMetadataValue} object
	 */
	public static MetadataValue getMetadataValue(JavaPlugin jPlugin, Object value) {
		return new FixedMetadataValue(jPlugin, value);
	}
}
