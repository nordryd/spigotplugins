package com.nordryd.world.generator;

import org.bukkit.Location;
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
    private static final int LENGTH = 13, HEIGHT = 6, startX = 0, startY = 10, startZ = 0;

    public static void generateLobby(World world) {
        world.setSpawnLocation(LENGTH / 2, startY + 1, LENGTH / 2);

        // STRUCTURE
        // floor
        for (int x = startX; x < LENGTH; x++) {
            for (int z = startZ; z < LENGTH; z++) {
                if (isOnEdge(x, z)) {
                    setBlockAt(world, x, startY, z, Material.GLASS);
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
                    setBlockAt(world, x, startY + HEIGHT, z, Material.GLASS);
                }
                else {
                    setBlockAt(world, x, startY + HEIGHT, z, Material.GLASS);
                }
            }
        }

        // walls
        for (int y = startY + 1; y < startY + HEIGHT; y++) {
            for (int x = startX; x < LENGTH; x++) {
                setBlockAt(world, x, y, startZ, Material.GLASS);
                setBlockAt(world, x, y, startZ + LENGTH - 1, Material.GLASS);
            }

            for (int z = startZ; z < LENGTH; z++) {
                setBlockAt(world, startX, y, z, Material.GLASS);
                setBlockAt(world, startX + LENGTH - 1, y, z, Material.GLASS);
            }
        }

        // SPECIAL BLOCKS
        // special block/sign locations (sans Y offsets)
        Location cancelLoc = new Location(world, startX + LENGTH - 3, startY + 1, startZ + LENGTH - 3);
        Location startLoc = new Location(world, startX + LENGTH - 3, startY + 1, startZ + 3);
        Location selectLoc = new Location(world, startX + LENGTH - 3, startY + 1, startZ + (LENGTH / 2));

        // interacting blocks
        setBlockAt(cancelLoc, Material.RED_CONCRETE_POWDER);
        setBlockAt(startLoc, Material.GREEN_CONCRETE_POWDER);
        setBlockAt(selectLoc, Material.WHITE_CONCRETE_POWDER);

        // signs
        setBlockAt(cancelLoc.add(0, 1, 0), Material.BIRCH_SIGN);
        setBlockAt(startLoc.add(0, 1, 0), Material.BIRCH_SIGN);
        setBlockAt(selectLoc.add(0, 1, 0), Material.BIRCH_SIGN);
    }

    private static void setBlockAt(World world, int x, int y, int z, Material type) {
        world.getBlockAt(x, y, z).setType(type);
    }

    private static void setBlockAt(Location location, Material type) {
        location.getBlock().setType(type);
    }

    private static boolean isOnEdge(int x, int z) {
        return (x == 0) || (x == LENGTH - 1) || (z == 0) || (z == LENGTH - 1);
    }

//    private static boolean isCorner(int x, int z) {
//        return ((x == 0) && (z == 0)) || ((x == 0) && (z == LENGTH - 1)) || ((x == LENGTH - 1) && (z == 0))
//                || ((x == LENGTH - 1) && (z == LENGTH - 1));
//    }
}