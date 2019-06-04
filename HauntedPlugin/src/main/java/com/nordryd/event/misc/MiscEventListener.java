package com.nordryd.event.misc;

import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.java.JavaPlugin;

import com.nordryd.event.EventListener;

/**
 * <p>
 * Class to handle all miscellaneous {@link Event}s.
 * </p>
 * <p>
 * {@link EventHandler}s may be placed here until refactored to be grouped with
 * other methods.
 * </p>
 * 
 * @author Nordryd
 */
public class MiscEventListener extends EventListener
{
	/**
	 * Constructor.
	 * 
	 * @param plugin
	 *            {@link JavaPlugin}
	 */
	public MiscEventListener(JavaPlugin jPlugin) {
		super(jPlugin);
	}
}
