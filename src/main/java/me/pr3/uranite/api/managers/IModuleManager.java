package me.pr3.uranite.api.managers;

import me.pr3.uranite.api.feature.module.IModule;

import java.util.Collection;

public interface IModuleManager {
    Collection<IModule> getModules();
    IModule getModule(String name);

    void setModuleInstance(String name, IModule module);

}
