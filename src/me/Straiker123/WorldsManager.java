package me.Straiker123;

import org.bukkit.World.Environment;

import java.io.File;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.entity.Player;

import me.Straiker123.Utils.voidGenerator;
import me.Straiker123.Utils.voidGenerator_1_8;

public class WorldsManager {
	
	
	public boolean load(String world, Environment generator, WorldType type) {
		if(Bukkit.getWorld(world)!=null)return false;
		return TheAPI.getWorldsManager().create(world, generator, type, true, 0);
		}
	/**
	 * Create new world, return boolean if world was created
	 * @param name Name of world (Required)
	 * @param generator World generator type (Required)
	 * @param type set null to create Void world
	 * @return boolean if world was created
	 */
	public boolean create(String name, Environment generator, WorldType type) {
		return create(name,generator,type,true,0);
	}
	/**
	 * Create new world, return boolean if world was created
	 * @param name Name of world (Required)
	 * @param generator World generator type (Required)
	 * @param type set null to create Void world
	 * @param generateStructures generate in world structures ?
	 * @param seed set 0 to generate random
	 * @return boolean if world was created
	 */
	public boolean create(String name, Environment generator, WorldType type, boolean generateStructures, long seed) {
		if (name==null||generator==null)
			return false;
		
		if(Bukkit.getWorld(name)==null) {
		WorldCreator c = new WorldCreator(name);
		c.generateStructures(generateStructures);
		if(generator!=null)
		c.environment(generator);
		if(seed!=0)
		c.seed(seed);
		if(type!=null)
		c.type(type);
		else {
			if(!TheAPI.getServerVersion().equals("v1_8_R3"))
		c.generator(new voidGenerator());
			else
				c.generator(new voidGenerator_1_8());
		}
		c.createWorld();
		if(type==null) {
			Location loc = new Location(Bukkit.getWorld(name), 0, 60, 0);
			Bukkit.getWorld(name).setSpawnLocation(0, 60, 0);
			loc.getBlock().setType(Material.GLASS);
		}
		return true;
	}return false;}
	private static boolean deleteDirectory(File path) {
		if(path.exists()) {
		File[] files = path.listFiles();
		for(int i=0; i<files.length; i++) {
		if(files[i].isDirectory()) {
		deleteDirectory(files[i]);
		}else {
		files[i].delete();
		}}}
		return( path.delete() );
	}
	public boolean delete(World name, boolean safeUnloadWorld) {
		if (name==null)
			return false;
		if(!safeUnloadWorld) {
			List<World> w = Bukkit.getWorlds();
			w.remove(name);
			if(w.isEmpty()==false) {
			Bukkit.unloadWorld(name, false);
			boolean delete = deleteDirectory(name.getWorldFolder());
			 return delete;
			}return false;
		}else {
			List<World> w = Bukkit.getWorlds();
			w.remove(name);
			if(w.isEmpty()==false) {
			Bukkit.unloadWorld(name, true);
			boolean delete = deleteDirectory(name.getWorldFolder());
			 return delete;
		}return false;
	}
}
	public boolean unloadWorld(String name, boolean saveWorld) {
		if (name==null)
			return false;
		if(Bukkit.getWorld(name)!=null) {
		List<World> w = Bukkit.getWorlds();
		w.remove(Bukkit.getWorld(name));
		if(w.isEmpty()==false) {
		for(Player p : TheAPI.getCountingAPI().getOnlinePlayers())
			if(p.getWorld().getName().equals(name)) {
				TheAPI.getPlayerAPI(p).setGodOnTime(30);
				TheAPI.getPlayerAPI(p).teleport(w.get(0).getSpawnLocation());
			}
		Bukkit.unloadWorld(name, saveWorld);
		return true;
	}return false;
		}return false;
	}
}
