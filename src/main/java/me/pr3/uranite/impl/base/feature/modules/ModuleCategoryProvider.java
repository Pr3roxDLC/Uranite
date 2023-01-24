package me.pr3.uranite.impl.base.feature.modules;

import me.pr3.cdi.annotations.scopes.ClientScoped;
import me.pr3.uranite.api.feature.module.IModuleCategory;

import java.util.*;

@ClientScoped
public class ModuleCategoryProvider {

    public static final ModuleCategory MOVEMENT = new ModuleCategory("MOVEMENT", "Movement");
    public static final ModuleCategory PLAYER = new ModuleCategory("PLAYER", "Player");
    public static final ModuleCategory COMBAT = new ModuleCategory("COMBAT", "Combat");
    public static final ModuleCategory CHAT = new ModuleCategory("CHAT", "Chat");

    public List<ModuleCategory> getCategories(){
        return Arrays.asList(MOVEMENT, PLAYER, COMBAT, CHAT);
    }

    public Optional<ModuleCategory> getModuleCategory(String key){
        return getCategories().stream().filter(moduleCategory -> Objects.equals(moduleCategory.key, key)).findFirst();
    }

    public static class ModuleCategory implements IModuleCategory {
        public final String key;
        public final String name;

        public ModuleCategory(String key, String name){
            this.key = key;
            this.name = name;
        }

        @Override
        public String getName() {
            return name;
        }
    }

}
