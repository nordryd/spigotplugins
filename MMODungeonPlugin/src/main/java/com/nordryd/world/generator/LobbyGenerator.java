package com.nordryd.world.generator;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.util.noise.SimplexOctaveGenerator;

import com.nordryd.util.IValues;

public class LobbyGenerator extends ChunkGenerator
{
    // Generally, more octaves -> smoother terrain...?
    private static final int OCTAVES = 8;

    // How to scale each coordinate
    private static final double SCALE = 0.005;

    // Minimum height of the whole world (each pillar will be jacked up this many
    // blocks, basically)
    private static final int MINIMUM_HEIGHT = 10;

    // Difference between highest & lowest possible heights of the world
    // TODO try to make this different for each biome?
    private static final int DEFAULT_MULTIPLIER = 0;

    // How much to alter the amplitude/frequency between each octave
    private static final double NOISE_AMPLITUDE = 0.5, NOISE_FREQUENCY = 0.5;
    private int currentHeight = MINIMUM_HEIGHT;

    @Override
    public ChunkData generateChunkData(World world, Random random, int chunkX, int chunkZ, BiomeGrid biome) {
        ChunkData chunk = createChunkData(world);

        // Octave generator MUST be placed here.
        SimplexOctaveGenerator generator = new SimplexOctaveGenerator(new Random(world.getSeed()), OCTAVES);

        // Larger scale = steeper terrain (0 = superflat?)
        generator.setScale(SCALE);

        // Generating the chunk's heights and blocks, doing each (x,z) as a "pillar"
        for (int x = 0; x < IValues.CHUNK_SIZE; x++) {
            for (int z = 0; z < IValues.CHUNK_SIZE; z++) {
                currentHeight = (int) ((generator.noise((chunkX * IValues.CHUNK_SIZE) + x, (chunkZ * IValues.CHUNK_SIZE) + z, NOISE_AMPLITUDE,
                        NOISE_FREQUENCY) * DEFAULT_MULTIPLIER) + MINIMUM_HEIGHT);
                chunk.setBlock(x, currentHeight, z, Material.BLACK_STAINED_GLASS);
            }
        }

        return chunk;
    }
}
