package com.nordryd.enums;

/**
 * <p>
 * Enum containing all plugin metadata keys.
 * </p>
 * 
 * @author Nordryd
 */
public enum Metadata
{
	FIRED_FROM_ENCHANTED_WEAPON("firedFromEnchantedWeapon");

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
}
