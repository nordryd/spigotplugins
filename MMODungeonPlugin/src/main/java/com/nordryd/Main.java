package com.nordryd;

import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

import com.nordryd.enums.Commands;
import com.nordryd.enums.Config;
import com.nordryd.event.CommandEventListener;
import com.nordryd.event.EventListener;
import com.nordryd.util.IReference.InfoMessages;
import com.nordryd.world.InstanceManager;
import com.nordryd.util.UpdateChecker;

/**
 * <p>
 * Main class for <b><i><u>MMODungeonPlugin</u></i></b>.
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

        Config.registerDefaultConfig(this);
        Commands.registerCommands(this, new CommandEventListener(this));
        EventListener.registerEventListeners(this);
        InstanceManager.restartInstances(this);
        
        UpdateChecker.checkForUpdates(logger);
    }

    @Override
    public void onDisable() {
        logger.info(InfoMessages.ON_DISABLE);
    }
}
