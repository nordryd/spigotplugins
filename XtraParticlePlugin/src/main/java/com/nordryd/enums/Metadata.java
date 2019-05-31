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
	FIRED_FROM_ENCHANTED_WEAPON("firedFromEnchantedWeapon"), PLAYER_HEALTH_LOW("playerHealthLow");

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
	
	public static MetadataValue getMetadataValue(JavaPlugin jPlugin, Object value) {
		return new FixedMetadataValue(jPlugin, value);
	}
}
