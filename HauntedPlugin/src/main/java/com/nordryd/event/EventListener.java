package com.nordryd.event;

import java.util.Random;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import com.nordryd.event.block.BlockEventListener;
import com.nordryd.event.entity.EntityEventListener;
import com.nordryd.event.entity.player.PlayerEventListener;
import com.nordryd.event.misc.MiscEventListener;

/**
 * <p>
 * Abstract class to represent all event listeners in the plugin.
 * </p>
 * 
 * @author Nordryd
 */
public abstract class EventListener implements Listener
{
	protected final JavaPlugin plugin;
	protected final Random rng;

	/**
	 * Constructor.
	 * 
	 * @param plugin
	 *            {@code JavaPlugin}
	 */
	protected EventListener(JavaPlugin jPlugin) {
		this.plugin = jPlugin;
		this.rng = new Random();
	}

	/**
	 * Called to initialize all {@code EventListener}s in the plugin.
	 * 
	 * @param jPlugin
	 *            {@code JavaPlugin}
	 */
	public static void initializeEventListeners(JavaPlugin jPlugin) {
		jPlugin.getServer().getPluginManager().registerEvents(new MiscEventListener(jPlugin), jPlugin);
		jPlugin.getServer().getPluginManager().registerEvents(new PlayerEventListener(jPlugin), jPlugin);
		jPlugin.getServer().getPluginManager().registerEvents(new EntityEventListener(jPlugin), jPlugin);
		jPlugin.getServer().getPluginManager().registerEvents(new BlockEventListener(jPlugin), jPlugin);
	}
}
