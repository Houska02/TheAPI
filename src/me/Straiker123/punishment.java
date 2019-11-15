package me.Straiker123;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;

public class punishment implements Listener {
	@EventHandler
	public void onLogin(PlayerLoginEvent e) {
		String s = e.getPlayer().getName();
		LoaderClass.ban.set(s+".ip", e.getRealAddress().toString().replace(".", "_"));
		LoaderClass.plugin.a.save();
		if(TheAPI.getPunishmentAPI().hasBan(s)) {
			e.disallow(Result.KICK_BANNED, TheAPI.colorize(LoaderClass.config.getString("Format.Ban")
					.replace("%player%", s)
					.replace("%reason%", TheAPI.getPunishmentAPI().getBanReason(s))));
		}
		if(TheAPI.getPunishmentAPI().hasTempBan(s)) {
			if((TheAPI.getPunishmentAPI().getTempBanStart(s) - System.currentTimeMillis() + TheAPI.getPunishmentAPI().getTempBanTime(s)) < 0)
				
				e.disallow(Result.KICK_BANNED, TheAPI.colorize(LoaderClass.config.getString("Format.TempBan")
						.replace("%player%", s)
						.replace("%reason%", TheAPI.getPunishmentAPI().getTempBanReason(s))));
		}
		if(TheAPI.getPunishmentAPI().hasBanIP_Player(s)) {
			e.disallow(Result.KICK_BANNED, TheAPI.colorize(LoaderClass.config.getString("Format.BanIP-Player")
					.replace("%player%", s)
					.replace("%reason%", TheAPI.getPunishmentAPI().getBanReason(s))));
		}
	}
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		String s = e.getPlayer().getName();
		if(TheAPI.getPunishmentAPI().hasTempBan(s)) {
			int time = (int)(TheAPI.getPunishmentAPI().getTempBanStart(s) - System.currentTimeMillis() + TheAPI.getPunishmentAPI().getTempBanTime(s));
			if(time < 0)
				e.setCancelled(true);
				e.getPlayer().sendMessage(TheAPI.colorize(LoaderClass.config.getString("Format.TempMute")
								.replace("%player%", s)
								.replace("%reason%", TheAPI.getPunishmentAPI().getTempMuteReason(s))
						.replace("%time%", TheAPI.getTimeConventorAPI().setTimeToString(time))));
		}
		if(TheAPI.getPunishmentAPI().hasMute(s)) {
			e.setCancelled(true);
			e.getPlayer().sendMessage(TheAPI.colorize(LoaderClass.config.getString("Format.Mute")
					.replace("%player%", s)
					.replace("%reason%", TheAPI.getPunishmentAPI().getTempMuteReason(s))));
		}
	}
}
