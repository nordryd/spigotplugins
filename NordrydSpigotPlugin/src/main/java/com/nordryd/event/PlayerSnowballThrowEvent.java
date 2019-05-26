package com.nordryd.event;

import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

public class PlayerSnowballThrowEvent extends PlayerEvent
{
	private static final HandlerList HANDLERS = new HandlerList();
	private final Snowball snowball;
	
	public PlayerSnowballThrowEvent(Player player, Snowball snowball) {
		super(player);
		this.snowball = snowball;
	}
	
	public Snowball getSnowball() {
		return snowball;
	}
	
	public HandlerList getHandlers() {
		return HANDLERS;
	}
	
	public static HandlerList getHandlerList() {
		return HANDLERS;
	}
}
