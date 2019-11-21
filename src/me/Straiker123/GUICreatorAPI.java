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
		if(LoaderClass.data.getString("guis")!=null)
			id=LoaderClass.data.getConfigurationSection("guis").getKeys(false).size()+"";
	}
	String t = "TheAPI - Missing name of GUI";
	public void setTitle(String title) {
		if(title!=null)
		t=title;
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
	String id = "0";
	public String getID() {
		return id;
	}

	
	public static enum Options{
		CANT_BE_TAKEN,
		RUNNABLE,
		SENDMESSAGES,
		SENDCOMMANDS
	}
	
	HashMap<Integer,ItemStack> map = new HashMap<Integer,ItemStack>();
	public void setItem(int position, ItemStack item, HashMap<Options, Object> options) {
		map.put(position,item);
		for(Options a:options.keySet()) {

			switch(a) {
			case CANT_BE_TAKEN:
				LoaderClass.data.set("guis."+getID()+"."+position+".CANT_BE_TAKEN", options.get(a));
				break;
			case RUNNABLE:
				LoaderClass.actions.put(getID()+"."+position,(Runnable) options.get(a));
				break;
			case SENDMESSAGES:
				LoaderClass.data.set("guis."+getID()+"."+position+".SENDMESSAGES", options.get(a));
				break;
			case SENDCOMMANDS:
				LoaderClass.data.set("guis."+getID()+"."+position+".SENDCOMMANDS", options.get(a));
				break;
			}
		}
		LoaderClass.data.set("guis."+getID()+"."+position+".item", item);
	}
	
	
	public void setItem(int position, ItemStack item) {
		map.put(position,item);
		LoaderClass.data.set("guis."+getID()+"."+position+".item", item);
	}
	
	public void open() {
		Inventory i = Bukkit.createInventory(p, f,TheAPI.colorize(t));
		for(Integer a : map.keySet()) {
			i.setItem(a, map.get(a));
		}
		LoaderClass.data.set("guis."+getID()+".title", t);
		LoaderClass.plugin.a.save();
		p.openInventory(i);
	}
}
