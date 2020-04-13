package com.nordryd.enums;

import org.bukkit.ChatColor;

/**
 * <p>
 * Interface containing any enum that involves instance attributes.
 * </p>
 * 
 * @author Nordryd
 */
public interface InstanceEnumHandler
{
    /**
     * <p>
     * Enum for any possible instance statuses.
     * </p>
     */
    public enum InstanceStatus
    {
        AWAITING, ACTIVE, CANCELLED, ENDED;
    }

    /**
     * <p>
     * Enum for the available dungeon difficulties.
     * </p>
     */
    public enum InstanceDifficulty
    {
        EASY(ChatColor.GREEN), MEDIUM(ChatColor.YELLOW, ChatColor.ITALIC), HARD(ChatColor.RED, ChatColor.ITALIC, ChatColor.BOLD);

        private final String prefix;

        private InstanceDifficulty(ChatColor... colors) {
            String prefix = "";
            for (ChatColor color : colors) {
                prefix += (color + "");
            }

            this.prefix = prefix;
        }

        @Override
        public String toString() {
            return prefix + super.toString() + ChatColor.RESET;
        }
    }

    /**
     * <p>
     * Enum for the different instance variants.
     * </p>
     */
    public enum InstanceType
    {
        /**
         * Travel through an designated area and fight various bosses.
         */
        DUNGEON,

        /**
         * Stay in one room and fight wave after wave of mobs until a pre-determined
         * wave, or until the party is overwhelmed.
         */
        ARENA,
        
        /**
         * Lobby before entering an instance.
         */
        LOBBY;
    }

    /**
     * <p>
     * Enum for the different roles that a player can be inside a dungeon. Will be
     * chosen in a lobby with special blocks (or signs?)
     * </p>
     */
    public enum InstancePlayerRole
    {
        /**
         * Protects other party members from taking damage.
         */
        TANK(ChatColor.LIGHT_PURPLE),

        /**
         * Mends other party members' wounds.
         */
        HEALER(ChatColor.AQUA),

        /**
         * Deals damage to enemies.
         */
        DAMAGE(ChatColor.RED);

        private String string;

        private InstancePlayerRole(ChatColor... colors) {
            String string = "";

            for (ChatColor color : colors) {
                string += (color + "");
            }

            this.string = string;
        }

        @Override
        public String toString() {
            return this.string + super.toString() + ChatColor.RESET;
        }
    }
}
