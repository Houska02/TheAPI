package me.Straiker123.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.Straiker123.Utils.EventsRegister;

public class GUIClickEvent extends Event implements Cancellable {
	
	public GUIClickEvent(Player player, Inventory inv, String title, int slot, ItemStack item) {
		this.player=player;
		this.inv=inv;
		this.title=title;
		this.slot=slot;
		this.item=item;
	}
	ItemStack item;
	int slot;
	String title;
	Player player;
	Inventory inv;
	boolean cancel;
	/**
	 * 
	 * @return Clicked slot
	 */
	public int getSlot() {
		return slot;
	}
	
	/**
	 * 
	 * @return Clicked item (Can be null)
	 */
	public ItemStack getItem() {
		return item;
	}
	
	/**
	 * @return is event cancelled
	 */
	@Override
	public boolean isCancelled() {
		return cancel;
	}
	/**
	 * 
	 * @return Title of GUI
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * Cancel GUI click event
	 */
	@Override
	public void setCancelled(boolean cancel) {
		this.cancel=cancel;
	}
	
	@Override
	public HandlerList getHandlers() {
		return new HandlerList();
	}
	
	public HandlerList getHandlerList() {
		return new HandlerList();
	}
	/**
	 * 
	 * @return Item is null
	 */
	public boolean isItemNull() {
		return getItem()==null;
	}
	
	/**
	 * 
	 * @return ID of GUI
	 */
	public String getID() {
		return EventsRegister.findGUI(title,player);
	}
	
	public boolean isPlayerInventory() {
		return getGUI().getType().equals(InventoryType.PLAYER);
	}
	
	/**
	 * 
	 * @return Player
	 */
	public Player getPlayer() {
		return player;
	}
	/**
	 * 
	 * @return Opened GUI
	 */
	public Inventory getGUI() {
		return inv;
	}

}
