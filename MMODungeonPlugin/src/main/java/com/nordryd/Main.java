package com.nordryd;

import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

import com.nordryd.command.CommandManager;
import com.nordryd.command.Commands;
import com.nordryd.config.ConfigManager;
import com.nordryd.event.EventListener;
import com.nordryd.instance.InstanceManager;
import com.nordryd.util.IReference.InfoMessages;
import com.nordryd.util.UpdateChecker;

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

        CommandManager cmdListener = new CommandManager(this);
        Commands.registerCommands(this, cmdListener, cmdListener);

        EventListener.registerEventListeners(this);

        UpdateChecker.checkForUpdates(logger);

        /**
         * TODO: get all the configs from PlayerConfig.WORLD_ON_LOGOUT and store it in a
         * map and restore it when the player joins?
         */
    }

    @Override
    public void onDisable() {
        logger.info(InfoMessages.ON_DISABLE);
    }
}
