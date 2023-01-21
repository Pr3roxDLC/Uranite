package me.pr3.uranite.impl.base.feature.modules.chat;

import me.pr3.cdi.annotations.scopes.ClientScoped;
import me.pr3.cdi.extensions.events.annotations.Observes;
import me.pr3.uranite.impl.base.feature.modules.AbstractModule;
import me.pr3.uranite.impl.base.feature.modules.Module;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ClientChatEvent;


@ClientScoped
@Module(name = "ChatSuffix", category = "CHAT", description = "Adds a string to the end of your messages")
public class ChatSuffix extends AbstractModule {

    public void onClientTick(@Observes ClientChatEvent event){
        event.setMessage(event.getMessage() + Minecraft.getMinecraft().player.getName());
    }

}
