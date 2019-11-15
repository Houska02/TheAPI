package me.Straiker123;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class TheAPICommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender s, Command arg1, String arg2, String[] args) {
		if(s.hasPermission("TheAPI.Reload")) {
			for(ConfigAPI a : LoaderClass.list)a.save();
			s.sendMessage(TheAPI.colorize("&7-----------------"));
			s.sendMessage(TheAPI.colorize("&6Version: &cv"+LoaderClass.plugin.getDescription().getVersion()));
			s.sendMessage(TheAPI.colorize("&6Plugins using TheAPI: &c"+TheAPI.getCountingAPI().getPluginsUsingTheAPI().size()));
			s.sendMessage(TheAPI.colorize("&7-----------------"));
			return true;
		}
		s.sendMessage(TheAPI.colorize("&cYou do not have permission &4TheAPI.Reload &cto do that!"));
		return true;
	}

}
