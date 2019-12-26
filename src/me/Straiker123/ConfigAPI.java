package me.Straiker123;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import me.Straiker123.Utils.Error;

public class ConfigAPI {
	String name;
	String h;
	String loc;
	FileConfiguration a;
	Map<String, Object> c=new HashMap<String, Object>();
	
	public ConfigAPI(String configName, String localization) {
	name=configName;	
	loc=localization;
	}
	/**
	 * @return boolean if exist string in config (Can be used before create a config)
	 */
	public boolean existPath(String string) {
		return new File("plugins/"+loc+"/"+name+"."+end).exists() && YamlConfiguration.loadConfiguration(new File("plugins/"+loc+"/"+name+"."+end)).getString(string) != null;
	}
	
	public void addDefault(String path, Object value) {
		if(!c.containsKey(path))
		c.put(path, value);
		else
			c.replace(path, value);
	}
	
	public void addDefaults(Map<String, Object> defaults){
		c=defaults;
	}
	String end = "yml";
	File f;
	
	public boolean existFile() {
		return f!=null;
	}
	
	public File getFile() {
		if(this.f==null) {
		File f = new File("plugins/"+loc+"/"+name+"."+end);
		if(f.exists())return f;
		try {
			f.createNewFile();
		} catch (IOException e) {
			if(LoaderClass.config.getConfig() == null || LoaderClass.config.getConfig() != null && !LoaderClass.config.getConfig().getBoolean("Options.HideErrors")) {
				TheAPI.getConsole().sendMessage(TheAPI.colorize("&bTheAPI&7: &cError when getting file of "+name+"."+end+" config:"));
				e.printStackTrace();
				TheAPI.getConsole().sendMessage(TheAPI.colorize("&bTheAPI&7: &cEnd of error."));
				}else
					Error.sendRequest("&bTheAPI&7: &cError when getting file of "+name+"."+end+" config");
		}
		return f;
		}
		return f;
	}
	
	public void setHeader(String header) {
		h=header;
	}
	public FileConfiguration getConfig() {
		return a;
	}
	public void setCustomEnd(String customEnd) {
		if(customEnd != null)
		end=customEnd;
	}
	
	private boolean check() {
		if(a==null) {
			TheAPI.getConsole().sendMessage(TheAPI.colorize("&bTheAPI&7: &cFileConfiguration is null"));
			return false;
		}else
		if(f==null) {
			TheAPI.getConsole().sendMessage(TheAPI.colorize("&bTheAPI&7: &cFile is null"));
			return false;
		}else
		return true;
	}
	
	public boolean save() {
		try {
		if(check()) {
		a.save(f);
		return true;
		}
		return false;
	} catch (Exception e) {
		if(LoaderClass.config.getConfig() == null || LoaderClass.config.getConfig() != null && !LoaderClass.config.getConfig().getBoolean("Options.HideErrors")) {
			TheAPI.getConsole().sendMessage(TheAPI.colorize("&bTheAPI&7: &cError when saving "+name+"."+end+" config:"));
		e.printStackTrace();
		TheAPI.getConsole().sendMessage(TheAPI.colorize("&bTheAPI&7: &cEnd of error."));
		}else
			Error.sendRequest("&bTheAPI&7: &cError when saving "+name+"."+end+" config");
		return false;
	}
	}
	
	public boolean reload() {
		try {
		f=new File("plugins/"+loc+"/"+name+"."+end);
		a = YamlConfiguration.loadConfiguration(f);
		if(h!=null)a.options().header(h);
		if(c!=null && !c.isEmpty()) {
		a.addDefaults(c);
		}
		a.options().copyDefaults(true).copyHeader(true);
		save();
		if(!LoaderClass.list.contains(this))
		LoaderClass.list.add(this);
		return true;
		} catch (Exception e) {
			if(LoaderClass.config.getConfig() == null || LoaderClass.config.getConfig() != null && !LoaderClass.config.getConfig().getBoolean("Options.HideErrors")) {
			TheAPI.getConsole().sendMessage(TheAPI.colorize("&bTheAPI&7: &cError when reloading "+name+"."+end+" config:"));
			e.printStackTrace();
			TheAPI.getConsole().sendMessage(TheAPI.colorize("&bTheAPI&7: &cEnd of error."));
			}else
				Error.sendRequest("&bTheAPI&7: &cError when reloading "+name+"."+end+" config");
			return false;
		}
	}
	
	public boolean create() {
		try {
		f=getFile();
		a = YamlConfiguration.loadConfiguration(f);
		if(h!=null)a.options().header(h);
		if(c!=null && !c.isEmpty()) {
		a.addDefaults(c);
		}
		a.options().copyDefaults(true).copyHeader(true);
		save();
		if(!LoaderClass.list.contains(this))
		LoaderClass.list.add(this);
		return true;
		} catch (Exception e) {
			if(LoaderClass.config.getConfig() == null || LoaderClass.config.getConfig() != null && !LoaderClass.config.getConfig().getBoolean("Options.HideErrors")) {
			TheAPI.getConsole().sendMessage(TheAPI.colorize("&bTheAPI&7: &cError when creating "+name+"."+end+" config:"));
			e.printStackTrace();
			TheAPI.getConsole().sendMessage(TheAPI.colorize("&bTheAPI&7: &cEnd of error."));
			}else
				Error.sendRequest("&bTheAPI&7: &cError when creating "+name+"."+end+" config");
			return false;
		}
	}
	public Map<String, Object> getDefaults(){
		return c;
	}
	
	public boolean delete() {
		File f = getFile();
		if(f.exists()) {
		f.delete();
		c.clear();
		if(LoaderClass.list.contains(this))
		LoaderClass.list.remove(this);
		return true;
		}
		return false;
	}
	
}
