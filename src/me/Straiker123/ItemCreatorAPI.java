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
	ItemStack a;
	public ItemCreatorAPI(ItemStack icon) {
		if(icon==null)a=new ItemStack(Material.STONE);
		else
		a=icon;
	}
	
	public Material getMaterial() {
		return a.getType();
	}
	
	public void setMaterial(String byName) {
		if(Material.matchMaterial(byName)!=null)a=new ItemStack(Material.matchMaterial(byName));
		if(a==null)a=new ItemStack(Material.STONE);
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
	public void addEnchantment(String e, int level) {
		if(e!= null)enchs.put(TheAPI.getEnchantmentAPI().getByName(e), level);
	}
	int s = 1;
	public void setAmount(int amount) {
		if(amount > 64)amount=64;
		s=amount;
	}
	
	public void setLore(List<String> lore) {
		if(lore!=null)
			for(String s:lore)
				addLore(s);
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
		if(TheAPI.isNewVersion()
				 &&!TheAPI.getServerVersion().equals("v1_13_R1"))
		w.put(a, s);
	}
	public void addAttrubuteModifiers(HashMap<Attribute, AttributeModifier> s) {
		if(TheAPI.isNewVersion()
							 &&!TheAPI.getServerVersion().equals("v1_13_R1"))
		w=s;
	}
	int dur=-1;
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
			i= a;
			a.setDurability((short)type.ordinal());
		}else {
			i=a;
			if(dur!=-1)
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
					if(model != -1 && TheAPI.isNewVersion()
							 &&!TheAPI.getServerVersion().equals("v1_13_R1")
							 &&!TheAPI.getServerVersion().equals("v1_13_R2"))
					m.setCustomModelData(model);
					 if(!TheAPI.getServerVersion().equals("v1_8_R3")
								&& !TheAPI.getServerVersion().equals("v1_8_R1")
								&& !TheAPI.getServerVersion().equals("v1_8_R2")
							 &&!TheAPI.getServerVersion().equals("v1_9_R1")  
							 &&!TheAPI.getServerVersion().equals("v1_9_R2") 
							 &&!TheAPI.getServerVersion().equals("v1_9_R3")
							 &&!TheAPI.getServerVersion().equals("v1_10_R1")
							 &&!TheAPI.getServerVersion().equals("v1_10_R2"))
					m.setUnbreakable(unb);
					if(map != null && !map.isEmpty())
					for(ItemFlag f: map)
					m.addItemFlags(f);
					if(w!=null && !w.isEmpty() 
							&& TheAPI.isNewVersion()
							 &&!TheAPI.getServerVersion().equals("v1_13_R1"))
					m.setAttributeModifiers((Multimap<Attribute, AttributeModifier>) w);	
					if(owner!=null)
					m.setOwner(owner);
					i.setItemMeta(m);
			}else{
			ItemMeta m=i.getItemMeta();
				if(name!=null)
				m.setDisplayName(name);
				if(lore!=null && !lore.isEmpty())m.setLore(lore);
				if(model != -1 && TheAPI.isNewVersion()
						 &&!TheAPI.getServerVersion().equals("v1_13_R1")
						 &&!TheAPI.getServerVersion().equals("v1_13_R2"))
				m.setCustomModelData(model);
				 if(!TheAPI.getServerVersion().equals("v1_8_R3")
							&& !TheAPI.getServerVersion().equals("v1_8_R1")
							&& !TheAPI.getServerVersion().equals("v1_8_R2")
						 &&!TheAPI.getServerVersion().equals("v1_9_R1")  
						 &&!TheAPI.getServerVersion().equals("v1_9_R2") 
						 &&!TheAPI.getServerVersion().equals("v1_9_R3")
						 &&!TheAPI.getServerVersion().equals("v1_10_R1")
						 &&!TheAPI.getServerVersion().equals("v1_10_R2"))
				m.setUnbreakable(unb);
				if(map != null && !map.isEmpty())
				for(ItemFlag f: map)
				m.addItemFlags(f);
				if(w!=null && !w.isEmpty() && !TheAPI.getServerVersion().equals("glowstone") 
						&& TheAPI.isNewVersion()
						 &&!TheAPI.getServerVersion().equals("v1_13_R1"))
				m.setAttributeModifiers((Multimap<Attribute, AttributeModifier>) w);
				i.setItemMeta(m);

			
			}
		
		
		return i;
	}
	
}
