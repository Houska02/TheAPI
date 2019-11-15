package me.Straiker123;

import java.util.List;

public class CooldownAPI {
	String c;
	public CooldownAPI(String cooldownname) {
		c=cooldownname;
	}
	
	
	public void createCooldown(double length) {
		LoaderClass.col.set(c+".start", System.currentTimeMillis());
		LoaderClass.col.set(c+".time", length);
		LoaderClass.plugin.f.save();
	}
	public void addPlayerToCooldown(String player) {
		LoaderClass.col.set(c+".users", LoaderClass.col.getStringList(c+".users").add(player));
		LoaderClass.plugin.f.save();
	}
	
	public boolean expired(String c, String p) {
		return (LoaderClass.col.getLong(c+".start")-System.currentTimeMillis())+LoaderClass.col.getInt(c+".time") < 0;
	}
	

	public void removeCooldown(String player, double length) {
		List<String> a=  LoaderClass.col.getStringList(c+".users");
		a.remove(player);
		if(a.size()==0) {
			LoaderClass.col.set(c, null);
		}else {
			LoaderClass.col.set(c+".users", a);
		}
		LoaderClass.plugin.f.save();
	}
}
