package com.nordryd.event;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.nordryd.enums.Commands;

/**
 * <p>
 * Class to handle all plugin commands.
 * </p>
 * 
 * @author Nordryd
 */
public class CommandEventListener extends EventListener implements CommandExecutor
{
    /**
     * Constructor.
     * 
     * @param plugin
     *        {@link JavaPlugin}
     */
    public CommandEventListener(JavaPlugin jPlugin) {
        super(jPlugin);
    }

    // Console = ConsoleCommandSender
    @Override
    public boolean onCommand(CommandSender cSender, Command cmd, String label, String[] args) {
        if (cSender instanceof Player) {
            if (label.equalsIgnoreCase(Commands.DEMO.getCommand()) && Commands.DEMO.isIssuedCommandValid(cSender, args.length)) {
                Bukkit.broadcastMessage(args[0] + " has issued the " + label + " command!");
                return true;
            }
        }
        else {

        }

        return false;
    }
}
