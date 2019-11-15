package me.Straiker123;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.plugin.InvalidDescriptionException;
import org.bukkit.plugin.InvalidPluginException;
import org.bukkit.plugin.Plugin;

public class PluginManagerAPI {
	public boolean enablePlugin(String plugin) {
		if(!Bukkit.getPluginManager().isPluginEnabled(plugin)) {
			Bukkit.getPluginManager().enablePlugin(getPlugin(plugin));
			return true;
		}
		return false;
	}
	
	public Plugin getPlugin(String plugin) {
		Plugin p = null;
		for(Plugin s:Bukkit.getPluginManager().getPlugins()) {
			if(s.getName().equalsIgnoreCase(plugin))p= s;
		}
		return p;
	}
	
	public String loadPlugin(String pluginName) {
		try {
			Plugin p = Bukkit.getPluginManager().loadPlugin(new File("plugins/"+pluginName+".jar"));
			p.onLoad();
			Bukkit.getPluginManager().enablePlugin(p);
			return TheAPI.colorize("&aPlugin loaded");
		} catch (InvalidPluginException e) {
			return TheAPI.colorize("&4Invalid plugin");
		} catch (InvalidDescriptionException e) {
			return TheAPI.colorize("&cInvalid description");
		}
	}
	
	
	public boolean disablePlugin(String plugin) {
		if(Bukkit.getPluginManager().isPluginEnabled(plugin)) {
			Bukkit.getPluginManager().disablePlugin(getPlugin(plugin));
			return true;
		}
		return false;
	}
}
