package me.pr3.uranite.impl.base.managers;

import me.pr3.uranite.impl.base.annotations.Module;
import me.pr3.uranite.api.feature.module.IModule;
import me.pr3.uranite.api.managers.IModuleManager;
import org.reflections.Reflections;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static me.pr3.catcher.Catcher.TRY;

public class BaseModuleManager implements IModuleManager {

    private final Map<String, IModule> moduleMap = new HashMap<>();

    public BaseModuleManager() {
        new Reflections().getTypesAnnotatedWith(Module.class).forEach(moduleClass -> {
            TRY(() -> {
                IModule module = (IModule) moduleClass.getDeclaredConstructor().newInstance();
                moduleMap.put(module.getName(), module);
            });
        });
    }

    @Override
    public Collection<IModule> getModules() {
        return moduleMap.values();
    }


}
