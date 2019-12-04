package me.Straiker123;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

public class GameAPI {
	private FileConfiguration w = LoaderClass.gameapi.getConfig();
	String s;
	public GameAPI(String name) {
		s= name;
	}
	public void createArena(String arena, String arenaName) {
		w.set(s+".Arenas."+arena+".Name", arenaName);
		LoaderClass.gameapi.save();
	}
	public void deleteArena(String arena) {
		w.set(s+".Arenas."+arena,null);
		LoaderClass.gameapi.save();
	}
	
	public void addPlayer(String arena, String team, String player) {
		List<String> list = w.getStringList(s+".Arenas."+arena+".Teams."+team);
		list.add(player);
		w.set(s+".Arenas."+arena+".Teams."+team, list);
		LoaderClass.gameapi.save();
	}
	
	public static enum Setting {
		arena_time,
		max_players,
		min_players,
		min_teams,
		runnable_on_end
	}

	public void arenaSetting(String arena, Setting s, Object value) {
		if(s==Setting.runnable_on_end) {
			LoaderClass.win_rewards.put(s+":"+arena, (Runnable)value);
			w.set(s+".Arenas."+arena+".Setting.win_rewards", true);
			return;
		}
		w.set(s+".Arenas."+arena+".Setting."+s.toString(), value);
		LoaderClass.gameapi.save();
	}
	
	public int getPlayersInGame(String arena) {
		int i = 0;
		for(String s:w.getConfigurationSection(s+".Arenas."+arena+".Teams").getKeys(false))
			i=i+w.getStringList(s+".Arenas."+arena+".Teams."+s).size()-1;
		return i;
	}
	
	public int getTeamsInGame(String arena) {
		int i = w.getConfigurationSection(s+".Arenas."+arena+".Teams").getKeys(false).size()-1;
		return i;
	}
	
	public List<String> getArenas(){
		List<String> l = new ArrayList<String>();
		if(w.getString("Arenas")!=null)
		for(String s : w.getConfigurationSection("Arenas").getKeys(false))
			l.add(s);
		return l;
	}
	
	public void startArena(String arena) {
		int mis = getPlayersInGame(arena)-w.getInt(s+".Arenas."+arena+".Setting.min_players");
		if(mis<0) {
			TheAPI.getConsole().sendMessage(TheAPI.colorize("&2TheGameAPI &b> &6In arena "+arena+" missing "+mis+" players"));
			return;
		}
		if(getPlayersInGame(arena)>w.getInt(s+".Arenas."+arena+".Setting.max_players")) {
			TheAPI.getConsole().sendMessage(TheAPI.colorize("&2TheGameAPI &b> &6In arena "+arena+" is too much players"));
			return;
		}
		if(getTeamsInGame(arena)>w.getInt(s+".Arenas."+arena+".Setting.min_teams")) {
			TheAPI.getConsole().sendMessage(TheAPI.colorize("&2TheGameAPI &b> &6Arena "+arena+" required minimal "+w.getInt(s+".Arenas."+arena+".Setting.min_teams")+" teams"));
			return;
		}
		
		w.set(s+".Arenas."+arena+".InGame", true);
		LoaderClass.gameapi.save();
		
		LoaderClass.GameAPI_Arenas.put(arena, Bukkit.getScheduler().scheduleSyncRepeatingTask(LoaderClass.plugin, new Runnable() {
			int time = 0;
			@Override
			public void run() {
				++time;

				for(String s:w.getConfigurationSection(s+".Arenas."+arena+".Teams").getKeys(false))
					for(String d:w.getStringList(s+".Arenas."+arena+".Teams."+s)) {
						if(Bukkit.getPlayer(d)==null) {
							List<String> list = w.getStringList(s+".Arenas."+arena+".Teams."+s);
							list.remove(d);
							w.set(s+".Arenas."+arena+".Teams."+s, list);
							LoaderClass.gameapi.save();
						}
					}
				
				if(time>=w.getInt(s+".Arenas."+arena+".Setting.arena_time")) {
					stopArena(arena,true);
					return;
				}
			}}, 20,20));
	}
	
	public void stopArena(String arena, boolean runnable_on_end) {
		w.set(s+".Arenas."+arena+".InGame", false);
		LoaderClass.gameapi.save();
		Bukkit.getScheduler().cancelTask(LoaderClass.GameAPI_Arenas.get(arena));
		if(runnable_on_end) {
			try {
			LoaderClass.win_rewards.get(s+":"+arena).run();
			}catch(Exception e) {
				
			}
		}
	}
	
}
