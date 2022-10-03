package me.pr3.uranite.impl.base.feature.module;

import me.pr3.uranite.api.feature.module.IModuleCategory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

//Fake enum to enable extension of this class
 public class BaseModuleCategory implements IModuleCategory {

    public static final IModuleCategory WORLD = new BaseModuleCategory("WORLD");
    public static final IModuleCategory PLAYER = new BaseModuleCategory("PLAYER");
    public static final IModuleCategory MISC = new BaseModuleCategory("MISC");
    public static final IModuleCategory CHAT = new BaseModuleCategory("CHAT");

    private static final Collection<IModuleCategory> MODULE_CATEGORIES = new ArrayList<>();


    private final String name;

    public static IModuleCategory valueOf(String string) {
        return MODULE_CATEGORIES.stream()
                .filter(iModuleCategory -> iModuleCategory.getName().equals(string))
                .findFirst().orElseThrow(() -> new IllegalArgumentException("No Such Module Category"));
    }

    public static Collection<IModuleCategory> values(){
        return MODULE_CATEGORIES;
    }


    public BaseModuleCategory(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseModuleCategory that = (BaseModuleCategory) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    static {
        MODULE_CATEGORIES.add(WORLD);
        MODULE_CATEGORIES.add(PLAYER);
        MODULE_CATEGORIES.add(MISC);
        MODULE_CATEGORIES.add(CHAT);
    }
}
