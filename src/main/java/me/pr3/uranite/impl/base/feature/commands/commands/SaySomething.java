package me.pr3.uranite.impl.base.feature.commands.commands;

import me.pr3.uranite.impl.base.feature.commands.AbstractCommand;
import me.pr3.uranite.impl.base.feature.commands.Command;

@Command(name="say", syntax = "say", description = "Says Something")
public class SaySomething extends AbstractCommand {
    long aLong = System.currentTimeMillis();
    @Override
    public void run(String[] args) {
        System.out.println(aLong);
    }
}
