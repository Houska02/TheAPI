package me.Straiker123;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

import org.bukkit.entity.Player;

import net.glowstone.entity.GlowPlayer;
import net.md_5.bungee.api.chat.ComponentBuilder;

public class TabListAPI {

	public void setTabListName(Player p, String name) {
		p.setPlayerListName(TheAPI.colorize(name));
	}

	public void setHeaderFooter(Player p, String header, String footer) {
		if(TheAPI.getServerVersion().equals("glowstone")) {
		try {
		GlowPlayer s = (GlowPlayer) p;
		s.setPlayerListHeaderFooter(new ComponentBuilder(TheAPI.colorize(header)).create(), new ComponentBuilder(TheAPI.colorize(footer)).create());
			return;
		}catch (Exception e) {
			TheAPI.getConsole().sendMessage(TheAPI.colorize("&bTheAPI&7: &4Error when sending footer, server version: "+TheAPI.getServerVersion()));
		}
		}
		try {
			Object tabHeader = getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", new Class[] { String.class }).invoke(null, new Object[] { "{\"text\":\"" + TheAPI.colorize(header) + "\"}" });
			Object tabFooter = getNMSClass("IChatBaseComponent")
					.getDeclaredClasses()[0].getMethod("a", new Class[] { String.class }).invoke(null, new Object[] { "{\"text\":\"" + TheAPI.colorize(footer) + "\"}" });

			Constructor<?> titleConstructor = getNMSClass("PacketPlayOutPlayerListHeaderFooter").getConstructor(new Class[0]);
			Object packet = titleConstructor.newInstance(new Object[0]);
			Field aField = null;
			Field bField = null;
			if (TheAPI.getServerVersion().equals("v1_14_R1") || TheAPI.getServerVersion().equals("v1_13_R2")) {
			    aField = packet.getClass().getDeclaredField("header");
			    bField = packet.getClass().getDeclaredField("footer");
			} else {
			   aField = packet.getClass().getDeclaredField("a");
			   bField = packet.getClass().getDeclaredField("b");
			}
			   aField.setAccessible(true);
			   aField.set(packet, tabHeader);
			bField.setAccessible(true);
			bField.set(packet, tabFooter);
			sendPacket(p,packet);
			} catch (Exception e) {
				TheAPI.getConsole().sendMessage(TheAPI.colorize("&bTheAPI&7: &4Error when sending footer, server version: "+TheAPI.getServerVersion()));
			}
	}

		public static Object getNMSPlayer(Player p) throws Exception {
		       return p.getClass().getMethod("getHandle", new Class[0]).invoke(p, new Object[0]);
		}

		private static Class<?> getNMSClass(String name) {
		     try {
		         return Class.forName("net.minecraft.server." + TheAPI.getServerVersion() + "." + name);
		     } catch (ClassNotFoundException e) {
					TheAPI.getConsole().sendMessage(TheAPI.colorize("&bTheAPI&7: &4Error when finding class 'net.minecraft.server."+TheAPI.getServerVersion() + "." + name+"', server version: "+TheAPI.getServerVersion()));
			         return null;
		     }
		}

		private static void sendPacket(Player player, Object packet) {
		     try {
		         Object handle = getNMSPlayer(player);
		         Object playerConnection = handle.getClass().getField("playerConnection").get(handle);
		         playerConnection.getClass().getMethod("sendPacket", new Class[] { getNMSClass("Packet") }).invoke(playerConnection, new Object[] { packet });
		     } catch (Exception e) {
					TheAPI.getConsole().sendMessage(TheAPI.colorize("&bTheAPI&7: &4Error when sending packets to player "+player.getName()+", server version: "+TheAPI.getServerVersion()));
		     }
		}
}
