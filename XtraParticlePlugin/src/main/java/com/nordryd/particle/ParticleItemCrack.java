package com.nordryd.particle;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.inventory.ItemStack;

import com.nordryd.util.Values;

public class ParticleItemCrack extends AbstractParticle
{
	private final ItemStack item;
	
	protected ParticleItemCrack(Location location, World world, int count, ItemStack item) {
		super(Particle.ITEM_CRACK, location, world, count);
		this.item = item;
	}
	
	public static Builder getBuilder(Location location, World world, ItemStack item) {
		return new Builder(location, world, item, new Random());
	}
	
	public static class Builder{
		private Location location;
		private World world;
		private int count;
		private ItemStack item;
		
		private Builder(Location location, World world, ItemStack item, Random rng) {
			this.location = location;
			this.world = world;
			this.count = rng.nextInt(Values.RANDOMIZER_VALUE_FOR_DEFAULT_PARTICLE_COUNTS + 1) + Values.RANDOMIZER_VALUE_FOR_DEFAULT_PARTICLE_COUNTS;
			this.item = item;
		}
		
		public ParticleItemCrack build() {
			return new ParticleItemCrack(this.location, this.world, this.count, this.item);
		}
		
		public Builder setCount(int count) {
			this.count = count;
			return this;
		}
	}

	public ItemStack getItem() {
		return item;
	}
}
