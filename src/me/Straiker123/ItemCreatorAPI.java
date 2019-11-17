package me.Straiker123;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.material.MaterialData;

import com.google.common.collect.Multimap;

@SuppressWarnings("deprecation")
public class ItemCreatorAPI {
	Material a;
	public ItemCreatorAPI(Material icon) {
		if(icon==null)a=Material.STONE;
		else
		a=icon;
	}
	
	public Material getMaterial() {
		return a;
	}
	
	public void setMaterial(String byName) {
		if(Material.matchMaterial(byName)!=null)a=Material.matchMaterial(byName);
		if(a==null)a=Material.STONE;
	}
	String name;
	public void setDisplayName(String newName) {
		if(newName!=null)
		name=TheAPI.colorize(newName);
	}
	
	List<String> lore = new ArrayList<String>();
	public void addLore(String line) {
		lore.add(TheAPI.colorize(line));
	}
	
	String owner;
	public void setOwner(String owner) {
		if(owner!=null)
			this.owner=owner;
	}
	
	HashMap<Enchantment, Integer> enchs = new HashMap<Enchantment, Integer>();
	public void addEnchantment(Enchantment e, int level) {
		if(e!= null)enchs.put(e, level);
	}
	
	int s = 1;
	public void setAmount(int amount) {
		if(amount > 64)amount=64;
		s=amount;
	}
	
	public void addLore(List<String> lore) {
		this.lore=lore;
	}
	
	int model = -1;
	public void setCustomModelData(int i) {
		model=i;
	}
	boolean unb;
	public void setUnbreakable(boolean unbreakable) {
		unb=unbreakable;
	}
	SkullType type = null;
	public void setSkullType(SkullType t) {
		if(t!=null)
			type=t;
	}
	private List<ItemFlag> map = new ArrayList<ItemFlag>();
	public void addItemFlag(ItemFlag itemflag) {
		if(itemflag!=null)
		map.add(itemflag);
	}
	HashMap<Attribute, AttributeModifier> w = new HashMap<Attribute, AttributeModifier>();
	public void addAttrubuteModifier(Attribute a, AttributeModifier s) {
		w.put(a, s);
	}
	public void addAttrubuteModifiers(HashMap<Attribute, AttributeModifier> s) {
		w=s;
	}
	int dur;
	public void setDurability(int amount) {
		dur=amount;
	}
	
	MaterialData data = null;
	public void setMaterialData(MaterialData data) {
		this.data=data;
	}
	
	@SuppressWarnings("unchecked")
	public ItemStack create() {
		
		
		ItemStack i = null;
		if(type!=null) {
			i= new ItemStack(a,s,(short)type.ordinal());
		}else {
			i=new ItemStack(a,s);
			i.setDurability((short)dur);
		}
		if(data != null)
		i.setData(data);
		if(enchs != null && !enchs.isEmpty())i.addUnsafeEnchantments(enchs);
			if(type!=null) {
				SkullMeta m=(SkullMeta)i.getItemMeta();
				if(name!=null)
					m.setDisplayName(name);
					if(lore!=null && !lore.isEmpty())m.setLore(lore);
					if(model != -1)
					m.setCustomModelData(model);
					m.setUnbreakable(unb);
					if(map != null && !map.isEmpty())
					for(ItemFlag f: map)
					m.addItemFlags(f);
					if(w!=null && !w.isEmpty())
					m.setAttributeModifiers((Multimap<Attribute, AttributeModifier>) w);	
					if(owner!=null)
					m.setOwner(owner);
					i.setItemMeta(m);
			}else{
			ItemMeta m=i.getItemMeta();
			if(name!=null)
				m.setDisplayName(name);
				if(lore!=null && !lore.isEmpty())m.setLore(lore);
				if(model != -1)
				m.setCustomModelData(model);
				m.setUnbreakable(unb);
				if(map != null && !map.isEmpty())
				for(ItemFlag f: map)
				m.addItemFlags(f);
				if(w!=null && !w.isEmpty())
				m.setAttributeModifiers((Multimap<Attribute, AttributeModifier>) w);
				i.setItemMeta(m);

			
			}
		
		
		return i;
	}
	
}
