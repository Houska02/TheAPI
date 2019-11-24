package me.Straiker123;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scoreboard.Scoreboard;

import net.glowstone.entity.GlowPlayer;

public class TheAPI {

	public static String colorize(String string) {
		if(string == null)return null;
		return ChatColor.translateAlternateColorCodes('&', string);
	}
	
	public static ConfigAPI getConfig(String localization,  String name) {
		return new ConfigAPI(name,localization);
	}
	public static void sendBossBar(Player p, String text, double progress, int timeToExpire) {
	try {
		if(timeToExpire<0)timeToExpire=0;
	BossBar a = Bukkit.createBossBar(TheAPI.colorize(text), BarColor.GREEN, BarStyle.SEGMENTED_20);
	if(progress<0)progress=0;
	if(progress>1)progress=1;
	a.setProgress(progress);
	a.addPlayer(p);
	Bukkit.getScheduler().runTaskLater(LoaderClass.plugin, new Runnable() {

		@Override
		public void run() {
			a.removeAll();
		}
		
	},(long) (20*timeToExpire));
	}catch(Exception e) {
		Error.err("sending bossbar to "+p.getName(), "Text is null");
	}}
	
	   public static void sendActionBar(Player p, String text) {
		   if(p == null) {
		    	 Error.err("sending ActionBar", "Player is null");
			   return;
		   }
		   if(TheAPI.getServerVersion().equals("glowstone")) {
				try {
					   ((GlowPlayer) p).sendActionBar(TheAPI.colorize(text));
						return;
					}catch (Exception e) {
				    	 Error.err("sending ActionBar to "+p.getName(), "Text is null");}
					}
		     Class<?> PACKET_PLAYER_CHAT_CLASS = null, ICHATCOMP = null, CHATMESSAGE = null,
	       CHAT_MESSAGE_TYPE_CLASS = null;
	     Constructor<?> PACKET_PLAYER_CHAT_CONSTRUCTOR = null, CHATMESSAGE_CONSTRUCTOR = null;
	     Object CHAT_MESSAGE_TYPE_ENUM_OBJECT = null;
		   boolean useByte = false;
		     try {
		       PACKET_PLAYER_CHAT_CLASS = Packets.getNMSClass("PacketPlayOutChat");
		       ICHATCOMP = Packets.getNMSClass("IChatBaseComponent");
		       try {
		         CHAT_MESSAGE_TYPE_CLASS = Packets.getNMSClass("ChatMessageType");
		         CHAT_MESSAGE_TYPE_ENUM_OBJECT = CHAT_MESSAGE_TYPE_CLASS.getEnumConstants()[2];
		 
		         PACKET_PLAYER_CHAT_CONSTRUCTOR = PACKET_PLAYER_CHAT_CLASS.getConstructor(ICHATCOMP,
		             CHAT_MESSAGE_TYPE_CLASS);
		       } catch (NoSuchMethodException e) {
		         PACKET_PLAYER_CHAT_CONSTRUCTOR = PACKET_PLAYER_CHAT_CLASS.getConstructor(ICHATCOMP, byte.class);
		         useByte = true;
		       }
		       CHATMESSAGE = Packets.getNMSClass("ChatMessage");
		       CHATMESSAGE_CONSTRUCTOR = CHATMESSAGE.getConstructor(String.class, Object[].class);
		     } catch (Exception e) {
		     }
		   
	     try {
	       Object icb = CHATMESSAGE_CONSTRUCTOR.newInstance(TheAPI.colorize(text), new Object[0]);
	       Object packet;
	       if (useByte)
	         packet = PACKET_PLAYER_CHAT_CONSTRUCTOR.newInstance(icb, (byte) 2);
	       else
	         packet = PACKET_PLAYER_CHAT_CONSTRUCTOR.newInstance(icb, CHAT_MESSAGE_TYPE_ENUM_OBJECT);
	       Packets.sendPacket(p, packet);
	     } catch (Exception e) {
	    	 Error.err("sending ActionBar to "+p.getName(), "Text is null");
		}
	   }
	
	public static NumbersAPI getNumbersAPI(String string) {
		return new NumbersAPI(string);
	}
	
	public static PlayerAPI getPlayerAPI(Player p) {
		return new PlayerAPI(p);
	}
	public static enum SudoType {
		CHAT,
		COMMAND
	}
	
	public static void sudo(Player target, SudoType type, String value) {
		switch(type) {
		case CHAT:
			target.chat(value);
			break;
		case COMMAND:
			target.performCommand(value);
			break;
		}
	}
	

	public static void sudoConsole(SudoType type, String value) {
		switch(type) {
		case CHAT:
			Bukkit.dispatchCommand(getConsole(), "say "+value);
			break;
		case COMMAND:
			Bukkit.dispatchCommand(getConsole(), value);
			break;
		}
	}
	
	private static void giveItems(Player p, ItemStack item) {
		if(item==null)return;
		 if (p.getInventory().firstEmpty() == -1) {
	            if(item != null)
	            p.getWorld().dropItem(p.getLocation(), item);
      } else {
	            if(item != null)
	            p.getInventory().addItem(item);
      }
	}

	public static void giveItem(Player p, ItemStack... item) {
		for(ItemStack i:item)
		giveItems(p,i);
	}
	public static void giveItem(Player p, Material item, int amount) {
		giveItems(p,new ItemStack(item,amount));
	}
	
	public void setDisplayName(Player p, String name) {
		p.setDisplayName(TheAPI.colorize(name));
	}
	public void setCustomName(Player p, String name) {
		p.setCustomName(TheAPI.colorize(name));
	}
	/**
	 * Set to null to reset chat format
	 * @param format
	 */
	public void setChatFormat(Player p, String format) {
		if(format!=null)
		LoaderClass.chatformat.put(p,format);
		else
			LoaderClass.chatformat.remove(p);
	}
	
	public static void broadcastMessage(String message) {
		for(Player p:Bukkit.getOnlinePlayers()) {
			p.sendMessage(colorize(message));
		}
		getConsole().sendMessage(colorize(message));
	}
	public static void broadcast(String message, String permission) {
		for(Player p:Bukkit.getOnlinePlayers()) {
			if(p.hasPermission(permission))
			p.sendMessage(colorize(message));
		}
		getConsole().sendMessage(colorize(message));
	}
	public static PunishmentAPI getPunishmentAPI() {
		return new PunishmentAPI();
	}
	
	public static void setServerMotd(String motd) {
		LoaderClass.plugin.motd=colorize(motd);
	}
	
	public static ReportSystem ReportSystem() {
		return ReportSystem();
	}
	
	public static void setMaxPlayers(int max) {
		LoaderClass.plugin.max=max;
	}
	
	@SuppressWarnings("deprecation")
	public static void vanish(Player p, String permission, boolean vanish) {
		if(vanish) {
			for (Player s : Bukkit.getOnlinePlayers()) {
	            if (s.hasPermission(permission)) continue;
	                s.hidePlayer(p);
	        }
		}else {
			for (Player s : Bukkit.getOnlinePlayers()) {
	                s.showPlayer(p);
	        }
		}
	}
	
	
	public static CommandSender getConsole() {
		return Bukkit.getConsoleSender();
	}
	public static WorldsManager getWorldsManager() {
		return new WorldsManager();
	}
	public static EconomyAPI getEconomyAPI() {
		return new EconomyAPI();
	}
	public static TabListAPI getTabListAPI() {
		return new TabListAPI();
	}
	
	public static CountingAPI getCountingAPI() {
		return new CountingAPI();
	}
	
	public static void sendHelpOp(CommandSender s, String message) {
		broadcast(LoaderClass.config.getString("Format.HelpOp")
					.replace("%message%", message).replace("%sender%", s.getName()),LoaderClass.config.getString("Format.HelpOp-Permission"));
		
		if(!s.hasPermission(LoaderClass.config.getString("Format.HelpOp-Permission")))
			s.sendMessage(colorize(LoaderClass.config.getString("Format.HelpOp")
				.replace("%message%", message).replace("%sender%", s.getName())));
	}

	//ReportSystem().sendReport(sender, target, report, message);
	
	public static NameTagAPI getNameTagAPI(Player p, String prefix, String suffix) {
		return new NameTagAPI(p, prefix, suffix);
	}
	
	
	public static CooldownAPI getCooldownAPI(String cooldown) {
		return new CooldownAPI(cooldown);
	}
	
	public static MemoryAPI getMemoryAPI() {
		return new MemoryAPI();
	}
	
	public static PluginManagerAPI getPluginsManagerAPI() {
		return new PluginManagerAPI();
	}
	
	public static EnchantmentAPI getEnchantmentAPI() {
		return new EnchantmentAPI();
	}
	
	public static ScoreboardAPI getScoreboardAPI(Player p, Scoreboard board) {
		return new ScoreboardAPI(p,board);
	}
	
	public static SoundAPI getSoundAPI() {
		return new SoundAPI();
	}
	
	public static TimeConventorAPI getTimeConventorAPI() {
		return new TimeConventorAPI();
	}
	
	public static GUICreatorAPI getGUICreatorAPI(Player p) {
		return new GUICreatorAPI(p);
	}
	
	public static ItemCreatorAPI getItemCreatorAPI(Material material) {
		return new ItemCreatorAPI(material);
	}
	
	public static String getServerVersion() {
		String serverVer = null;
		try {
			 serverVer= Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
	 }catch(Exception e) {
			 serverVer=Bukkit.getServer().getClass().getPackage().getName().split("\\.")[1];
	 }
		return serverVer;
	}
	
	public static double getServerTPS() {
		
		try {
	    	Object minecraftServer = null;
	    	Field recentTps = null;
	            Server server = LoaderClass.plugin.getServer();
	            Field consoleField = server.getClass().getDeclaredField("console");
	            consoleField.setAccessible(true);
	            minecraftServer = consoleField.get(server);
	     
	            recentTps = minecraftServer.getClass().getSuperclass().getDeclaredField("recentTps");
	            recentTps.setAccessible(true);
	        
	        double tps = ((double[]) recentTps.get(minecraftServer))[0];
	        if(tps>20)tps=20;
			return getNumbersAPI(String.format("%2.02f", tps).replaceAll(",", ".")).getDouble();
	    	}catch(Throwable e) {
	    		return 20.0;
	    	}
	}
	
	public static int getPlayerPing(Player p) {
		if(getServerVersion().equals("glowstone")) {
			try {
			return ((GlowPlayer)p).getUserListEntry().getPing();
			}catch(Exception e) {
				return -1;
			}
		}
		try {
	        Class<?> craftPlayer = Class.forName("org.bukkit.craftbukkit."
	                + getServerVersion() + ".entity.CraftPlayer");
	        Object handle = craftPlayer.getMethod("getHandle").invoke(p);
	        Integer ping = (Integer) handle.getClass().getDeclaredField("ping").get(handle);
	        return ping.intValue();
	    } catch (ClassNotFoundException | IllegalAccessException
	            | IllegalArgumentException | InvocationTargetException
	            | NoSuchMethodException | SecurityException
	            | NoSuchFieldException e) {
	        return -1;
	    }
	}
}
