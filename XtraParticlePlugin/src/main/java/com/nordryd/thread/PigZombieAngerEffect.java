package com.nordryd.thread;

import org.bukkit.entity.PigZombie;
import org.bukkit.scheduler.BukkitRunnable;

import com.nordryd.particle.ParticleFactory;
import com.nordryd.particle.ParticleFlame;

/**
 * <p>
 * Class to handle pig zombies getting pissed off
 * </p>
 * 
 * @author Nordryd
 */
public class PigZombieAngerEffect extends BukkitRunnable
{
	private final PigZombie pigZombie;

	/**
	 * Constructor.
	 * @param pigZombie
	 * 			{@link PigZombie} that is pissed off.
	 */
	public PigZombieAngerEffect(final PigZombie pigZombie) {
		this.pigZombie = pigZombie;
	}

	@Override
	public void run() {
		ParticleFactory.spawnParticles(ParticleFlame.getBuilder(pigZombie.getEyeLocation(), pigZombie.getWorld()).build());

		if (!pigZombie.isValid()) {
			stop();
		}
	}

	private void stop() {
		cancel();
	}
}
