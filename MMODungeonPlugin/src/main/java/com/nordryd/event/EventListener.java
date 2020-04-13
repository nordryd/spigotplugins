package com.nordryd.event;

import java.util.Random;

import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.nordryd.command.CommandManager;
import com.nordryd.enums.ColorEnumHandler.ANSIColor;
import com.nordryd.instance.LobbyManager;
import com.nordryd.player.PlayerEventListener;
import com.nordryd.util.annotation.GameRegistration;
import com.nordryd.world.WorldEventListener;

/**
 * <p>
 * Abstract class to represent all event listeners in the plugin.
 * </p>
 * 
 * <p>
 * TODO: PlayerInstanceEnterEvent, PlayerInstanceLeaveEvent, InstanceLoadEvent
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
        pluginManager.registerEvents(new CommandManager(jPlugin), jPlugin);
        pluginManager.registerEvents(new ServerEventListener(jPlugin), jPlugin);
        pluginManager.registerEvents(new WorldEventListener(jPlugin), jPlugin);
        pluginManager.registerEvents(new LobbyManager(jPlugin), jPlugin);

        jPlugin.getLogger().info(ANSIColor.YELLOW + "EventListeners registered successfully!" + ANSIColor.RESET);
    }
}
