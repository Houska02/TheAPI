package me.Straiker123;

import org.bukkit.enchantments.Enchantment;

public class EnchantmentAPI {
	public Enchantment[] values() {
		return Enchantment.values();
	}
	
	private String replace(String s) {
		return s.replaceAll("%SHARPNESS%", "DAMAGE_ALL").replaceAll("%FIRE%", "ARROW_FIRE")
				.replaceAll("%INFINITY%", "ARROW_INFINITE").replaceAll("%FIREASPECT%", "FIRE_ASPECT")
				.replaceAll("%RESPIRATION%", "OXYGEN").replaceAll("%LOOTING%", "LOOT_BONUS_MOBS")
				.replaceAll("%FORTUNE%", "LOOT_BONUS_BLOCKS").replaceAll("%UNBREAKING%", "DURABILITY")
				.replaceAll("%AQUA_AFFINITY%", "WATER_WORKER").replaceAll("%PROTECTION%", "PROTECTION_ENVIRONMENTAL")
				.replaceAll("%BLAST_PROTECTION%", "PROTECTION_EXPLOSIONS").replaceAll("%FEATHER_FALLING%", "PROTECTION_FALL")
				.replaceAll("%FIRE_PROTECTION%", "PROTECTION_FIRE").replaceAll("%PROJECTILE_PROTECTION%", "PROTECTION_PROJECTILE")
				.replaceAll("%CURSE_OF_VANISHING%", "VANISHING_CURSE").replaceAll("%CURSE_OF_BINDING%", "BINDING_CURSE")
				.replaceAll("%SMITE", "DAMAGE_UNDEAD%").replaceAll("%POWER%", "ARROW_DAMAGE").replaceAll("ALLDAMAGE", "DAMAGE_ALL").replaceAll("DAMAGEALL", "DAMAGE_ALL")
				.replaceAll("%BANE_OF_ARTHROPODS%", "DAMAGE_ARTHROPODS").replaceAll("%EFFICIENCY%", "DIG_SPEED").replaceAll("%ALL_DAMAGE%", "DAMAGE_ALL")
				.replaceAll("%PUNCH%", "ARROW_KNOCKBACK").replaceAll("%LOOTMOBS%", "LOOT_BONUS_MOBS").replaceAll("%LOOTBLOCKS%", "LOOT_BONUS_BLOCKS");
	}
	
	public boolean isEnchantment(String string) {
		if(string != null) {
		return !replace("%"+string.toUpperCase()+"%").contains("%");
		}
		return false;
	}
	
	@SuppressWarnings("deprecation")
	public Enchantment getByName(String string) {
	if(isEnchantment(string)) {
		return Enchantment.getByName(replace("%"+string.toUpperCase()+"%"));
	}
	return null;
	}
	
	public Enchantment valueOf(String string) {
		return getByName(string);
	}
	
	public void registerEnchantment(Enchantment e) {
		Enchantment.registerEnchantment(e);
	}
}
