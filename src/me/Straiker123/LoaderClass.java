package me.Straiker123;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import me.Straiker123.TimeConventorAPI.EndWords;
import net.milkbowl.vault.economy.Economy;

public class LoaderClass extends JavaPlugin {
	public static LoaderClass plugin;
	public static FileConfiguration config;
	public static List<ConfigAPI> list = new ArrayList<ConfigAPI>();
	public static FileConfiguration data;
	public ConfigAPI f;
	
	public static HashMap<String, Runnable> actions = new HashMap<String, Runnable>();
	
	public ConfigAPI a;
	
	public void onLoad() {
		TheAPI.getConsole().sendMessage(TheAPI.colorize("&bTheAPI&7: &8********************"));
		TheAPI.getConsole().sendMessage(TheAPI.colorize("&bTheAPI&7: &6Action: &6Loading plugin.."));
		TheAPI.getConsole().sendMessage(TheAPI.colorize("&bTheAPI&7: &8********************"));
	}
	public boolean e;
	public String motd;
	public int max;
	public void onEnable() {
		plugin=this;
		createConfig();
		
		new TheAPI();
		new TimeConventorAPI();
		Bukkit.getPluginManager().registerEvents(new punishment(), this);
		Bukkit.getPluginCommand("TheAPI").setExecutor(new TheAPICommand());
		TheAPI.getConsole().sendMessage(TheAPI.colorize("&bTheAPI&7: &8********************"));
		TheAPI.getConsole().sendMessage(TheAPI.colorize("&bTheAPI&7: &6Action: &aEnabling plugin, creating config and registering economy.."));
		TheAPI.getConsole().sendMessage(TheAPI.colorize("&bTheAPI&7: &8********************"));
		
		if(TheAPI.getPluginsManagerAPI().getPlugin("Vault") == null || !getVaultEconomy()) {
			TheAPI.getConsole().sendMessage(TheAPI.colorize("&bTheAPI&7: &8********************"));
			TheAPI.getConsole().sendMessage(TheAPI.colorize("&bTheAPI&7: &cPlugin not found Vault Economy, EconomyAPI is disabled."));
			TheAPI.getConsole().sendMessage(TheAPI.colorize("&bTheAPI&7: &cYou can enabled EconomyAPI by set custom Economy in EconomyAPI."));
			TheAPI.getConsole().sendMessage(TheAPI.colorize("&bTheAPI&7: &c *TheAPI will still normally work without problems*"));
			TheAPI.getConsole().sendMessage(TheAPI.colorize("&bTheAPI&7: &8********************"));
			e=false;
		}else {
			e=true;
		}
		new EconomyAPI();
		TheAPI.getConsole().sendMessage(TheAPI.colorize("&bTheAPI&7: &aTheAPI using "+TheAPI.getCountingAPI().getPluginsUsingTheAPI().size()+" plugin(s)"));
	}
	
	public static Economy economy;
	private boolean getVaultEconomy() {
		RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
	    if (economyProvider != null) {
	    	economy = economyProvider.getProvider();
	    	
	    }
		return economy != null;
	}
	
	public static HashMap<Player, String> chatformat = new HashMap<Player, String>();
	private void createConfig() {
		f = TheAPI.getConfig("TheAPI", "Config");
		f.addDefault("Words.Second", "s");
		f.addDefault("Words.Minute", "min");
		f.addDefault("Words.Hour", "h");
		f.addDefault("Words.Day", "d");
		f.addDefault("Words.Week", "w");
		f.addDefault("Words.Month", "mon");
		f.addDefault("Words.Year", "y");
		f.addDefault("Words.Century", "cen");
		f.addDefault("Words.Millenium", "mil");
		f.addDefault("Format.Mute", "&6You are muted for &c%reason%");
		f.addDefault("Format.TempMute", "&6You are muted for &c%reason% &6on &c%time%");
		f.addDefault("Format.Ban", "&6You are banned for &c%reason%");
		f.addDefault("Format.TempBan", "&6You are banned for &c%reason% &6on &c%time%");
		f.addDefault("Format.BanIP-Player", "&6You are ip-banned for &c%reason%");
		f.addDefault("Format.BanIP-IPAddress", "&6Your ip address is banned for &c%reason%");
		f.addDefault("Format.Broadcast.Mute", "&6Player &c%player% &6muted for &c%reason%");
		f.addDefault("Format.Broadcast.TempMute", "&6Player &c%player% &6muted for &c%reason% &6on &c%time%");
		f.addDefault("Format.Broadcast.Ban", "&6Player &c%player% &6banned for &c%reason%");
		f.addDefault("Format.Broadcast.TempBan", "&6Player &c%player% &6banned for &c%reason% &6on &c%time%");
		f.addDefault("Format.Broadcast.BanIP-Player", "&6Player &c%player% &6ip-banned for &c%reason%");
		f.addDefault("Format.Broadcast.BanIP-IPAddress", "&6IP Address &c%ip% &6banned for &c%reason%");

		f.addDefault("Format.Broadcast.Mute-Permission", "TheAPI.Mute");
		f.addDefault("Format.Broadcast.TempMute-Permission", "TheAPI.TempMute");
		f.addDefault("Format.Broadcast.Ban-Permission", "TheAPI.Ban");
		f.addDefault("Format.Broadcast.TempBan-Permission", "TheAPI.TempBan");
		f.addDefault("Format.Broadcast.BanIP-Player-Permission", "TheAPI.BanIP");
		f.addDefault("Format.Broadcast.BanIP-IPAddress-Permission", "TheAPI.BanIP");
		f.addDefault("Format.HelpOp", "&0[&4HelpOp&0] &c%sender%&8: &c%message%");
		f.addDefault("Format.HelpOp-Permission", "TheAPI.HelpOp");
		f.addDefault("Format.Report", "&0[&4Report&0] &c%sender% &6reported &c%reported% &6for &c%message%");
		f.addDefault("Format.Report-Permission", "TheAPI.Report");
		f.create();
		config=f.getConfig();

		a = TheAPI.getConfig("TheAPI", "Data");
		a.setCustomEnd("dat");
		a.create();
		data=a.getConfig();
	}
	
	
	public void onDisable() {
		TheAPI.getConsole().sendMessage(TheAPI.colorize("&bTheAPI&7: &8********************"));
		TheAPI.getConsole().sendMessage(TheAPI.colorize("&bTheAPI&7: &6Action: &cDisabling plugin and saving configs.."));
		TheAPI.getConsole().sendMessage(TheAPI.colorize("&bTheAPI&7: &8********************"));
		new TimeConventorAPI();
		if(EndWords.values() != null)
		for(EndWords s : EndWords.values())
			config.set("Words."+s.name(), TheAPI.getTimeConventorAPI().getEndWord(s));
		data.set("guis", null);
		for(ConfigAPI s:list) {
			s.save();
		}
	}
}
