package me.Straiker123;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import me.Straiker123.TimeConventorAPI.EndWords;
import me.Straiker123.Utils.TheAPICommand;
import me.Straiker123.Utils.punishment;
import net.milkbowl.vault.economy.Economy;

public class LoaderClass extends JavaPlugin {
	public static LoaderClass plugin;
	public static List<ConfigAPI> list = new ArrayList<ConfigAPI>();

	public static HashMap<String, Runnable> actions = new HashMap<String, Runnable>();
	
	//arena, int - runnable
	public static HashMap<String, Integer> GameAPI_Arenas = new HashMap<String, Integer>();
	public static HashMap<String, Integer> gameapi_timer = new HashMap<String, Integer>();
	public static HashMap<String, Runnable> win_rewards = new HashMap<String, Runnable>();

	public static ConfigAPI data;
	public static ConfigAPI config;
	public static ConfigAPI gameapi;
	
	public void onLoad() {
		createConfig();
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
		Bukkit.getScheduler().runTaskLater(this, new Runnable() {

			@Override
			public void run() {
				if(TheAPI.getCountingAPI().getPluginsUsingTheAPI().size() != 0)
				TheAPI.getConsole().sendMessage(TheAPI.colorize("&bTheAPI&7: &aTheAPI using "+TheAPI.getCountingAPI().getPluginsUsingTheAPI().size()+" plugin(s)"));
				else {
					TheAPI.getConsole().sendMessage(TheAPI.colorize("&bTheAPI&7: &6TheAPI isn't used in any plugin, disabling plugin.."));
					stop();
				}
			}
			
		}, 200);
		
	}
	
	private void stop() {					
		Bukkit.getPluginManager().disablePlugin(this);
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
		gameapi=TheAPI.getConfig("TheAPI", "GameAPI");
		gameapi.setCustomEnd("dat");
		gameapi.create();
		config = TheAPI.getConfig("TheAPI", "Config");
		config.addDefault("Words.Second", "s");
		config.addDefault("Words.Minute", "min");
		config.addDefault("Words.Hour", "h");
		config.addDefault("Words.Day", "d");
		config.addDefault("Words.Week", "w");
		config.addDefault("Words.Month", "mon");
		config.addDefault("Words.Year", "y");
		config.addDefault("Words.Century", "cen");
		config.addDefault("Words.Millenium", "mil");
		config.addDefault("Format.Mute", "&6You are muted for &c%reason%");
		config.addDefault("Format.TempMute", "&6You are muted for &c%reason% &6on &c%time%");
		config.addDefault("Format.Ban", "&6You are banned for &c%reason%");
		config.addDefault("Format.TempBan", "&6You are banned for &c%reason% &6on &c%time%");
		config.addDefault("Format.BanIP", "&6You are ip-banned for &c%reason%");
		config.addDefault("Format.TempBanIP", "&6You are temp ip-banned for &c%reason% &6on &c%time%");
		config.addDefault("Format.Broadcast.Mute", "&6Player &c%player% &6muted for &c%reason%");
		config.addDefault("Format.Broadcast.TempMute", "&6Player &c%player% &6muted for &c%reason% &6on &c%time%");
		config.addDefault("Format.Broadcast.Ban", "&6Player &c%player% &6banned for &c%reason%");
		config.addDefault("Format.Broadcast.TempBan", "&6Player &c%player% &6banned for &c%reason% &6on &c%time%");
		config.addDefault("Format.Broadcast.BanIP", "&6Player &c%player% &6ip-banned for &c%reason%");
		config.addDefault("Format.Broadcast.TempBanIP", "&6Player &c%player% &6temp ip-banned for &c%reason% &6on &c%time%");
		config.addDefault("Format.Broadcast.Mute-Permission", "TheAPI.Mute");
		config.addDefault("Format.Broadcast.TempMute-Permission", "TheAPI.TempMute");
		config.addDefault("Format.Broadcast.Ban-Permission", "TheAPI.Ban");
		config.addDefault("Format.Broadcast.TempBan-Permission", "TheAPI.TempBan");
		config.addDefault("Format.Broadcast.BanIP-Permission", "TheAPI.BanIP");
		config.addDefault("Format.Broadcast.TempBanIP-Permission", "TheAPI.TempBanIP");
		config.addDefault("Format.HelpOp", "&0[&4HelpOp&0] &c%sender%&8: &c%message%");
		config.addDefault("Format.HelpOp-Permission", "TheAPI.HelpOp");
		config.addDefault("Format.Report", "&0[&4Report&0] &c%sender% &6reported &c%reported% &6for &c%message%");
		config.addDefault("Format.Report-Permission", "TheAPI.Report");
		config.create();

		data = TheAPI.getConfig("TheAPI", "Data");
		data.setCustomEnd("dat");
		data.create();
	}
	
	
	public void onDisable() {
		TheAPI.getConsole().sendMessage(TheAPI.colorize("&bTheAPI&7: &8********************"));
		TheAPI.getConsole().sendMessage(TheAPI.colorize("&bTheAPI&7: &6Action: &cDisabling plugin and saving configs.."));
		TheAPI.getConsole().sendMessage(TheAPI.colorize("&bTheAPI&7: &8********************"));
		new TimeConventorAPI();
		if(EndWords.values() != null)
		for(EndWords s : EndWords.values())
			config.getConfig().set("Words."+s.name(), TheAPI.getTimeConventorAPI().getEndWord(s));
		data.getConfig().set("guis", null);
		for(ConfigAPI s:list) {
			s.save();
		}
	}
}
