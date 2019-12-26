package me.Straiker123;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

public class EnchantmentAPI {
	public Enchantment[] values() {
		return Enchantment.values();
	}
	public List<Enchantment> valuesInList() {
		List<Enchantment> a = new ArrayList<Enchantment>();
		for(Enchantment e : Enchantment.values())
			a.add(e);
		return a;
	}
	
	private String replace(String s) {
		return s.replace("%SHARPNESS%", "DAMAGE_ALL").replace("%FIRE%", "ARROW_FIRE")
				.replace("%INFINITY%", "ARROW_INFINITE").replace("%FIREASPECT%", "FIRE_ASPECT")
				.replace("%RESPIRATION%", "OXYGEN").replace("%LOOTING%", "LOOT_BONUS_MOBS")
				.replace("%FORTUNE%", "LOOT_BONUS_BLOCKS").replace("%UNBREAKING%", "DURABILITY")
				.replace("%AQUA_AFFINITY%", "WATER_WORKER").replace("%PROTECTION%", "PROTECTION_ENVIRONMENTAL")
				.replace("%BLAST_PROTECTION%", "PROTECTION_EXPLOSIONS").replace("%FEATHER_FALLING%", "PROTECTION_FALL")
				.replace("%FIRE_PROTECTION%", "PROTECTION_FIRE").replace("%PROJECTILE_PROTECTION%", "PROTECTION_PROJECTILE")
				.replace("%CURSE_OF_VANISHING%", "VANISHING_CURSE").replace("%CURSE_OF_BINDING%", "BINDING_CURSE")
				.replace("%SMITE%", "DAMAGE_UNDEAD").replace("%POWER%", "ARROW_DAMAGE").replace("%ALLDAMAGE%", "DAMAGE_ALL").replace("%DAMAGEALL%", "DAMAGE_ALL")
				.replace("%BANE_OF_ARTHROPODS%", "DAMAGE_ARTHROPODS").replace("%EFFICIENCY%", "DIG_SPEED").replace("%ALL_DAMAGE%", "DAMAGE_ALL")
				.replace("%PUNCH%", "ARROW_KNOCKBACK").replace("%LOOTMOBS%", "LOOT_BONUS_MOBS").replace("%LOOTBLOCKS%", "LOOT_BONUS_BLOCKS")
				.replace("%UNBREAKABLE%", "UNBREAKABLE");
	}
	
	public boolean isEnchantment(String string) {
		return getByName(string) != null;
	}
	
	@SuppressWarnings("deprecation")
	public Enchantment getByName(String enchant) {
	try {
		if(Enchantment.getByName(enchant.toUpperCase())==null)
			return Enchantment.getByName(replace("%"+enchant.toUpperCase()+"%"));
		return Enchantment.getByName(enchant.toUpperCase());
	}catch(Exception e) {
	return null;
	}
	}
	
	public Enchantment valueOf(String string) {
		return getByName(string);
	}
	
	
	public List<String> getEnchantments(ItemStack item) {
		List<String> list = new ArrayList<String>();
		return list;
	}
	
	/**
	 * In this API you can create your own enchantment,
	 * @return EnchantmentCreatorAPI
	 */
	public EnchantmentCreatorAPI getEnchantmentCreatorAPI() {
		return new EnchantmentCreatorAPI();
	}
	
	public boolean registerEnchantment(Enchantment e) {
		   boolean registered = true;
		    try {
		        Field f = Enchantment.class.getDeclaredField("acceptingNew");
		        f.setAccessible(true);
		        f.set(null, true);
		        Enchantment.registerEnchantment(e);
		    } catch (Exception ea) {
		        registered = false;
		    }
		    return registered;
	}
}
