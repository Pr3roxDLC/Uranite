package me.pr3.uranite.api.feature.command;

import java.util.Collection;

public interface ICommand {
    void run(String message);
    String getSyntax();
    String getDescription();
    String getName();
}
