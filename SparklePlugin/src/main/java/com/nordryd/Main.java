package com.nordryd;

import java.util.logging.Logger;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import com.nordryd.event.EventListener;
import com.nordryd.util.Reference.Dev;
import com.nordryd.util.UpdateChecker;
import com.nordryd.util.Values.Config;

/**
 * <p>
 * Main class for <b>XtraParticlePlugin</b>
 * </p>
 * 
 * @author Nordryd
 */
public class Main extends JavaPlugin
{
	FileConfiguration config = getConfig();
	Logger logger = getLogger();

	@Override
	public void onEnable() {
		logger.info("Xtra Particle Plugin started. Hello, world!");
		logger.info("Please contact " + Dev.PLAYER_NAME + " on discord with any problems.");
		logger.info("Dev Discord: " + Dev.DISCORD);

		UpdateChecker.checkForUpdates(logger);

		config.addDefault(Config.DO_SPARKLES, true);
		config.addDefault(Config.DO_DUST, true);

		config.options().copyDefaults(true);
		saveConfig();

		getServer().getPluginManager().registerEvents(new EventListener(config), this);
	}

	@Override
	public void onDisable() {
		getLogger().info("Xtra Particle Plugin stopped. Goodbye, world!");
	}
}
