package com.fob.tasks.emre.common;

import com.google.inject.Injector;

public class InjectionManager {

    /**
     * Global injector
     */
    private static Injector injector;

    public static void loadInjector(final Injector i) {
        injector = i;
    }

    public static <T> T getInstance(final Class<T> clazz) {
        return injector.getInstance(clazz);
    }
}
