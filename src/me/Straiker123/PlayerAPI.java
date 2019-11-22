package me.Straiker123;

import java.lang.reflect.Constructor;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scoreboard.Scoreboard;

import net.glowstone.entity.GlowPlayer;

public class PlayerAPI {
	Player s;
	public PlayerAPI(Player a) {
		s=a;
	}
	public void teleport(Location loc) {
		if(loc.getWorld()!=null && loc!=null)
		s.teleport(loc);
		else {
			Error.err("teleporting "+s.getName(), "Location is null");
		}
	}
	public void teleport(Entity entity) {
		if(entity!=null)
		s.teleport(entity);
		else {
			Error.err("teleporting "+s.getName(), "Entity is null");
		}
	}
	public void teleport(Location loc,TeleportCause cause) {
		if(loc.getWorld()!=null && loc!=null) {
			if(cause!=null)
		s.teleport(loc,cause);
			else {
				Error.err("teleporting "+s.getName(), "TeleportCause is null");
			}
		}else {
			Error.err("teleporting "+s.getName(), "Location is null");
		}
	}
	public void teleport(Entity entity,TeleportCause cause) {
		if(entity!=null) {
			if(cause!=null)
		s.teleport(entity,cause);
			else {
				Error.err("teleporting "+s.getName(), "TeleportCause is null");
			}
		}else {
			Error.err("teleporting "+s.getName(), "Entity is null");
		}
	}

	public void setFreeze(boolean freeze) {
		s.setAI(!freeze);
	}
	public boolean isFreezen() {
		return !s.hasAI();
	}
	
	public static enum InvseeType {
		INVENTORY,
		ENDERCHEST
	}
	public void invsee(Player target, InvseeType type) {
		if(target==null) {
			Error.err("opening inventory to "+s.getName(), "Target is null");
			return;
		}
		if(type==null) {
			Error.err("opening inventory to "+s.getName(), "InvseeType is null");
			return;
		}
		try {
		switch(type) {
		case INVENTORY:
			s.openInventory(target.getInventory()); 
			break;
		case ENDERCHEST:
			s.openInventory(target.getEnderChest());
			break;
		}
		}catch(Exception e) {
			Error.err("opening inventory to "+s.getName(), "Uknown");
		}
	}
	public void msg(String message) {
		try {
		s.sendMessage(TheAPI.colorize(message));
		}catch(Exception e) {
			Error.err("sending message to "+s.getName(), "Message is null");
		}
	}
	@SuppressWarnings("deprecation")
	public void setHealth(double health) {
		try {
		if(s.getMaxHealth()<health) {
			s.setMaxHealth(health);
		}
		s.setHealth(health);
		}catch(Exception e) {
			Error.err("setting health on "+s.getName(), "Health limit reached");
		}
	}
	
	public void setFood(int food) {
		try {
		s.setFoodLevel(food);
		}catch(Exception e) {
			Error.err("setting food on "+s.getName(), "Food limit reached");
		}
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

	public void takeExp(int exp) {
		int take = (int)s.getExp();
		if(take-exp < 0) {
			s.setExp(take);
		}else
			s.setExp(take-exp);
	}
	
	@SuppressWarnings("deprecation")
	public void resetMaxHealth() {
		s.setMaxHealth(20);
	}

	public void resetExp() {
		s.setExp(0);
	}
	public void resetLevel() {
		s.setLevel(0);
	}
	
	@SuppressWarnings("deprecation")
	public void sendTitle(String firstLine, String nextLine) {
		try {
		s.sendTitle(TheAPI.colorize(firstLine), TheAPI.colorize(nextLine));
		}catch(Exception e) {
			Error.err("sending title to "+s.getName(), "Line is null");
		}
	}
	
	public void sendBossBar(String line, double progress, double timeToExpire) {
		try {
			if(timeToExpire<0)timeToExpire=0;
		BossBar a = Bukkit.createBossBar(TheAPI.colorize(line), BarColor.GREEN, BarStyle.SEGMENTED_20);
		if(progress<0)progress=0;
		if(progress>1)progress=1;
		a.setProgress(progress);
		a.addPlayer(s);
		Bukkit.getScheduler().runTaskLater(LoaderClass.plugin, new Runnable() {

			@Override
			public void run() {
				a.removeAll();
			}
			
		},(long) (20*timeToExpire));
		}catch(Exception e) {
			Error.err("sending bossbar to "+s.getName(), "Line is null");
		}
	}
   public void sendActionBar(String line) {
	   if(TheAPI.getServerVersion().equals("glowstone")) {
			try {
				   ((GlowPlayer) s).sendActionBar(TheAPI.colorize(line));
					return;
				}catch (Exception e) {
					TheAPI.getConsole().sendMessage(TheAPI.colorize("&bTheAPI&7: &4Error when sending ActionBar, server version: "+TheAPI.getServerVersion()));
				}
				}
	     Class<?> PACKET_PLAYER_CHAT_CLASS = null, ICHATCOMP = null, CHATMESSAGE = null,
       CHAT_MESSAGE_TYPE_CLASS = null;
     Constructor<?> PACKET_PLAYER_CHAT_CONSTRUCTOR = null, CHATMESSAGE_CONSTRUCTOR = null;
     Object CHAT_MESSAGE_TYPE_ENUM_OBJECT = null;
	   boolean useByte = false;
	     try {
	       PACKET_PLAYER_CHAT_CLASS = Packets.getNMSClass("PacketPlayOutChat");
	       ICHATCOMP = Packets.getNMSClass("IChatBaseComponent");
	       try {
	         CHAT_MESSAGE_TYPE_CLASS = Packets.getNMSClass("ChatMessageType");
	         CHAT_MESSAGE_TYPE_ENUM_OBJECT = CHAT_MESSAGE_TYPE_CLASS.getEnumConstants()[2];
	 
	         PACKET_PLAYER_CHAT_CONSTRUCTOR = PACKET_PLAYER_CHAT_CLASS.getConstructor(ICHATCOMP,
	             CHAT_MESSAGE_TYPE_CLASS);
	       } catch (NoSuchMethodException e) {
	         PACKET_PLAYER_CHAT_CONSTRUCTOR = PACKET_PLAYER_CHAT_CLASS.getConstructor(ICHATCOMP, byte.class);
	         useByte = true;
	       }
	       CHATMESSAGE = Packets.getNMSClass("ChatMessage");
	       CHATMESSAGE_CONSTRUCTOR = CHATMESSAGE.getConstructor(String.class, Object[].class);
	     } catch (Exception e) {
	     }
	   
     try {
       Object icb = CHATMESSAGE_CONSTRUCTOR.newInstance(TheAPI.colorize(line), new Object[0]);
       Object packet;
       if (useByte)
         packet = PACKET_PLAYER_CHAT_CONSTRUCTOR.newInstance(icb, (byte) 2);
       else
         packet = PACKET_PLAYER_CHAT_CONSTRUCTOR.newInstance(icb, CHAT_MESSAGE_TYPE_ENUM_OBJECT);
       Packets.sendPacket(s, packet);
     } catch (Exception e) {
			TheAPI.getConsole().sendMessage(TheAPI.colorize("&bTheAPI&7: &4Error when sending ActionBar, server version: "+TheAPI.getServerVersion()));
     }
   }
	
	public void giveLevel(int level) {
		s.setLevel(s.getLevel()+level);
	}
	
	public List<Entity> getNearbyEntities(double x, double y, double z){
		return s.getNearbyEntities(x, y, z);
	}

	public void closeOpenInventory() {
		s.getOpenInventory().close();
	}
	
	public String getAddress() {
	try {
		return TheAPI.getPunishmentAPI().getIP(s.getName());
	}catch(Exception e) {
		Error.err("getting ip address of "+s.getName(), "Address is null");
		return null;
	}
	}
	
	public void setScoreboard(Scoreboard sb) {
		try {
		if(sb!=null)
		s.setScoreboard(sb);
		else
			s.setScoreboard(s.getServer().getScoreboardManager().getNewScoreboard());
		}catch(Exception e) {
			Error.err("setting scoreboard on "+s.getName(), "Scoreboard is null");
		}
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
	public void setMaxAir() {
		 s.setRemainingAir(s.getMaximumAir());
	}
	public void setAir(int air) {
		 s.setRemainingAir(air);
	}
	public void setGodOnTime(int time) {
		 s.setNoDamageTicks(time*20);
	}
	@SuppressWarnings("deprecation")
	public ItemStack getItemInHand() {
		return s.getItemInHand();
	}
	/**
	 * Kick player from serveer with reason
	 */
	public void kick(String reason) {
		if(reason==null)reason="Uknown";
		 s.kickPlayer(TheAPI.colorize(reason));
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
