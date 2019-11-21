package me.Straiker123;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

public class TheAPICommand implements CommandExecutor {

	
	private String getPlugin(Plugin a) {
		if(a.isEnabled())return "&a"+a.getName();
		return "&c"+a.getName()+" &7(Disabled)";
	}
	
	@Override
	public boolean onCommand(CommandSender s, Command arg1, String arg2, String[] args) {
		if(s.hasPermission("TheAPI.Reload")) {
			s.sendMessage(TheAPI.colorize("&7-----------------"));
			s.sendMessage(TheAPI.colorize("&6Reloading configs.."));
			for(ConfigAPI a : LoaderClass.list)a.save();
			s.sendMessage(TheAPI.colorize("&6Version: &cv"+LoaderClass.plugin.getDescription().getVersion()));
			if(TheAPI.getCountingAPI().getPluginsUsingTheAPI().size()!=0) {
			s.sendMessage(TheAPI.colorize("&6Plugins using TheAPI:"));
			for(Plugin a: TheAPI.getCountingAPI().getPluginsUsingTheAPI())
			s.sendMessage(TheAPI.colorize("&7 - "+getPlugin(a)));
			}
			s.sendMessage(TheAPI.colorize("&7-----------------"));
			return true;
		}
		s.sendMessage(TheAPI.colorize("&cYou do not have permission &4TheAPI.Reload &cto do that!"));
		return true;
	}

}
