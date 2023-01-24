package me.pr3.uranite.impl.base.feature.modules.chat;

import me.pr3.cdi.annotations.Inject;
import me.pr3.cdi.annotations.PostConstruct;
import me.pr3.cdi.annotations.scopes.ClientScoped;
import me.pr3.cdi.extensions.events.annotations.Observes;
import me.pr3.uranite.impl.base.feature.modules.AbstractModule;
import me.pr3.uranite.impl.base.feature.modules.Module;
import me.pr3.uranite.impl.base.managers.CommandManager;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ClientChatEvent;


@ClientScoped
@Module(name = "ChatSuffix", category = "CHAT", description = "Adds a string to the end of your messages")
public class ChatSuffix extends AbstractModule {

    @Inject
    CommandManager commandManager;

    @Override
    @PostConstruct
    public void postConstruct() {
        super.postConstruct();
        this.setEnabled(true);
    }

    public void onClientTick(@Observes ClientChatEvent event) {
        if (event.getMessage().startsWith(commandManager.getPrefix()) || event.getMessage().startsWith("/")) return;
        event.setMessage(event.getMessage() + Minecraft.getMinecraft().player.getName());
    }

}
