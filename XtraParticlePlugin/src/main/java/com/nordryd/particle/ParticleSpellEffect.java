package com.nordryd.particle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;

import com.nordryd.enums.ParticleColor;

/**
 * <p>
 * Class for <b><i>spell effect</i></b> particles with certain characteristics.
 * Based on <b>SPELL_MOB</b> particles.
 * </p>
 * 
 * @author Nordryd
 */
public class ParticleSpellEffect extends PluginParticle
{
	private final List<ParticleColor> colors;
	private final Random rng;

	private ParticleSpellEffect(Location location, World world, int count, List<ParticleColor> colors) {
		super(Particle.SPELL_MOB, location, world, count);
		this.rng = new Random();
		this.colors = colors;
	}

	/**
	 * Get a {@code Builder} to get an instance of {@code ParticleSpellEffect}.
	 * 
	 * @param location
	 *            The {@code Location} to spawn the particle at.
	 * @param world
	 *            The {@code World} to spawn the particle at.
	 * @return A new Builder.
	 */
	public static Builder getBuilder(Location location, World world) {
		return new Builder(location, world, new Random());
	}

	/**
	 * Builder for {@code ParticleSpellEffect}.
	 */
	public static class Builder
	{
		private Location location;
		private World world;
		private int count;
		private List<ParticleColor> colors;

		private Builder(Location location, World world, Random rng) {
			this.location = location;
			this.world = world;
			this.count = rng.nextInt(6) + 5;
		}

		/**
		 * @return A new instance of {@code ParticleSpellEffect} based on the Builder's
		 *         fields.
		 */
		public ParticleSpellEffect build() {
			return new ParticleSpellEffect(this.location, this.world, this.count, (this.colors.equals(null) ? new ArrayList<ParticleColor>(Arrays.asList(ParticleColor.WHITE)) : this.colors));
		}

		/**
		 * Set the amount of particles to spawn.
		 * 
		 * @param count
		 *            The amount of particles to spawn.
		 * @return This {@code Builder}.
		 */
		public Builder setCount(int count) {
			this.count = count;
			return this;
		}
		
		/**
		 * Set the possible colors for this particle. They will be chosen at random when spawned.
		 * @param colors
		 * 			The possible colors for this particle.
		 * @return
		 * 			This {@code Builder}
		 */
		public Builder setColors(ParticleColor... colors) {
			this.colors = new ArrayList<>(Arrays.asList(colors));
			return this;
		}
		
		/**
		 * Set the possible colors for this particle. They will be chosen at random when spawned.
		 * @param colors
		 * 			The possible colors for this particle.
		 * @return
		 * 			This {@code Builder}
		 */
		public Builder setColors(List<ParticleColor> colors) {
			this.colors = colors;
			return this;
		}
	}
	
	/**
	 * @return One randomly chosen color from the possible colors.
	 */
	public ParticleColor getColor() {
		return colors.get(rng.nextInt(colors.size()));
	}
}
