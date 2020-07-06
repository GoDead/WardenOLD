package io.github.retrooper.packetevents.event.impl;

import io.github.retrooper.packetevents.event.PacketEvent;
import io.github.retrooper.packetevents.packet.PacketType;
import org.bukkit.entity.Player;

public final class PacketReceiveEvent extends PacketEvent {
    private final Player player;
    private final String name;
    private final Object packet;
    private boolean cancelled;

    public PacketReceiveEvent(final Player player, final String packetName, final Object packet) {
        this.player = player;
        this.name = packetName;
        this.packet = packet;
    }

    /**
     * Get the player sending the packet
     *
     * @return
     */
    public Player getPlayer() {
        return this.player;
    }

    /**
     * Get the packet's name (NMS packet class simple name)
     *
     * @return
     */
    public String getPacketName() {
        return this.name;
    }

    /**
     * Get the NMS packet object
     *
     * @return packet object
     */
    public Object getNMSPacket() {
        return this.packet;
    }

    /**
     * Get the class of the NMS packet object
     *
     * @return packet object class
     */
    public Class<?> getNMSPacketClass() {
        return getNMSPacket().getClass();
    }

    /**
     * Get the ID of the packet
     *
     * @return packet id
     */
    public int getPacketId() {
        Integer id = PacketType.Client.packetIds.get(getNMSPacketClass());
        if (id == null) {
            return -1;
        }
        return id;
    }

    /**
     * Returns if the packet has been cancelled
     *
     * @return cancelled
     */
    public boolean isCancelled() {
        return cancelled;
    }

    /**
     * Cancel the packet
     *
     * @param cancelled
     */
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
