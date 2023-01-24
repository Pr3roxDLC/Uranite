package me.pr3.uranite;

import me.pr3.cdi.EntryPoint;
import me.pr3.cdi.extensions.events.EventSystemExtension;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.Mod;

import static net.minecraftforge.fml.common.Mod.EventHandler;
import static net.minecraftforge.fml.common.Mod.Instance;


@Mod(
        modid = Uranite.MOD_ID,
        name = Uranite.MOD_NAME,
        version = Uranite.VERSION
)
public class Uranite {

    public static final String MOD_ID = "uranite";
    public static final String MOD_NAME = "Uranite";
    public static final String VERSION = "1.0-SNAPSHOT";

    /**
     * This is the instance of your mod as created by Forge. It will never be null.
     */
    @Instance(MOD_ID)
    public static Uranite INSTANCE;

    /**
     * This is the first initialization event. Register tile entities here.
     * The registry events below will have fired prior to entry to this method.
     */
    @EventHandler
    public void preinit(FMLPreInitializationEvent event) {
        EntryPoint.init(new EventSystemExtension());
    }

    /**
     * This is the second initialization event. Register custom recipes
     */
    @EventHandler
    public void init(FMLInitializationEvent event) {

    }

    /**
     * This is the final initialization event. Register actions from other mods here
     */
    @EventHandler
    public void postinit(FMLPostInitializationEvent event) {

    }
}