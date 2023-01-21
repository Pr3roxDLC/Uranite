package me.pr3.uranite.impl.base.managers;

import me.pr3.cdi.annotations.scopes.ClientScoped;
import me.pr3.uranite.api.feature.command.ICommand;
import me.pr3.uranite.api.managers.ICommandManager;

import java.util.Collection;

@ClientScoped
public class CommandManager implements ICommandManager {
    @Override
    public Collection<ICommand> getCommands() {
        return null;
    }
}
