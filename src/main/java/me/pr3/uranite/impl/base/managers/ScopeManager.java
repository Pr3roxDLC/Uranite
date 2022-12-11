package me.pr3.uranite.impl.base.managers;

import com.google.inject.Inject;
import me.pr3.uranite.Uranite;
import me.pr3.uranite.api.feature.module.IModule;
import me.pr3.uranite.impl.base.BaseGuiceModule;
import me.pr3.uranite.impl.base.annotations.Module;
import me.pr3.uranite.impl.base.annotations.scopes.ClientScoped;
import me.pr3.uranite.impl.base.annotations.scopes.LifeScoped;
import me.pr3.uranite.impl.base.annotations.scopes.ServerScoped;
import me.pr3.uranite.impl.base.annotations.scopes.WorldScoped;
import me.pr3.uranite.impl.base.util.scopes.SimpleScope;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import org.reflections.Reflections;

import javax.inject.Named;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

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
        manager.updateModules(ClientScoped.class);
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
        if (e.getEntity().equals(Minecraft.getMinecraft().player)) {
            //TODO Why does this happen
            TRY(() -> {
                lifeScope.exit();
            });
            lifeScope.enter();
            manager.updateModules(LifeScoped.class);
        }
    }

    private static Set<Class<?>> scopedClasses = null;

    public static void updateScopedInstances(Class<?>... scopes){
        if (scopedClasses == null) {
            Reflections ref = new Reflections();
            scopedClasses = new HashSet<>();
            for(Class scope : scopes){
                scopedClasses.addAll(ref.getTypesAnnotatedWith(scope));
            }
        }
        scopedClasses.forEach(scopedClass -> {
            for (Class<?> scopeClass : scopes) {
                if (scopedClass.isAnnotationPresent((Class<? extends Annotation>) scopeClass)) {
                    TRY(() -> {
                        BaseGuiceModule.instanceMap.put(scopeClass, Uranite.INJECTOR.getInstance(scopeClass));
                    }).CATCH(Throwable::printStackTrace);
                }
            }
        });
    }

}
