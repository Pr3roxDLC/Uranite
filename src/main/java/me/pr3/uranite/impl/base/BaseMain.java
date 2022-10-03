package me.pr3.uranite.impl.base;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import me.pr3.uranite.Uranite;
import me.pr3.uranite.impl.base.managers.BaseModuleManager;
import me.pr3.uranite.impl.base.managers.ScopeManager;
import me.pr3.uranite.impl.base.util.SimpleScope;

public class BaseMain {

    @Inject
    public BaseMain( @Named("clientScope") SimpleScope scope){
        //This is the client lifecycle, everything happens in here

        //Enter the @ClientScope
        scope.enter();
        //Not required as it represents the entire lifecycle of the client, only here for the sake of correctness
        Runtime.getRuntime().addShutdownHook(new Thread(scope::exit));
        //Client initialization
        BaseModuleManager manager = Uranite.INJECTOR.getInstance(BaseModuleManager.class);
        manager.init();
        ScopeManager scopeManager = Uranite.INJECTOR.getInstance(ScopeManager.class);
        scopeManager.init();


    }


}
