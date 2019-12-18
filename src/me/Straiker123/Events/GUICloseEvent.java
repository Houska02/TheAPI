package me.Straiker123.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.Inventory;

import me.Straiker123.Utils.Events;

public class GUICloseEvent extends Event {
	public GUICloseEvent(Player player, Inventory gui, String title) {
		s=player;
		this.gui=gui;
		t=title;
	}
	
	Inventory gui;
	Player s;
	String t;

	private static final HandlerList handler = new HandlerList();
	@Override
	public HandlerList getHandlers() {
		return handler;
	}
	
	public static HandlerList getHandlerList() {
		return handler;
	}
	
	/**
	 * 
	 * @return Closing GUI
	 */
	public Inventory getGUI() {
		return gui;
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
		return Events.findGUI(t,s);
	}

}
