package me.Straiker123;

import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

public class ScoreboardAPI {
	Player p;
	HashMap<Integer,String> map = new HashMap<Integer, String>();
	String title = "&bTheAPI";
	Scoreboard s;
	public ScoreboardAPI(Player p, Scoreboard sb) {
		this.p=p;
		s=sb;
	}
	
	public void setTitle(String title) {
		if(title!=null)
		this.title=title;
	}
	
	public void addLine(String line, int position) {
		map.put(position,TheAPI.colorize(line));
	}
	public Scoreboard getScoreboard() {
		return s;
	}
	
	@SuppressWarnings("deprecation")
	public void create() {
		TheAPI.broadcastMessage("&6Clearing memory..");
		String cleared = TheAPI.getMemoryAPI().clearMemory();
		TheAPI.broadcastMessage("&aCleared "+cleared+"MB of memory!");
		Objective d = s.getObjective("a");
		 if(d!=null)
			 d.unregister();
			 d=s.registerNewObjective("a", "dummy");
		d.setDisplaySlot(DisplaySlot.SIDEBAR);
		d.setDisplayName(TheAPI.colorize(title));
		if(map.isEmpty()==false && map!=null)
		  for(Integer w:map.keySet()) {
			  String s = map.get(w);
			  try {
			  if(s.length() > 40)s=s.substring(0,39);
			  d.getScore(s).setScore(w);
			  }catch(Exception e) {
				  if(s.length() > 16)s=s.substring(0,15);
				  d.getScore(s).setScore(w);
			  }
			}
		p.setScoreboard(s);
	}
}
