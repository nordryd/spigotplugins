package com.nordryd.util;

/**
 * <p>
 * Interface for storing values about the plugin and Spigot links.
 * </p>
 * 
 * @author Nordryd
 */
public interface IReference
{
	public static final String PLUGIN_NAME = "XtraParticlePlugin";
	public static final String CURRENT_VERSION = "1.7";

	public static final String RESOURCE_URL = "https://www.spigotmc.org/resources/betteradmin.65264";
	public static final String API_LINK = "https://api.spigotmc.org/legacy/update.php?resource=65264";

	/**
	 * Interface containing messages to display when the plugin starts and stops.
	 */
	public static interface InfoMessages
	{
		public static final String ON_ENABLE = IReference.PLUGIN_NAME + " started. Hello, world!";
		public static final String CONTACT = "Please contact " + Dev.DISCORD + " on discord with any problems.";
		public static final String ON_DISABLE = IReference.PLUGIN_NAME + " stopped. Goodbye, world!";
	}

	/**
	 * Interface containing information about the plugin's dev.
	 */
	public static interface Dev
	{
		public static final String PLAYER_NAME = "Nordryd";
		public static final String SIGO_PLAYER_NAME = "daniesings";
		public static final String DISCORD = "Nordryd#9298";
	}
}
