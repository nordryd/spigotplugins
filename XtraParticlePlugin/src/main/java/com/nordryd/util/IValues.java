package com.nordryd.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.bukkit.event.entity.EntityTargetEvent.TargetReason;

/**
 * <p>
 * Interface for various values used throughout the plugin. This is to reduce
 * the prevalence of magic values throughout the code.
 * </p>
 * 
 * @author Nordryd
 */
public interface IValues
{
	public static final String PREFIX = ChatColor.DARK_AQUA + "[" + Reference.PLUGIN_NAME + "]" + ChatColor.AQUA;
	public static final String PLAYER_NAME_ESCAPE = "%p";
	public static final List<String> TOOLS = new ArrayList<>(Arrays.asList("_PICKAXE", "_AXE", "_SHOVEL", "_HOE", "_SWORD", "TRIDENT"));

	public static final double LOW_HEALTH_THRESHOLD = 6.5;
	public static final double LOW_FOOD_THRESHOLD = 6;
	public static final double LOW_AIR_THRESHOLD = 6.5;
	public static final double BLOCK_CENTER_OFFSET = 0.5;

	public static final int RANDOMIZER_VALUE_FOR_DEFAULT_PARTICLE_COUNTS = 5;

	public static final float[] PITCH = { 0.5f, 1.0f, 2.0f };

	public static final List<TargetReason> INVALID_HOSTILE_REASONS = new ArrayList<>(
			Arrays.asList(TargetReason.TARGET_ATTACKED_ENTITY, TargetReason.TEMPT, TargetReason.FOLLOW_LEADER));

	public static final List<EntityType> ENDER_ENTITIES = new ArrayList<>(
			Arrays.asList(EntityType.ENDER_DRAGON, EntityType.ENDERMAN, EntityType.SHULKER, EntityType.ENDERMITE));

	/**
	 * Interface for storing time values (ms).
	 */
	public static interface Time
	{
		public static final long DAY = 0, NOON = 6000, NIGHT = 13000, MIDNIGHT = 18000;
	}
}
