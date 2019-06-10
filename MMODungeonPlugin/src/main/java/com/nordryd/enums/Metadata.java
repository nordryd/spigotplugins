package com.nordryd.enums;

import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * <p>
 * Enum containing all plugin metadata keys.
 * </p>
 * 
 * <p>
 * TODO Change this to an interface with strings, since that's all this enum is.
 * </p>
 * 
 * @author Nordryd
 */
public enum Metadata
{
    /**
     * Marks the player as being low on health, and an alert has been given.
     */
    PLAYER_HEALTH_LOW("playerHealthLow"),

    /**
     * Marks the tool as the region edit tool.
     */
    REGION_EDIT_TOOL("regionEditTool"),

    /**
     * Marks the player as currently instanced.
     */
    IS_INSTANCED("isInstanced"),

    /**
     * The instance the player is in. Format it as instanceID.instanceOfInstanceID ?
     */
    INSTANCE_ID("instanceID"),

    /**
     * <p>
     * The party this player is in. Common IDs between players denote they are in
     * the same party.
     * </p>
     * <p>
     * A value of <b><i>0</i></b> means the player is not in a party.
     * </p>
     */
    PARTY_ID("partyID");

    private final String key;

    private Metadata(String string) {
        this.key = string;
    }

    /**
     * @return The metadata's key.
     */
    public String getKey() {
        return key;
    }

    /**
     * Get a {@link FixedMetadataValue} based on the plugin and value given.
     * 
     * @param jPlugin
     *        {@link JavaPlugin}
     * @param value
     *        Value for this metadata.
     * @return A {@link FixedMetadataValue} object
     */
    public static MetadataValue getMetadataValue(JavaPlugin jPlugin, Object value) {
        return new FixedMetadataValue(jPlugin, value);
    }
}
