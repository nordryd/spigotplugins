package com.nordryd.enums;

/**
 * Enum for all strings and default values for the plugin configuration.
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
}
