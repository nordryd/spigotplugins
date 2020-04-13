package com.nordryd.item;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.nordryd.util.IUtility;

/**
 * <p>
 * Abstract class to represent some tool.
 * </p>
 * 
 * @author Nordryd
 */
public abstract class AbstractTool extends ItemStack
{
    public static final Map<String, String> ABSTRACT_TOOL_DISPLAY_NAMES = new HashMap<>();

    /**
     * Constructor.
     * 
     * @param name
     *        The name of the tool.
     * @param material
     *        The tool's material.
     */
    public AbstractTool(String name, Material material) {
        super(material);
        String toolName = IUtility.getFormattedToolName(name);

        ItemMeta meta = this.getItemMeta();
        meta.addEnchant(Enchantment.DURABILITY, 100, true);
        meta.setDisplayName(toolName);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_PLACED_ON);

        this.setItemMeta(meta);

        ABSTRACT_TOOL_DISPLAY_NAMES.put(name, toolName);
    }
}
