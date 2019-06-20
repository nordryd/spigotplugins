package com.nordryd.world.generator;

import org.bukkit.Material;
import org.bukkit.World;

/**
 * <p>
 * Class for generating a lobby in a normal world, as well as sets the spawn
 * location.
 * </p>
 * 
 * @author Nordryd
 */
public class LobbyGenerator
{
    private static final int LENGTH = 12, HEIGHT = 6, startX = 0, startY = 118, startZ = 0;

    public static void generateLobby(World world) {
        world.setSpawnLocation(LENGTH / 2, startY + 1, LENGTH / 2);

        // STRUCTURE
        // floor
        for (int x = startX; x < LENGTH; x++) {
            for (int z = startZ; z < LENGTH; z++) {
                if (isOnEdge(x, z)) {
                    setBlockAt(world, x, startY, z, Material.POLISHED_DIORITE);
                }
                else {
                    setBlockAt(world, x, startY, z, Material.GLASS);
                }
            }
        }

        // roof
        for (int x = startX; x < LENGTH; x++) {
            for (int z = startZ; z < LENGTH; z++) {
                if (isOnEdge(x, z)) {
                    setBlockAt(world, x, startY + HEIGHT, z, Material.POLISHED_DIORITE);
                }
                else {
                    setBlockAt(world, x, startY + HEIGHT, z, Material.GLASS);
                }
            }
        }

        // walls
        for (int y = startY + 1; y < startY + HEIGHT; y++) {
            for (int x = startX; x < LENGTH; x++) {
                setBlockAt(world, x, y, startZ, isCorner(x, startZ) ? Material.POLISHED_DIORITE : Material.GLASS);
                setBlockAt(world, x, y, startZ + LENGTH - 1, isCorner(x, startZ + LENGTH - 1) ? Material.POLISHED_DIORITE : Material.GLASS);
            }

            for (int z = startZ; z < LENGTH; z++) {
                setBlockAt(world, startX, y, z, isCorner(startX, z) ? Material.POLISHED_DIORITE : Material.GLASS);
                setBlockAt(world, startX + LENGTH - 1, y, z, isCorner(startX + LENGTH - 1, z) ? Material.POLISHED_DIORITE : Material.GLASS);
            }
        }
    }

    private static void setBlockAt(World world, int x, int y, int z, Material type) {
        world.getBlockAt(x, y, z).setType(type);
    }

    private static boolean isOnEdge(int x, int z) {
        return (x == 0) || (x == LENGTH - 1) || (z == 0) || (z == LENGTH - 1);
    }

    private static boolean isCorner(int x, int z) {
        return ((x == 0) && (z == 0)) || ((x == 0) && (z == LENGTH - 1)) || ((x == LENGTH - 1) && (z == 0))
                || ((x == LENGTH - 1) && (z == LENGTH - 1));
    }
}