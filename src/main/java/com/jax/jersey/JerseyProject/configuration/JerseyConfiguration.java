package com.jax.jersey.JerseyProject.configuration;

import com.jax.jersey.JerseyProject.exception.DataNotFoundExceptionMapper;
import com.jax.jersey.JerseyProject.exception.GenericExceptionMapper;
import com.jax.jersey.JerseyProject.filter.LoggingFilter;
import com.jax.jersey.JerseyProject.filter.PoweredByResponseFilter;
import com.jax.jersey.JerseyProject.resource.CommentResource;
import com.jax.jersey.JerseyProject.resource.InjectDemoResource;
import com.jax.jersey.JerseyProject.resource.MessageResource;
import com.jax.jersey.JerseyProject.resource.ProfileResource;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfiguration extends ResourceConfig {
    public JerseyConfiguration() {
        register(MessageResource.class);
        register(ProfileResource.class);
        register(InjectDemoResource.class);
        register(CommentResource.class);
//        register(DataNotFoundExceptionMapper.class);
//        register(GenericExceptionMapper.class);
        register(PoweredByResponseFilter.class);
        register(LoggingFilter.class);
    }
}
