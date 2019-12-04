package me.Straiker123;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PunishmentAPI {

	public List<String> getAccounts(String player){
		return findPlayerByIP(getIP(player));
	}
	public void setBan(String player, String reason) {
		if(reason==null)reason="Uknown";
		LoaderClass.data.getConfig().set("bans."+player+".ban", reason);
		LoaderClass.data.save();
		Player p = Bukkit.getPlayer(player);
		if(p!=null)
		
			p.kickPlayer(TheAPI.colorize(LoaderClass.config.getConfig().getString("Format.Ban")
				.replace("%player%", player)
				.replace("%reason%", reason)));
			if(!silent) {
				TheAPI.broadcastMessage(LoaderClass.config.getConfig().getString("Format.Broadcast.Ban")
						.replace("%player%", player)
						.replace("%reason%", reason));
			}else {
			TheAPI.broadcast(LoaderClass.config.getConfig().getString("Format.Broadcast-Ban")
					.replace("%player%", player)
					.replace("%reason%", reason),LoaderClass.config.getConfig().getString("Format.Broadcast-Ban-Permission"));
			}
		}
	public static boolean silent;
	public void setSilent(boolean silent) {
		PunishmentAPI.silent=silent;
	}

	public void setTempBan(String player, String reason, long time) {
		if(reason==null)reason="Uknown";
		LoaderClass.data.getConfig().set("bans."+player+".tempban.reason", reason);
		LoaderClass.data.getConfig().set("bans."+player+".tempban.start", System.currentTimeMillis());
		LoaderClass.data.getConfig().set("bans."+player+".tempban.time", time);
		LoaderClass.data.save();
		Player p = Bukkit.getPlayer(player);
		if(p!=null)
		p.kickPlayer(TheAPI.colorize(LoaderClass.config.getConfig().getString("Format.TempBan")
				.replace("%player%", player)
				.replace("%reason%", reason)
				.replace("%time%", TheAPI.getTimeConventorAPI().setTimeToString(time)))); 
		if(!silent) {
			TheAPI.broadcastMessage(LoaderClass.config.getConfig().getString("Format.TempBan")
					.replace("%player%", player)
					.replace("%reason%", reason)
					.replace("%time%", TheAPI.getTimeConventorAPI().setTimeToString(time)));
		}else {
			TheAPI.broadcast(LoaderClass.config.getConfig().getString("Format.Broadcast-TempBan")
					.replace("%player%", player)
					.replace("%reason%", reason)
					.replace("%time%", TheAPI.getTimeConventorAPI().setTimeToString(time)),LoaderClass.config.getConfig().getString("Format.Broadcast-TempBan-Permission"));
		}
	}
	
	private boolean isIP(String s) {
		s=s.replaceFirst("/", "");
        Matcher m = Pattern.compile("^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$").matcher(s.toLowerCase());
   	     return m.find();
	}

	public void setBanIP(String playerOrIP, String reason) {
		if(reason==null)reason="Uknown";
		String ip =playerOrIP;
		if(!isIP(playerOrIP))ip=getIP(playerOrIP);
		LoaderClass.data.getConfig().set("bans."+ip+".banip", reason);
		LoaderClass.data.save();
		for(String s : findPlayerByIP(ip)) {
	
		Player p = Bukkit.getPlayer(s);
		if(p!=null)
			p.kickPlayer(TheAPI.colorize(LoaderClass.config.getConfig().getString("Format.BanIP")
					.replace("%player%", s)
					.replace("%reason%", reason))); 
		}
		if(!silent) {
			TheAPI.broadcastMessage(LoaderClass.config.getConfig().getString("Format.Broadcast-BanIP")
					.replace("%target%", ip)
					.replace("%reason%", reason));
		}else {
			TheAPI.broadcast(LoaderClass.config.getConfig().getString("Format.Broadcast-BanIP")
					.replace("%target%", ip)
					.replace("%reason%", reason),LoaderClass.config.getConfig().getString("Format.Broadcast-BanIP-Permission"));
		}
	}
	public List<String> findPlayerByIP(String ip) {
		List<String> ips = new ArrayList<String>();
		if(LoaderClass.data.getConfig().getString("data")!=null)
		for(String s:LoaderClass.data.getConfig().getConfigurationSection("data").getKeys(false)) {
			if(ip.equals(getIP(s)))ips.add(s);
		}
		return ips;
	}
	
	public String getIP(String player) {
		return LoaderClass.data.getConfig().getString("data."+player+".ip").replace("_", ".");
	}
	
	public void setMute(String player, String reason) {
		if(reason==null)reason="Uknown"; 
		LoaderClass.data.getConfig().set("bans."+player+".mute", reason);
		LoaderClass.data.save(); 
		if(!silent) {
			TheAPI.broadcastMessage(LoaderClass.config.getConfig().getString("Format.Broadcast.Mute")
					.replace("%player%", player)
					.replace("%reason%", reason));
		}else {
			TheAPI.broadcast(LoaderClass.config.getConfig().getString("Format.Broadcast.Mute")
					.replace("%player%", player)
					.replace("%reason%", reason),LoaderClass.config.getConfig().getString("Format.Broadcast-Mute-Permission"));
		}
	}
	public void setTempMute(String player, String reason, long time) {
		if(reason==null)reason="Uknown";
		LoaderClass.data.getConfig().set("bans."+player+".tempmute.time", time);
		LoaderClass.data.getConfig().set("bans."+player+".tempmute.start", System.currentTimeMillis());
		LoaderClass.data.getConfig().set("bans."+player+".tempmute.reason", reason);
		LoaderClass.data.save(); 
		if(!silent) {

			TheAPI.broadcastMessage(LoaderClass.config.getConfig().getString("Format.Broadcast.TempMute")
					.replace("%player%", player)
					.replace("%reason%", reason).replace("%time%", TheAPI.getTimeConventorAPI().setTimeToString(time)));
			}else {
			TheAPI.broadcast(LoaderClass.config.getConfig().getString("Format.Broadcast.TempMute")
					.replace("%player%", player)
					.replace("%reason%", reason).replace("%time%", TheAPI.getTimeConventorAPI().setTimeToString(time))
					,LoaderClass.config.getConfig().getString("Format.Broadcast-TempMute-Permission"));
		}
	}
	public void setTempBanIP(String playerOrIP, String reason, long time) {
		if(reason==null)reason="Uknown";
		
		String ip =playerOrIP;
		if(!isIP(ip))
		ip=getIP(playerOrIP);
		LoaderClass.data.getConfig().set("bans."+ip+".tempbanip.time", time);
		LoaderClass.data.getConfig().set("bans."+ip+".tempbanip.start", System.currentTimeMillis());
		LoaderClass.data.getConfig().set("bans."+ip+".tempbanip.reason", reason);
		LoaderClass.data.save(); 
		if(!silent) {

			TheAPI.broadcastMessage(LoaderClass.config.getConfig().getString("Format.Broadcast.TempBanIP")
					.replace("%target%", ip)
					.replace("%reason%", reason).replace("%time%", TheAPI.getTimeConventorAPI().setTimeToString(time)));
			}else {
			TheAPI.broadcast(LoaderClass.config.getConfig().getString("Format.Broadcast.TempBanIP")
					.replace("%target%", ip)
					.replace("%reason%", reason).replace("%time%", TheAPI.getTimeConventorAPI().setTimeToString(time))
					,LoaderClass.config.getConfig().getString("Format.Broadcast-TempBanIP-Permission"));
		}
	}

	public boolean hasBan(String player) {
		return LoaderClass.data.getConfig().getString("bans."+player+".ban") != null;
	}
	public boolean hasTempMute(String player) {
		return LoaderClass.data.getConfig().getString("bans."+player+".tempmute.start") != null;
	}
	public boolean hasBanIP(String playerOrIP) {
		if(isIP(playerOrIP))
			return LoaderClass.data.getConfig().getString("bans."+playerOrIP+".banip") != null;
		return LoaderClass.data.getConfig().getString("bans."+getIP(playerOrIP)+".banip") != null;
	}
	public boolean hasTempBanIP(String playerOrIP) {
		if(isIP(playerOrIP)) {
		int time = (int) (getTempBanIPStart(playerOrIP) - System.currentTimeMillis() + getTempBanIPTime(playerOrIP));
		return time < 0;
		}else {
			int time = (int) (getTempBanIPStart(getIP(playerOrIP)) - System.currentTimeMillis() + getTempBanIPTime(getIP(playerOrIP)));
			return time < 0;
		}
	}
	
	public int getTempBanIP_ExpireTime(String playerOrIP){
		if(isIP(playerOrIP)) {
			int time = (int) (getTempBanIPStart(playerOrIP) - System.currentTimeMillis() + getTempBanIPTime(playerOrIP));
			return time;
			}else {
				int time = (int) (getTempBanIPStart(getIP(playerOrIP)) - System.currentTimeMillis() + getTempBanIPTime(getIP(playerOrIP)));
				return time;
			}
	}
	public boolean hasMute(String player) {
		return LoaderClass.data.getConfig().getString("bans."+player+".mute") != null;
	}
	public boolean hasTempBan(String player) {
		int time = (int) (getTempBanStart(player) - System.currentTimeMillis() + getTempBanTime(player));
		return time < 0;
	}
	public int getTempBan_ExpireTime(String player) {
		int time = (int) (getTempBanStart(player) - System.currentTimeMillis() + getTempBanTime(player));
		return time;
	}
	public String getBanReason(String player) {
		return LoaderClass.data.getConfig().getString("bans."+player+".ban");
	}
	public String getTempBanReason(String player) {
		return LoaderClass.data.getConfig().getString("bans."+player+".tempban.reason");
	}
	public String getBanIPReason(String playerOrIP) {
		if(isIP(playerOrIP))
			return LoaderClass.data.getConfig().getString("bans."+playerOrIP+".banip");
		return LoaderClass.data.getConfig().getString("bans."+getIP(playerOrIP)+".banip");
	}
	public String getMuteReason(String player) {
		return LoaderClass.data.getConfig().getString("bans."+player+".mute");
	}
	public String getTempMuteReason(String player) {
		return LoaderClass.data.getConfig().getString("bans."+player+".tempmute.reason");
	}
	public long getTempBanTime(String player) {
		return LoaderClass.data.getConfig().getLong("bans."+player+".tempban.time");
	}
	public long getTempMuteTime(String player) {
		return LoaderClass.data.getConfig().getLong("bans."+player+".tempmute.time");
	}

	public long getTempBanStart(String player) {
		return LoaderClass.data.getConfig().getLong("bans."+player+".tempban.start");
	}
	public long getTempMuteStart(String player) {
		return LoaderClass.data.getConfig().getLong("bans."+player+".tempmute.start");
	}

	public long getTempBanIPStart(String player) {
		if(isIP(player))
			return LoaderClass.data.getConfig().getLong("bans."+player+".tempbanip.start");
		return LoaderClass.data.getConfig().getLong("bans."+getIP(player)+".tempbanip.start");
	}
	public long getTempBanIPTime(String player) {
		if(isIP(player))
			return LoaderClass.data.getConfig().getLong("bans."+player+".tempbanip.time");
		return LoaderClass.data.getConfig().getLong("bans."+getIP(player)+".tempbanip.time");
	}
	public String getTempBanIPReason(String player) {
		if(isIP(player))
			return LoaderClass.data.getConfig().getString("bans."+player+".tempbanip.reason");
		return LoaderClass.data.getConfig().getString("bans."+getIP(player)+".tempbanip.reason");
	}

	
	public void unMute(String player) {
		LoaderClass.data.getConfig().set("bans."+player+".mute", null);
		LoaderClass.data.save();
	}
	public void unTempMute(String player) {
		LoaderClass.data.getConfig().set("bans."+player+".tempmute", null);
		LoaderClass.data.save();
	}
	public void unBan(String player) {
		LoaderClass.data.getConfig().set("bans."+player+".ban", null);
		LoaderClass.data.save();
	}
	public void unTempBan(String player) {
		LoaderClass.data.getConfig().set("bans."+player+".tempban", null);
		LoaderClass.data.save();
	}
	public void unBanIP(String playerOrIP) {
		if(isIP(playerOrIP))
			LoaderClass.data.getConfig().set("bans."+playerOrIP+".banip", null);
		else
			LoaderClass.data.getConfig().set("bans."+getIP(playerOrIP)+".banip", null);
		LoaderClass.data.save();
	}

	public void unTempBanIP(String playerOrIP) {
		if(isIP(playerOrIP))
			LoaderClass.data.getConfig().set("bans."+playerOrIP+".tempbanip", null);
		else
			LoaderClass.data.getConfig().set("bans."+getIP(playerOrIP)+".tempbanip", null);
		LoaderClass.data.save();
	}
	
}
