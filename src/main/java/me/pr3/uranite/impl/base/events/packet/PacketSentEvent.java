package me.pr3.uranite.impl.base.events.packet;

import net.minecraft.network.Packet;
import net.minecraftforge.fml.common.eventhandler.Cancelable;

public class PacketSentEvent extends PacketEvent{
    public PacketSentEvent(Packet<?> packet) {
        super(packet);
    }
}
