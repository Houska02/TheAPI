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

	
	public static enum Options{
		CANT_BE_TAKEN,
		RUNNABLE,
		SENDMESSAGES,
		SENDCOMMANDS
	}
	
	HashMap<Integer,ItemStack> map = new HashMap<Integer,ItemStack>();
	public void setItem(int position, ItemStack item, HashMap<Options, Object> options) {
		map.put(position,item);
		
		String click = ".ALL";
		for(Options a:options.keySet()) {

			switch(a) {
			case CANT_BE_TAKEN:
				LoaderClass.data.set("guis."+p.getName()+"."+getID()+"."+position+".CANT_BE_TAKEN", options.get(a));
				break;
			case RUNNABLE:
				if(LoaderClass.actions.get(p.getName()+"."+getID()+"."+position+click)==null)
				LoaderClass.actions.put(p.getName()+"."+getID()+"."+position+click,(Runnable) options.get(a));
				break;
			case SENDMESSAGES:
				LoaderClass.data.set("guis."+p.getName()+"."+getID()+"."+position+".SENDMESSAGES"+click, options.get(a));
				break;
			case SENDCOMMANDS:
				LoaderClass.data.set("guis."+p.getName()+"."+getID()+"."+position+".SENDCOMMANDS"+click, options.get(a));
				break;
			}
		}
		LoaderClass.data.set("guis."+p.getName()+"."+getID()+"."+position+".item", item);
	}
	
	public static enum ClickTypes{
		LEFT,
		RIGHT,
		MIDDLE,
		MIDDLE_WITH_SHIFT,
		LEFT_WITH_SHIFT,
		RIGHT_WITH_SHIFT,
		ALL
	}
	
	public void setItem(int position, ItemStack item, HashMap<Options, Object> options, ClickTypes click) {

		String c = "."+click.toString();
		map.put(position,item);
		for(Options a:options.keySet()) {

			switch(a) {
			case CANT_BE_TAKEN:
				LoaderClass.data.set("guis."+p.getName()+"."+getID()+"."+position+".CANT_BE_TAKEN", options.get(a));
				break;
			case RUNNABLE:
				if(LoaderClass.actions.get(p.getName()+"."+getID()+"."+position+c)==null)
				LoaderClass.actions.put(p.getName()+"."+getID()+"."+position+c,(Runnable) options.get(a));
				break;
			case SENDMESSAGES:
				LoaderClass.data.set("guis."+p.getName()+"."+getID()+"."+position+".SENDMESSAGES"+c, options.get(a));
				break;
			case SENDCOMMANDS:
				LoaderClass.data.set("guis."+p.getName()+"."+getID()+"."+position+".SENDCOMMANDS"+c, options.get(a));
				break;
			}
		}
		LoaderClass.data.set("guis."+p.getName()+"."+getID()+"."+position+".item", item);
	}

	public void addItem(ItemStack item) {
		
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
