package me.pr3.uranite.api.managers;

import me.pr3.uranite.api.feature.module.IModule;

import javax.enterprise.context.ApplicationScoped;
import java.util.Collection;

@ApplicationScoped
public interface IModuleManager {
    Collection<IModule> getModules();
}
