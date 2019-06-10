package com.nordryd.enums;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import com.nordryd.annotation.GameRegistration;
import com.nordryd.util.IValues.ICmdStrings;

/**
 * <p>
 * Enum for all the commands in the plugin
 * </p>
 * 
 * <p>
 * This only stores information about each command. Commands must be added to
 * <i>plugin.yml</i> manually.<br>
 * TODO Can that be changed? Generate {@code plugin.yml}?
 * </p>
 * 
 * @author Nordryd
 */
public enum Commands
{
    /**
     * Just a demo command for testing.
     */
    DEMO("demo", 1, true);

    private final String command;
    private final int params;
    private final boolean isAdminCmd;

    private Commands(String command, int params, boolean isAdminCmd) {
        this.command = ICmdStrings.CMD_PREFIX + command;
        this.params = params;
        this.isAdminCmd = isAdminCmd;
    }

    /**
     * @return The command itself.
     */
    public String getCommand() {
        return this.command;
    }

    /**
     * If the issued command is valid, and should be allowed to execute.
     * 
     * @param cSender
     *        {@link CommandSender} to check if they are a server op (if
     *        applicable).
     * @param args
     *        Number of arguments given.
     * @return <b>TRUE</b> if the {@link CommandSender} has sufficient permission, &
     *         the number of arguments is correct.<br>
     *         <b>FALSE</b> if any conditions are <i>not</i> met.
     */
    public boolean isIssuedCommandValid(CommandSender cSender, int args) {
        return (this.params == args) && (this.isAdminCmd ? cSender.isOp() : true);
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
    public static void registerCommands(JavaPlugin jPlugin, CommandExecutor cExecutor) {
        for (Commands command : Commands.values()) {
            jPlugin.getCommand(command.getCommand()).setExecutor(cExecutor);
        }
    }
}
