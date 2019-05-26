package com.nordryd.event;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Egg;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LightningStrike;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerEggThrowEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.weather.LightningStrikeEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.nordryd.util.Values.Config;

public class EventListener implements Listener
{
	private JavaPlugin plugin;

	public EventListener(JavaPlugin plugin) {
		this.plugin = plugin;

		StringEvent sevent = new StringEvent("Fucking PlayerEvent... how does it work?");
		plugin.getServer().getPluginManager().callEvent(sevent);
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent pjevent) {
		Player player = pjevent.getPlayer();

		player.sendMessage(plugin.getConfig().getBoolean(Config.YOU_ARE_AWESOME) ? "You are awesome! " : "You are not awesome... ");
	}

	@EventHandler
	public void onPlayerEggThrow(PlayerEggThrowEvent petevent) {
		Egg egg = petevent.getEgg();
		World world = egg.getWorld();

		world.strikeLightning(egg.getLocation());
	}

	@EventHandler
	public void onLightningStrike(LightningStrikeEvent lsevent) {
		LightningStrike lightning = lsevent.getLightning();
		World world = lightning.getWorld();

		world.spawnEntity(lightning.getLocation(), EntityType.CAT);
	}

	@EventHandler
	public void onStringEvent(StringEvent sevent) {
		Bukkit.getServer().broadcastMessage(sevent.getString());
	}
}
