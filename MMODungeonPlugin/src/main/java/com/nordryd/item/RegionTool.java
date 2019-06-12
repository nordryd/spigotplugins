package com.nordryd.item;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.nordryd.util.IPluginTool;

import net.md_5.bungee.api.ChatColor;

public class RegionTool extends ItemStack implements IPluginTool
{
    public static final String NAME = ChatColor.LIGHT_PURPLE + "MMOD:" + ChatColor.AQUA + " Region Editing Tool";

    public RegionTool() {
        super(Material.STRING);

        ItemMeta meta = this.getItemMeta();
        meta.addEnchant(Enchantment.DURABILITY, 100, true);
        meta.setDisplayName(NAME);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_PLACED_ON);

        this.setItemMeta(meta);
    }
}
