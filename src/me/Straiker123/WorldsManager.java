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
import org.bukkit.entity.Entity;
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
			World world = Bukkit.getWorld(name);
			Location loc = new Location(world, x, y-2, z);
			loc.getBlock().setType(Material.GLASS);
		}
		return true;
	}return false;}
	
	
	public boolean delete(String name, boolean safeUnloadWorld) {
		if(Bukkit.getWorld(name)!=null) {
		if(!safeUnloadWorld) {
			List<World> w = Bukkit.getWorlds();
			w.remove(Bukkit.getWorld(name));
			if(w.isEmpty()==false) {
			for(Player p : TheAPI.getCountingAPI().getOnlinePlayers())
				if(p.getWorld().getName().equals(name)) {
					p.setNoDamageTicks(120*20);
					p.teleport(new Location(w.get(0),0,200,0));
				}
			Bukkit.unloadWorld(name, false);
			new File(Bukkit.getWorldContainer().getPath()+"/"+name).delete();
			return true;
			}return false;
		}else {
			List<World> w = Bukkit.getWorlds();
			w.remove(Bukkit.getWorld(name));
			if(w.isEmpty()==false) {
			for(Player p : TheAPI.getCountingAPI().getOnlinePlayers())
				if(p.getWorld().getName().equals(name)) {
					p.setNoDamageTicks(200*20);
					p.teleport(new Location(w.get(0),0,200,0));
				}
			for(Entity e: Bukkit.getWorld(name).getEntities()) {
				e.remove();
			}
			Bukkit.unloadWorld(name, true);
			new File(Bukkit.getWorldContainer().getPath()+"/"+name).delete();
			return true;
		}return false;
	}
		}
		return false;
}
	public boolean unloadWorld(String name, boolean saveWorld) {
		if(Bukkit.getWorld(name)!=null) {
		List<World> w = Bukkit.getWorlds();
		w.remove(Bukkit.getWorld(name));
		if(w.isEmpty()==false) {
		for(Player p : TheAPI.getCountingAPI().getOnlinePlayers())
			if(p.getWorld().getName().equals(name)) {
				p.setNoDamageTicks(120*20);
				p.teleport(new Location(w.get(0),0,200,0));
			}
		Bukkit.unloadWorld(name, saveWorld);
	}return false;
		}return false;
	}
}
