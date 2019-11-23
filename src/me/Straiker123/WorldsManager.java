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

public class WorldsManager {
	/**
	 * 
	 * @param type
	 * Set null for Empty world (Void)
	 */
	public boolean create(String name, Environment generator, WorldType type, boolean generateStructures, long seed) {
		if(Bukkit.getWorld(name)==null) {
		WorldCreator c = new WorldCreator(name);
		c.generateStructures(generateStructures);
		if(generator!=null)
		c.environment(generator);
		if(seed!=0)
		c.seed(seed);
		if(type!=null)
		c.type(type);
		else 
		c.generator(new voidGenerator());
		c.createWorld();
		if(type==null) {
			int x = Bukkit.getWorld(name).getSpawnLocation().getBlock().getX();
			int y = Bukkit.getWorld(name).getSpawnLocation().getBlock().getY();
			int z = Bukkit.getWorld(name).getSpawnLocation().getBlock().getZ();
			Location loc = new Location(Bukkit.getWorld(name), x, y-2, z);
			loc.getBlock().setType(Material.GLASS);
		}
		return true;
	}return false;}
	
	  public static boolean deleteDirectory(File path) {
	        if (path.exists()) {
	            File[] files = path.listFiles();
	            for (int i = 0; i < files.length; ++i) {
	                if (files[i].isDirectory()) {
	                    deleteDirectory(files[i]);
	                    continue;
	                }
	                files[i].delete();
	            }
	        }
	        return path.delete();
	    }
	public boolean delete(World name, boolean safeUnloadWorld) {
		if(!safeUnloadWorld) {
			List<World> w = Bukkit.getWorlds();
			w.remove(name);
			if(w.isEmpty()==false) {
			for(Player p : TheAPI.getCountingAPI().getOnlinePlayers())
				if(p.getWorld().equals(name)) {
					TheAPI.getPlayerAPI(p).setGodOnTime(30);
					TheAPI.getPlayerAPI(p).teleport(new Location(w.get(0),0,160,0));
				}
			Bukkit.unloadWorld(name, false);
			new File(Bukkit.getWorldContainer().getPath()+"/"+name).delete();
			return true;
			}return false;
		}else {
			List<World> w = Bukkit.getWorlds();
			w.remove(name);
			if(w.isEmpty()==false) {
			for(Player p : TheAPI.getCountingAPI().getOnlinePlayers())
				if(p.getWorld().equals(name)) {
					TheAPI.getPlayerAPI(p).setGodOnTime(30);
					TheAPI.getPlayerAPI(p).teleport(new Location(w.get(0),0,160,0));
				}
			Bukkit.unloadWorld(name, true);
			 if (name.getWorldFolder().exists()) {
		            for (File f:name.getWorldFolder().listFiles()) {
		                f.delete();
		            }
		        }
			return true;
		}return false;
	}
}
	public boolean unloadWorld(String name, boolean saveWorld) {
		if(Bukkit.getWorld(name)!=null) {
		List<World> w = Bukkit.getWorlds();
		w.remove(Bukkit.getWorld(name));
		if(w.isEmpty()==false) {
		for(Player p : TheAPI.getCountingAPI().getOnlinePlayers())
			if(p.getWorld().getName().equals(name)) {
				TheAPI.getPlayerAPI(p).setGodOnTime(30);
				TheAPI.getPlayerAPI(p).teleport(new Location(w.get(0),0,200,0));
			}
		Bukkit.unloadWorld(name, saveWorld);
		return true;
	}return false;
		}return false;
	}
}
