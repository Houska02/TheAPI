package me.Straiker123.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.Inventory;

import me.Straiker123.Utils.EventsRegister;

public class GUIOpenEvent extends Event implements Cancellable {
	public GUIOpenEvent(Player player, Inventory gui, String title) {
		t=title;
		s=player;
		this.gui=gui;
	}
	
	boolean c;
	String t;
	Player s;
	Inventory gui;
	
	/**
	 * @return is GUI open event cancelled
	 */
	@Override
	public boolean isCancelled() {
		return c;
	}
	/**
	 * Cancel GUI open event
	 */
	@Override
	public void setCancelled(boolean cancel) {
		c=cancel;
	}
	
	/**
	 * 
	 * @return Title of GUI
	 */
	public String getTitle() {
		return t;
	}

	/**
	 * 
	 * @return Player
	 */
	public Player getPlayer() {
		return s;
	}
	
	/**
	 * 
	 * @return ID of GUI
	 */
	public String getID() {
		return EventsRegister.findGUI(t,s);
	}
	
	/**
	 * 
	 * @return Opening GUI
	 */
	public Inventory getGUI() {
		return gui;
	}
	
	@Override
	public HandlerList getHandlers() {
		return new HandlerList();
	}
	
	public HandlerList getHandlerList() {
		return new HandlerList();
	}
	
}
