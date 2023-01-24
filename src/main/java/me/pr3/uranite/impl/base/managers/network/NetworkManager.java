package me.pr3.uranite.impl.base.managers.network;

import io.netty.channel.ChannelPipeline;
import me.pr3.cdi.annotations.Inject;
import me.pr3.cdi.annotations.PostConstruct;
import me.pr3.cdi.annotations.scopes.ClientScoped;
import me.pr3.cdi.extensions.events.EventManager;
import me.pr3.cdi.extensions.events.annotations.Observes;
import me.pr3.cdi.extensions.events.annotations.filters.If;
import net.minecraft.client.Minecraft;


import java.util.Objects;

import static me.pr3.cdi.extensions.events.annotations.filters.If.WORLD_NON_NULL;
import static net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;

@ClientScoped
public class NetworkManager {
    @Inject
    Connection connection;

    @PostConstruct
    public void postConstruct(){
        EventManager.subscribe(this);
    }

    public void onClientTick(@Observes @If(WORLD_NON_NULL) ClientTickEvent event){
        ChannelPipeline pipeline = Objects.requireNonNull(Minecraft.getMinecraft().getConnection()).getNetworkManager().channel().pipeline();
        pipeline.addBefore("packet_handler", "packet_interceptor", connection);
        EventManager.unsubscribe(this);
    }
}
