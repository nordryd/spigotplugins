package com.nordryd.util;

/**
 * <p>
 * Interface for various values used throughout the plugin. This is to reduce
 * the prevalence of magic values throughout the code.
 * </p>
 * 
 * @author Nordryd
 */
public interface Values
{
	public static final String DEV_PLAYER_NAME = "Nordryd";

	public static final int BEACON_SIN_RADIUS = 2;
	public static final int NULL_EXPLOSION = 0;
	public static final int LIGHTNING_STRIKE_RADIUS = 7;

	/**
	 * <p>
	 * Interface for storing any and all config.yml arguments.
	 * </p>
	 * 
	 * @author Nordryd
	 */
	public static interface Config
	{
		public static final String YOU_ARE_AWESOME = "youAreAwesome";
		public static final String TEST_NUMBER = "testNumber";
	}
}
