package me.pr3.uranite.impl.base.feature.module;

import me.pr3.uranite.impl.base.annotations.Module;
import me.pr3.uranite.api.feature.module.IModule;
import me.pr3.uranite.api.feature.module.IModuleCategory;

public class BaseModule implements IModule {

    private final String name;
    private final IModuleCategory category;
    private final String description;
    private boolean enabled = false;

    public BaseModule(){
        name = this.getClass().getAnnotation(Module.class).name();
        category = BaseModuleCategory.valueOf(this.getClass().getAnnotation(Module.class).category());
        description = this.getClass().getAnnotation(Module.class).description();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public IModuleCategory getCategory() {
        return category;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
