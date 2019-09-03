package reaperz.plugin.utils;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import net.minecraft.server.v1_14_R1.Packet;
import net.minecraft.server.v1_14_R1.PacketPlayOutEntityDestroy;
import net.minecraft.server.v1_14_R1.PacketPlayOutNamedEntitySpawn;
import net.minecraft.server.v1_14_R1.PacketPlayOutPlayerInfo;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_14_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import reaperz.plugin.main.main;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;

public class Nick {

    public static HashMap<Player, String> current = new HashMap<>();
    public static Field name;

    public static boolean isNicked(Player p) {
        return current.containsKey(p);
    }

    public static void nickPlayer(Player p, String nick) {
        CraftPlayer cp = (CraftPlayer) p;
        current.put(p, nick);

        try {
            name.set(cp.getProfile(), nick);
        } catch (Exception e) {
        }

        setSkin(cp, nick);

        PacketPlayOutEntityDestroy destroy = new PacketPlayOutEntityDestroy(cp.getEntityId());
        sendPacket(destroy, p);
        PacketPlayOutPlayerInfo removetab = new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER, cp.getHandle());
        sendPacket(removetab);

        new BukkitRunnable() {
            @Override
            public void run() {
                PacketPlayOutPlayerInfo addtab = new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, cp.getHandle());
                sendPacket(addtab);
                PacketPlayOutNamedEntitySpawn spawn = new PacketPlayOutNamedEntitySpawn(cp.getHandle());
                sendPacket(spawn, p);
            }
        }.runTaskLater(main.getInstance(), 4);

    }

    public static void setSkin(CraftPlayer cp, String nick) {
        GameProfile skingp = cp.getProfile();
        try {
            skingp = GameProfileBuilder.fetch(UUIDFetcher.getUUID(nick));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        Collection<Property> prop = skingp.getProperties().get("textures");
        cp.getProfile().getProperties().removeAll("textures");
        cp.getProfile().getProperties().putAll("textures", prop);
    }

    public static void sendPacket(Packet<?> packet, Player p) {
        for(Player all : Bukkit.getOnlinePlayers()) {
            if(!all.equals(p)) {
                ((CraftPlayer) all).getHandle().playerConnection.sendPacket(packet);
            }
        }
    }

    public static void sendPacket(Packet<?> packet) {
        for(Player all : Bukkit.getOnlinePlayers()) {
            ((CraftPlayer)all).getHandle().playerConnection.sendPacket(packet);
        }
    }

    public static Field getField(Class<?> clazz, String name) {
        try {
            Field f = clazz.getDeclaredField(name);
            f.setAccessible(true);
            return f;
        } catch (NoSuchFieldException | SecurityException e) {
        }
        return null;
    }
}
