package me.Straiker123;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.generator.ChunkGenerator;

public class voidGenerator extends ChunkGenerator {
	 String the_void;
	    public ChunkGenerator.ChunkData generateChunkData(World world, Random random, int chunkx, int chunkz, ChunkGenerator.BiomeGrid biome) {
	        ChunkGenerator.ChunkData data = this.createChunkData(world);
	        for (int x = 0; x < 16; ++x) {
	            for (int z = 0; z < 16; ++z) {

	            	if(Bukkit.getVersion().contains("1.13")||Bukkit.getVersion().contains("1.14"))the_void="THE_VOID";
	            	if(Bukkit.getVersion().contains("1.12")||Bukkit.getVersion().contains("1.11")
	            			||Bukkit.getVersion().contains("1.10")||Bukkit.getVersion().contains("1.9"))the_void="VOID";
	                biome.setBiome(x, z, Biome.valueOf(the_void));
	                
	            }
	        }
	        return data;
	    }
}
