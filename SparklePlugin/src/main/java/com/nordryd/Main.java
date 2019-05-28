package com.nordryd;

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

	@Override
	public void onEnable() {
		getLogger().info("Xtra Particle Plugin started. Hello, world!");
		getLogger().info("Please contact " + Dev.PLAYER_NAME + " on discord with any problems.");
		getLogger().info("Dev Discord: " + Dev.DISCORD);

		UpdateChecker updateChecker = new UpdateChecker(this.getLogger());
		updateChecker.start();

		config.addDefault(Config.DO_SPARKLES, true);

		config.options().copyDefaults(true);
		saveConfig();

		getServer().getPluginManager().registerEvents(new EventListener(config), this);
	}

	@Override
	public void onDisable() {
		getLogger().info("Xtra Particle Plugin stopped. Goodbye, world!");
	}
}
