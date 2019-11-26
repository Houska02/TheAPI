package me.Straiker123;

import org.bukkit.enchantments.Enchantment;

public class EnchantmentAPI {
	public Enchantment[] values() {
		return Enchantment.values();
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
				.replace("%PUNCH%", "ARROW_KNOCKBACK").replace("%LOOTMOBS%", "LOOT_BONUS_MOBS").replace("%LOOTBLOCKS%", "LOOT_BONUS_BLOCKS");
	}
	
	public boolean isEnchantment(String string) {
		return getByName(string) != null;
	}
	
	@SuppressWarnings("deprecation")
	public Enchantment getByName(String enchant) {
	try {
		return Enchantment.getByName(replace("%"+enchant.toUpperCase()+"%"));
	}catch(Exception e) {
	return null;
	}
	}
	
	public Enchantment valueOf(String string) {
		return getByName(string);
	}
	
	public void registerEnchantment(Enchantment e) {
		if(e!=null)
		Enchantment.registerEnchantment(e);
	}
}
