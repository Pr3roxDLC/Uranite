package me.pr3.uranite.impl.base.feature.command;

import me.pr3.uranite.api.feature.command.ICommand;
import me.pr3.uranite.impl.base.annotations.Command;


import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static me.pr3.catcher.Catcher.TRY;

public abstract class BaseCommand implements ICommand {

    private final String syntax;
    private final String description;
    private final String name;

    public BaseCommand() {
        name = this.getClass().getAnnotation(Command.class).name();
        syntax = this.getClass().getAnnotation(Command.class).syntax();
        description = this.getClass().getAnnotation(Command.class).description();
    }

    public void run(String input) {
        TRY(() -> {
            List<String> strings = Arrays.stream(input.split(" ")).skip(1).collect(Collectors.toList());
            executeCommand(strings);
        }).CATCH(this::handleError);
    }

    public abstract void executeCommand(List<String> args);

    public void handleError(Throwable throwable){
        throwable.printStackTrace();
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
