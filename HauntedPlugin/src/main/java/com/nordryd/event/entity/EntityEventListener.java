package com.nordryd.event.entity;

import org.bukkit.event.entity.EntityEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.nordryd.event.EventListener;
import com.nordryd.event.entity.player.PlayerEventListener;

/**
 * <p>
 * Class to handle all {@link EntityEvent}s. {@link EntityEvent}s that pertain
 * specifically to players will be handled in {@link PlayerEventListener}.
 * </p>
 * 
 * @author Nordryd
 */
public class EntityEventListener extends EventListener
{
	/**
	 * Constructor.
	 * 
	 * @param plugin
	 *            {@link JavaPlugin}
	 */
	public EntityEventListener(JavaPlugin jPlugin) {
		super(jPlugin);
	}

	
}
