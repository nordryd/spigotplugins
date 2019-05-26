package com.nordryd.event;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class StringEvent extends Event
{
	private static final HandlerList HANDLERS = new HandlerList();
	private final String string;
	
	public StringEvent(String string) {
		this.string = string;
	}
	
	public String getString() {
		return string;
	}
	
	public HandlerList getHandlers() {
		return HANDLERS;
	}
	
	public static HandlerList getHandlerList() {
		return HANDLERS;
	}
}
