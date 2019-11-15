package me.Straiker123;

import java.util.ArrayList;
import java.util.Collection;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class CountingAPI {
	
	public int getMaxPlayers() {
		return Bukkit.getMaxPlayers();
	}
	
	public Collection<? extends Player> getOnlinePlayers(){
		return Bukkit.getOnlinePlayers();
	}

	public Collection<Plugin> getEnabledPlugins(){
		 Collection<Plugin> a = new ArrayList<Plugin>();
		 for(Plugin p : Bukkit.getPluginManager().getPlugins()) {
			 if(p.isEnabled())a.add(p);
		 }
		 return a;
	}
	public Collection<Plugin> getDisabledPlugins(){
		 Collection<Plugin> a = new ArrayList<Plugin>();
		 for(Plugin p : Bukkit.getPluginManager().getPlugins()) {
			 if(!p.isEnabled())a.add(p);
		 }
		 return a;
	}
	

	public Collection<Plugin> getPlugins(){
		 Collection<Plugin> a = new ArrayList<Plugin>();
		 for(Plugin p : Bukkit.getPluginManager().getPlugins()) {
			a.add(p);
		 }
		 return a;
	}
	
	
	public Collection<Plugin> getPluginsUsingTheAPI() {
		 Collection<Plugin> a = new ArrayList<Plugin>();
		 for(Plugin p : Bukkit.getPluginManager().getPlugins()) {
			 if(p ==null ||p.isEnabled()==false)continue;
				if(p.getDescription().getDepend() != null && p.getDescription().getDepend().contains("TheAPI")
						||p.getDescription().getSoftDepend() != null && p.getDescription().getSoftDepend().contains("TheAPI"))a.add(p);
			}
		 return a;
	}
}
