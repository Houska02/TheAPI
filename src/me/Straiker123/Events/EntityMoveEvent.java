package me.Straiker123.Events;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class EntityMoveEvent extends Event implements Cancellable {
	Entity s;
	public EntityMoveEvent(Entity p,Location from, Location to) {
		s=p;
		f=from;
		t=to;
	}
	Location t;
	Location f;
	
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
	
	public Entity getEntity() {
		return s;
	}

	@Override
	public HandlerList getHandlers() {
		return new HandlerList();
	}
	
	public HandlerList getHandlerList() {
		return new HandlerList();
	}}