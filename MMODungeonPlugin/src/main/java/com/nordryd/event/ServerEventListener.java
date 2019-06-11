package com.nordryd.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.server.ServerLoadEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.nordryd.enums.Config;
import com.nordryd.util.IValues;

/**
 * <p>
 * Class for all {@link ServerEvents}.
 * </p>
 * 
 * @author Nordryd
 */
public class ServerEventListener extends EventListener
{
    /**
     * Constructor.
     * 
     * @param jPlugin
     *        {@link JavaPlugin}
     */
    public ServerEventListener(JavaPlugin jPlugin) {
        super(jPlugin);
    }

    @EventHandler
    public void onServerLoad(ServerLoadEvent slevent) {
        double lowHealthThreshold = jPlugin.getConfig().getDouble(Config.LOW_HEALTH_THRESHOLD.getKey());

        if (lowHealthThreshold <= 0.0) {
            jPlugin.getLogger().info("<WARNING> Config." + Config.LOW_HEALTH_THRESHOLD.getKey()
                    + " cannot be less than or equal to 0! Please use Config." + Config.DO_LOW_HEALTH_EFFECTS.getKey() + " to disable them.");
            jPlugin.getLogger().info("<WARNING> **Value has been set to " + IValues.LOW_HEALTH_THRESHOLD_UNDERFLOW_RESET);

            jPlugin.getConfig().set(Config.LOW_HEALTH_THRESHOLD.getKey(), IValues.LOW_HEALTH_THRESHOLD_UNDERFLOW_RESET);
            jPlugin.saveConfig();
        }
        else if (lowHealthThreshold >= IValues.PLAYER_HEALTH_MAX) {
            jPlugin.getLogger().info(
                    "<WARNING> Config." + Config.LOW_HEALTH_THRESHOLD.getKey() + " cannot be greater than or equal to the player's max health!");
            jPlugin.getLogger().info("<WARNING> **Value has been set to " + IValues.LOW_HEALTH_THRESHOLD_OVERFLOW_RESET + ".");

            jPlugin.getConfig().set(Config.LOW_HEALTH_THRESHOLD.getKey(), IValues.LOW_HEALTH_THRESHOLD_OVERFLOW_RESET);
            jPlugin.saveConfig();
        }
    }
}
