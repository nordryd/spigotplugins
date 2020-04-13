package com.nordryd.world.generator;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.World;
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
public class SkyGenerator extends ChunkGenerator
{
    // Generally, more octaves -> smoother terrain...?
    private static final int OCTAVES = 8;

    // How to scale each coordinate
    private static final double SCALE = 0.005;

    // Minimum height of the whole world (each pillar will be jacked up this many
    // blocks, basically)
    private static final int MINIMUM_HEIGHT = 64;

    // Difference between highest & lowest possible heights of the world
    // TODO try to make this different for each biome?
    private static final int DEFAULT_MULTIPLIER = 0;

    // How much to alter the amplitude/frequency between each octave
    private static final double NOISE_AMPLITUDE = 0.5, NOISE_FREQUENCY = 0.5;
    private int generatedHeight = MINIMUM_HEIGHT;

    @Override
    public ChunkData generateChunkData(World world, Random random, int chunkX, int chunkZ, BiomeGrid biome) {
        ChunkData chunk = createChunkData(world);

        // Octave generator MUST be placed here.
        SimplexOctaveGenerator generator = new SimplexOctaveGenerator(new Random(world.getSeed()), OCTAVES);

        // Larger scale = steeper terrain
        generator.setScale(SCALE);

        // Set spawn in the first chunk to be at the height of the origin chunk chunk
        world.setSpawnLocation(IValues.CHUNK_SIZE / 2, generatedHeight + 1, IValues.CHUNK_SIZE / 2);

        // Generating the chunk's heights and blocks, doing each (x,z) as a "pillar"
        for (int x = 0; x < IValues.CHUNK_SIZE; x++) {
            for (int z = 0; z < IValues.CHUNK_SIZE; z++) {

                // 15 & 50 bc the multiplier is the difference between the highest and lowest
                // possible heights of the world, and 50 is the min height of the whole world
                // (can be changed if desired)
                generatedHeight = (int) ((generator.noise((chunkX * IValues.CHUNK_SIZE) + x, (chunkZ * IValues.CHUNK_SIZE) + z, NOISE_AMPLITUDE,
                        NOISE_FREQUENCY) * DEFAULT_MULTIPLIER) + MINIMUM_HEIGHT);

                if ((chunkX == 0) && (chunkZ == 0)) {
                    chunk.setBlock(x, generatedHeight, z, (isOnChunkEdge(x, z) ? Material.COBBLESTONE : Material.STONE_BRICKS));
                }
                else {
                    setBlocksBelowToAir(chunk, generatedHeight, x, z);
                }
            }
        }

        return chunk;
    }

    private boolean isOnChunkEdge(int x, int z) {
        return (x == 0) || (x == IValues.CHUNK_SIZE - 1) || (z == 0) || (z == IValues.CHUNK_SIZE - 1);
    }

    private void setBlocksBelowToAir(ChunkData chunk, int height, int x, int z) {
        for (int y = height; y >= 0; y--) {
            chunk.setBlock(x, y, z, Material.AIR);
        }
    }
}
