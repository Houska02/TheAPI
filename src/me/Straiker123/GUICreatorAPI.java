package me.Straiker123;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.Straiker123.Events.GUIOpenEvent;

public class GUICreatorAPI {
	Player p;
	public GUICreatorAPI(Player s) {
		p=s;
		if(LoaderClass.data.getConfig().getString("guis."+s.getName())!=null)
			id=LoaderClass.data.getConfig().getConfigurationSection("guis."+s.getName()).getKeys(false).size()+"";
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
	
	public Player getPlayer() {
		return p;
	}
	
	int f = 9;
	public void setSize(int size) {
		f = size(size);
	}
	String id = "0";
	public String getID() {
		return id;
	}
	
	public static enum Options{
		CANT_BE_TAKEN,
		CANT_PUT_ITEM,

		RUNNABLE_ON_INV_CLOSE,
		SENDMESSAGES_ON_INV_CLOSE,
		SENDCOMMANDS_ON_INV_CLOSE,
		
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
	
	
	
	private ItemStack createWrittenBook(ItemStack a) {
		Material ms = Material.matchMaterial("WRITABLE_BOOK");
		if(ms==null)ms=Material.matchMaterial("BOOK_AND_QUILL");
		ItemCreatorAPI s = TheAPI.getItemCreatorAPI(ms);
		 if(a.getItemMeta().hasDisplayName())
		 s.setDisplayName(a.getItemMeta().getDisplayName());
		 if(a.getItemMeta().hasLore())s.setLore(a.getItemMeta().getLore());
		 if(!TheAPI.getServerVersion().equals("v1_8_R3")
				 &&!TheAPI.getServerVersion().equals("v1_9_R1")
				 &&!TheAPI.getServerVersion().equals("v1_9_R2")
				 &&!TheAPI.getServerVersion().equals("v1_9_R3")
				 &&!TheAPI.getServerVersion().equals("v1_10_R1")
				 &&!TheAPI.getServerVersion().equals("v1_10_R2")
				 &&!TheAPI.getServerVersion().equals("v1_11_R1")
				 &&!TheAPI.getServerVersion().equals("v1_12_R1")
				 &&!TheAPI.getServerVersion().equals("v1_13_R1")
				 &&!TheAPI.getServerVersion().equals("v1_13_R2"))
		if(a.getItemMeta().hasCustomModelData()) s.setCustomModelData(a.getItemMeta().getCustomModelData());
		 if(!TheAPI.getServerVersion().equals("v1_8_R3")
				 &&!TheAPI.getServerVersion().equals("v1_9_R1")  
				 &&!TheAPI.getServerVersion().equals("v1_9_R2") 
				 &&!TheAPI.getServerVersion().equals("v1_9_R3")
				 &&!TheAPI.getServerVersion().equals("v1_10_R1")
				 &&!TheAPI.getServerVersion().equals("v1_10_R2"))
		 s.setUnbreakable(a.getItemMeta().isUnbreakable());
		 return s.create();
	}
	
	private ItemStack createHead(ItemStack a) {
		ItemCreatorAPI s = TheAPI.getItemCreatorAPI(Material.matchMaterial("PLAYER_HEAD"));
		 if(a.getItemMeta().hasDisplayName())
		 s.setDisplayName(a.getItemMeta().getDisplayName());
		 if(a.getItemMeta().hasLore())s.setLore(a.getItemMeta().getLore());
		 if(!TheAPI.getServerVersion().equals("v1_8_R3")
				 &&!TheAPI.getServerVersion().equals("v1_9_R1")
				 &&!TheAPI.getServerVersion().equals("v1_9_R2")
				 &&!TheAPI.getServerVersion().equals("v1_9_R3")
				 &&!TheAPI.getServerVersion().equals("v1_10_R1")
				 &&!TheAPI.getServerVersion().equals("v1_10_R2")
				 &&!TheAPI.getServerVersion().equals("v1_11_R1")
				 &&!TheAPI.getServerVersion().equals("v1_12_R1")
				 &&!TheAPI.getServerVersion().equals("v1_13_R1")
				 &&!TheAPI.getServerVersion().equals("v1_13_R2"))
		if(a.getItemMeta().hasCustomModelData()) s.setCustomModelData(a.getItemMeta().getCustomModelData());
		 if(!TheAPI.getServerVersion().equals("v1_8_R3")
				 &&!TheAPI.getServerVersion().equals("v1_9_R1")  
				 &&!TheAPI.getServerVersion().equals("v1_9_R2") 
				 &&!TheAPI.getServerVersion().equals("v1_9_R3")
				 &&!TheAPI.getServerVersion().equals("v1_10_R1")
				 &&!TheAPI.getServerVersion().equals("v1_10_R2"))
		 s.setUnbreakable(a.getItemMeta().isUnbreakable());
		 return s.create();
	}
	/**
	 * Set item on position to the gui with options
	 * @param options
	 * CANT_PUT_ITEM - Global, can player put to the gui item from his inventory (true/false)
	 * CANT_BE_TAKEN - Can player take item from gui (true/false)
	 * 
	 * RUNNABLE - Ignoring click type, run everything in runnable (Runnable)
	 * SENDMESSAGES - Ignoring click type, send list of messages to the player (List<String>)
	 * SENDCOMMANDS - Ignoring click type, send list of commands as console (List<String>)
	 */
	public void setItem(int position, ItemStack item, HashMap<Options, Object> options) {
		map.put(position,item);
		for(Options a:options.keySet()) {
			switch(a) {
			case CANT_PUT_ITEM:
				LoaderClass.data.getConfig().set("guis."+p.getName()+"."+getID()+".CANT_PUT_ITEM", options.get(a));
				break;
			case CANT_BE_TAKEN:
				LoaderClass.data.getConfig().set("guis."+p.getName()+"."+getID()+"."+position+".CANT_BE_TAKEN", options.get(a));
				break;
			case RUNNABLE:
				if(LoaderClass.actions.get(p.getName()+"."+getID()+"."+position+".RUNNABLE")==null)
				LoaderClass.actions.put(p.getName()+"."+getID()+"."+position+".RUNNABLE",(Runnable) options.get(a));
				break;
			case SENDMESSAGES:
				LoaderClass.data.getConfig().set("guis."+p.getName()+"."+getID()+"."+position+".SENDMESSAGES", options.get(a));
				break;
			case SENDCOMMANDS:
				LoaderClass.data.getConfig().set("guis."+p.getName()+"."+getID()+"."+position+".SENDCOMMANDS", options.get(a));
				break;
				

			case RUNNABLE_ON_INV_CLOSE:
				if(LoaderClass.actions.get(p.getName()+"."+getID()+".RUNNABLE_ON_INV_CLOSE")==null)
				LoaderClass.actions.put(p.getName()+"."+getID()+".RUNNABLE_ON_INV_CLOSE",(Runnable) options.get(a));
				break;
			case SENDMESSAGES_ON_INV_CLOSE:
				LoaderClass.data.getConfig().set("guis."+p.getName()+"."+getID()+".SENDMESSAGES_ON_INV_CLOSE", options.get(a));
				break;
			case SENDCOMMANDS_ON_INV_CLOSE:
				LoaderClass.data.getConfig().set("guis."+p.getName()+"."+getID()+".SENDCOMMANDS_ON_INV_CLOSE", options.get(a));
				break;

			case RUNNABLE_LEFT_CLICK:
				if(LoaderClass.actions.get(p.getName()+"."+getID()+"."+position)==null)
				LoaderClass.actions.put(p.getName()+"."+getID()+"."+position+".RUNNABLE_LEFT_CLICK",(Runnable) options.get(a));
				break;
			case SENDMESSAGES_LEFT_CLICK:
				LoaderClass.data.getConfig().set("guis."+p.getName()+"."+getID()+"."+position+".SENDMESSAGES_LEFT_CLICK", options.get(a));
				break;
			case SENDCOMMANDS_LEFT_CLICK:
				LoaderClass.data.getConfig().set("guis."+p.getName()+"."+getID()+"."+position+".SENDCOMMANDS_LEFT_CLICK", options.get(a));
				break;

			case RUNNABLE_RIGHT_CLICK:
				if(LoaderClass.actions.get(p.getName()+"."+getID()+"."+position)==null)
				LoaderClass.actions.put(p.getName()+"."+getID()+"."+position+".RUNNABLE_RIGHT_CLICK",(Runnable) options.get(a));
				break;
			case SENDMESSAGES_RIGHT_CLICK:
				LoaderClass.data.getConfig().set("guis."+p.getName()+"."+getID()+"."+position+".SENDMESSAGES_RIGHT_CLICK", options.get(a));
				break;
			case SENDCOMMANDS_RIGHT_CLICK:
				LoaderClass.data.getConfig().set("guis."+p.getName()+"."+getID()+"."+position+".SENDCOMMANDS_RIGHT_CLICK", options.get(a));
				break;

			case RUNNABLE_MIDDLE_CLICK:
				if(LoaderClass.actions.get(p.getName()+"."+getID()+"."+position)==null)
				LoaderClass.actions.put(p.getName()+"."+getID()+"."+position+".RUNNABLE_MIDDLE_CLICK",(Runnable) options.get(a));
				break;
			case SENDMESSAGES_MIDDLE_CLICK:
				LoaderClass.data.getConfig().set("guis."+p.getName()+"."+getID()+"."+position+".SENDMESSAGES_MIDDLE_CLICK", options.get(a));
				break;
			case SENDCOMMANDS_MIDDLE_CLICK:
				LoaderClass.data.getConfig().set("guis."+p.getName()+"."+getID()+"."+position+".SENDCOMMANDS_MIDDLE_CLICK", options.get(a));
				break;

			case RUNNABLE_SHIFT_WITH_LEFT_CLICK:
				if(LoaderClass.actions.get(p.getName()+"."+getID()+"."+position)==null)
				LoaderClass.actions.put(p.getName()+"."+getID()+"."+position+".RUNNABLE_SHIFT_WITH_LEFT_CLICK",(Runnable) options.get(a));
				break;
			case SENDMESSAGES_SHIFT_WITH_LEFT_CLICK:
				LoaderClass.data.getConfig().set("guis."+p.getName()+"."+getID()+"."+position+".SENDMESSAGES_SHIFT_WITH_LEFT_CLICK", options.get(a));
				break;
			case SENDCOMMANDS_SHIFT_WITH_LEFT_CLICK:
				LoaderClass.data.getConfig().set("guis."+p.getName()+"."+getID()+"."+position+".SENDCOMMANDS_SHIFT_WITH_LEFT_CLICK", options.get(a));
				break;

			case RUNNABLE_SHIFT_WITH_RIGHT_CLICK:
				if(LoaderClass.actions.get(p.getName()+"."+getID()+"."+position)==null)
				LoaderClass.actions.put(p.getName()+"."+getID()+"."+position+".RUNNABLE_SHIFT_WITH_RIGHT_CLICK",(Runnable) options.get(a));
				break;
			case SENDMESSAGES_SHIFT_WITH_RIGHT_CLICK:
				LoaderClass.data.getConfig().set("guis."+p.getName()+"."+getID()+"."+position+".SENDMESSAGES_SHIFT_WITH_RIGHT_CLICK", options.get(a));
				break;
			case SENDCOMMANDS_SHIFT_WITH_RIGHT_CLICK:
				LoaderClass.data.getConfig().set("guis."+p.getName()+"."+getID()+"."+position+".SENDCOMMANDS_SHIFT_WITH_RIGHT_CLICK", options.get(a));
				break;
				
			}
		}
		if(item.getType().name().equals("WRITTEN_BOOK")||item.getType().name().equals("BOOK_AND_QUILL"))
			LoaderClass.data.getConfig().set("guis."+p.getName()+"."+getID()+"."+position+".item", createWrittenBook(item));
		else
			if(item.getType().name().equals("LEGACY_SKULL_ITEM")||
					item.getType().name().equals("SKULL_ITEM")
					||item.getType().name().equals("PLAYER_HEAD"))
				LoaderClass.data.getConfig().set("guis."+p.getName()+"."+getID()+"."+position+".item", createHead(item));
			else
		LoaderClass.data.getConfig().set("guis."+p.getName()+"."+getID()+"."+position+".item", item);
	}

	/**
	 * Add item to the first empty slot in gui
	 * @param item
	 * Item in gui, you can use instance geItemCreatorAPI to create item
	 */
	public void addItem(ItemStack item) {
		if(getFirstEmpty()!=-1)
		setItem(getFirstEmpty(), item);
	}
		
	Inventory inv;
	/**
	 *
	 * @return opened gui
	 */
	public Inventory getGUI() {
		return inv;
	}

	/**
	 *
	 * @return boolean is gui full
	 */
	public boolean isFull() {
		return getFirstEmpty()!=-1;
	}
	
	/**
	 * return -1 mean in menu isn't empty slot
	 * @return int where is empty slot (if available)
	 */
	public int getFirstEmpty() {
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
	/**
	 * Add item to the first empty slot in gui with options
	 * @param options
	 * CANT_PUT_ITEM - Global, can player put to the gui item from his inventory (true/false)
	 * CANT_BE_TAKEN - Can player take item from gui (true/false)
	 * 
	 * RUNNABLE - Ignoring click type, run everything in runnable (Runnable)
	 * SENDMESSAGES - Ignoring click type, send list of messages to the player (List<String>)
	 * SENDCOMMANDS - Ignoring click type, send list of commands as console (List<String>)
	 */
	public void addItem(ItemStack item, HashMap<Options, Object> options) {
		if(getFirstEmpty()!=-1)
		setItem(getFirstEmpty(), item, options);
	}
	

	/**
	 * Set item on position to the gui
	 * @param position
	 * Position in gui
	 * @param item
	 * Item in gui, you can use instance ItemCreatorAPI to create item
	 */
	public void setItem(int position, ItemStack item) {
		if(map.get(position)==null)
		map.put(position,item);
		else
			map.replace(position,item);
		if(item.getType().name().equals("WRITTEN_BOOK")||item.getType().name().equals("BOOK_AND_QUILL"))
			LoaderClass.data.getConfig().set("guis."+p.getName()+"."+getID()+"."+position+".item", createWrittenBook(item));
		else
			if(item.getType().name().equals("LEGACY_SKULL_ITEM")||
					item.getType().name().equals("SKULL_ITEM")
					||item.getType().name().equals("PLAYER_HEAD"))
				LoaderClass.data.getConfig().set("guis."+p.getName()+"."+getID()+"."+position+".item", createHead(item));
			else
		LoaderClass.data.getConfig().set("guis."+p.getName()+"."+getID()+"."+position+".item", item);
	}

	/**
	 * Open gui
	 * 
	 */
	public void open() {
		Inventory i = Bukkit.createInventory(p, f,TheAPI.colorize(t));
		for(Integer a : map.keySet()) {
			i.setItem(a, map.get(a));
		}
		LoaderClass.data.getConfig().set("guis."+p.getName()+"."+getID()+".title", t);
		LoaderClass.data.save();
		GUIOpenEvent e = new GUIOpenEvent(p,i,TheAPI.colorize(t));
		Bukkit.getPluginManager().callEvent(e);
		if(!e.isCancelled())
		p.openInventory(i);
		inv=i;
	}
}
