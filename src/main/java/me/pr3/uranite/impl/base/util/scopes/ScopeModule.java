package me.pr3.uranite.impl.base.util.scopes;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import me.pr3.uranite.impl.base.annotations.scopes.ClientScoped;
import me.pr3.uranite.impl.base.annotations.scopes.LifeScoped;
import me.pr3.uranite.impl.base.annotations.scopes.ServerScoped;
import me.pr3.uranite.impl.base.annotations.scopes.WorldScoped;

public class ScopeModule extends AbstractModule {
    private final SimpleScope clientScope = new SimpleScope();
    private final SimpleScope lifeScope = new SimpleScope();
    private final SimpleScope serverScope = new SimpleScope();
    private final SimpleScope worldScope = new SimpleScope();

    @Override
    protected void configure() {
        // tell Guice about the scope
        bindScope(ClientScoped.class, clientScope);
        bindScope(LifeScoped.class, lifeScope);
        bindScope(ServerScoped.class, serverScope);
        bindScope(WorldScoped.class, worldScope);
    }

    @Provides
    @Named("clientScope")
    SimpleScope provideClientScope() {
        return clientScope;
    }
    @Provides
    @Named("lifeScope")
    SimpleScope provideLifeScope() {
        return lifeScope;
    }
    @Provides
    @Named("serverScope")
    SimpleScope provideServerScope() {
        return serverScope;
    }
    @Provides
    @Named("worldScope")
    SimpleScope provideWorldScope() {
        return worldScope;
    }
}
