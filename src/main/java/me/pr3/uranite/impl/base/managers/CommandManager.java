package me.pr3.uranite.impl.base.managers;

import me.pr3.cdi.annotations.Inject;
import me.pr3.cdi.annotations.scopes.ClientScoped;
import me.pr3.cdi.extensions.events.EventManager;
import me.pr3.cdi.extensions.events.annotations.Observes;
import me.pr3.cdi.managers.ScopeManager;
import me.pr3.uranite.api.feature.command.ICommand;
import me.pr3.uranite.api.managers.ICommandManager;
import me.pr3.uranite.impl.base.feature.commands.Command;
import net.minecraftforge.client.event.ClientChatEvent;
import org.reflections.Reflections;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

@ClientScoped
public class CommandManager implements ICommandManager {
    String prefix = ".";
    private final HashMap<String, Class<?>> commands = new HashMap<>();

    @Inject
    public CommandManager() {
        Reflections ref = new Reflections();
        ref.getTypesAnnotatedWith(Command.class).forEach(clazz -> commands.put(clazz.getAnnotation(Command.class).name(), clazz));
        EventManager.subscribe(this);
    }

    @Override
    public Collection<ICommand> getCommands() {
        return null;
    }

    public String getPrefix(){
        return prefix;
    }

    public void onPlayerSendChat(@Observes ClientChatEvent event) {
        if (event.getMessage().startsWith(prefix)) {
            String message = event.getMessage().replace(prefix, "");
            String command = message.split(" ")[0];
            try {
                ICommand commandInstance = (ICommand) ScopeManager.INSTANCE.getInstance(commands.get(command));
                commandInstance.run(Arrays.stream(message.split(" "))
                        .skip(1)
                        .toArray(String[]::new));
            } catch (Exception e) {
                //TOTO Handle exceptions
            }

        }
    }

}
