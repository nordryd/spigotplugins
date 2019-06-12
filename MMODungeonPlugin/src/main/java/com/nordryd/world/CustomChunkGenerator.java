package com.nordryd.world;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.util.noise.SimplexOctaveGenerator;

import com.nordryd.util.IValues;

/**
 * <p>
 * TODO This will become the {@code Instance} class, and will be the chunk
 * generator.
 * </p>
 * 
 * @author Nordryd
 */
public class CustomChunkGenerator extends ChunkGenerator
{
    // Generally, more octaves -> smoother terrain...?
    private static final int OCTAVES = 8;

    // How to scale each coordinate
    private static final double SCALE = 0.005;

    // Minimum height of the whole world (each pillar will be jacked up this many
    // blocks, basically)
    private static final int MINIMUM_HEIGHT = 50;

    // Difference between highest & lowest possible heights of the world
    // TODO try to make this different for each biome?
    private static final int DEFAULT_MULTIPLIER = 7;

    // How much to alter the amplitude/frequency between each octave
    private static final double NOISE_AMPLITUDE = 0.5, NOISE_FREQUENCY = 0.5;
    private int currentHeight = MINIMUM_HEIGHT;

    // Set block materials
    private static final Material TOP_BLOCK = Material.GRASS_BLOCK;
    private static final Material SUB_TOP_BLOCK = Material.DIRT;
    private static final Material FILLER_BLOCK = Material.STONE;
    private static final Material BEDROCK = Material.BEDROCK;

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
                // 15 & 50 bc the multiplier is the difference between the highest and lowest
                // possible heights of the world, and 50 is the min height of the whole world
                // (can be changed if desired)
                currentHeight = (int) ((generator.noise((chunkX * IValues.CHUNK_SIZE) + x, (chunkZ * IValues.CHUNK_SIZE) + z, NOISE_AMPLITUDE,
                        NOISE_FREQUENCY) * DEFAULT_MULTIPLIER) + MINIMUM_HEIGHT);

                // Set spawn in the first chunk
                if ((x == 0) && (z == 0)) {
                    world.setSpawnLocation(x, currentHeight + 1, z);
                }

                // After getting the height, we can build the pillar
                // The top block
                if (biome.getBiome(x, z) == Biome.RIVER) {
                    chunk.setBlock(x, currentHeight, z, Material.WATER);
                }
                else {
                    chunk.setBlock(x, currentHeight, z, TOP_BLOCK);
                }

                // The sub top block
                chunk.setBlock(x, currentHeight - 1, z, SUB_TOP_BLOCK);

                // The rest of the pillar until the very bottom
                for (int height = (currentHeight - 2); height > 0; height--) {
                    chunk.setBlock(x, height, z, FILLER_BLOCK);
                }

                // The bedrock butt of the pillar
                chunk.setBlock(x, 0, z, BEDROCK);
            }
        }

        return chunk;
    }
}
