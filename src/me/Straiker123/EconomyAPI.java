package me.Straiker123;

import net.milkbowl.vault.economy.Economy;

public class EconomyAPI {
	Economy e = LoaderClass.economy;
	public void setEconomy(Economy a) {
		if(a!=null) {
		e=a;
		LoaderClass.plugin.e=true;
		}
	}
	@SuppressWarnings("deprecation")
	public void depositPlayer(String player, double money) {
		if(TheAPI.getPluginsManagerAPI().getPlugin("Vault").isEnabled() && e!=null && LoaderClass.plugin.e)
		e.depositPlayer(player, money);
	}
	@SuppressWarnings("deprecation")
	public void depositPlayer(String player, String world, double money) {
		if(TheAPI.getPluginsManagerAPI().getPlugin("Vault").isEnabled() && e!=null && LoaderClass.plugin.e)
		e.depositPlayer(player,world, money);
	}
	@SuppressWarnings("deprecation")
	public void withdrawPlayer(String player, double money) {
		if(TheAPI.getPluginsManagerAPI().getPlugin("Vault").isEnabled() && e!=null && LoaderClass.plugin.e)
		e.withdrawPlayer(player, money);
	}
	@SuppressWarnings("deprecation")
	public void withdrawPlayer(String player, String world, double money) {
		if(TheAPI.getPluginsManagerAPI().getPlugin("Vault").isEnabled() && e!=null && LoaderClass.plugin.e)
		e.withdrawPlayer(player,world, money);
	}
	@SuppressWarnings("deprecation")
	public double getBalance(String player) {
		if(TheAPI.getPluginsManagerAPI().getPlugin("Vault").isEnabled() && e!=null && LoaderClass.plugin.e)
			return e.getBalance(player);
		return 0.0;
	}
	@SuppressWarnings("deprecation")
	public double getBalance(String player, String world) {
		if(TheAPI.getPluginsManagerAPI().getPlugin("Vault").isEnabled() && e!=null && LoaderClass.plugin.e)
			return e.getBalance(player,world);
		return 0.0;
	}
	@SuppressWarnings("deprecation")
	public boolean has(String player, double money) {
		return e.has(player,money) && e!=null && LoaderClass.plugin.e;
	}
	@SuppressWarnings("deprecation")
	public boolean has(String player, String world, double money) {
		return e.has(player, world,money) && e!=null && LoaderClass.plugin.e;
	}
	
	@SuppressWarnings("deprecation")
	public boolean createAccount(String player) {
		if(TheAPI.getPluginsManagerAPI().getPlugin("Vault").isEnabled() && e!=null && LoaderClass.plugin.e)
			return e.createPlayerAccount(player);
		return false;
	}
	
	public String format(double money) {
		if(TheAPI.getPluginsManagerAPI().getPlugin("Vault").isEnabled() && e!=null && LoaderClass.plugin.e)
			return e.format(money);
		return ""+money;
	}
}
