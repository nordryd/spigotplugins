package com.nordryd.util;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * <p>
 * Interface to handle broadcasting messages in the context of this plugin.
 * Basically it places the plugin's prefix at the beginning of each message.
 * </p>
 * 
 * @author Nordryd
 */
public interface Broadcaster
{
	/**
	 * Broadcast a message with {@link Bukkit#broadcastMessage(String)} prepending
	 * the plugin's prefix.
	 * 
	 * @param message
	 *            The message to be broadcast.
	 */
	public static void broadcastMessage(String message) {
		Bukkit.broadcastMessage(Values.PREFIX + " " + message);
	}

	/**
	 * Send a player one or more messages with {@link Player#sendMessage(String)}
	 * prepending the plugin's prefix.
	 * 
	 * @param player
	 *            The player to whom the messages will be sent.
	 * @param messages
	 *            The message(s) to send.
	 */
	public static void sendMessage(Player player, String... messages) {
		for(String message : messages) {
			player.sendMessage(Values.PREFIX + " " + message);
		}
	}
}
