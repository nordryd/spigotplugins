package com.nordryd.event.entity.player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityEvent;
import org.bukkit.event.player.PlayerEggThrowEvent;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.nordryd.entity.Spirit;
import com.nordryd.event.EventListener;
import com.nordryd.util.IUtility;

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
	 *        {@code JavaPlugin}
	 */
	public PlayerEventListener(JavaPlugin jPlugin) {
		super(jPlugin);
	}

	@EventHandler
	public void onPlayerEggThrow(PlayerEggThrowEvent petevent) {
		petevent.setHatching(false);
		Spirit spirit = new Spirit(IUtility.getWorldServerFromWorld(petevent.getEgg().getWorld()), petevent.getEgg().getLocation(),
				"please kill the pig");
		// BukkitTask task = new SpiritThread(plugin,
		// petevent.getEgg().getLocation()).runTask(plugin);
	}
}
