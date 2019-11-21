package me.Straiker123;

import java.util.HashMap;

public class ReportSystem {
	public void sendReport(String sender, String reported, String message) {
		LoaderClass.data.set("data."+sender+".reports."+reported+".target", reported);
		LoaderClass.data.set("data."+sender+".reports."+reported+".message", message);
		LoaderClass.plugin.a.save();
		TheAPI.broadcast(LoaderClass.config.getString("Format.Report").replace("%sender%", sender).replace("%target%", reported)
				.replace("%target%", reported).replace("%message%", message), LoaderClass.config.getString("Format.Report-Permission"));
	}
	/**
	 * 
	 * @return HashMap<ReportMessage, ReportedPlayer>
	 */
	public HashMap<String, String> getReports(String player){
		HashMap<String, String> a = new HashMap<String, String>();
		for(String s:LoaderClass.data.getConfigurationSection("data."+player+".reports").getKeys(false))
		a.put(LoaderClass.data.getString("data."+player+".reports."+s+".message"), LoaderClass.data.getString("data."+player+".reports."+s+".target"));
		return a;
	}
	
}
