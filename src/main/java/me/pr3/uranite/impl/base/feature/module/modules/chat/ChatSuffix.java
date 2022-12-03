package me.pr3.uranite.impl.base.feature.module.modules.chat;

import me.pr3.uranite.impl.base.annotations.Module;
import me.pr3.uranite.impl.base.annotations.scopes.LifeScoped;
import me.pr3.uranite.impl.base.feature.module.BaseModule;
import me.pr3.uranite.impl.base.managers.BaseCommandManager;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;


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
