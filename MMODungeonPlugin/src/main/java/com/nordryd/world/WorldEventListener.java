package com.nordryd.world;

import org.bukkit.event.EventHandler;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.event.world.WorldSaveEvent;
import org.bukkit.event.world.WorldUnloadEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.nordryd.enums.ColorEnumHandler.ANSIColor;
import com.nordryd.event.EventListener;

public class WorldEventListener extends EventListener
{
    /**
     * Constructor.
     * 
     * @param plugin
     *        {@link JavaPlugin}
     */
    public WorldEventListener(JavaPlugin jPlugin) {
        super(jPlugin);
    }

    @EventHandler
    public void onWorldLoad(WorldLoadEvent wlevent) {
        jPlugin.getLogger().info(ANSIColor.CYAN + "World " + wlevent.getWorld().getName() + " loaded!" + ANSIColor.RESET);
    }

    @EventHandler
    public void onWorldLoad(WorldUnloadEvent wuevent) {
        jPlugin.getLogger().info(ANSIColor.CYAN + "World " + wuevent.getWorld().getName() + " unloaded!" + ANSIColor.RESET);
    }

    @EventHandler
    public void onWorldSave(WorldSaveEvent wsevent) {
        jPlugin.getLogger().info(ANSIColor.CYAN + "World " + wsevent.getWorld().getName() + " loaded!" + ANSIColor.RESET);
    }
}
