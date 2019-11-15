package me.Straiker123;

import java.util.HashMap;
import java.util.List;

public class ReportSystem {
	public void sendReport(String s, String target, String message) {
		LoaderClass.ban.set(s+".reports."+target+".target", target);
		LoaderClass.ban.set(s+".reports."+target+".message", message);
		LoaderClass.plugin.a.save();
		TheAPI.broadcast(LoaderClass.config.getString("Format.Report").replace("%sender%", s).replace("%target%", target)
				.replace("%target%", target).replace("%message%", message), LoaderClass.config.getString("Format.Report-Permission"));
	}
	
	public List<String> getAccounts(String player){
		return TheAPI.getPunishmentAPI().findPlayerByIP(LoaderClass.ban.getString(player+".ip"));
	}
	/**
	 * 
	 * @return HashMap<Message, ReportedPlayer>
	 */
	public HashMap<String, String> getReports(String player){
		HashMap<String, String> a = new HashMap<String, String>();
		for(String s:LoaderClass.ban.getConfigurationSection(player+".reports").getKeys(false))
		a.put(LoaderClass.ban.getString(player+".reports."+s+".message"), LoaderClass.ban.getString(player+".reports."+s+".target"));
		return a;
	}
	
}
