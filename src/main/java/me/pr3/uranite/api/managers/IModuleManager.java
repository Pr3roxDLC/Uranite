package me.pr3.uranite.api.managers;

import me.pr3.uranite.api.feature.module.IModule;

import java.util.Collection;

public interface IModuleManager {
    void init();
    Collection<IModule> getModules();

}
