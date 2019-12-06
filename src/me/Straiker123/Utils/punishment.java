package me.Straiker123.Utils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.inventory.ItemStack;

import me.Straiker123.ItemCreatorAPI;
import me.Straiker123.LoaderClass;
import me.Straiker123.PunishmentAPI;
import me.Straiker123.TheAPI;
import me.Straiker123.TheAPI.SudoType;
import me.Straiker123.WorldBorderAPI.WarningMessageType;

@SuppressWarnings("deprecation")
public class punishment implements Listener {
	
	private ItemStack createWrittenBook(ItemStack a) {
		Material ms = Material.matchMaterial("WRITABLE_BOOK");
		if(ms==null)ms=Material.matchMaterial("BOOK_AND_QUILL");
		ItemCreatorAPI s = TheAPI.getItemCreatorAPI(ms);
		 if(a.getItemMeta().hasDisplayName())
		 s.setDisplayName(a.getItemMeta().getDisplayName());
		 if(a.getItemMeta().hasLore())s.setLore(a.getItemMeta().getLore());
		 if(!TheAPI.getServerVersion().equals("v1_8_R3")
				 &&!TheAPI.getServerVersion().equals("v1_9_R1")
				 &&!TheAPI.getServerVersion().equals("v1_9_R2")
				 &&!TheAPI.getServerVersion().equals("v1_9_R3")
				 &&!TheAPI.getServerVersion().equals("v1_10_R1")
				 &&!TheAPI.getServerVersion().equals("v1_10_R2")
				 &&!TheAPI.getServerVersion().equals("v1_11_R1")
				 &&!TheAPI.getServerVersion().equals("v1_12_R1")
				 &&!TheAPI.getServerVersion().equals("v1_13_R1")
				 &&!TheAPI.getServerVersion().equals("v1_13_R2"))
		if(a.getItemMeta().hasCustomModelData()) s.setCustomModelData(a.getItemMeta().getCustomModelData());
		 if(!TheAPI.getServerVersion().equals("v1_8_R3")
				 &&!TheAPI.getServerVersion().equals("v1_9_R1")  
				 &&!TheAPI.getServerVersion().equals("v1_9_R2") 
				 &&!TheAPI.getServerVersion().equals("v1_9_R3")
				 &&!TheAPI.getServerVersion().equals("v1_10_R1")
				 &&!TheAPI.getServerVersion().equals("v1_10_R2"))
		 s.setUnbreakable(a.getItemMeta().isUnbreakable());
		 return s.create();
	}
	
	private ItemStack createHead(ItemStack a) {
		ItemCreatorAPI s = TheAPI.getItemCreatorAPI(Material.matchMaterial("PLAYER_HEAD"));
		 if(a.getItemMeta().hasDisplayName())
		 s.setDisplayName(a.getItemMeta().getDisplayName());
		 if(a.getItemMeta().hasLore())s.setLore(a.getItemMeta().getLore());
		 if(!TheAPI.getServerVersion().equals("v1_8_R3")
				 &&!TheAPI.getServerVersion().equals("v1_9_R1")
				 &&!TheAPI.getServerVersion().equals("v1_9_R2")
				 &&!TheAPI.getServerVersion().equals("v1_9_R3")
				 &&!TheAPI.getServerVersion().equals("v1_10_R1")
				 &&!TheAPI.getServerVersion().equals("v1_10_R2")
				 &&!TheAPI.getServerVersion().equals("v1_11_R1")
				 &&!TheAPI.getServerVersion().equals("v1_12_R1")
				 &&!TheAPI.getServerVersion().equals("v1_13_R1")
				 &&!TheAPI.getServerVersion().equals("v1_13_R2"))
		if(a.getItemMeta().hasCustomModelData()) s.setCustomModelData(a.getItemMeta().getCustomModelData());
		 if(!TheAPI.getServerVersion().equals("v1_8_R3")
				 &&!TheAPI.getServerVersion().equals("v1_9_R1")  
				 &&!TheAPI.getServerVersion().equals("v1_9_R2") 
				 &&!TheAPI.getServerVersion().equals("v1_9_R3")
				 &&!TheAPI.getServerVersion().equals("v1_10_R1")
				 &&!TheAPI.getServerVersion().equals("v1_10_R2"))
		 s.setUnbreakable(a.getItemMeta().isUnbreakable());
		 return s.create();
	}
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void onMove(PlayerMoveEvent e) {
		if(TheAPI.getWorldBorder(e.getTo().getWorld()).isOutside(e.getTo())) {
			if(LoaderClass.data.getConfig().getString("WorldBorder."+e.getTo().getWorld().getName()+".Type")!=null) {
			WarningMessageType t = WarningMessageType.valueOf(
					LoaderClass.data.getConfig().getString("WorldBorder."+e.getTo().getWorld().getName()+".Type"));
			String msg = LoaderClass.data.getConfig().getString("WorldBorder."+e.getTo().getWorld().getName()+".Message");
			if(msg==null)return;
			switch(t) {
			case ACTIONBAR:
				TheAPI.sendActionBar(e.getPlayer(), msg);
				break;
			case BOSSBAR:
				TheAPI.sendBossBar(e.getPlayer(), msg, 1, 5);
				break;
			case CHAT:
				TheAPI.getPlayerAPI(e.getPlayer()).msg(msg);
				break;
			case NONE:
				break;
			case SUBTITLE:
				TheAPI.getPlayerAPI(e.getPlayer()).sendTitle("", msg);
				break;
			case TITLE:
				TheAPI.getPlayerAPI(e.getPlayer()).sendTitle(msg, "");
				break;
			}
			}
		}
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void onChunkLoad(ChunkLoadEvent e) {
		if(TheAPI.getWorldBorder(e.getWorld()).isOutside(e.getChunk().getBlock(e.getChunk().getX(), 100, e.getChunk().getZ()).getLocation())) {
			e.getChunk().unload(true);
		}
	}
	
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void onLogin(PlayerLoginEvent e) {
		String s = e.getPlayer().getName();
		LoaderClass.data.getConfig().set("data."+s+".ip", e.getRealAddress().toString().replace(".", "_"));
		LoaderClass.data.save();
		PunishmentAPI a = TheAPI.getPunishmentAPI();
		if(a.hasBan(s)) {
			e.disallow(Result.KICK_BANNED, TheAPI.colorize(LoaderClass.config.getConfig().getString("Format.Ban")
					.replace("%player%", s)
					.replace("%reason%", a.getBanReason(s))));
			return;
		}
		if(a.hasTempBan(s)) {
				e.disallow(Result.KICK_BANNED, TheAPI.colorize(LoaderClass.config.getConfig().getString("Format.TempBan")
						.replace("%player%", s)
						.replace("%time%", TheAPI.getTimeConventorAPI().setTimeToString(a.getTempBan_ExpireTime(s)))
						.replace("%reason%", a.getTempBanReason(s))));
				return;
		}
		if(a.hasBanIP(s)) {
			e.disallow(Result.KICK_BANNED, TheAPI.colorize(LoaderClass.config.getConfig().getString("Format.BanIP")
					.replace("%player%", s)
					.replace("%reason%",a.getBanReason(s))));
			return;
		}
		if(a.hasTempBanIP(s)) {
			e.disallow(Result.KICK_BANNED, TheAPI.colorize(LoaderClass.config.getConfig().getString("Format.TempBanIP")
					.replace("%player%", s)
					.replace("%time%", TheAPI.getTimeConventorAPI().setTimeToString(a.getTempBanIP_ExpireTime(s)))
					.replace("%reason%", a.getTempBanIPReason(s))));
			return;
		}
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void onLeave(PlayerQuitEvent e) {
		if(Bukkit.getOnlinePlayers().size()-1 <= 0) {
			LoaderClass.data.getConfig().set("guis", null);
			LoaderClass.data.save();
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
				e.getPlayer().sendMessage(TheAPI.colorize(LoaderClass.config.getConfig().getString("Format.TempMute")
								.replace("%player%", s)
								.replace("%reason%", TheAPI.getPunishmentAPI().getTempMuteReason(s))
						.replace("%time%", TheAPI.getTimeConventorAPI().setTimeToString(time))));
		}
		if(TheAPI.getPunishmentAPI().hasMute(s)) {
			e.setCancelled(true);
			e.getPlayer().sendMessage(TheAPI.colorize(LoaderClass.config.getConfig().getString("Format.Mute")
					.replace("%player%", s)
					.replace("%reason%", TheAPI.getPunishmentAPI().getTempMuteReason(s))));
		}
	}
	private String findGUI(String title, Player p) {
		String w=null;
		for(String a:LoaderClass.data.getConfig().getConfigurationSection("guis."+p.getName()).getKeys(false)) {
		if(title.equals(TheAPI.colorize(LoaderClass.data.getConfig().getString("guis."+p.getName()+"."+a+".title"))))w= a;
		}
	return w;
	}
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void onClose(InventoryCloseEvent e) {
		Player p = (Player)e.getPlayer();
		if(LoaderClass.data.getConfig().getString("guis."+p.getName())==null) {
			return;
		}
		String title = e.getView().getTitle();
		String a = findGUI(title,p);
		if(a!=null) {
			if(LoaderClass.data.getConfig().getString("guis."+p.getName()+"."+a+".SENDMESSAGES_ON_INV_CLOSE")!=null)
				for(String s: LoaderClass.data.getConfig().getStringList("guis."+p.getName()+"."+a+".SENDMESSAGES_ON_INV_CLOSE"))
					TheAPI.broadcastMessage(s);
			if(LoaderClass.data.getConfig().getString("guis."+p.getName()+"."+a+".SENDCOMMANDS_ON_INV_CLOSE")!=null)
				for(String s: LoaderClass.data.getConfig().getStringList("guis."+p.getName()+"."+a+".SENDCOMMANDS_ON_INV_CLOSE"))
					TheAPI.sudoConsole(SudoType.COMMAND, s);
		    if(LoaderClass.actions.isEmpty()==false) {
			if(LoaderClass.actions.get(p.getName()+"."+a+".RUNNABLE_ON_INV_CLOSE")!=null)
				LoaderClass.actions.get(p.getName()+"."+a+".RUNNABLE_ON_INV_CLOSE").run();
		    }
		}
	}
	private boolean isSame(Player p, String a, int slot, ItemStack i) {
		if(LoaderClass.data.getConfig().getItemStack("guis."+p.getName()+"."+a+"."+slot+".item")!=null)
		return LoaderClass.data.getConfig().getItemStack("guis."+p.getName()+"."+a+"."+slot+".item").equals(i);
		return false;
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void onClick(InventoryClickEvent e) {
		Player p = (Player)e.getWhoClicked();
		if(LoaderClass.data.getConfig().getString("guis."+p.getName())==null) {
			return;
		}
		String title = e.getView().getTitle();
		String a = findGUI(title,p);
		if(a!=null) {
			ItemStack i = e.getCurrentItem();
		if(i != null) {
			if(e.getClickedInventory().getType()==InventoryType.PLAYER 
					&& LoaderClass.data.getConfig().getString("guis."+p.getName()+"."+a+".CANT_PUT_ITEM")!=null) {
			e.setCancelled(LoaderClass.data.getConfig().getBoolean("guis."+p.getName()+"."+a+".CANT_PUT_ITEM"));
			return;
			}
			if(i.getType().name().equals("WRITTEN_BOOK")||i.getType().name().equals("BOOK_AND_QUILL"))i=createWrittenBook(i);

			if(i.getType().name().equals("LEGACY_SKULL_ITEM")||
					i.getType().name().equals("SKULL_ITEM")
					||i.getType().name().equals("PLAYER_HEAD"))
				i=createHead(i);
			
			if(isSame(p,a,e.getSlot(),i)) {
				e.setCancelled(LoaderClass.data.getConfig().getBoolean("guis."+p.getName()+"."+a+"."+e.getSlot()+".CANT_BE_TAKEN"));
				
				if(LoaderClass.data.getConfig().getString("guis."+p.getName()+"."+a+"."+e.getSlot()+".SENDMESSAGES")!=null)
					for(String s: LoaderClass.data.getConfig().getStringList("guis."+p.getName()+"."+a+"."+e.getSlot()+".SENDMESSAGES"))
						TheAPI.broadcastMessage(s);
				
					if(e.getClick().isLeftClick()&& !e.getClick().isShiftClick())
						if(LoaderClass.data.getConfig().getString("guis."+p.getName()+"."+a+"."+e.getSlot()+".SENDMESSAGES_LEFT_CLICK")!=null)
					for(String s: LoaderClass.data.getConfig().getStringList("guis."+p.getName()+"."+a+"."+e.getSlot()+".SENDMESSAGES_LEFT_CLICK"))
						TheAPI.broadcastMessage(s);
					
					if(e.getClick().isRightClick()&& !e.getClick().isShiftClick())
						if(LoaderClass.data.getConfig().getString("guis."+p.getName()+"."+a+"."+e.getSlot()+".SENDMESSAGES_RIGHT_CLICK")!=null)
					for(String s: LoaderClass.data.getConfig().getStringList("guis."+p.getName()+"."+a+"."+e.getSlot()+".SENDMESSAGES_RIGHT_CLICK"))
						TheAPI.broadcastMessage(s);
					
					if(e.getClick().isCreativeAction())
						if(LoaderClass.data.getConfig().getString("guis."+p.getName()+"."+a+"."+e.getSlot()+".SENDMESSAGES_MIDDLE_CLICK")!=null)
					for(String s: LoaderClass.data.getConfig().getStringList("guis."+p.getName()+"."+a+"."+e.getSlot()+".SENDMESSAGES_MIDDLE_CLICK"))
						TheAPI.broadcastMessage(s);
					
					if(e.getClick().isLeftClick() && e.getClick().isShiftClick())
						if(LoaderClass.data.getConfig().getString("guis."+p.getName()+"."+a+"."+e.getSlot()+".SENDMESSAGES_SHIFT_WITH_LEFT_CLICK")!=null)
					for(String s: LoaderClass.data.getConfig().getStringList("guis."+p.getName()+"."+a+"."+e.getSlot()+".SENDMESSAGES_SHIFT_WITH_LEFT_CLICK"))
						TheAPI.broadcastMessage(s);
					
					if(e.getClick().isRightClick() && e.getClick().isShiftClick())
						if(LoaderClass.data.getConfig().getString("guis."+p.getName()+"."+a+"."+e.getSlot()+".SENDMESSAGES_SHIFT_WITH_RIGHT_CLICK")!=null)
					for(String s: LoaderClass.data.getConfig().getStringList("guis."+p.getName()+"."+a+"."+e.getSlot()+".SENDMESSAGES_SHIFT_WITH_RIGHT_CLICK"))
						TheAPI.broadcastMessage(s);
				
				if(LoaderClass.data.getConfig().getString("guis."+p.getName()+"."+a+"."+e.getSlot()+".SENDCOMMANDS")!=null)
					for(String s: LoaderClass.data.getConfig().getStringList("guis."+p.getName()+"."+a+"."+e.getSlot()+".SENDCOMMANDS"))
						TheAPI.sudoConsole(SudoType.COMMAND, s);
				
					if(e.getClick().isLeftClick()&& !e.getClick().isShiftClick())
						if(LoaderClass.data.getConfig().getString("guis."+p.getName()+"."+a+"."+e.getSlot()+".SENDCOMMANDS_LEFT_CLICK")!=null)
					for(String s: LoaderClass.data.getConfig().getStringList("guis."+p.getName()+"."+a+"."+e.getSlot()+".SENDCOMMANDS_LEFT_CLICK"))
						TheAPI.sudoConsole(SudoType.COMMAND, s);
					
					if(e.getClick().isRightClick()&& !e.getClick().isShiftClick())
						if(LoaderClass.data.getConfig().getString("guis."+p.getName()+"."+a+"."+e.getSlot()+".SENDCOMMANDS_RIGHT_CLICK")!=null)
					for(String s: LoaderClass.data.getConfig().getStringList("guis."+p.getName()+"."+a+"."+e.getSlot()+".SENDCOMMANDS_RIGHT_CLICK"))
						TheAPI.sudoConsole(SudoType.COMMAND, s);
					
					if(e.getClick().isLeftClick() && e.getClick().isShiftClick())
						if(LoaderClass.data.getConfig().getString("guis."+p.getName()+"."+a+"."+e.getSlot()+".SENDCOMMANDS_SHIFT_WITH_LEFT_CLICK")!=null)
					for(String s: LoaderClass.data.getConfig().getStringList("guis."+p.getName()+"."+a+"."+e.getSlot()+".SENDCOMMANDS_SHIFT_WITH_LEFT_CLICK"))
						TheAPI.sudoConsole(SudoType.COMMAND, s);
					
					if(e.getClick().isRightClick() && e.getClick().isShiftClick())
						if(LoaderClass.data.getConfig().getString("guis."+p.getName()+"."+a+"."+e.getSlot()+".SENDCOMMANDS_SHIFT_WITH_RIGHT_CLICK")!=null)
					for(String s: LoaderClass.data.getConfig().getStringList("guis."+p.getName()+"."+a+"."+e.getSlot()+".SENDCOMMANDS_SHIFT_WITH_RIGHT_CLICK"))
						TheAPI.sudoConsole(SudoType.COMMAND, s);
					
					if(e.getClick().isCreativeAction())
						if(LoaderClass.data.getConfig().getString("guis."+p.getName()+"."+a+"."+e.getSlot()+".SENDCOMMANDS_MIDDLE_CLICK")!=null)
					for(String s: LoaderClass.data.getConfig().getStringList("guis."+p.getName()+"."+a+"."+e.getSlot()+".SENDCOMMANDS_MIDDLE_CLICK"))
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
		}}}}}}
