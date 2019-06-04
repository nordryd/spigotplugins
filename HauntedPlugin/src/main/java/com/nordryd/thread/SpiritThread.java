package com.nordryd.thread;

import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class SpiritThread extends BukkitRunnable
{
	private JavaPlugin plugin;
	private Location location;

	public SpiritThread(JavaPlugin plugin, Location location) {
		this.plugin = plugin;
		this.location = location;
	}

	@Override
	public void run() {

		try {
			Thread.sleep(500);
		}
		catch (InterruptedException iexception) {
			
		}
		
		kill();
	}

	private void kill() {
		this.plugin = null;
		this.location = null;
		cancel();
	}
}
