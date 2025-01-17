package io.github.retrooper.packetevents.utils;

import io.github.retrooper.packetevents.PacketEvents;
import io.github.retrooper.packetevents.annotations.Nullable;
import io.github.retrooper.packetevents.enums.ServerVersion;
import io.github.retrooper.packetevents.packetwrappers.Sendable;
import io.github.retrooper.packetevents.reflectionutils.Reflection;
import io.github.retrooper.packetevents.utils.nms_entityfinder.EntityFinderUtils;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public final class NMSUtils {
    private static final ServerVersion version = PacketEvents.getAPI().getServerUtilities().getServerVersion();
    public static String nettyPrefix;
    private static final String nmsDir = ServerVersion.getNMSDirectory();
    private static final String obcDir = ServerVersion.getOBCDirectory();
    public static Class<?> minecraftServerClass, craftWorldsClass,
            packetClass, entityPlayerClass, playerConnectionClass,
            craftPlayerClass, serverConnectionClass, craftEntityClass,
            craftItemStack, nmsItemStackClass, networkManagerClass, nettyChannelClass;
    private static Method getCraftWorldHandleMethod, getServerConnection, getCraftPlayerHandle, getCraftEntityHandle, sendPacketMethod, asBukkitCopy;
    private static Field entityPlayerPingField, playerConnectionField;

    static {
        try {
            nettyPrefix = "io.netty";
            Class.forName("io.netty.channel.Channel");
        } catch (ClassNotFoundException e) {
            nettyPrefix = "net.minecraft.util.io.netty";
        }
        try {
            nettyChannelClass = getNettyClass("channel.Channel");
            minecraftServerClass = getNMSClass("MinecraftServer");
            craftWorldsClass = getOBCClass("CraftWorld");
            craftPlayerClass = getOBCClass("entity.CraftPlayer");
            entityPlayerClass = getNMSClass("EntityPlayer");
            packetClass = getNMSClass("Packet");
            playerConnectionClass = getNMSClass("PlayerConnection");
            serverConnectionClass = getNMSClass("ServerConnection");
            craftEntityClass = getOBCClass("entity.CraftEntity");
            craftItemStack = getOBCClass("inventory.CraftItemStack");
            nmsItemStackClass = getNMSClass("ItemStack");
            networkManagerClass = getNMSClass("NetworkManager");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //METHODS
        try {
            getCraftWorldHandleMethod = craftWorldsClass.getMethod("getHandle");
            getCraftPlayerHandle = craftPlayerClass.getMethod("getHandle");
            getCraftEntityHandle = craftEntityClass.getMethod("getHandle");
            sendPacketMethod = playerConnectionClass.getMethod("sendPacket", packetClass);
            getServerConnection = minecraftServerClass.getMethod("getServerConnection");
            asBukkitCopy = craftItemStack.getMethod("asBukkitCopy", nmsItemStackClass);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }


        try {
            entityPlayerPingField = entityPlayerClass.getField("ping");
            playerConnectionField = entityPlayerClass.getField("playerConnection");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public static Object getMinecraftServerInstance() throws InvocationTargetException, IllegalAccessException {
        return Reflection.getMethod(minecraftServerClass, "getServer", 0).invoke(null);
    }

    public static double[] recentTPS() throws IllegalAccessException, InvocationTargetException {
        final Object minecraftServerObj = getMinecraftServerInstance();
        return (double[]) Reflection.getField(minecraftServerClass, double[].class, 0).get(minecraftServerObj);
    }

    public static Class<?> getNMSClass(String name) throws ClassNotFoundException {
        return Class.forName(nmsDir + "." + name);
    }

    public static Class<?> getNMSClassWithoutException(String name) {
        try {
            return getNMSClass(name);
        } catch (ClassNotFoundException e) {
            return null;
        }

    }

    public static Class<?> getOBCClass(String name) throws ClassNotFoundException {
        return Class.forName(obcDir + "." + name);
    }

    public static Class<?> getNettyClass(String name) throws ClassNotFoundException {
        return Class.forName(nettyPrefix + "." + name);
    }

    public static final String getChannelFutureListFieldName() {
        if (version.isLowerThan(ServerVersion.v_1_8))
            return "e";
        if (version.isLowerThan(ServerVersion.v_1_13))
            return "g";
        return "f";
    }

    @Nullable
    public static Entity getEntityById(final int id) {
        return EntityFinderUtils.getEntityById(id);
    }

    @Nullable
    public static Entity getEntityByIdWithWorld(final World world, final int id) {
        return EntityFinderUtils.getEntityByIdWithWorld(world, id);
    }

    public static Object getNMSEntity(final Entity entity) {
        final Object craftEntity = craftEntityClass.cast(entity);
        try {
            return getCraftEntityHandle.invoke(craftEntity);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object getCraftPlayer(final Player player) {
        return craftPlayerClass.cast(player);
    }

    public static Object getEntityPlayer(final Player player) {
        Object entityPlayer = null;
        try {
            return getCraftPlayerHandle.invoke(getCraftPlayer(player));
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object getPlayerConnection(final Player player) {
        try {
            return playerConnectionField.get(getEntityPlayer(player));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object getNetworkManager(final Player player) {
        try {
            return Reflection.getField(playerConnectionClass, networkManagerClass, 0).get(getPlayerConnection(player));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object getChannel(final Player player) {
        try {
            return Reflection.getField(networkManagerClass, nettyChannelClass, 0).get(getNetworkManager(player));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int getPlayerPing(final Player player) {
        Object entityPlayer = getEntityPlayer(player);
        try {
            return entityPlayerPingField.getInt(entityPlayer);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static ItemStack toBukkitItemStack(final Object nmsItemstack) {
        try {
            return (ItemStack) asBukkitCopy.invoke(null, nmsItemstack);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean is_1_8() {
        return version.isHigherThan(ServerVersion.v_1_7_10)
                && version.isLowerThan(ServerVersion.v_1_9);
    }

    public static void sendSendableWrapper(final Player player, final Sendable sendable) {
        sendNMSPacket(player, sendable.asNMSPacket());
    }

    public static void sendNMSPacket(final Player player, final Object nmsPacket) {
        Object entityPlayer = getEntityPlayer(player);
        Object playerConnection = null;
        try {
            playerConnection = playerConnectionField.get(entityPlayer);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        try {
            sendPacketMethod.invoke(playerConnection, nmsPacket);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }


    public static String getServerConnectionFieldName() {
        return "p";
    }
}
