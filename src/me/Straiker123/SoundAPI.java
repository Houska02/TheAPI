package me.Straiker123;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import me.Straiker123.Utils.Error;

public class SoundAPI {
	public boolean existSound(String sound) {
		return valueOf(sound) != null;
	}

	public void playSound(Player player, String sound, float volume, float pitch) {
		if(player == null) {
			Error.err("playing sound", "Player is null");
		}
		if(sound == null ||getByName(sound) ==null) {
			Error.err("playing sound", "Sound is null");
		}
		player.playSound(player.getLocation(), getByName(sound), volume, pitch);
	}

	public void playSound(Location where, String sound, float volume, float pitch) {
		if(where != null && where.getWorld()!=null && getByName(sound)!=null)
			if(where == null||where.getWorld()==null) {
				Error.err("playing sound", "Location is null");
			}
			if(sound == null ||getByName(sound) ==null) {
				Error.err("playing sound", "Sound is null");
			}
		where.getWorld().playSound(where, getByName(sound), volume, pitch);
	}

	public void playSound(Player player, Sound sound, float volume, float pitch) {
		playSound(player, sound.name());
	}

	public void playSound(Location where, Sound sound, float volume, float pitch) {
		playSound(where, sound.name());
	}
	
	public void playSound(Player player, String sound) {
		playSound(player, sound,1 ,1);
	}

	public void playSound(Location where, String sound) {
		playSound(where, sound,1 ,1);
	}

	public void playSound(Player player, Sound sound) {
		playSound(player, sound,1 ,1);
	}

	public void playSound(Location where, Sound sound) {
		playSound(where, sound,1 ,1);
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
				if(s.name().toLowerCase().equals(sound.toLowerCase()))a=s.name();
				else
				if(s.name().toLowerCase().contains(sound.toLowerCase()))c=s.name();
			}
		if(a==null && c!=null)a=c;
		if(Sound.valueOf(a) != null)
		return Sound.valueOf(a);
		return null;
	}
}
