package me.pr3.uranite.impl.base.feature.module;

import me.pr3.uranite.api.feature.module.IModuleCategory;

import java.util.ArrayList;
import java.util.Collection;

public class BaseModuleCategory implements IModuleCategory {
    public static IModuleCategory WORLD = new BaseModuleCategory("WORLD");
    public static IModuleCategory PLAYER = new BaseModuleCategory("PLAYER");
    public static IModuleCategory MISC = new BaseModuleCategory("MISC");
    public static IModuleCategory CHAT = new BaseModuleCategory("CHAT");

    public static Collection<IModuleCategory> MODULE_CATEGORIES = new ArrayList<>();


    private final String name;

    public static IModuleCategory valueOf(String string) {
        return MODULE_CATEGORIES.stream()
                .filter(iModuleCategory -> iModuleCategory.getName().equals(string))
                .findFirst().orElseThrow(() -> new IllegalArgumentException("No Such Module Category"));
    }

    public BaseModuleCategory(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    static {
        MODULE_CATEGORIES.add(WORLD);
        MODULE_CATEGORIES.add(PLAYER);
        MODULE_CATEGORIES.add(MISC);
        MODULE_CATEGORIES.add(CHAT);
    }
}
