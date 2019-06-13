package com.nordryd.event;

import java.util.Random;

import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.nordryd.enums.ColorEnumHandler.ANSIColor;
import com.nordryd.util.annotation.GameRegistration;

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
     *        {@code JavaPlugin}
     */
    protected EventListener(JavaPlugin jPlugin) {
        this.jPlugin = jPlugin;
        this.rng = new Random();
    }

    /**
     * Called to initialize all {@code EventListener}s in the plugin.
     * 
     * @param jPlugin
     *        {@code JavaPlugin}
     */
    @GameRegistration
    public static void registerEventListeners(JavaPlugin jPlugin) {
        PluginManager pluginManager = jPlugin.getServer().getPluginManager();
        
        pluginManager.registerEvents(new PlayerEventListener(jPlugin), jPlugin);
        pluginManager.registerEvents(new EntityEventListener(jPlugin), jPlugin);
        pluginManager.registerEvents(new BlockEventListener(jPlugin), jPlugin);
        pluginManager.registerEvents(new CommandEventListener(jPlugin), jPlugin);
        pluginManager.registerEvents(new ServerEventListener(jPlugin), jPlugin);
        pluginManager.registerEvents(new WorldEventListener(jPlugin), jPlugin);
        
        jPlugin.getLogger().info(ANSIColor.YELLOW + "EventListeners registered successfully!" + ANSIColor.RESET);
    }
}
