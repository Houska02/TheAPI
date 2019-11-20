package me.Straiker123;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;
import org.bukkit.event.server.ServerListPingEvent;

@SuppressWarnings("deprecation")
public class punishment implements Listener {
	@EventHandler
	public void onLogin(PlayerLoginEvent e) {
		String s = e.getPlayer().getName();
		LoaderClass.data.set("data."+s+".ip", e.getRealAddress().toString().replace(".", "_"));
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

	@EventHandler
	public void onMotd(ServerListPingEvent e) {
		if(LoaderClass.plugin.motd!=null)
		e.setMotd(LoaderClass.plugin.motd);
		if(LoaderClass.plugin.max!=-1)
		e.setMaxPlayers(LoaderClass.plugin.max);
	}

	@EventHandler
	public void onChat(PlayerChatEvent e) {
		if(LoaderClass.chatformat.get(e.getPlayer()) != null)
		e.setFormat(LoaderClass.chatformat.get(e.getPlayer()).replace("%", "%%")
				.replace("%%player%%", e.getPlayer().getName())
				.replace("%%playername%%", e.getPlayer().getDisplayName())
				.replace("%%playercustom%%", e.getPlayer().getCustomName())
				.replace("%%hp%%", e.getPlayer().getHealth()+"")
				.replace("%%food%%", e.getPlayer().getFoodLevel()+"")
				.replace("%%world%%", e.getPlayer().getWorld().getName()+"")
				.replace("%%x%%", e.getPlayer().getLocation().getBlockX()+"")
				.replace("%%y%%", e.getPlayer().getLocation().getBlockY()+"")
				.replace("%%z%%", e.getPlayer().getLocation().getBlockZ()+"").replace("%message%", e.getMessage().replace("%", "%%")));
	}
}
