package me.Straiker123;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class GUICreatorAPI {
	Player p;
	public GUICreatorAPI(Player s) {
		p=s;
	}
	String t = "TheAPI - Missing name of GUI";
	public void setTitle(String title) {
		if(title!=null)
		t=TheAPI.colorize(title);
	}
	public int size(int o) {
	switch(o) {
	case 9:
	return 9;
	case 18:
	return 18;
	case 27:
	return 27;
	case 36:
	return 36;
	case 48:
	return 48;
	case 54:
	return 54;
	default:
	return 9;
	}
	}
	int f = 9;
	public void setSize(int size) {
		f = size(size);
	}
	/**
	 * 
	 * @param position
	 * Position in gui
	 * @param item
	 * Item in gui, you can use instance geItemCreatorAPI to create item
	 */
	HashMap<Integer,ItemStack> map = new HashMap<Integer,ItemStack>();
	public void setItem(int position, ItemStack item) {
		map.put(position,item);
	}
	
	public void open() {
		Inventory i = Bukkit.createInventory(p, f,t);
		for(Integer a : map.keySet()) {
			i.setItem(a, map.get(a));
		}
		p.openInventory(i);
	}
}
