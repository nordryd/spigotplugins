package com.nordryd.entity;

import org.bukkit.ChatColor;
import org.bukkit.Location;

import net.minecraft.server.v1_14_R1.ChatMessage;
import net.minecraft.server.v1_14_R1.EntityPig;
import net.minecraft.server.v1_14_R1.EntityTypes;
import net.minecraft.server.v1_14_R1.EntityZombie;
import net.minecraft.server.v1_14_R1.PathfinderGoalNearestAttackableTarget;
import net.minecraft.server.v1_14_R1.World;

public class Spirit extends EntityZombie
{
	public Spirit(World world, Location location) {
		this(world, location, "");
	}
	
	public Spirit(World world, Location location, String customName) {
		super(EntityTypes.ZOMBIE, world);
		this.setCustomName(new ChatMessage(ChatColor.AQUA + customName));
		this.setCustomNameVisible(this.getCustomName().getString().equals(""));
		
		this.setHealth(50);
		
		this.targetSelector.a(new PathfinderGoalNearestAttackableTarget<EntityPig>(this, EntityPig.class, true));
		
		this.setLocation(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
		world.addEntity(this);
	}
}
