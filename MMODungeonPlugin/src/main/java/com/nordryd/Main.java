package com.nordryd;

import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

import com.nordryd.config.ConfigManager;
import com.nordryd.enums.Commands;
import com.nordryd.event.CommandEventListener;
import com.nordryd.event.EventListener;
import com.nordryd.util.IReference.InfoMessages;
import com.nordryd.util.UpdateChecker;
import com.nordryd.world.InstanceManager;

/**
 * <p>
 * Main class for <b><i><u>MMODungeonPlugin</u></i></b>.
 * </p>
 * 
 * <p>
 * To get plugin reference:<br>
 * {@code Main.getPlugin(Class<? extends JavaPlugin>)}
 * </p>
 * 
 * @author Nordryd
 */
public class Main extends JavaPlugin
{
    private final Logger logger = getLogger();

    @Override
    public void onEnable() {
        logger.info(InfoMessages.ON_ENABLE);
        logger.info(InfoMessages.CONTACT);

        InstanceManager.restartInstances();

        ConfigManager.registerConfigs(this);

        CommandEventListener cmdListener = new CommandEventListener(this);
        Commands.registerCommands(this, cmdListener, cmdListener);

        EventListener.registerEventListeners(this);

        UpdateChecker.checkForUpdates(logger);
    }

    @Override
    public void onDisable() {
        logger.info(InfoMessages.ON_DISABLE);
    }
}
