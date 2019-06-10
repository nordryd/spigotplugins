package com.nordryd.event;

import java.util.Random;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import com.nordryd.annotation.GameRegistration;

/**
 * <p>
 * Abstract class to represent all event listeners in the plugin.
 * </p>
 * 
 * @author Nordryd
 */
public abstract class EventListener implements Listener
{
	protected final JavaPlugin jPlugin;
	protected final Random rng;

	/**
	 * Constructor.
	 * 
	 * @param plugin
	 *            {@code JavaPlugin}
	 */
	protected EventListener(JavaPlugin jPlugin) {
		this.jPlugin = jPlugin;
		this.rng = new Random();
	}

	/**
	 * Called to initialize all {@code EventListener}s in the plugin.
	 * 
	 * @param jPlugin
	 *            {@code JavaPlugin}
	 */
	@GameRegistration
	public static void registerEventListeners(JavaPlugin jPlugin) {
		jPlugin.getServer().getPluginManager().registerEvents(new PlayerEventListener(jPlugin), jPlugin);
		jPlugin.getServer().getPluginManager().registerEvents(new EntityEventListener(jPlugin), jPlugin);
		jPlugin.getServer().getPluginManager().registerEvents(new BlockEventListener(jPlugin), jPlugin);
	}
}
