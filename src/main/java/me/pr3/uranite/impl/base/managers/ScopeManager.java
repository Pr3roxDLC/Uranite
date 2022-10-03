package me.pr3.uranite.impl.base.managers;

import com.google.inject.Inject;
import me.pr3.uranite.impl.base.annotations.scopes.ClientScoped;
import me.pr3.uranite.impl.base.annotations.scopes.LifeScoped;
import me.pr3.uranite.impl.base.annotations.scopes.ServerScoped;
import me.pr3.uranite.impl.base.annotations.scopes.WorldScoped;
import me.pr3.uranite.impl.base.util.SimpleScope;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;

import javax.inject.Named;

import static me.pr3.catcher.Catcher.TRY;

@ClientScoped
public class ScopeManager {

    @Inject
    @Named("lifeScope")
    SimpleScope lifeScope;

    @Inject
    @Named("serverScope")
    SimpleScope serverScope;

    @Inject
    @Named("worldScope")
    SimpleScope worldScope;

    @Inject
    BaseModuleManager manager;


    public void init() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onServerConnectedEvent(FMLNetworkEvent.ClientConnectedToServerEvent e) {
        System.out.println("Connected to Server: " + e.getConnectionType());
        serverScope.enter();
        lifeScope.enter();
        worldScope.enter();
        manager.updateModules(ServerScoped.class, LifeScoped.class, WorldScoped.class);
    }

    @SubscribeEvent
    public void onServerDisconnectedEvent(FMLNetworkEvent.ClientDisconnectionFromServerEvent e) {
        System.out.println("Disconnected from Server");
        serverScope.exit();
    }

    @SubscribeEvent
    public void onPlayerDeath(LivingDeathEvent e) {
        if(e.getEntity() == Minecraft.getMinecraft().player) {
            //TODO Why does this happen
            TRY(() -> {
                lifeScope.exit();
            });
            lifeScope.enter();
            manager.updateModules(LifeScoped.class);
        }
    }

}
