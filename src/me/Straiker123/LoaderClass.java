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

	public static FileConfiguration ban;
	public ConfigAPI s;
	

	public static FileConfiguration col;
	public ConfigAPI f;
	
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
		createConfigs();
		
		new TheAPI();
		new TimeConventorAPI();
		Bukkit.getPluginManager().registerEvents(new motd(), this);
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
		startChecker();
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
		a = TheAPI.getConfig("TheAPI", "Config");
		a.addDefault("Words.Second", "s");
		a.addDefault("Words.Minute", "min");
		a.addDefault("Words.Hour", "h");
		a.addDefault("Words.Day", "d");
		a.addDefault("Words.Week", "w");
		a.addDefault("Words.Month", "mon");
		a.addDefault("Words.Year", "y");
		a.addDefault("Words.Century", "cen");
		a.addDefault("Words.Millenium", "mil");
		a.addDefault("Format.Mute", "&6You are muted for &c%reason%");
		a.addDefault("Format.TempMute", "&6You are muted for &c%reason% &6on &c%time%");
		a.addDefault("Format.Ban", "&6You are banned for &c%reason%");
		a.addDefault("Format.TempBan", "&6You are banned for &c%reason% &6on &c%time%");
		a.addDefault("Format.BanIP-Player", "&6You are ip-banned for &c%reason%");
		a.addDefault("Format.BanIP-IPAddress", "&6Your ip address is banned for &c%reason%");
		a.addDefault("Format.Broadcast.Mute", "&6Player &c%player% &6muted for &c%reason%");
		a.addDefault("Format.Broadcast.TempMute", "&6Player &c%player% &6muted for &c%reason% &6on &c%time%");
		a.addDefault("Format.Broadcast.Ban", "&6Player &c%player% &6banned for &c%reason%");
		a.addDefault("Format.Broadcast.TempBan", "&6Player &c%player% &6banned for &c%reason% &6on &c%time%");
		a.addDefault("Format.Broadcast.BanIP-Player", "&6Player &c%player% &6ip-banned for &c%reason%");
		a.addDefault("Format.Broadcast.BanIP-IPAddress", "&6IP Address &c%ip% &6banned for &c%reason%");

		a.addDefault("Format.Broadcast.Mute-Permission", "TheAPI.Mute");
		a.addDefault("Format.Broadcast.TempMute-Permission", "TheAPI.TempMute");
		a.addDefault("Format.Broadcast.Ban-Permission", "TheAPI.Ban");
		a.addDefault("Format.Broadcast.TempBan-Permission", "TheAPI.TempBan");
		a.addDefault("Format.Broadcast.BanIP-Player-Permission", "TheAPI.BanIP");
		a.addDefault("Format.Broadcast.BanIP-IPAddress-Permission", "TheAPI.BanIP");
		a.addDefault("Format.HelpOp", "&0[&4HelpOp&0] &c%sender%&8: &c%message%");
		a.addDefault("Format.HelpOp-Permission", "TheAPI.HelpOp");
		a.addDefault("Format.Report", "&0[&4Report&0] &c%sender% &6reported &c%reported% &6for &c%message%");
		a.addDefault("Format.Report-Permission", "TheAPI.Report");
		
		a.create();
		config=a.getConfig();

		f = TheAPI.getConfig("TheAPI", "Cooldowns");
		f.setCustomEnd("dat");
		f.create();
		col=f.getConfig();
	}
	private void createConfigs() {
		s = TheAPI.getConfig("TheAPI", "Punishment");
		s.create();
		ban=s.getConfig();
	}
	private void startChecker() {
		Bukkit.getScheduler().runTaskLater(this, new Runnable() {
			@Override
			public void run() {
				TheAPI.getConsole().sendMessage(TheAPI.colorize("&bTheAPI&7: &8********************"));
				TheAPI.getConsole().sendMessage(TheAPI.colorize("&bTheAPI&7: &6Action: &eChecking for plugins that using TheAPI.."));
				TheAPI.getConsole().sendMessage(TheAPI.colorize("&bTheAPI&7: &8********************"));
				broadcast();
			}
		}, 100);
	}
	
	private void broadcast() {
		Bukkit.getScheduler().runTaskLater(this, new Runnable() {
			@Override
			public void run() {
				TheAPI.getConsole().sendMessage(TheAPI.colorize("&bTheAPI&7: &aTheAPI using "+TheAPI.getCountingAPI().getPluginsUsingTheAPI().size()+" plugin(s)"));
			}
		}, 100);
	}
	
	
	public void onDisable() {
		TheAPI.getConsole().sendMessage(TheAPI.colorize("&bTheAPI&7: &8********************"));
		TheAPI.getConsole().sendMessage(TheAPI.colorize("&bTheAPI&7: &6Action: &cDisabling plugin and saving configs.."));
		TheAPI.getConsole().sendMessage(TheAPI.colorize("&bTheAPI&7: &8********************"));
		if(EndWords.values() != null)
		for(EndWords s : EndWords.values())
		config.set("Words."+s.name(), TheAPI.getTimeConventorAPI().getEndWord(s));
		for(ConfigAPI s:list) {
			s.save();
		}
	}
}
