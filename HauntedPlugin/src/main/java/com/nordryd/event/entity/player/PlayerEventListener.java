package com.nordryd.event.entity.player;

import org.bukkit.event.entity.EntityEvent;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.nordryd.event.EventListener;

/**
 * <p>
 * Class to handle all {@link PlayerEvent}s, or events that specifically involve
 * players (like {@link EntityEvent}s that are only handled for
 * {@code EntityType.PLAYER}).
 * </p>
 * 
 * @author Nordryd
 */
public class PlayerEventListener extends EventListener
{
	/**
	 * Constructor.
	 * 
	 * @param jPlugin
	 *            {@code JavaPlugin}
	 */
	public PlayerEventListener(JavaPlugin jPlugin) {
		super(jPlugin);
	}
}
