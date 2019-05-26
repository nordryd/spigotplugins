package com.nordryd;

import static java.lang.System.out;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import com.nordryd.event.EventListener;
import com.nordryd.util.UpdateChecker;
import com.nordryd.util.Values;
import com.nordryd.util.Values.Config;

public class Main extends JavaPlugin
{
	FileConfiguration config = getConfig();

	@Override
	public void onEnable() {
		getLogger().info("onEnable has been invoked!");
		out.println(Values.PREFIX + " Hello!");
		UpdateChecker updateChecker = new UpdateChecker();
		updateChecker.run();

		config.addDefault(Config.YOU_ARE_AWESOME, true);
		config.options().copyDefaults(true);
		saveConfig();

		getServer().getPluginManager().registerEvents(new EventListener(this), this);
	}

	@Override
	public void onDisable() {
		getLogger().info("onDisable has been invoked!");
		out.println(Values.PREFIX + " Goodbye!");
	}
}
