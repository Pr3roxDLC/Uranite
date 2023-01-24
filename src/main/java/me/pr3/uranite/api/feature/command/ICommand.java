package me.pr3.uranite.api.feature.command;

public interface ICommand {
    void run(String[] args);
    String getSyntax();
    String getDescription();
    String getName();
}
