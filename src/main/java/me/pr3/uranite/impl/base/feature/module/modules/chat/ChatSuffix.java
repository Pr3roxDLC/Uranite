package me.pr3.uranite.impl.base.feature.module.modules.chat;

import me.pr3.uranite.impl.base.annotations.Module;
import me.pr3.uranite.impl.base.annotations.event.Observes;
import me.pr3.uranite.impl.base.annotations.scopes.LifeScoped;
import me.pr3.uranite.impl.base.feature.module.BaseModule;
import me.pr3.uranite.impl.base.managers.BaseCommandManager;
import net.minecraftforge.client.event.ClientChatEvent;


@LifeScoped
@Module(name = "ChatSuffix", category = "CHAT", description = "Adds a Suffix to your chat messages")
public class ChatSuffix extends BaseModule {

    public String suffix = " | Uranite";
    public void onChatMessage(@Observes ClientChatEvent e){
        if(e.getMessage().startsWith("/") || e.getMessage().startsWith(BaseCommandManager.PREFIX))return;
        e.setMessage(e.getMessage() + suffix);
    }
}
