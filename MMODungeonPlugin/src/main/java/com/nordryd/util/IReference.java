package com.nordryd.util;

import com.nordryd.enums.ANSIColor;
import com.nordryd.util.annotation.PluginUtility;

/**
 * <p>
 * Utility interface for storing values about the plugin and Spigot links.
 * </p>
 * 
 * @author Nordryd
 */
@PluginUtility
public interface IReference
{
    public static final String PLUGIN_NAME = "MMODungeonPlugin";
    public static final String CURRENT_VERSION = "1.7";

    public static final String RESOURCE_URL = "https://www.spigotmc.org/resources/betteradmin.65264";
    public static final String API_LINK = "https://api.spigotmc.org/legacy/update.php?resource=65264";

    /**
     * <p>
     * Interface containing messages to display when the plugin starts and stops.
     * </p>
     */
    @PluginUtility
    public static interface InfoMessages
    {
        public static final String ON_ENABLE = ANSIColor.CYAN.getSeq() + IReference.PLUGIN_NAME + " started. Hello, world!"
                + ANSIColor.RESET.getSeq();
        public static final String CONTACT = ANSIColor.CYAN.getSeq() + "Please contact " + Dev.DISCORD + " on discord with any problems."
                + ANSIColor.RESET.getSeq();
        public static final String ON_DISABLE = ANSIColor.CYAN.getSeq() + IReference.PLUGIN_NAME + " stopped. Goodbye, world!"
                + ANSIColor.RESET.getSeq();
    }

    /**
     * <p>
     * Interface containing information about the plugin's dev.
     * </p>
     */
    @PluginUtility
    public static interface Dev
    {
        public static final String PLAYER_NAME = "Nordryd";
        public static final String DISCORD = "Nordryd#9298";
    }
}
