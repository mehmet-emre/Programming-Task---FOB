package com.fob.tasks.emre.server;

import com.fob.tasks.emre.services.StackServiceBasicImpl;
import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.Scopes;

public class RestApiModule implements Module {

    @Override
    public void configure(Binder binder) {
        // no Just-in-time binding magic..
        binder.requireExplicitBindings();

        binder.bind(StackServiceBasicImpl.class).in(Scopes.SINGLETON);
    }
}
