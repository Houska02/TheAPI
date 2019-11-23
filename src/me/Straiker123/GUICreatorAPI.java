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
		if(LoaderClass.data.getString("guis."+s.getName())!=null)
			id=LoaderClass.data.getConfigurationSection("guis."+s.getName()).getKeys(false).size()+"";
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
	
	/**
	 * CAN_PUT_ITEM - Global, can player put to the gui item from his inventory (true/false)
	 * CANT_BE_TAKEN - Can player take item from gui (true/false)
	 * 
	 * RUNNABLE - Ignoring click type, run everything in runnable (Runnable)
	 * SENDMESSAGES - Ignoring click type, send list of messages to the player (List<String>)
	 * SENDCOMMANDS - Ignoring click type, send list of commands as console (List<String>)
	 */
	public static enum Options{
		CANT_BE_TAKEN,
		CAN_PUT_ITEM,
		
		RUNNABLE,
		SENDMESSAGES,
		SENDCOMMANDS,

		RUNNABLE_RIGHT_CLICK,
		SENDMESSAGES_RIGHT_CLICK,
		SENDCOMMANDS_RIGHT_CLICK,
		
		RUNNABLE_LEFT_CLICK,
		SENDMESSAGES_LEFT_CLICK,
		SENDCOMMANDS_LEFT_CLICK,
		
		RUNNABLE_SHIFT_WITH_RIGHT_CLICK,
		SENDMESSAGES_SHIFT_WITH_RIGHT_CLICK,
		SENDCOMMANDS_SHIFT_WITH_RIGHT_CLICK,
		
		RUNNABLE_SHIFT_WITH_LEFT_CLICK,
		SENDMESSAGES_SHIFT_WITH_LEFT_CLICK,
		SENDCOMMANDS_SHIFT_WITH_LEFT_CLICK,
		
		RUNNABLE_MIDDLE_CLICK,
		SENDMESSAGES_MIDDLE_CLICK,
		SENDCOMMANDS_MIDDLE_CLICK,
	}
	
	HashMap<Integer,ItemStack> map = new HashMap<Integer,ItemStack>();
	
	public void setItem(int position, ItemStack item, HashMap<Options, Object> options) {
		map.put(position,item);
		for(Options a:options.keySet()) {

			switch(a) {
			case CAN_PUT_ITEM:
				LoaderClass.data.set("guis."+p.getName()+"."+getID()+".CAN_PUT_ITEM", options.get(a));
				break;
			case CANT_BE_TAKEN:
				LoaderClass.data.set("guis."+p.getName()+"."+getID()+"."+position+".CANT_BE_TAKEN", options.get(a));
				break;
			case RUNNABLE:
				if(LoaderClass.actions.get(p.getName()+"."+getID()+"."+position)==null)
				LoaderClass.actions.put(p.getName()+"."+getID()+"."+position,(Runnable) options.get(a));
				break;
			case SENDMESSAGES:
				LoaderClass.data.set("guis."+p.getName()+"."+getID()+"."+position+".SENDMESSAGES", options.get(a));
				break;
			case SENDCOMMANDS:
				LoaderClass.data.set("guis."+p.getName()+"."+getID()+"."+position+".SENDCOMMANDS", options.get(a));
				break;

			case RUNNABLE_LEFT_CLICK:
				if(LoaderClass.actions.get(p.getName()+"."+getID()+"."+position)==null)
				LoaderClass.actions.put(p.getName()+"."+getID()+"."+position+".RUNNABLE_LEFT_CLICK",(Runnable) options.get(a));
				break;
			case SENDMESSAGES_LEFT_CLICK:
				LoaderClass.data.set("guis."+p.getName()+"."+getID()+"."+position+".SENDMESSAGES_LEFT_CLICK", options.get(a));
				break;
			case SENDCOMMANDS_LEFT_CLICK:
				LoaderClass.data.set("guis."+p.getName()+"."+getID()+"."+position+".SENDCOMMANDS_LEFT_CLICK", options.get(a));
				break;

			case RUNNABLE_RIGHT_CLICK:
				if(LoaderClass.actions.get(p.getName()+"."+getID()+"."+position)==null)
				LoaderClass.actions.put(p.getName()+"."+getID()+"."+position+".RUNNABLE_RIGHT_CLICK",(Runnable) options.get(a));
				break;
			case SENDMESSAGES_RIGHT_CLICK:
				LoaderClass.data.set("guis."+p.getName()+"."+getID()+"."+position+".SENDMESSAGES_RIGHT_CLICK", options.get(a));
				break;
			case SENDCOMMANDS_RIGHT_CLICK:
				LoaderClass.data.set("guis."+p.getName()+"."+getID()+"."+position+".SENDCOMMANDS_RIGHT_CLICK", options.get(a));
				break;

			case RUNNABLE_MIDDLE_CLICK:
				if(LoaderClass.actions.get(p.getName()+"."+getID()+"."+position)==null)
				LoaderClass.actions.put(p.getName()+"."+getID()+"."+position+".RUNNABLE_MIDDLE_CLICK",(Runnable) options.get(a));
				break;
			case SENDMESSAGES_MIDDLE_CLICK:
				LoaderClass.data.set("guis."+p.getName()+"."+getID()+"."+position+".SENDMESSAGES_MIDDLE_CLICK", options.get(a));
				break;
			case SENDCOMMANDS_MIDDLE_CLICK:
				LoaderClass.data.set("guis."+p.getName()+"."+getID()+"."+position+".SENDCOMMANDS_MIDDLE_CLICK", options.get(a));
				break;

			case RUNNABLE_SHIFT_WITH_LEFT_CLICK:
				if(LoaderClass.actions.get(p.getName()+"."+getID()+"."+position)==null)
				LoaderClass.actions.put(p.getName()+"."+getID()+"."+position+".RUNNABLE_SHIFT_WITH_LEFT_CLICK",(Runnable) options.get(a));
				break;
			case SENDMESSAGES_SHIFT_WITH_LEFT_CLICK:
				LoaderClass.data.set("guis."+p.getName()+"."+getID()+"."+position+".SENDMESSAGES_SHIFT_WITH_LEFT_CLICK", options.get(a));
				break;
			case SENDCOMMANDS_SHIFT_WITH_LEFT_CLICK:
				LoaderClass.data.set("guis."+p.getName()+"."+getID()+"."+position+".SENDCOMMANDS_SHIFT_WITH_LEFT_CLICK", options.get(a));
				break;

			case RUNNABLE_SHIFT_WITH_RIGHT_CLICK:
				if(LoaderClass.actions.get(p.getName()+"."+getID()+"."+position)==null)
				LoaderClass.actions.put(p.getName()+"."+getID()+"."+position+".RUNNABLE_SHIFT_WITH_RIGHT_CLICK",(Runnable) options.get(a));
				break;
			case SENDMESSAGES_SHIFT_WITH_RIGHT_CLICK:
				LoaderClass.data.set("guis."+p.getName()+"."+getID()+"."+position+".SENDMESSAGES_SHIFT_WITH_RIGHT_CLICK", options.get(a));
				break;
			case SENDCOMMANDS_SHIFT_WITH_RIGHT_CLICK:
				LoaderClass.data.set("guis."+p.getName()+"."+getID()+"."+position+".SENDCOMMANDS_SHIFT_WITH_RIGHT_CLICK", options.get(a));
				break;
				
			}
		}
		LoaderClass.data.set("guis."+p.getName()+"."+getID()+"."+position+".item", item);
	}

	public void addItem(ItemStack item) {
		setItem(find(), item);
	}
	
	private int find() {
		int i = -1;
		boolean find=false;
		for(int a=0; a<f; ++a) {
			if(find)break;
			if(map.get(a)==null) {
				i=a;
				find=true;
			}
		}
		return i;
	}
	public void addItem(ItemStack item, HashMap<Options, Object> options) {
		if(find()!=-1)
		setItem(find(), item, options);
	}
	
	
	public void setItem(int position, ItemStack item) {
		if(map.get(position)==null)
		map.put(position,item);
		else
			map.replace(position,item);
		LoaderClass.data.set("guis."+p.getName()+"."+getID()+"."+position+".item", item);
	}
	
	public void open() {
		Inventory i = Bukkit.createInventory(p, f,TheAPI.colorize(t));
		for(Integer a : map.keySet()) {
			i.setItem(a, map.get(a));
		}
		LoaderClass.data.set("guis."+p.getName()+"."+getID()+".title", t);
		LoaderClass.plugin.a.save();
		p.openInventory(i);
	}
}
