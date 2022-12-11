package me.pr3.uranite.impl.base.feature.module.modules.chat;

import com.google.inject.Inject;
import com.google.inject.Provider;
import me.pr3.uranite.impl.base.annotations.Module;
import me.pr3.uranite.impl.base.annotations.scopes.ServerScoped;
import me.pr3.uranite.impl.base.feature.module.BaseModule;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@ServerScoped
@Module(name = "Test", category = "CHAT",description = "SOMETHING")
public class SomeTestModule extends BaseModule {

    @Inject
    private Provider<ChatSuffix> suffix;

    public SomeTestModule(){
        System.out.println("Created " + SomeTestModule.class.getName());
    }

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event){
        System.out.println(suffix==null?"null":suffix.get().test);
    }

}
