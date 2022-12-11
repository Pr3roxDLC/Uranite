package me.pr3.uranite.impl.base.event;

import me.pr3.uranite.impl.base.annotations.event.Observes;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

import static me.pr3.catcher.Catcher.TRY;

public class EventBus {

    public EventBus(){
        MinecraftForge.EVENT_BUS.register(this);
    }

    Map<Class<? extends Event>, Set<Pair<Object, Method>>> listenerMap = new HashMap<>();

    //This maybe could be improved by caching which methods listen to what event for each class so we dont have to loop over all
    //the methods in a class everytime we subscribe an instance
    @SuppressWarnings("unchecked")
    public void subscribe(Object object){
        for (Method n : object.getClass().getMethods()) {
            TRY(() -> {
                if (n.getParameterAnnotations()[0][0] instanceof Observes) {
                    if(!listenerMap.containsKey(object.getClass())){
                        listenerMap.put((Class<? extends Event>) n.getParameters()[0].getType(), new HashSet<>());
                    }
                    listenerMap.get(n.getParameters()[0].getType()).add(new ImmutablePair<>(object, n));
                }
            });
        }

    }

    public void unsubscribe(Object object){
        for (Map.Entry<Class<? extends Event>, Set<Pair<Object, Method>>> classSetEntry : listenerMap.entrySet()) {
            Class<? extends Event> eventClass = classSetEntry.getKey();
            Set<Pair<Object, Method>> pairs = classSetEntry.getValue();
            pairs.removeAll(pairs.stream().filter(p -> p.getLeft().equals(object)).collect(Collectors.toSet()));
        }
    }

    @SubscribeEvent
    public void onAnyEvent(Event e){
        if(listenerMap.containsKey(e.getClass())){
           listenerMap.get(e.getClass()).forEach(n -> {
               TRY(() -> {
                   n.getRight().invoke(n.getLeft(), e);
               });
           });

        }
    }



}
