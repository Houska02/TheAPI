package me.Straiker123;

public class Error {
	public static void err(String message, String reason) {
		TheAPI.getConsole().sendMessage(TheAPI.colorize("&cTheAPI&7: &cAn severe error when &4"+message+"&c, reason: &4"+reason));
	}
}
