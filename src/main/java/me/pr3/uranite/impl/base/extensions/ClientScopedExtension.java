package me.pr3.uranite.impl.base.extensions;


import me.pr3.uranite.impl.base.annotations.scopes.ClientScopeEnabled;
import me.pr3.uranite.impl.base.annotations.scopes.ClientScoped;
import org.tomitribe.microscoped.core.ScopeContext;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.AfterBeanDiscovery;
import javax.enterprise.inject.spi.BeforeBeanDiscovery;
import javax.enterprise.inject.spi.Extension;



public class ClientScopedExtension implements Extension {

    public void beforeBeanDiscovery(@Observes BeforeBeanDiscovery bbd) {
        bbd.addScope(ClientScoped.class, true, false);
        bbd.addInterceptorBinding(ClientScopeEnabled.class);
    }

    public void afterBeanDiscovery(@Observes AfterBeanDiscovery abd) {

        abd.addContext(new ScopeContext<>(ClientScoped.class));

    }
}