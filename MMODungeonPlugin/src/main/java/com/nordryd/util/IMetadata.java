package com.nordryd.util;

import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.java.JavaPlugin;

import com.nordryd.annotation.PluginUtility;

/**
 * <p>
 * Interface containing all plugin metadata keys.
 * </p>
 * 
 * <p>
 * TODO: Document the metadata strings
 * </p>
 * 
 * @author Nordryd
 */
@PluginUtility
public interface IMetadata
{
    public static final String PLAYER_HEALTH_LOW = "playerHealthLow";
    public static final String PLAYER_DUNGEON_EDIT_MODE = "playerDungeonEditMode";

    /**
     * <p>
     * The instance the player is in. Format: instanceID.instanceOfInstanceID.
     * </p>
     * 
     * <p>
     * A value of 0 means the player is <i>not</i> in an instance.
     * </p>
     * 
     * <p>
     * A non-zero value means the player <i>is</i> instanced, and certain
     * permissions will follow from that
     * </p>
     */
    public static final String PLAYER_IS_INSTANCED = "playerInstanceID";
    public static final String PLAYER_PARTY_ID = "playerPartyID";

    public static final String DUNGEON_EDIT_TOOL = "dungeonEditTool";
    public static final String DUNGEON_ID = "dungeonID";

    public static final String TANK_BLOCK = "tankBlock", HEALER_BLOCK = "healerBlock", DAMAGE_BLOCK = "damageBlock";

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
