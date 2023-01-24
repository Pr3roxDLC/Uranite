package me.pr3.uranite.impl.base.feature.commands.commands;

import me.pr3.cdi.annotations.Inject;
import me.pr3.uranite.api.feature.module.IModule;
import me.pr3.uranite.impl.base.feature.commands.AbstractCommand;
import me.pr3.uranite.impl.base.feature.commands.Command;
import me.pr3.uranite.impl.base.managers.ModuleManager;

@Command(name = "toggle", syntax = "<module>")
public class Toggle extends AbstractCommand {

    @Inject
    ModuleManager moduleManager;

    @Override
    public void run(String[] args) {
        IModule module = moduleManager.getModule(args[0]);
        if(module!=null){
            module.setEnabled(!module.isEnabled());
        }
    }
}
