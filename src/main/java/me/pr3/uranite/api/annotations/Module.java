package me.pr3.uranite.api.annotations;

import me.pr3.uranite.api.feature.module.IModuleCategory;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Module {

    String name();
    String category();
    String description();
}
