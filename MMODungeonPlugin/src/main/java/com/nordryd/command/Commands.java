package com.nordryd.command;

import java.util.Optional;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.java.JavaPlugin;

import com.nordryd.enums.ColorEnumHandler.ANSIColor;
import com.nordryd.util.IValues;
import com.nordryd.util.annotation.GameRegistration;

/**
 * <p>
 * Enum for all the commands in the plugin
 * </p>
 * 
 * <p>
 * This only stores information about each command. Commands must be added to
 * <i>plugin.yml</i> manually.<br>
 * TODO: Can that be changed? Generate {@code plugin.yml}?
 * </p>
 * 
 * @author Nordryd
 */
public enum Commands
{
    HELP("help", false),
    START_INSTANCE("start_instance", false, "instance_name"),
    END_INSTANCE("end_instance", false),
    LIST_INSTANCES("list_instances", false),
    REGION_EDIT_TOOL("region_edit_tool", true),
    INSTANCE_EDIT_TOOL("instance_edit_tool", true),
    PARTY_CHAT("party_chat", false);

    private final String command, parameters[];
    private final boolean isAdminCmd;

    private Commands(String command, boolean isAdminCmd, String... autocompleteNames) {
        this.command = command;
        this.isAdminCmd = isAdminCmd;
        this.parameters = autocompleteNames;
    }

    private Commands(String command, boolean isAdminCmd) {
        this(command, isAdminCmd, "");
    }

    /**
     * @return The command itself.
     */
    public String getCommand() {
        return this.command;
    }

    /**
     * @return The names of each of the command's parameters.
     */
    public String[] getParameters() {
        return this.parameters;
    }

    @Override
    public String toString() {
        String string = "/" + IValues.CMD_PREFIX + " " + this.command + " ";

        if (!this.parameters[0].isEmpty()) {
            for (String param : this.parameters) {
                string += "[" + param + "] ";
            }
        }

        return string.substring(0, string.length() - 1);
    }

    /**
     * Validates the given command.
     * 
     * @param command
     *        The command given after /mmod
     * @param sender
     *        The command sender.
     * @param args
     *        The number of arguments given in the command.
     * @return An {@link Optional} container with the corresponding command inside.
     *         Use {@link Optional#get()} to retrieve it.<br>
     *         If the command is not valid, an empty {@link Optional} will be given
     *         instead (to prevent the passing around of {@code null}s).
     */
    public static Optional<Commands> validateCommand(String command, CommandSender sender, int args) {
        for (Commands cmd : Commands.values()) {
            if (command.equalsIgnoreCase(cmd.getCommand())) {
                boolean isValid = isIssuedCommandValid(cmd, sender, args);

                if (!isValid) {
                    sender.sendMessage(ChatColor.DARK_RED + "Usage:" + ChatColor.RED + cmd + ChatColor.RESET);
                }

                return isValid ? Optional.ofNullable(cmd) : Optional.empty();
            }
        }

        return Optional.empty();
    }

    /**
     * <p>
     * Registers all commands.
     * </p>
     * 
     * <p>
     * <b><i>WARNING:</i></b> Commands must be manually added to the
     * {@code plugin.yml} file, or else this will throw an exception.<br>
     * Is there a way to change this? Can commands be added to the
     * {@code plugin.yml} via code?
     * </p>
     * 
     * @param jPlugin
     *        {@link JavaPlugin}
     * @param cExecutor
     *        {@link CommandExecutor} handler class.
     */
    @GameRegistration
    public static void registerCommands(JavaPlugin jPlugin, CommandExecutor cExecutor, TabCompleter tCompleter) {
        jPlugin.getCommand(IValues.CMD_PREFIX).setExecutor(cExecutor);
        jPlugin.getCommand(IValues.CMD_PREFIX).setTabCompleter(tCompleter);

        jPlugin.getLogger().info(ANSIColor.BLUE + "Commands registered successfully!" + ANSIColor.RESET);
    }

    private static boolean isIssuedCommandValid(Commands cmd, CommandSender sender, int args) {
        if (cmd.parameters[0].isEmpty()) {
            return cmd.isAdminCmd ? sender.isOp() : true;
        }
        else {
            return ((cmd.parameters.length + 1) == args) && (cmd.isAdminCmd ? sender.isOp() : true);
        }
    }
}
