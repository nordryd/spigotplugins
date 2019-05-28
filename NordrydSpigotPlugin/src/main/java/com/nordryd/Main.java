package com.nordryd;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import com.nordryd.event.EventListener;
import com.nordryd.util.UpdateChecker;
import com.nordryd.util.Values.Config;

/**
 * <p>
 * Main class for <b>NordrydSpigotPlugin</b>
 * </p>
 * 
 * @author Nordryd
 */
public class Main extends JavaPlugin
{
	FileConfiguration config = getConfig();

	@Override
	public void onEnable() {
		getLogger().info("Hello, world!");

		UpdateChecker updateChecker = new UpdateChecker(this.getLogger());
		updateChecker.start();

		config.addDefault(Config.YOU_ARE_AWESOME, true);
		config.addDefault(Config.PLAYERS_SLEEPING_TO_SKIP_NIGHT, 1);
		
		config.options().copyDefaults(true);
		saveConfig();

		getServer().getPluginManager().registerEvents(new EventListener(config), this);
	}

	@Override
	public void onDisable() {
		getLogger().info("Goodbye, world!");
	}
}
