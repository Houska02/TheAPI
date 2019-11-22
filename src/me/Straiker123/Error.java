package me.Straiker123;

import org.bukkit.Bukkit;

public class Error {
	public static void err(String message, String reason) {
		Bukkit.getLogger().severe(TheAPI.colorize("&cTheAPI&7: &cAn severe error when &4"+message+"&c, reason: &4"+reason));
	}
}
