package me.Straiker123;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.ServerListPingEvent;

import me.Straiker123.GUICreatorAPI.ClickTypes;
import me.Straiker123.TheAPI.SudoType;

@SuppressWarnings("deprecation")
public class punishment implements Listener {
	@EventHandler(priority = EventPriority.LOWEST)
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

	@EventHandler(priority = EventPriority.LOWEST)
	public void onLeave(PlayerQuitEvent e) {
		if(Bukkit.getOnlinePlayers().size()-1 == 0) {
			LoaderClass.data.set("guis", null);
			LoaderClass.plugin.a.save();
			LoaderClass.actions.clear();
		}
	}
	@EventHandler(priority = EventPriority.LOWEST)
	public void onMotd(ServerListPingEvent e) {
		if(LoaderClass.plugin.motd!=null)
		e.setMotd(LoaderClass.plugin.motd);
		if(LoaderClass.plugin.max>0)
		e.setMaxPlayers(LoaderClass.plugin.max);
	}

	@EventHandler(priority = EventPriority.LOWEST)
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
	private String findGUI(String title, Player p) {
		String w=null;
		for(String a:LoaderClass.data.getConfigurationSection("guis."+p.getName()).getKeys(false)) {
		if(title.equals(TheAPI.colorize(LoaderClass.data.getString("guis."+p.getName()+"."+a+".title"))))w= a;
		}
	return w;
	}
	
	private ClickTypes getType(ClickType e, String s) {
		ClickTypes w = null;
		if(e.isLeftClick() && !e.isShiftClick())if(ClickTypes.LEFT == ClickTypes.valueOf(s))w=ClickTypes.LEFT;
		else
		if(e.isLeftClick() && e.isShiftClick())if(ClickTypes.LEFT_WITH_SHIFT == ClickTypes.valueOf(s))w= ClickTypes.LEFT_WITH_SHIFT;
		else
		if(e.isRightClick() && !e.isShiftClick())if(ClickTypes.RIGHT == ClickTypes.valueOf(s))w=  ClickTypes.RIGHT;
		else
		if(e.isRightClick() && e.isShiftClick())if(ClickTypes.RIGHT_WITH_SHIFT == ClickTypes.valueOf(s))w=  ClickTypes.RIGHT_WITH_SHIFT;
		else
		if(e==ClickType.MIDDLE && !e.isShiftClick())if(ClickTypes.MIDDLE == ClickTypes.valueOf(s))w=  ClickTypes.MIDDLE;
		else
		if(e==ClickType.MIDDLE && e.isShiftClick())if(ClickTypes.MIDDLE_WITH_SHIFT == ClickTypes.valueOf(s))w=  ClickTypes.MIDDLE_WITH_SHIFT;
		if(s.equalsIgnoreCase("ALL"))w=ClickTypes.ALL;
		return w;
	}
	
	private String getSearch(String p, String g, String where, int slot) {
		List<String> list = new ArrayList<String>();
		for(String w:LoaderClass.data.getConfigurationSection
				("guis."+p+"."+g+"."+slot+"."+where).getKeys(false)) {
			list.add(w);
		}
		if(list.get(0)!=null)
		return list.get(0);
		else
			return "ALL";
	}
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void onClick(InventoryClickEvent e) {
		Player p = (Player)e.getWhoClicked();
		if(LoaderClass.data.getString("guis."+p.getName())==null) {
			return;
		}
		String title = e.getView().getTitle();
		String a = findGUI(title,p);
		if(a!=null && e.getCurrentItem() != null) {
			if(LoaderClass.data.getItemStack("guis."+p.getName()+"."+a+"."+e.getSlot()+".item").equals(e.getCurrentItem())) {
				e.setCancelled(LoaderClass.data.getBoolean("guis."+p.getName()+"."+a+"."+e.getSlot()+".CANT_BE_TAKEN"));
				if(LoaderClass.data.getString("guis."+p.getName()+"."+a+"."+e.getSlot()+".SENDMESSAGES")!=null) {
					String w = getSearch(p.getName(),a,"SENDMESSAGES",e.getSlot());
					if(w!=null)
					for(String s: LoaderClass.data.getStringList("guis."+p.getName()+"."+a+"."+e.getSlot()+".SENDMESSAGES."+getType(e.getClick(),w))) {
						TheAPI.broadcastMessage(s);
					}}
				}}
				
				if(LoaderClass.data.getString("guis."+p.getName()+"."+a+"."+e.getSlot()+".SENDCOMMANDS")!=null) {
					String w = getSearch(p.getName(),a,"SENDCOMMANDS",e.getSlot());
					if(w!=null)
						for(String s: LoaderClass.data.getStringList("guis."+p.getName()+"."+a+"."+e.getSlot()+".SENDCOMMANDS."+getType(e.getClick(),w))) {
							TheAPI.sudoConsole(SudoType.COMMAND, s);
						}
					}
		}
}
