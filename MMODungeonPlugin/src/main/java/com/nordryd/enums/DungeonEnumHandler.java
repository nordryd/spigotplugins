package com.nordryd.enums;

import org.bukkit.ChatColor;

/**
 * <p>
 * Dungeon difficulty along with their modifiers.
 * </p>
 * 
 * @author Nordryd
 */
public interface DungeonEnumHandler
{
    /**
     * <p>
     * Enum for any possible instance statuses.
     * </p>
     */
    public enum Status
    {
        AWAITING, ACTIVE, CANCELLED, ENDED;
    }

    /**
     * <p>
     * Enum for the available dungeon difficulties.
     * </p>
     */
    public enum Difficulty
    {
        NORMAL, HEROIC, MYTHIC;
    }

    /**
     * <p>
     * Enum for the different instance variants.
     * </p>
     */
    public enum Type
    {
        /**
         * Travel through an designated area and fight various bosses.
         */
        DUNGEON,

        /**
         * Stay in one room and fight wave after wave of mobs until a pre-determined
         * wave, or until the party is overwhelmed.
         */
        ARENA;
    }

    /**
     * <p>
     * Enum for the different roles that a player can be inside a dungeon. Will be
     * chosen in a lobby with special blocks (or signs?)
     * </p>
     */
    public enum PlayerRole
    {
        /**
         * Protects other party members from taking damage.
         */
        TANK(ChatColor.LIGHT_PURPLE + "tank"),

        /**
         * Mends other party members' wounds.
         */
        HEALER(ChatColor.AQUA + "healer"),

        /**
         * Deals damage to enemies.
         */
        DAMAGE(ChatColor.RED + "damage");

        private String string;

        private PlayerRole(String string) {
            this.string = string;
        }

        @Override
        public String toString() {
            return this.string;
        }
    }
}
