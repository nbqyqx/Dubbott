package com.tops001.dubbott.test.exception.model.mapped;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import com.tops001.dubbott.test.exception.api.mapped.GuestbookService;

/**
 * The JAX-RS Application config class.
 */
public class GuestbookApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<Class<?>>();
        classes.add(GuestbookService.class);
        classes.add(WebApplicationExceptionMapProvider.class);
        classes.add(RuntimeExceptionMappingProvider.class);
        classes.add(NullPointerExceptionMapProvider.class);
        classes.add(GuestbookErrorExceptionMappingProvider.class);
        classes.add(GuestbookExceptionMapProvider.class);
        return classes;
    }

}
