package com.myfitness.service;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/restful-services")
public class MyResource extends Application {

    private Set<Object> singletons = new HashSet<>();

    public MyResource() {
        // Register your service class here
        singletons.add(new FitnessService());
    }

    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }
}
