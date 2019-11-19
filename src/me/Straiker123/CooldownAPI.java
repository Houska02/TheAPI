package me.Straiker123;

public class CooldownAPI {
	String c;
	public CooldownAPI(String cooldownname) {
		c=cooldownname;
	}
	
	
	public void createCooldown(String player, double length) {
		LoaderClass.col.set(c+"."+player+".start", System.currentTimeMillis());
		LoaderClass.col.set(c+"."+player+".time", length);
		LoaderClass.plugin.f.save();
	}
	
	public boolean expired(String player) {
		return getTimeToExpire(player) < 0;
	}
	/**
	 * 
	 * @return long
	 * If return is -1, it mean cooldown isn't exist
	 */
	public long getStart(String player) {
		if(LoaderClass.col.getString(c+"."+player)!=null)return LoaderClass.col.getLong(c+"."+player+".start");
		return -1;
	}

	/**
	 * 
	 * @return long
	 * If return is -1, it mean cooldown isn't exist
	 */
	public int getTimeToExpire(String player) {
		if(getStart(player) != -1)
		return (int) ((getStart(player)-System.currentTimeMillis())+getCooldown(player));
		return -1;
	}
	

	/**
	 * 
	 * @return double
	 * If return is -1, it mean cooldown isn't exist
	 */
	public double getCooldown(String player) {
		if(LoaderClass.col.getString(c+"."+player)!=null)return LoaderClass.col.getLong(c+"."+player+".time");
		return -1;
	}

	public void removeCooldown(String player) {
		LoaderClass.col.set(c+"."+player, null);
		LoaderClass.plugin.f.save();
	}
}
