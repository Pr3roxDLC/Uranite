package me.pr3.uranite.impl.base.managers;

import me.pr3.cdi.annotations.scopes.ClientScoped;
import me.pr3.uranite.api.feature.module.IModule;
import me.pr3.uranite.api.managers.IModuleManager;

import java.util.Collection;
import java.util.HashMap;

@ClientScoped
public class ModuleManager implements IModuleManager {
    private final HashMap<String, IModule> moduleMap = new HashMap<>();

    @Override
    public Collection<IModule> getModules() {
        return moduleMap.values();
    }

    @Override
    public IModule getModule(String name) {
        return moduleMap.get(name);
    }

    @Override
    public void setModuleInstance(String name, IModule module) {
        moduleMap.put(name, module);
    }


}
