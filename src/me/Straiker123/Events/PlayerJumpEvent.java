package me.Straiker123.Events;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerJumpEvent extends Event implements Cancellable {
	Player s;
	public PlayerJumpEvent(Player p,Location from, Location to, int jump) {
		s=p;
		f=from;
		t=to;
		i=jump;
	}
	private static final HandlerList handler = new HandlerList();
	int i;
	Location t;
	Location f;
	
	public int getJump() {
		return i;
	}
	public Location getTo() {
		return t;
	}
	public Location getToFrom() {
		return f;
	}
	
	@Override
	public boolean isCancelled() {
		return cancel;
	}
	boolean cancel;
	@Override
	public void setCancelled(boolean cancel) {
		this.cancel=cancel;
	}
	
	public Player getPlayer() {
		return s;
	}

	@Override
	public HandlerList getHandlers() {
		return handler;
	}
	
	public static HandlerList getHandlerList() {
		return handler;
	}
}
