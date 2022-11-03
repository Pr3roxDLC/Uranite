package me.pr3.uranite.impl.base.feature.command.commands;

import com.google.inject.Inject;
import me.pr3.uranite.api.managers.IModuleManager;
import me.pr3.uranite.impl.base.annotations.Command;
import me.pr3.uranite.impl.base.feature.command.BaseCommand;

import java.util.List;
import java.util.Locale;

@Command(name = "Toggle", syntax = "toggle <Module> / toggle <Module> <On/Off>", description = "Toggles a Module")
public class Toggle extends BaseCommand {

    @Inject
    IModuleManager manager;

    @Override
    public void executeCommand(List<String> args) {
        if(args.size() == 1){
            manager.getModule(args.get(0).toLowerCase(Locale.ROOT)).setEnabled(!manager.getModule(args.get(0)).isEnabled());
        }else if(args.size() == 2){
            boolean toggleState = false;
            if(args.get(1).equalsIgnoreCase("On")){
                toggleState = true;
            }else if(!args.get(1).equalsIgnoreCase("Off")){
                throw new IllegalArgumentException("Second Parameter must be either \"On\" or \"Off\"");
            }
            manager.getModule(args.get(0).toLowerCase()).setEnabled(toggleState);
        }
    }
}
