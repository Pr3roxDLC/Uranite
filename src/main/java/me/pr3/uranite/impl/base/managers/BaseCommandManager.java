package me.pr3.uranite.impl.base.managers;

import com.google.inject.Inject;
import me.pr3.uranite.Uranite;
import me.pr3.uranite.api.feature.command.ICommand;
import me.pr3.uranite.api.feature.module.IModule;
import me.pr3.uranite.api.managers.ICommandManager;
import me.pr3.uranite.api.managers.IModuleManager;
import me.pr3.uranite.impl.base.annotations.Command;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.reflections.Reflections;

import java.util.Collection;
import java.util.HashMap;

public class BaseCommandManager implements ICommandManager {

    public static final String PREFIX = ".";

    public final HashMap<String, ICommand> commands = new HashMap<>();

    @Inject
    public BaseCommandManager() {
        new Reflections().getTypesAnnotatedWith(Command.class).forEach(clazz -> {
            ICommand command = (ICommand) Uranite.INJECTOR.getInstance(clazz);
            commands.put(command.getName().toLowerCase(), command);
        });
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onChatMessageSent(ClientChatEvent event) {
        if (event.getMessage().startsWith(PREFIX)) {
            event.setCanceled(true);
            String commandName = event.getMessage().split(" ")[0].replace(PREFIX, "");
            ICommand command = commands.get(commandName.toLowerCase());
            command.run(event.getMessage());

        }
    }

    @Override
    public Collection<ICommand> getCommands() {
        return commands.values();
    }

}
