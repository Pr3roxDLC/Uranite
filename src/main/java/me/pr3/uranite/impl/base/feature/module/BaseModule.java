package me.pr3.uranite.impl.base.feature.module;

import me.pr3.uranite.impl.base.annotations.Module;
import me.pr3.uranite.api.feature.module.IModule;
import me.pr3.uranite.api.feature.module.IModuleCategory;
import net.minecraftforge.common.MinecraftForge;

import java.util.Objects;

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
        if(this.enabled != enabled){
            if(enabled){
                this.onEnabled();
            }else {
                this.onDisabled();
            }
        }
        this.enabled = enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseModule that = (BaseModule) o;
        return Objects.equals(name, that.name) && Objects.equals(category, that.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, category);
    }

    public void onEnabled(){
        MinecraftForge.EVENT_BUS.register(this);
    }

    public void onDisabled(){
        MinecraftForge.EVENT_BUS.unregister(this);
    }
}
