package me.pr3.uranite.impl.base.events.packet;

import net.minecraft.network.Packet;
import net.minecraftforge.fml.common.eventhandler.Cancelable;
import net.minecraftforge.fml.common.eventhandler.Event;
@Cancelable
public class PacketEvent extends Event {
    private final Packet<?> packet;
    public PacketEvent(Packet<?> packet){
        this.packet = packet;
    }

    public Packet<?> getPacket(){
        return packet;
    }
}
