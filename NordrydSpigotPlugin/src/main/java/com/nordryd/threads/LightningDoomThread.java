package com.nordryd.threads;

import org.bukkit.event.Event;

/**
 * <p>
 * Thread class for the impending doom of a player by slowly approaching
 * lightning. It should attempt to follow the player, and not stop until it
 * reaches them. <b><i>MWAHAHAHAHAHAHAHAHAH!!!</i></b>
 * </p>
 * 
 * <p>
 * Following the player will require some pathing in order to figure out how to
 * get to the player. Might be fun.
 * </p>
 * 
 * <p>
 * Alternatively, consider using {@code Bukkit.getScheduler} instead to have
 * something inside Spigot to use.
 * </p>
 * 
 * @author Nordryd
 */
public class LightningDoomThread extends Thread
{
	private final Event event;
	private final long delay;

	/**
	 * <p>
	 * Constructor
	 * </p>
	 * 
	 * @param event
	 *            Event object.
	 * @param delay
	 *            The delay between each tick in milliseconds (ms).
	 */
	public LightningDoomThread(Event event, long delay) {
		this.event = event;
		this.delay = delay;
	}

	@Override
	public void run() {

	}
}
