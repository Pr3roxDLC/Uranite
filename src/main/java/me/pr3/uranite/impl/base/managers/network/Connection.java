package me.pr3.uranite.impl.base.managers.network;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.ChannelPromise;
import me.pr3.cdi.annotations.Inject;
import me.pr3.cdi.annotations.scopes.ClientScoped;
import me.pr3.cdi.extensions.events.EventManager;
import me.pr3.uranite.impl.base.events.packet.PacketEvent;
import me.pr3.uranite.impl.base.events.packet.PacketReceivedEvent;
import me.pr3.uranite.impl.base.events.packet.PacketSentEvent;
import net.minecraft.network.Packet;

@ClientScoped
public class Connection extends ChannelDuplexHandler {

    public Connection(){
        System.out.println("Created new Connection");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object packet) throws Exception {
        PacketEvent event = new PacketReceivedEvent((Packet<?>) packet);
        EventManager.post(event);
        if (event.isCanceled()){
            return;
        }
        super.channelRead(ctx, packet);
    }


    @Override
    public void write(ChannelHandlerContext ctx, Object packet, ChannelPromise promise) throws Exception {
        PacketEvent event = new PacketSentEvent((Packet<?>) packet);
        EventManager.post(event);
        if (event.isCanceled()){
            return;
        }
        super.write(ctx, packet, promise);
    }

}
