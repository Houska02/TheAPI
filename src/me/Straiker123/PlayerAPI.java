package me.Straiker123;

import java.util.Collection;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MainHand;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.Scoreboard;

public class PlayerAPI {
	Player s;
	public PlayerAPI(Player a) {
		s=a;
	}
	public void teleport(Location loc) {
		s.teleport(loc);
	}
	public void teleport(Entity entity) {
		s.teleport(entity);
	}
	public void teleport(Location loc,TeleportCause cause) {
		s.teleport(loc,cause);
	}
	public void teleport(Entity entity,TeleportCause cause) {
		s.teleport(entity,cause);
	}
	
	public static enum InvseeType {
		INVENTORY,
		ENDERCHEST
	}
	public void invsee(Player target, InvseeType type) {
		switch(type) {
		case INVENTORY:
			s.openInventory(target.getInventory());
			break;
		case ENDERCHEST:
			s.openInventory(target.getEnderChest());
			break;
		}
	}
	public void msg(String message) {
		s.sendMessage(TheAPI.colorize(message));
	}
	@SuppressWarnings("deprecation")
	public void setHealth(double health) {
		if(s.getMaxHealth()<health) {
			s.setMaxHealth(health);
		}
		s.setHealth(health);
	}
	
	public void setFood(int food) {
		s.setFoodLevel(food);
	}

	public void setFly(boolean allowFlying, boolean enableFlying) {
		if(allowFlying) {
			LoaderClass.data.set("data."+s.getName()+".fly",true);
			LoaderClass.plugin.a.save();
		s.setAllowFlight(true);
		s.setFlying(enableFlying);
		}else {
			LoaderClass.data.set("data."+s.getName()+".fly",false);
			LoaderClass.plugin.a.save();
			s.setFlying(enableFlying);
			s.setAllowFlight(false);
		}
	}
	public boolean allowedFly() {
		return LoaderClass.data.getBoolean("data."+s.getName()+".fly");
	}
	
	public Inventory getInventory() {
		return s.getInventory();
	}

	public float getExp() {
		return s.getExp();
	}
	public int getLevel() {
		return s.getLevel();
	}
	public int getExpToLevel() {
		return s.getExpToLevel();
	}
	public void giveExp(int exp) {
		s.giveExp(exp);
	}
	
	public void giveExpToLever(int exp) {
		s.giveExpLevels(exp);
	}
	
	public void giveLevel(int level) {
		s.setLevel(getLevel()+level);
	}
	
	public Collection<Entity> getNearbyEntities(double x, double y, double z){
		return s.getNearbyEntities(x, y, z);
	}
	
	public void setLevel(int level) {
		s.setLevel(level);
	}
	
	public void addPotionEffect(PotionEffect e) {
		if(e!=null) {
			s.addPotionEffect(e);
		}
	}
	public void addPotionEffects(Collection<PotionEffect> effects) {
		for(PotionEffect e: effects) {
			if(e!=null)
		s.addPotionEffect(e);}
	}
	public void removePotionEffect(PotionEffectType e) {
		if(e!=null) {
			s.removePotionEffect(e);
		}
	}
	public boolean hasPotionEffect(PotionEffectType e) {
		if(e!=null) {
			return s.hasPotionEffect(e);
		}
		return false;
	}
	
	public void closeInventory() {
		s.getOpenInventory().close();
	}

	public void damage(double damage) {
		s.damage(damage);
	}
	public void hurt(double damage) {
		s.damage(damage);
	}
	
	public String getAddress() {
		return TheAPI.getPunishmentAPI().getIP(s.getName());
	}
	
	public void setScoreboard(Scoreboard sb) {
		s.setScoreboard(sb);
	}
	
	public Location getLocation() {
		return s.getLocation();
	}
	/**
	 * int from -10 to 10
	 * @param speed
	 */
	public void setFlySpeed(int speed) {
		if(speed<-10)speed=-10;
		if(speed>10)speed=10;
		s.setFlySpeed(speed/10);
	}
	

	/**
	 * int from -10 to 10
	 * @param speed
	 */
	public void setWalkSpeed(int speed) {
		if(speed<-10)speed=-10;
		if(speed>10)speed=10;
		s.setWalkSpeed(speed/10);
	}

	public int getFlySpeed() {
		return (int)s.getFlySpeed()*10;
	}
	public int getWalkSpeed() {
		return (int)s.getWalkSpeed()*10;
	}
	
	public Inventory getEnderChest() {
		return s.getEnderChest();
	}

	public GameMode getGameMode() {
		return s.getGameMode();
	}
	public double getHealth() {
		return s.getHealth();
	}
	public int getFood() {
		return s.getFoodLevel();
	}
	public int getAir() {
		return s.getRemainingAir();
	}
	public int getMaxAir() {
		return s.getMaximumAir();
	}
	public void setMaxAir() {
		 s.setRemainingAir(getAir());
	}
	public void setAir(int air) {
		 s.setRemainingAir(air);
	}
	public void setGodOnTime(int time) {
		 s.setNoDamageTicks(time*20);
	}

	public Entity getKiller() {
		return s.getKiller();
	}
	@SuppressWarnings("deprecation")
	public ItemStack getItemInHand() {
		return s.getItemInHand();
	}
	public MainHand getMainHand() {
		return s.getMainHand();
	}

	public int getMaxFireTicks() {
		return s.getMaxFireTicks();
	}
	
	/**
	 * Set int -1 to set fire off
	 * @param fire
	 */
	public void setFireTicks(int fire) {
		 s.setFireTicks(fire);
	}
	
	public InventoryView getOpenInventory() {
		return s.getOpenInventory();
	}
	
	/**
	 * Kick player from serveer with reason
	 */
	public void kick(String reason) {
		if(reason==null)reason="Uknown";
		 s.kickPlayer(TheAPI.colorize(reason));
	}
	
	public boolean hasPerm(String permission) {
		return s.hasPermission(permission);
	}
	
	public World getWorld() {
		return s.getWorld();
	}
	
	public int getSleepTicks() {
		return s.getSleepTicks();
	}

	public void setSleepingIgnored(boolean ignore) {
		s.setSleepingIgnored(ignore);
	}
	public void isSleepingIgnored() {
		s.isSleepingIgnored();
	}
	
	public PotionEffect getPotionEffect(PotionEffectType e) {
		if(e!=null) {
			return s.getPotionEffect(e);
		}
		return null;
	}
	
	public Collection<PotionEffect> getPotionEffects() {
			return s.getActivePotionEffects();
	}
	
	
	public void setGod(boolean enable) {
			LoaderClass.data.set("data."+s.getName()+".god",enable);
			LoaderClass.plugin.a.save();
			s.setInvulnerable(enable);
	}
	public boolean allowedGod() {
		return LoaderClass.data.getBoolean("data."+s.getName()+".god");
	}
	
}