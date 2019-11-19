package me.Straiker123;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scoreboard.Scoreboard;

public class TheAPI {

	public static String colorize(String string) {
		if(string == null)return null;
		return ChatColor.translateAlternateColorCodes('&', string);
	}
	
	public static ConfigAPI getConfig(String localization,  String name) {
		return new ConfigAPI(name,localization);
	}
	
	public static NumbersAPI getNumbersAPI(String string) {
		return new NumbersAPI(string);
	}

	public static void invsee(Player p, Player target) {
		p.openInventory(target.getInventory());
	}
	
	public static void giveItem(Player p, ItemStack item) {
		if(item==null)return;
		 if (p.getInventory().firstEmpty() == -1) {
	            if(item != null)
	            p.getWorld().dropItem(p.getLocation(), item);
      } else {
	            if(item != null)
	            p.getInventory().addItem(item);
      }
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
	
	public static void giveItem(Player p, ItemStack item, int amount) {
		for(int i = 0; i<amount; ++i)
		giveItem(p,item);
	}
	public static void giveItem(Player p, Material item, int amount) {
			giveItem(p,new ItemStack(item),amount);
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
		for(Player p: Bukkit.getOnlinePlayers()) {
			if(p.hasPermission(LoaderClass.config.getString("Format.HelpOp-Permission")))p.sendMessage(colorize(LoaderClass.config.getString("Format.HelpOp")
					.replace("%message%", message).replace("%sender%", s.getName())));
		}
		if(!s.hasPermission(LoaderClass.config.getString("Format.HelpOp-Permission")))s.sendMessage(colorize(LoaderClass.config.getString("Format.HelpOp")
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
		return LoaderClass.plugin.getServer().getClass().getPackage().getName().split("\\.")[3];
	}
	
	public static double getServerTPS() {
		try {
		try {
	    	Object minecraftServer = null;
	    	Field recentTps = null;
	            Server server = Bukkit.getServer();
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
	    }catch(Exception e) {
    		return 20.0;
	    }
	}
	
	public static int getPlayerPing(Player p) {
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
