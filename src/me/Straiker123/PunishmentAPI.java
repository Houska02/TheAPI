package me.Straiker123;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PunishmentAPI {
	
	public void setBan(String player, String reason) {
		if(reason==null)reason="Uknown";
		LoaderClass.ban.set(player+".ban", reason);
		LoaderClass.plugin.a.save();
		Player p = Bukkit.getPlayer(player);
		if(p!=null)
		
			p.kickPlayer(TheAPI.colorize(LoaderClass.config.getString("Format.Ban")
				.replace("%player%", player)
				.replace("%reason%", reason)));
			if(!silent) {
				TheAPI.broadcastMessage(LoaderClass.config.getString("Format.Broadcast.Ban")
						.replace("%player%", player)
						.replace("%reason%", reason));
			}else {
			TheAPI.broadcast(LoaderClass.config.getString("Format.Broadcast-Ban")
					.replace("%player%", player)
					.replace("%reason%", reason),LoaderClass.config.getString("Format.Broadcast-Ban-Permission")); //perm :D hm i hate my mouse Czech republic
			}
		}
	public static boolean silent;
	public void setSilent(boolean silent) {
		PunishmentAPI.silent=silent;
	}

	public void setTempBan(String player, String reason, long time) {
		if(reason==null)reason="Uknown";
		LoaderClass.ban.set(player+".tempban.reason", reason);
		LoaderClass.ban.set(player+".tempban.start", System.currentTimeMillis());
		LoaderClass.ban.set(player+".tempban.time", time);
		LoaderClass.plugin.a.save();
		Player p = Bukkit.getPlayer(player);
		if(p!=null)
		p.kickPlayer(TheAPI.colorize(LoaderClass.config.getString("Format.TempBan")
				.replace("%player%", player)
				.replace("%reason%", reason)
				.replace("%time%", TheAPI.getTimeConventorAPI().setTimeToString(time)))); 
		if(!silent) {
			TheAPI.broadcastMessage(LoaderClass.config.getString("Format.TempBan")
					.replace("%player%", player)
					.replace("%reason%", reason)
					.replace("%time%", TheAPI.getTimeConventorAPI().setTimeToString(time)));
		}else {
			TheAPI.broadcast(LoaderClass.config.getString("Format.Broadcast-TempBan")
					.replace("%player%", player)
					.replace("%reason%", reason)
					.replace("%time%", TheAPI.getTimeConventorAPI().setTimeToString(time)),LoaderClass.config.getString("Format.Broadcast-TempBan-Permission"));
		}
	}
	public void setBanIP_IPAddress(String ip, String reason) { 
		if(reason==null)reason="Uknown";
		LoaderClass.ban.set(ip+".banip", reason);
		LoaderClass.plugin.a.save();
	
		for(String s : findPlayerByIP(ip)) {
		Player p = Bukkit.getPlayer(s);
		if(p!=null)
			p.kickPlayer(TheAPI.colorize(LoaderClass.config.getString("Format.BanIP-IPAddress")
					.replace("%ip%", ip)
					.replace("%reason%", reason))); 
	
		}
		if(!silent) {
			TheAPI.broadcastMessage(LoaderClass.config.getString("Format.Broadcast-BanIP-IPAddress")
					.replace("%ip%", ip)
					.replace("%reason%", reason));
		}else {
			TheAPI.broadcast(LoaderClass.config.getString("Format.Broadcast-BanIP-IPAddress")
					.replace("%ip%", ip)
					.replace("%reason%", reason),LoaderClass.config.getString("Format.Broadcast-BanIP-IPAddress-Permission"));
		}
	}
	public void setBanIP_Player(String player, String reason) {
		if(reason==null)reason="Uknown";
		String ip =LoaderClass.ban.getString(player+".ip");
		LoaderClass.ban.set(ip+".banip", reason);
		LoaderClass.plugin.a.save();
	
		Player p = Bukkit.getPlayer(player);
		if(p!=null)
			p.kickPlayer(TheAPI.colorize(LoaderClass.config.getString("Format.BanIP-Player")
					.replace("%player%", player)
					.replace("%reason%", reason))); 
		if(!silent) {
			TheAPI.broadcastMessage(LoaderClass.config.getString("Format.Broadcast-BanIP-Player")
					.replace("%player%", player)
					.replace("%reason%", reason));
		}else {
			TheAPI.broadcast(LoaderClass.config.getString("Format.Broadcast-BanIP-IPAddress")
					.replace("%player%", player)
					.replace("%reason%", reason),LoaderClass.config.getString("Format.Broadcast-BanIP-Player-Permission"));
		}
	}
	public List<String> findPlayerByIP(String ip) {
		List<String> ips = new ArrayList<String>();
		
		for(String s:LoaderClass.ban.getConfigurationSection("").getKeys(false)) {
			if(ip.equals(LoaderClass.ban.getString(s+".ip").replace("_", ".")))ips.add(s);
		}
		return ips;
	
	}
	public void setMute(String player, String reason) {
		if(reason==null)reason="Uknown"; 
		LoaderClass.ban.set(player+".mute", reason);
		LoaderClass.plugin.a.save(); 
		if(!silent) {
			TheAPI.broadcastMessage(LoaderClass.config.getString("Format.Broadcast.Mute")
					.replace("%player%", player)
					.replace("%reason%", reason));
		}else {
			TheAPI.broadcast(LoaderClass.config.getString("Format.Broadcast.Mute")
					.replace("%player%", player)
					.replace("%reason%", reason),LoaderClass.config.getString("Format.Broadcast-Mute-Permission"));
		}
	}
	public void setTempMute(String player, String reason, long time) {
		if(reason==null)reason="Uknown";
		LoaderClass.ban.set(player+".tempmute.time", time);
		LoaderClass.ban.set(player+".tempmute.start", System.currentTimeMillis());
		LoaderClass.ban.set(player+".tempmute.reason", reason);
		LoaderClass.plugin.a.save(); 
		if(!silent) {

			TheAPI.broadcastMessage(LoaderClass.config.getString("Format.Broadcast.TempMute")
					.replace("%player%", player)
					.replace("%reason%", reason).replace("%time%", TheAPI.getTimeConventorAPI().setTimeToString(time)));
			}else {
			TheAPI.broadcast(LoaderClass.config.getString("Format.Broadcast.TempMute")
					.replace("%player%", player)
					.replace("%reason%", reason).replace("%time%", TheAPI.getTimeConventorAPI().setTimeToString(time))
					,LoaderClass.config.getString("Format.Broadcast-TempMute-Permission"));
		}
	}

	public boolean hasBan(String player) {
		return LoaderClass.ban.getString(player+".ban") != null;
	}
	public boolean hasTempMute(String player) {
		return LoaderClass.ban.getString(player+".tempmute.start") != null;
	}
	public boolean hasBanIP_Player(String player) {
		return LoaderClass.ban.getString(player+".banip") != null;
	}
	public boolean hasBanIP_IPAddress(String player) {
		return LoaderClass.ban.getString(findPlayerByIP(player)+".banip") != null;
	}
	public boolean hasMute(String player) {
		return LoaderClass.ban.getString(player+".mute") != null;
	}
	public boolean hasTempBan(String player) {
		return LoaderClass.ban.getString(player+".tempban.start") != null;
	}
	public String getBanReason(String player) {
		return LoaderClass.ban.getString(player+".ban");
	}
	public String getTempBanReason(String player) {
		return LoaderClass.ban.getString(player+".tempban.reason");
	}
	public String getBanIP_PlayerReason(String player) {
		return LoaderClass.ban.getString(player+".banip");
	}
	public String getBanIP_IPAddressReason(String ip) {
		return LoaderClass.ban.getString(findPlayerByIP(ip)+".banip");
	}
	public String getMuteReason(String player) {
		return LoaderClass.ban.getString(player+".mute");
	}
	public String getTempMuteReason(String player) {
		return LoaderClass.ban.getString(player+".tempmute.reason");
	}
	public long getTempBanTime(String player) {
		return LoaderClass.ban.getLong(player+".tempban.time");
	}
	public long getTempMuteTime(String player) {
		return LoaderClass.ban.getLong(player+".tempmute.time");
	}

	public long getTempBanStart(String player) {
		return LoaderClass.ban.getLong(player+".tempban.start");
	}
	public long getTempMuteStart(String player) {
		return LoaderClass.ban.getLong(player+".tempmute.start");
	}

	
	public void unMute(String player) {
		LoaderClass.ban.set(player+".mute", null);
		LoaderClass.plugin.a.save();
	}
	public void unTempMute(String player) {
		LoaderClass.ban.set(player+".tempmute", null);
		LoaderClass.plugin.a.save();
	}
	public void unBan(String player) {
		LoaderClass.ban.set(player+".ban", null);
		LoaderClass.plugin.a.save();
	}
	public void unTempBan(String player) {
		LoaderClass.ban.set(player+".tempban", null);
		LoaderClass.plugin.a.save();
	}
	public void unBanIP_Player(String player) {
		LoaderClass.ban.set(player+".banip", null);
		LoaderClass.plugin.a.save();
	}
	public void unBanIP_IPAddress(String ip) {
		for(String i:findPlayerByIP(ip))
		LoaderClass.ban.set(i+".banip", null);
		LoaderClass.plugin.a.save();
	}
	
}
