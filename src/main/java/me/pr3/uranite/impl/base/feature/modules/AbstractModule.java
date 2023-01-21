package me.pr3.uranite.impl.base.feature.modules;

import me.pr3.cdi.annotations.Inject;
import me.pr3.cdi.extensions.events.EventManager;
import me.pr3.uranite.api.feature.module.IModule;
import me.pr3.uranite.api.feature.module.IModuleCategory;

public abstract class AbstractModule implements IModule {
    private String name = "";
    private String category = "";
    private String description = "";
    private boolean enabled = true;

    @Inject
    private ModuleCategoryProvider moduleCategoryProvider;

    public AbstractModule(){
      Module module = this.getClass().getAnnotation(Module.class);
      name = module.name();
      category = module.category();
      description = module.description();
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

    public void onEnable(){
        EventManager.subscribe(this);
    }

    public void onDisable(){
        EventManager.unsubscribe(this);
    }

    @Override
    public void setEnabled(boolean enabled) {
        if(this.enabled && !enabled){

        }
    }
}
