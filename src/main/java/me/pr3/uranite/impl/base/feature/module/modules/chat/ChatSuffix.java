package me.pr3.uranite.impl.base.feature.module.modules.chat;

import com.google.inject.Inject;
import me.pr3.uranite.impl.base.annotations.Module;
import me.pr3.uranite.impl.base.annotations.scopes.ClientScoped;
import me.pr3.uranite.impl.base.annotations.scopes.LifeScoped;
import me.pr3.uranite.impl.base.annotations.scopes.ServerScoped;
import me.pr3.uranite.impl.base.feature.module.BaseModule;
import me.pr3.uranite.impl.base.feature.module.BaseModuleCategory;
import me.pr3.uranite.impl.base.managers.BaseCommandManager;
import me.pr3.uranite.impl.base.managers.BaseModuleManager;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.io.FilterOutputStream;
import java.util.Random;


@LifeScoped
@Module(name = "ChatSuffix", category = "CHAT", description = "Adds a Suffix to your chat messages")
public class ChatSuffix extends BaseModule {

    public long test = 0;

    public ChatSuffix(){
        test = System.currentTimeMillis();
        System.out.println("Created " + ChatSuffix.class.getName() + test);
    }

    @SubscribeEvent
    public void onChatMessage(ClientChatEvent e){
        if(e.getMessage().startsWith("/") || e.getMessage().startsWith(BaseCommandManager.PREFIX))return;
        e.setMessage(e.getMessage() + test);
    }

}
