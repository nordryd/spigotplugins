package com.nordryd.event;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.nordryd.enums.Commands;
import com.nordryd.item.RegionTool;

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

    @Override
    public boolean onCommand(CommandSender cSender, Command cmd, String label, String[] args) {
        if (cSender instanceof Player) {
            if (label.equalsIgnoreCase(Commands.REGION_EDITING_TOOL.getCommand())
                    && Commands.REGION_EDITING_TOOL.isIssuedCommandValid(cSender, args.length)) {
                
                ((Player)cSender).getInventory().addItem(new RegionTool());
                return true;
            }
        }

        return false;
    }
}
