package me.Straiker123;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class SoundAPI {
	public boolean existSound(String sound) {
		return valueOf(sound) != null;
	}
	
	public void playSound(Player player, String sound, float volume, float pitch) {
		player.playSound(player.getLocation(), getByName(sound), volume, pitch);
	}

	public void playSound(Location where, String sound, float volume, float pitch) {
		where.getWorld().playSound(where, getByName(sound), volume, pitch);
	}
	
	public Sound[] values() {
		return Sound.values();
	}
	
	public List<Sound> valuesInList(){
		List<Sound> a = new ArrayList<Sound>();
		for(Sound s : values())a.add(s);
		return a;
	}
	/**
	 * 
	 * @param sound
	 * Name of sound
	 * @return Sound
	 */
	public Sound valueOf(String sound) {
		return getByName(sound);
	}
	
	/**
	 * 
	 * @param sound
	 * Name of sound
	 * @return Sound
	 */
	public Sound getByName(String sound) {
		String a = null;
		String c = null;
		for(Sound s:values()) {
				if(a!=null)break;
				if(s.name().toLowerCase().equals(sound))a=s.name();
				else
				if(s.name().toLowerCase().contains(sound))c=s.name();
			}
		if(a==null && c!=null)a=c;
		if(Sound.valueOf(a) != null)
		return Sound.valueOf(a);
		return null;
	}
}
