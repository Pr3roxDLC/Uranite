package me.pr3.uranite.impl.base.feature.modules;

import me.pr3.cdi.annotations.Inject;
import me.pr3.cdi.extensions.events.EventManager;
import me.pr3.uranite.api.feature.module.IModule;
import me.pr3.uranite.api.feature.module.IModuleCategory;
import me.pr3.uranite.impl.base.managers.ModuleManager;

public abstract class AbstractModule implements IModule {
    private String name = "";
    private String category = "";
    private String description = "";
    private boolean enabled = false;

    @Inject
    private ModuleCategoryProvider moduleCategoryProvider;

    @Inject
    private ModuleManager moduleManager;

    public AbstractModule() {
        Module module = this.getClass().getAnnotation(Module.class);
        name = module.name();
        category = module.category();
        description = module.description();
        moduleManager.setModuleInstance(name, this);
    }


    @Override
    public String getName() {
        return name;
    }

    @Override
    public IModuleCategory getCategory() {
        return moduleCategoryProvider.getModuleCategory(category).orElseThrow(() -> new IllegalArgumentException("No Such Module Category: " + category));
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void onEnable() {
        EventManager.subscribe(this);
    }

    public void onDisable() {
        EventManager.unsubscribe(this);
    }

    @Override
    public void setEnabled(boolean enabled) {
        if (!this.enabled && enabled) {
            onEnable();
        }
        if (this.enabled && !enabled) {
            onDisable();
        }
    }
}
