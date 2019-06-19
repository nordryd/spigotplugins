package com.nordryd.event;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.nordryd.enums.Commands;
import com.nordryd.enums.Instance;
import com.nordryd.item.InstanceEditTool;
import com.nordryd.item.RegionEditTool;
import com.nordryd.util.IUtility;
import com.nordryd.util.IValues;
import com.nordryd.world.InstanceManager;

import net.md_5.bungee.api.ChatColor;

/**
 * <p>
 * Class to handle all plugin commands.
 * </p>
 * 
 * @author Nordryd
 */
public class CommandEventListener extends EventListener implements CommandExecutor, TabCompleter
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
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if ((sender instanceof Player) && (label.equalsIgnoreCase(IValues.CMD_PREFIX))) {
            Player player = (Player) sender;

            Optional<Commands> validatedCmd = Commands.validateCommand(((args.length == 0) ? "" : args[0]), sender, args.length);
            if (!validatedCmd.isPresent()) {
                return false;
            }

            switch (validatedCmd.get()) {
            case HELP:
                String string = ChatColor.BLUE + "" + ChatColor.UNDERLINE + "Available Commands:\n" + ChatColor.RESET;

                for (Commands command : Commands.values()) {
                    string += ChatColor.AQUA + command.toString() + "\n";
                }

                player.sendMessage(string.substring(0, string.length() - 1));

                return true;
            case LIST_INSTANCES:
                String str = ChatColor.BLUE + "" + ChatColor.UNDERLINE + "Available Commands:\n" + ChatColor.RESET;

                for (String s : InstanceManager.getInstanceNames()) {
                    str += ChatColor.AQUA + s + "\n";
                }

                player.sendMessage(str.substring(0, str.length() - 1));

                return true;
            case PORT:
                Optional<World> world = InstanceManager.getInstanceFromName(args[1]);
                if (world.isPresent()) {
                    if (args[1].equals(IValues.HOMEWORLD_STRING) && IUtility.isWorldNetherOrEnd(player.getWorld().getName())) {
                        player.sendMessage(ChatColor.RED + "Yeah, no. Nice try. Stop trying to cheat & take the portal :P");
                        return true;
                    }

                    player.teleport(world.get().getSpawnLocation());
                }
                else {
                    player.sendMessage(ChatColor.RED + "The world " + args[1] + " does not exist!");
                }

                return true;
            case CREATE_TEST_WORLD:
                player.sendMessage("World being created. Players will be immobilized during this time.");
                InstanceManager.createInstance(Instance.SKY);

                return true;
            case REGION_EDIT_TOOL:
                player.getInventory().addItem(new RegionEditTool());
                return true;
            case INSTANCE_EDIT_TOOL:
                player.sendMessage(ChatColor.BLUE + "Keep the tool in offhand/mainhand to enable editing mode");
                player.getInventory().addItem(new InstanceEditTool());
                return true;
            default:
                return false;
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args) {
        if (sender instanceof Player) {
            if (cmd.getName().equalsIgnoreCase(IValues.CMD_PREFIX)) {
                List<String> commands = new ArrayList<>();
                for (Commands command : Commands.values()) {
                    commands.add(command.getCommand());
                }

                if (args[0].equals(Commands.PORT.getCommand())) {
                    return InstanceManager.getInstanceNames();
                }

                return commands;
            }
        }

        return null;
    }
}
