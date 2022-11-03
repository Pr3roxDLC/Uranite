package me.pr3.uranite.api.managers;

import me.pr3.uranite.api.feature.command.ICommand;

import java.util.Collection;

public interface ICommandManager {
    Collection<ICommand> getCommands();

}
