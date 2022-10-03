package me.pr3.uranite.impl.base;

import com.google.inject.AbstractModule;
import com.google.inject.ScopeAnnotation;
import me.pr3.uranite.api.managers.IModuleManager;
import me.pr3.uranite.impl.base.managers.BaseModuleManager;
import me.pr3.uranite.impl.base.util.scopes.ScopeModule;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class BaseGuiceModule extends AbstractModule {

    //Do all the binding automatically, custom implementation of @Specializes
    @Override
    protected void configure() {
        Set<Class<?>> scopes = new HashSet<>(new Reflections().getTypesAnnotatedWith(ScopeAnnotation.class));
        System.out.println(scopes);
        Set<Class<?>> classes = new HashSet<>(new Reflections("me.pr3.uranite", new SubTypesScanner(false)).getSubTypesOf(Object.class));
        System.out.println(classes);

        Set<Class<?>> scopedClasses = classes.stream()
                .filter(clazz -> isClassAnnotatedWithOneOfProvided(clazz, scopes))
                .collect(Collectors.toSet());
        System.out.println(scopedClasses);

        //All classes that are @Scoped will be supplied as an @Inject(able) Dependency, to access a dependency you can either use the interface your class implements
        //or use the implementation, or the specialization of your class declared with @Specializes
        for (Class clazz : scopedClasses) {
            System.out.println("Binding class: " + clazz.getName());
            bind(clazz);
        }

        bind(IModuleManager.class).to(BaseModuleManager.class);

        install(new ScopeModule());

    }

    public boolean isClassAnnotatedWithOneOfProvided(Class<?> clazz, Set<Class<?>> annotations) {
        for (Annotation annotation : clazz.getDeclaredAnnotations()) {
            if (annotations.contains(annotation.annotationType())) return true;
        }
        return false;
    }
}
