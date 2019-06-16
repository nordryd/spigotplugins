package com.nordryd.item;

import org.bukkit.Material;

public class InstanceEditTool extends AbstractTool
{
    public InstanceEditTool() {
        super("Instance Edit Tool", Material.BRICK);
    }
    
    public static String getUniqueName() {
        return "Instance Edit Tool";
    }
}
