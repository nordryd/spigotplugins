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
	public static final int BEACON_SIN_RADIUS = 6;
	public static final int NULL_EXPLOSION = 0;
	public static final int LIGHTNING_STRIKE_RADIUS = 3;
	
	public static final float[] PITCH = {0.5f, 1.0f, 2.0f};

	/**
	 * Interface for storing time values (ms).
	 */
	public static interface Time
	{
		public static final long DAY = 0, NOON = 6000, NIGHT = 13000, MIDNIGHT = 18000;
	}

	/**
	 * Interface for storing any and all config.yml arguments.
	 */
	public static interface Config
	{
		public static final String YOU_ARE_AWESOME = "youAreAwesome";
		public static final String PLAYERS_SLEEPING_TO_SKIP_NIGHT = "playersSleepingToSkipNight";
	}
}
