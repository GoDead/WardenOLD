package io.github.retrooper.packetevents;

import io.github.retrooper.packetevents.annotations.PacketHandler;
import io.github.retrooper.packetevents.event.PacketListener;
import io.github.retrooper.packetevents.event.impl.BukkitMoveEvent;
import io.github.retrooper.packetevents.event.impl.PacketReceiveEvent;
import io.github.retrooper.packetevents.event.impl.PlayerInjectEvent;
import io.github.retrooper.packetevents.event.impl.ServerTickEvent;
import io.github.retrooper.packetevents.packet.PacketType;
import io.github.retrooper.packetevents.packet.PacketTypeClasses;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;

public final class PacketEvents implements PacketListener, Listener {
    private static final PacketEventsAPI packetEventsAPI = new PacketEventsAPI();
    private static final PacketEvents instance = new PacketEvents();
    private static boolean hasRegistered;
    private static int currentTick;
    private static Plugin plugin;
    private static boolean hasLoaded;

    /**
     * Call this before start(), this is also very heavy
     */
    public static void load() {
        PacketTypeClasses.Client.load();
        PacketTypeClasses.Server.load();
        hasLoaded = true;
    }

    /**
     * Starts the server tick task and registers what is needed to be registered
     *
     * @param plugin
     */
    public static void start(final Plugin plugin) {
        if (!hasLoaded) {
            load();
        }
        if (!hasRegistered) {
            //Register Bukkit and PacketListener
            getAPI().getEventManager().registerListener(getInstance());

            Bukkit.getPluginManager().registerEvents(getInstance(), plugin);

            //Start the server tick task
            final Runnable tickRunnable = new Runnable() {
                @Override
                public void run() {
                    getAPI().getEventManager().callEvent(new ServerTickEvent(currentTick++, PacketEvents.getAPI().currentMillis()));
                }
            };
            getAPI().setServerTickTask(Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, tickRunnable, 0L, 1L));
            hasRegistered = true;
            PacketEvents.plugin = plugin;
        }
    }

    /**
     * Stop all tasks and unregisters all PacketEvents' listeners
     */
    public static void stop() {
        if (getAPI().getServerTickTask() != null) {
            getAPI().getServerTickTask().cancel();
        }
        getAPI().getEventManager().unregisterAllListeners();

        PacketType.Client.packetIds.clear();
        PacketType.Server.packetIds.clear();
    }

    public static PacketEventsAPI getAPI() {
        return packetEventsAPI;
    }


    /**
     * Get an instance of the PacketEvents API class
     *
     * @return instance
     */
    private static PacketEvents getInstance() {
        return instance;
    }

    public static Plugin getPlugin() {
        return plugin;
    }

    /**
     * Do not check the client version in or before the PlayerInjectEvent, use the PostPlayerInjectEvent.
     * It is not recommended to do much in the PlayerInjectEvent, as some fields in the Player object are be null.
     * Use the PostPlayerInjectEvent which is only called after the PlayerJoinEvent if the player was injected.
     *
     * @param e
     */
    @PacketHandler
    public void onInject(final PlayerInjectEvent e) {
        getAPI().getPlayerUtilities().setClientVersion(e.getPlayer(), e.getClientVersion());
    }

    @PacketHandler
    public void onReceive(final PacketReceiveEvent e) {
    }

    @EventHandler
    public void onJoin(final PlayerJoinEvent e) {
        getAPI().getPlayerUtilities().injectPlayer(e.getPlayer());
    }

    @EventHandler
    public void onQuit(final PlayerQuitEvent e) {
        getAPI().getPlayerUtilities().clearClientVersion(e.getPlayer());
        getAPI().getPlayerUtilities().uninjectPlayer(e.getPlayer());
    }

    @EventHandler
    public void onMove(final PlayerMoveEvent e) {
        getAPI().getEventManager().callEvent(new BukkitMoveEvent(e));
    }
}
