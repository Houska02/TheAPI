package me.Straiker123;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class NameTagAPI {
	String prefix;
	String suffix;
	Player p;
	public NameTagAPI(Player p, String prefix, String suffix) {
		this.p=p;
		this.prefix=prefix;
		this.suffix=suffix;
	}
	/**
	 * 
	 * @param teamName
	 * By teamName you can sort players in tablist -> create sorted tablist
	 */
	@SuppressWarnings("deprecation")
	public void setNameTag(String teamName, Scoreboard sb) {
		if(teamName==null)teamName="z";
		if(sb.getTeam(teamName)==null)sb.registerNewTeam(teamName);
		Team t = sb.getTeam(teamName);
		
		if(suffix !=null)
		t.setSuffix(TheAPI.colorize(suffix));

		if(prefix != null) {
		t.setPrefix(TheAPI.colorize(prefix));
		if (TheAPI.getServerVersion().contains("1_14") ||TheAPI.getServerVersion().contains("1_13"))
			t.setColor(fromPrefix(prefix));
		}
		t.addPlayer(p);
	}

	private ChatColor fromPrefix(String prefix) {
		char colour = '\u0000';
		char[] chars = prefix.toCharArray();
		for (int i = 0; i < chars.length; ++i) {
    char code;
    char at = chars[i];
    if (at != '\u00a7' && at != '&' || i + 1 >= chars.length || ChatColor.getByChar((char)(code = chars[i + 1])) == null) continue;
    colour = code;
		}
		return colour == '\u0000' ? ChatColor.RESET : ChatColor.getByChar((char)colour);
	}

}