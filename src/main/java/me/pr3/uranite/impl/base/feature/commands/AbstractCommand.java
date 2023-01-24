package me.pr3.uranite.impl.base.feature.commands;

import me.pr3.uranite.api.feature.command.ICommand;

public abstract class AbstractCommand implements ICommand {

    private final String name;
    private final String syntax;
    private final String description;

    public AbstractCommand(){
        Command command = this.getClass().getAnnotation(Command.class);
        name = command.name();
        syntax = command.syntax();
        description = command.description();
    }

    @Override
    public String getSyntax() {
        return syntax;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getName() {
        return name;
    }
}
