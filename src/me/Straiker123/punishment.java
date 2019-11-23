package me.Straiker123;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.ServerListPingEvent;

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
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void onClick(InventoryClickEvent e) {
		Player p = (Player)e.getWhoClicked();
		if(LoaderClass.data.getString("guis."+p.getName())==null) {
			return;
		}
		String title = e.getView().getTitle();
		String a = findGUI(title,p);
		if(a!=null) {
		if(e.getCurrentItem() != null) {
			if(e.getClickedInventory().getType()==InventoryType.PLAYER 
					&& LoaderClass.data.getString("guis."+p.getName()+"."+a+".CAN_PUT_ITEM")!=null) {
			e.setCancelled(LoaderClass.data.getBoolean("guis."+p.getName()+"."+a+".CAN_PUT_ITEM"));
			}
			if(LoaderClass.data.getItemStack("guis."+p.getName()+"."+a+"."+e.getSlot()+".item").equals(e.getCurrentItem())) {
				e.setCancelled(LoaderClass.data.getBoolean("guis."+p.getName()+"."+a+"."+e.getSlot()+".CANT_BE_TAKEN"));
				
				if(LoaderClass.data.getString("guis."+p.getName()+"."+a+"."+e.getSlot()+".SENDMESSAGES")!=null)
					for(String s: LoaderClass.data.getStringList("guis."+p.getName()+"."+a+"."+e.getSlot()+".SENDMESSAGES"))
						TheAPI.broadcastMessage(s);
				
					if(e.getClick().isLeftClick()&& !e.getClick().isShiftClick())
						if(LoaderClass.data.getString("guis."+p.getName()+"."+a+"."+e.getSlot()+".SENDMESSAGES_LEFT_CLICK")!=null)
					for(String s: LoaderClass.data.getStringList("guis."+p.getName()+"."+a+"."+e.getSlot()+".SENDMESSAGES_LEFT_CLICK"))
						TheAPI.broadcastMessage(s);
					
					if(e.getClick().isRightClick()&& !e.getClick().isShiftClick())
						if(LoaderClass.data.getString("guis."+p.getName()+"."+a+"."+e.getSlot()+".SENDMESSAGES_RIGHT_CLICK")!=null)
					for(String s: LoaderClass.data.getStringList("guis."+p.getName()+"."+a+"."+e.getSlot()+".SENDMESSAGES_RIGHT_CLICK"))
						TheAPI.broadcastMessage(s);
					
					if(e.getClick().isCreativeAction())
						if(LoaderClass.data.getString("guis."+p.getName()+"."+a+"."+e.getSlot()+".SENDMESSAGES_MIDDLE_CLICK")!=null)
					for(String s: LoaderClass.data.getStringList("guis."+p.getName()+"."+a+"."+e.getSlot()+".SENDMESSAGES_MIDDLE_CLICK"))
						TheAPI.broadcastMessage(s);
					
					if(e.getClick().isLeftClick() && e.getClick().isShiftClick())
						if(LoaderClass.data.getString("guis."+p.getName()+"."+a+"."+e.getSlot()+".SENDMESSAGES_SHIFT_WITH_LEFT_CLICK")!=null)
					for(String s: LoaderClass.data.getStringList("guis."+p.getName()+"."+a+"."+e.getSlot()+".SENDMESSAGES_SHIFT_WITH_LEFT_CLICK"))
						TheAPI.broadcastMessage(s);
					
					if(e.getClick().isRightClick() && e.getClick().isShiftClick())
						if(LoaderClass.data.getString("guis."+p.getName()+"."+a+"."+e.getSlot()+".SENDMESSAGES_SHIFT_WITH_RIGHT_CLICK")!=null)
					for(String s: LoaderClass.data.getStringList("guis."+p.getName()+"."+a+"."+e.getSlot()+".SENDMESSAGES_SHIFT_WITH_RIGHT_CLICK"))
						TheAPI.broadcastMessage(s);
				
				if(LoaderClass.data.getString("guis."+p.getName()+"."+a+"."+e.getSlot()+".SENDCOMMANDS")!=null)
					for(String s: LoaderClass.data.getStringList("guis."+p.getName()+"."+a+"."+e.getSlot()+".SENDCOMMANDS"))
						TheAPI.sudoConsole(SudoType.COMMAND, s);
				
					if(e.getClick().isLeftClick()&& !e.getClick().isShiftClick())
						if(LoaderClass.data.getString("guis."+p.getName()+"."+a+"."+e.getSlot()+".SENDCOMMANDS_LEFT_CLICK")!=null)
					for(String s: LoaderClass.data.getStringList("guis."+p.getName()+"."+a+"."+e.getSlot()+".SENDCOMMANDS_LEFT_CLICK"))
						TheAPI.sudoConsole(SudoType.COMMAND, s);
					
					if(e.getClick().isRightClick()&& !e.getClick().isShiftClick())
						if(LoaderClass.data.getString("guis."+p.getName()+"."+a+"."+e.getSlot()+".SENDCOMMANDS_RIGHT_CLICK")!=null)
					for(String s: LoaderClass.data.getStringList("guis."+p.getName()+"."+a+"."+e.getSlot()+".SENDCOMMANDS_RIGHT_CLICK"))
						TheAPI.sudoConsole(SudoType.COMMAND, s);
					
					if(e.getClick().isLeftClick() && e.getClick().isShiftClick())
						if(LoaderClass.data.getString("guis."+p.getName()+"."+a+"."+e.getSlot()+".SENDCOMMANDS_SHIFT_WITH_LEFT_CLICK")!=null)
					for(String s: LoaderClass.data.getStringList("guis."+p.getName()+"."+a+"."+e.getSlot()+".SENDCOMMANDS_SHIFT_WITH_LEFT_CLICK"))
						TheAPI.sudoConsole(SudoType.COMMAND, s);
					
					if(e.getClick().isRightClick() && e.getClick().isShiftClick())
						if(LoaderClass.data.getString("guis."+p.getName()+"."+a+"."+e.getSlot()+".SENDCOMMANDS_SHIFT_WITH_RIGHT_CLICK")!=null)
					for(String s: LoaderClass.data.getStringList("guis."+p.getName()+"."+a+"."+e.getSlot()+".SENDCOMMANDS_SHIFT_WITH_RIGHT_CLICK"))
						TheAPI.sudoConsole(SudoType.COMMAND, s);
					
					if(e.getClick().isCreativeAction())
						if(LoaderClass.data.getString("guis."+p.getName()+"."+a+"."+e.getSlot()+".SENDCOMMANDS_MIDDLE_CLICK")!=null)
					for(String s: LoaderClass.data.getStringList("guis."+p.getName()+"."+a+"."+e.getSlot()+".SENDCOMMANDS_MIDDLE_CLICK"))
						TheAPI.sudoConsole(SudoType.COMMAND, s);
			    if(LoaderClass.actions.isEmpty()==false) {
				if(LoaderClass.actions.get(p.getName()+"."+a+"."+e.getSlot()+".RUNNABLE")!=null)
					LoaderClass.actions.get(p.getName()+"."+a+"."+e.getSlot()+".RUNNABLE").run();

				if(e.getClick().isLeftClick()&& !e.getClick().isShiftClick())
				if(LoaderClass.actions.get(p.getName()+"."+a+"."+e.getSlot()+".RUNNABLE_LEFT_CLICK")!=null)
					LoaderClass.actions.get(p.getName()+"."+a+"."+e.getSlot()+".RUNNABLE_LEFT_CLICK").run();

				if(e.getClick().isRightClick()&& !e.getClick().isShiftClick())
				if(LoaderClass.actions.get(p.getName()+"."+a+"."+e.getSlot()+".RUNNABLE_RIGHT_CLICK")!=null)
					LoaderClass.actions.get(p.getName()+"."+a+"."+e.getSlot()+".RUNNABLE_RIGHT_CLICK").run();

				if(e.getClick().isCreativeAction())
				if(LoaderClass.actions.get(p.getName()+"."+a+"."+e.getSlot()+".RUNNABLE_MIDDLE_CLICK")!=null)
					LoaderClass.actions.get(p.getName()+"."+a+"."+e.getSlot()+".RUNNABLE_MIDDLE_CLICK").run();

				if(e.getClick().isLeftClick()&& e.getClick().isShiftClick())
				if(LoaderClass.actions.get(p.getName()+"."+a+"."+e.getSlot()+".RUNNABLE_SHIFT_WITH_LEFT_CLICK")!=null)
					LoaderClass.actions.get(p.getName()+"."+a+"."+e.getSlot()+".RUNNABLE_SHIFT_WITH_LEFT_CLICK").run();

				if(e.getClick().isRightClick()&& e.getClick().isShiftClick())
				if(LoaderClass.actions.get(p.getName()+"."+a+"."+e.getSlot()+".RUNNABLE_SHIFT_WITH_RIGHT_CLICK")!=null)
					LoaderClass.actions.get(p.getName()+"."+a+"."+e.getSlot()+".RUNNABLE_SHIFT_WITH_RIGHT_CLICK").run();
		}}}}
	}
}
