package com.nordryd.player;

import java.util.Optional;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * <p>
 * Class for managing tasks involving players.
 * </p>
 * 
 * <p>
 * TODO Player might get disconnected in the lobby with their party members in
 * the lobby. In this case, either port all players out of the lobby, or attach
 * some metadata/config data to the player
 * (PlayerConfig.QUIT_IN_LOBBY_WITH_PARTY_STILL_INSIDE) to restore their state.
 * </p>
 * 
 * @author Nordryd
 */
public class PlayerManager
{
    public static void setPlayerInstanceReturnLocation(Player player) {
        Location location = player.getLocation();

        PlayerConfig.RETURN_LOCATIONS.setPlayerValue(player,
                player.getWorld().getName() + " " + location.getBlockX() + " " + location.getBlockY() + " " + location.getBlockZ());
    }

    public static void returnPlayer(Player player) {
        String receivedValue = PlayerConfig.RETURN_LOCATIONS.removePlayerValue(player);
        if (!receivedValue.isEmpty()) {
            String[] playerData = receivedValue.split(" ");
            Bukkit.getPlayer(playerData[0]).teleport(new Location(Bukkit.getWorld(playerData[1]), Integer.parseInt(playerData[2]),
                    Integer.parseInt(playerData[3]), Integer.parseInt(playerData[4])));
        }
        else {
            player.sendMessage(ChatColor.DARK_RED + "You are not in an instance");
        }
    }

    public static void storeExitingPlayerWorld(Player player) {
        PlayerConfig.WORLD_ON_LOGOUT.setPlayerValue(player, player.getWorld().getName());
    }

    public static void restorePlayerLocation(Player player) {
        String receivedValue = PlayerConfig.WORLD_ON_LOGOUT.removePlayerValue(player);

        if (!receivedValue.isEmpty()) {
            String[] playerData = receivedValue.split(" ");
            if (!Optional.ofNullable(Bukkit.getWorld(playerData[1])).isPresent()) {
                player.sendMessage("Your instance no longer exists! Returning you to your original location...");
                returnPlayer(player);
            }
        }
    }
}
