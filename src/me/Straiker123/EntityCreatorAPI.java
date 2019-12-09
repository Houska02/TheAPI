package me.Straiker123;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

public class EntityCreatorAPI {
	EntityType t;
	public EntityCreatorAPI(EntityType type) {
		t=type;
	}
	public static enum AttackDamageType{
		MINIMAL,
		MAXIMAL
	}
	double min;
	double max;
	double set;
	
	/**
	 * This method rewrite whole attack damage
	 *  but will not register additional damage to the sword, bow, etc.
	 */
	public void setAttackDamage(double amount) {
		if(amount>0)
			set=amount;
	}
	
	public void addAttackDamage(AttackDamageType type, double amount) {
		if(amount>0)
		switch(type) {
		case MAXIMAL:
			max=amount;
			break;
		case MINIMAL:
			min=amount;
			break;
		}
	}
	boolean ai=true;
	public void setAI(boolean setAI) {
		ai=setAI;
	}
	boolean collidable=true;
	public void setCollidable(boolean collidable) {
		this.collidable=collidable;
	}
	
	String name;
	public void setCustomName(String name) {
		this.name=TheAPI.colorize(name);
	}
	boolean visible=true;
	public void setCustomNameVisible(boolean visible) {
		this.visible=visible;
	}
	double hp;
	public void setHealth(double amount) {
		if(amount>0)
		hp=amount;
	}
	boolean items=true;
	public void setCanPickupItems(boolean can) {
		items = can;
	}
	boolean gravity=true;
	public void setGravity(boolean setGravity) {
		gravity=setGravity;
	}
	boolean glow;
	public void setGlowing(boolean setGlow) {
		glow=setGlow;
	}
	boolean isGod;
	public void setGod(boolean setGod) {
		setGod=isGod;
	}
	int god;
	public void setGodOnTime(int time) {
		god=time;
	}
	Entity entity;
	public void setPassenger(Entity passenger) {
		entity=passenger;
	}
	boolean silent;
	public void setSilent(boolean setSilent) {
		silent=setSilent;
	}
	
	@SuppressWarnings("deprecation")
	public void summonEntity(Location l) {
		LivingEntity e = (LivingEntity) l.getWorld().spawnEntity(l, t);
		if(hp>0) {
		e.setMaxHealth(hp);
		e.setHealth(hp);
		}
		e.setCustomNameVisible(visible);
		
		e.setCustomName(name);
		if(set==0) {
		if(min>0)
		e.addScoreboardTag("damage:min:"+min);
		if(max>0)
		e.addScoreboardTag("damage:max:"+max);
		}else {
			e.addScoreboardTag("damage:set:"+set);
		}
		e.setAI(ai);
		e.setCollidable(collidable);
		e.setCanPickupItems(items);
		e.setGravity(gravity);
		e.setGlowing(glow);
		e.setInvulnerable(isGod);
		if(god>0)
		e.setNoDamageTicks(20*god);
		if(entity!=null)
		e.setPassenger(entity);
		e.setSilent(silent);
	}
}
