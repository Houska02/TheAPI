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
		map.put(position,line);
	}
	
	@SuppressWarnings("deprecation")
	public void create() {
		Objective d = s.getObjective("a");
		 if(d!=null)
			 d.unregister();
			 d=s.registerNewObjective("a", "dummy");
		d.setDisplaySlot(DisplaySlot.SIDEBAR);
		d.setDisplayName(TheAPI.colorize(title));
		if(map.isEmpty()==false && map!=null)
		  for(Integer w:map.keySet()) {
			  d.getScore(TheAPI.colorize(map.get(w))).setScore(w);
			}
		p.setScoreboard(s);
	}
}
