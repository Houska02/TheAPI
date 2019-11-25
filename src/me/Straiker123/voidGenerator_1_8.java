package me.Straiker123;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;

public class voidGenerator_1_8 extends ChunkGenerator {
    List<BedrockCoords> blocks = new ArrayList<BedrockCoords>();
	    public byte[][] generateBlockSections(World world, Random random, int chunkX, int chunkZ, ChunkGenerator.BiomeGrid biomeGrid) {
	        byte[][] result = new byte[world.getMaxHeight() / 16][];
	        Iterator<BedrockCoords> it = blocks.iterator();
	        while (it.hasNext()) {
	            int z;
	            BedrockCoords block = it.next();
	            if (block.x < chunkX * 16 || block.x >= (chunkX + 1) * 16 || block.z < chunkZ * 16 || block.z >= (chunkZ + 1) * 16) continue;
	            int x = block.x % 16;
	            if (x < 0) {
	                x += 16;
	            }
	            if ((z = block.z % 16) < 0) {
	                z += 16;
	            }
	            this.setBlock(result, x, block.y, z, (byte)7);
	            it.remove();
	        }
	        return result;
	    }

	    public Location getFixedSpawnLocation(World world, Random random) {
	        return new Location(world, (double)0, (double)60 + 1, (double)0);
	    }

	    private void setBlock(byte[][] result, int x, int y, int z, byte blkid) {
	        if (result[y >> 4] == null) {
	            result[y >> 4] = new byte[4096];
	        }
	        result[y >> 4][(y & 15) << 8 | z << 4 | x] = blkid;
	    }
	}