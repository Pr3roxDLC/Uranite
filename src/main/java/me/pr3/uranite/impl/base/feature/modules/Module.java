package me.pr3.uranite.impl.base.feature.modules;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Module {
    String name();
    String category();
    String description();
}
