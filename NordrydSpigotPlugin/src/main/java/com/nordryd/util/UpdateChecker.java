package com.nordryd.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.bukkit.plugin.java.JavaPlugin;

public class UpdateChecker extends Thread
{
	private final JavaPlugin plugin;
	
	public UpdateChecker(JavaPlugin plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public void run() {
		URLConnection conn = null;
		
		try {
			conn = new URL(Reference.API_LINK).openConnection();
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			
			if(reader.readLine().equals(Reference.CURRENT_VERSION)) {
				plugin.getLogger().info("No updates available atm.");
			}
			else {
				plugin.getLogger().info("There's an update! Download it at " + Reference.RESOURCE_URL);
			}
		}
		catch(MalformedURLException mue) {
			
		}
		catch(IOException ie) {
			
		}
	}
}
