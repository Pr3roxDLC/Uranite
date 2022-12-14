package me.pr3.uranite;

import com.google.inject.Guice;
import com.google.inject.Injector;
import me.pr3.uranite.impl.base.BaseGuiceModule;
import me.pr3.uranite.impl.base.BaseMain;
import me.pr3.uranite.impl.base.annotations.scopes.ClientScoped;
import me.pr3.uranite.impl.base.annotations.scopes.LifeScoped;
import me.pr3.uranite.impl.base.annotations.scopes.ServerScoped;
import me.pr3.uranite.impl.base.annotations.scopes.WorldScoped;
import me.pr3.uranite.impl.base.event.EventBus;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashSet;
import java.util.Set;

@Mod(
        modid = Uranite.MOD_ID,
        name = Uranite.MOD_NAME,
        version = Uranite.VERSION
)
public class Uranite {

    public static final String MOD_ID = "uranite";
    public static final String MOD_NAME = "Uranite";
    public static final String VERSION = "1.0-SNAPSHOT";

    public static final Set<Class<?>> SCOPES = new HashSet<>();

    public static Injector INJECTOR;

    public static final EventBus EVENT_BUS = new EventBus();

    /**
     * This is the instance of your mod as created by Forge. It will never be null.
     */
    @Mod.Instance(MOD_ID)
    public static Uranite INSTANCE;

    /**
     * This is the first initialization event. Register tile entities here.
     * The registry events below will have fired prior to entry to this method.
     */
    @Mod.EventHandler
    public void preinit(FMLPreInitializationEvent event) {

        SCOPES.add(ClientScoped.class);
        SCOPES.add(LifeScoped.class);
        SCOPES.add(ServerScoped.class);
        SCOPES.add(WorldScoped.class);


        INJECTOR = Guice.createInjector(new BaseGuiceModule());
        INJECTOR.getInstance(BaseMain.class);

    }

    /**
     * This is the second initialization event. Register custom recipes
     */
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {

    }

    /**
     * This is the final initialization event. Register actions from other mods here
     */
    @Mod.EventHandler
    public void postinit(FMLPostInitializationEvent event) {

    }
}