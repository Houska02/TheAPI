package me.Straiker123;

import java.util.ArrayList;
import java.util.List;

import net.milkbowl.vault.economy.Economy;

public class EconomyAPI {
	Economy e = LoaderClass.economy;
	public void setEconomy(Economy a) {
		if(a!=null) {
		e=a;
		LoaderClass.plugin.e=true;
		}else {

			e=null;
			LoaderClass.plugin.e=false;
		}
	}

	public boolean hasBankSupport() {
		if(e!=null && LoaderClass.plugin.e)
			return e.hasBankSupport();
		return false;
	}
	@SuppressWarnings("deprecation")
	public boolean hasAccount(String player) {
		if(e!=null && LoaderClass.plugin.e)
			return e.hasAccount(player);
		return false;
	}
	@SuppressWarnings("deprecation")
	public boolean hasAccount(String player, String world) {
		if(e!=null && LoaderClass.plugin.e)
			return e.hasAccount(player,world);
		return false;
	}

	public List<String> getBanks() {
		if(e!=null && LoaderClass.plugin.e&& hasBankSupport())
			return e.getBanks();
		return new ArrayList<String>();
	}
	public String getName() {
		if(e!=null && LoaderClass.plugin.e)
			return e.getName();
		return null;
	}
	public void bankDeposit(String bank, double money) {
		if(e!=null && LoaderClass.plugin.e&& hasBankSupport())
			 e.bankDeposit(bank, money);
	}
	public void bankWithdraw(String bank, double money) {
		if(e!=null && LoaderClass.plugin.e&& hasBankSupport())
			 e.bankWithdraw(bank, money);
	}
	public boolean bankHas(String bank, double money) {
		if(e!=null && LoaderClass.plugin.e&& hasBankSupport())
			 return bankBalance(bank) >= money;
		return false;
	}

	@SuppressWarnings("deprecation")
	public void createBank(String bank, String owner) {
		if(e!=null && LoaderClass.plugin.e && hasBankSupport())
			e.createBank(bank, owner);
	}
	public void deleteBank(String bank) {
		if(e!=null && LoaderClass.plugin.e && hasBankSupport())
			e.deleteBank(bank);
	}
	@SuppressWarnings("deprecation")
	public boolean isBankMember(String bank, String player) {
		if(e!=null && LoaderClass.plugin.e && hasBankSupport())
			return e.isBankMember(bank, player).transactionSuccess();
		return false;
	}
	@SuppressWarnings("deprecation")
	public boolean isBankOwner(String bank, String player) {
		if(e!=null && LoaderClass.plugin.e && hasBankSupport())
			return e.isBankOwner(bank, player).transactionSuccess();
		return false;
	}
	
	public double bankBalance(String bank) {
		if(e!=null && LoaderClass.plugin.e&& hasBankSupport())
			 return e.bankBalance(bank).balance;
		return 0.0;
	}
	
	@SuppressWarnings("deprecation")
	public void depositPlayer(String player, double money) {
		if(e!=null && LoaderClass.plugin.e)
		e.depositPlayer(player, money);
	}
	@SuppressWarnings("deprecation")
	public void depositPlayer(String player, String world, double money) {
		if(e!=null && LoaderClass.plugin.e)
			e.depositPlayer(player,world, money);
	}
	@SuppressWarnings("deprecation")
	public void withdrawPlayer(String player, double money) {
		if(e!=null && LoaderClass.plugin.e)
		e.withdrawPlayer(player, money);
	}
	@SuppressWarnings("deprecation")
	public void withdrawPlayer(String player, String world, double money) {
		if(e!=null && LoaderClass.plugin.e)
		e.withdrawPlayer(player,world, money);
	}
	@SuppressWarnings("deprecation")
	public double getBalance(String player) {
		if(e!=null && LoaderClass.plugin.e)
			return e.getBalance(player);
		return 0.0;
	}
	@SuppressWarnings("deprecation")
	public double getBalance(String player, String world) {
		if(e!=null && LoaderClass.plugin.e)
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
		if(e!=null && LoaderClass.plugin.e)
			return e.createPlayerAccount(player);
		return false;
	}
	
	public String format(double money) {
		if(e!=null && LoaderClass.plugin.e)
			return e.format(money);
		return ""+money;
	}
}
