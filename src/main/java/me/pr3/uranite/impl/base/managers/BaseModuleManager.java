package me.pr3.uranite.impl.base.managers;

import com.google.inject.Inject;
import me.pr3.uranite.Uranite;
import me.pr3.uranite.impl.base.annotations.Module;
import me.pr3.uranite.api.feature.module.IModule;
import me.pr3.uranite.api.managers.IModuleManager;
import me.pr3.uranite.impl.base.annotations.scopes.ClientScoped;
import net.minecraftforge.common.MinecraftForge;
import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.util.*;

import static me.pr3.catcher.Catcher.TRY;

@ClientScoped
public class BaseModuleManager implements IModuleManager {

    private final Map<String, IModule> moduleMap = new HashMap<>();
    private static final Set<Class<?>> moduleClasses = new HashSet<>();

    @Override
    public void init() {
        moduleClasses.addAll(new Reflections().getTypesAnnotatedWith(Module.class));
        updateModules(ClientScoped.class);
    }

    //Produces a new Instance for all modules annotated with one of the given Scopes
    public void updateModules(Class<?>... scopes) {
        moduleClasses.forEach(moduleClass -> {
            for (Class<?> scopeClass : scopes) {
                if (moduleClass.isAnnotationPresent((Class<? extends Annotation>) scopeClass)) {
                    TRY(() -> {
                        String moduleName = moduleClass.getDeclaredAnnotation(Module.class).name();
                        if (moduleMap.containsKey(moduleName)) {
                            MinecraftForge.EVENT_BUS.unregister(moduleMap.get(moduleName));
                        }
                        IModule module = (IModule) Uranite.INJECTOR.getInstance(moduleClass);
                        moduleMap.put(moduleName, module);
                    }).CATCH(Throwable::printStackTrace);
                }
            }
        });
    }

    @Override
    public Collection<IModule> getModules() {
        return moduleMap.values();
    }


}
