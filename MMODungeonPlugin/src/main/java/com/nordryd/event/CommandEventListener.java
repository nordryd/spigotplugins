package com.nordryd.event;

import java.util.Optional;

import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.server.TabCompleteEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.nordryd.enums.Commands;
import com.nordryd.item.RegionTool;
import com.nordryd.world.InstanceManager;

import net.md_5.bungee.api.ChatColor;

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

    @EventHandler
    public void onTabComplete(TabCompleteEvent tcevent) {

    }

    /**
     * TODO: create_new_world will become start_party (or something that initializes
     * the instance) TODO: port_to_world will become start_instance
     * 
     * TODO: need commands for party management (get party, promote to leader
     * (coloring leader differently as such), leave party, kick from party, disband
     * party, view party, choose_instance)
     * 
     * TODO: need commands for instance management (start_instance, tp_out,
     * tp_back_to_dungeon (detect if player was dced or dungeon was interrupted, and
     * say if the request is invalid)
     * 
     * TODO: study how WoW instancing works, and try to replicate it.
     */
    @Override
    public boolean onCommand(CommandSender cSender, Command cmd, String label, String[] args) {
        if (cSender instanceof Player) {
            Player player = (Player) cSender;
            if (label.equalsIgnoreCase(Commands.HELP.getCommand()) && Commands.HELP.isIssuedCommandValid(cSender, args.length)) {
                player.sendMessage(Commands.getAllCommands());
                return true;
            }
            else if (label.equalsIgnoreCase(Commands.REGION_EDITING_TOOL.getCommand())
                    && Commands.REGION_EDITING_TOOL.isIssuedCommandValid(cSender, args.length)) {

                player.getInventory().addItem(new RegionTool());
                return true;
            }
            else if (label.equalsIgnoreCase(Commands.CREATE_NEW_WORLD.getCommand())
                    && Commands.CREATE_NEW_WORLD.isIssuedCommandValid(cSender, args.length)) {
                player.sendMessage("World being created. Players will be immobilized during this time.");
                String name = "test";
                InstanceManager.createInstance(name);

                return true;
            }
            else if (label.equalsIgnoreCase(Commands.LIST_INSTANCES.getCommand())
                    && Commands.LIST_INSTANCES.isIssuedCommandValid(cSender, args.length)) {
                player.sendMessage(InstanceManager.getActiveInstanceNames());
                return true;
            }
            else if (label.equalsIgnoreCase(Commands.PORT_TO_WORLD.getCommand())
                    && Commands.PORT_TO_WORLD.isIssuedCommandValid(cSender, args.length)) {
                // TODO WORLDS NOT MAINTAINED PAST RESTART
                Optional<World> world = InstanceManager.getInstanceFromName(args[0]);
                if (world.isPresent()) {
                    player.teleport(world.get().getSpawnLocation());
                }
                else {
                    player.sendMessage(ChatColor.RED + "The world " + args[0] + " does not exist!");
                }
                return true;
            }
        }

        return false;
    }
}
