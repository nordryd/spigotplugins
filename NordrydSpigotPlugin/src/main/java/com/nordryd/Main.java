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
		getLogger().info("Herro!");

		UpdateChecker updateChecker = new UpdateChecker(this.getLogger());
		updateChecker.start();

		config.addDefault(Config.YOU_ARE_AWESOME, true);
		config.options().copyDefaults(true);
		saveConfig();

		getServer().getPluginManager().registerEvents(new EventListener(), this);
	}

	@Override
	public void onDisable() {
		getLogger().info("Goodbaiaru!");
	}
}
