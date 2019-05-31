package com.nordryd;

import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

import com.nordryd.enums.Config;
import com.nordryd.event.EventListener;
import com.nordryd.util.Reference.InfoMessages;
import com.nordryd.util.UpdateChecker;

/**
 * <p>
 * Main class for <b>XtraParticlePlugin</b>.
 * </p>
 * 
 * @author Nordryd
 */
public class Main extends JavaPlugin
{
	Logger logger = getLogger();

	@Override
	public void onEnable() {
		logger.info(InfoMessages.ON_ENABLE);
		logger.info(InfoMessages.CONTACT);

		UpdateChecker.checkForUpdates(logger);
		Config.addDefaultConfig(this);

		getServer().getPluginManager().registerEvents(new EventListener(this), this);
	}

	@Override
	public void onDisable() {
		getLogger().info(InfoMessages.ON_DISABLE);
	}
}
