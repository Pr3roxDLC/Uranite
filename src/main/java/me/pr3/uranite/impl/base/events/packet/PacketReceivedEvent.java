package me.pr3.uranite.impl.base.events.packet;

import net.minecraft.network.Packet;

public class PacketReceivedEvent extends PacketEvent{
    public PacketReceivedEvent(Packet<?> packet) {
        super(packet);
    }
}
