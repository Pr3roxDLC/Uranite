package me.pr3.uranite.impl.base;

import com.google.inject.AbstractModule;
import com.google.inject.ScopeAnnotation;
import com.google.inject.multibindings.Multibinder;
import me.pr3.uranite.Uranite;
import me.pr3.uranite.impl.base.util.scopes.ScopeModule;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.util.*;
import java.util.stream.Collectors;

@SuppressWarnings("deprecation")
public class BaseGuiceModule extends AbstractModule {

    //HashMap keeping track of the current instance for each class
    public static Map<Class<?>, Object> instanceMap = new HashMap<>();

    //Do all the binding automatically, custom implementation of @Specializes
    @Override
    protected void configure() {
//        Set<Class<?>> scopes = new HashSet<>(new Reflections().getTypesAnnotatedWith(ScopeAnnotation.class));
//        System.out.println(scopes);
//        Set<Class<?>> classes = new HashSet<>(new Reflections("me.pr3.uranite", new SubTypesScanner(false)).getSubTypesOf(Object.class));
//        System.out.println(classes);
//
//        Set<Class<?>> scopedClasses = classes.stream()
//                .filter(clazz -> isClassAnnotatedWithOneOfProvided(clazz, scopes))
//                .collect(Collectors.toSet());
//        System.out.println(scopedClasses);
//
//        //All classes that are @Scoped will be supplied as an @Inject(able) Dependency, to access a dependency you can either use the interface your class implements
//        //or use the implementation, or the specialization of your class declared with @Specializes
//        for (Class clazz : scopedClasses) {
//            System.out.println("Binding class: " + clazz.getName());
//            instanceMap.put(clazz, null);
//            bind(clazz).toConstructor(clazz.getConstructors()[0]);
//        }
//
//        bind(IModuleManager.class).to(BaseModuleManager.class);

        Set<Class<?>> scopes = new HashSet<>(new Reflections().getTypesAnnotatedWith(ScopeAnnotation.class));
        System.out.println(scopes);
        Set<Class<?>> classes = new HashSet<>(new Reflections("me.pr3.uranite", new SubTypesScanner(false)).getSubTypesOf(Object.class));
        System.out.println(classes);

        Set<Class<?>> scopedClasses = classes.stream()
                .filter(clazz -> isClassAnnotatedWithOneOfProvided(clazz, scopes))
                .collect(Collectors.toSet());
        System.out.println(scopedClasses);

        Multibinder<Object> multibinder = Multibinder.newSetBinder(binder(), Object.class);

        for (Class scopedClass : scopedClasses) {

            Class<? extends Annotation> scope = getScopeAnnotationForClass(scopedClass);
            if(scope!=null) {
                System.out.println("Binding " + scopedClass + " to " + scope);
                multibinder.addBinding().to(scopedClass).in(scope);
            }

        }


        install(new ScopeModule());


    }

    public Class<? extends Annotation> getScopeAnnotationForClass(Class<?> clazz) {
        for (Class<?> scope : Uranite.SCOPES) {
            if (clazz.isAnnotationPresent((Class<? extends Annotation>) scope)) {
                return (Class<? extends Annotation>) scope;
            }
        }
        return null;
    }

    public boolean isClassAnnotatedWithOneOfProvided(Class<?> clazz, Set<Class<?>> annotations) {
        for (Annotation annotation : clazz.getDeclaredAnnotations()) {
            if (annotations.contains(annotation.annotationType())) return true;
        }
        return false;
    }


}
