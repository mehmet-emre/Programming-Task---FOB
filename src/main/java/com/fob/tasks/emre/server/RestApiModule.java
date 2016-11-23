package com.fob.tasks.emre.server;

import com.fob.tasks.emre.services.StackServiceBasicImpl;
import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.Scopes;

/**
 * Guice module to bind dependencies
 * 
 * Why not using spring dependency injection (@Autowired etc) ?
 * Using spring DI would have been better (i already have those dependencies with spring and one might consider tha Guice is
 * an additional unnecessary dependency in this project), but i am not familiar with spring dependency injection,
 * i am using spring mostly for its other features (embedded Tomcat, MockMvc for testing etc)
 * and so far i have used mostly Guice in my projects.
 * 
 * @author Emre
 */
public class RestApiModule implements Module {

    @Override
    public void configure(Binder binder) {
        // no Just-in-time binding magic..
        binder.requireExplicitBindings();

        binder.bind(StackServiceBasicImpl.class).in(Scopes.SINGLETON);
    }
}
