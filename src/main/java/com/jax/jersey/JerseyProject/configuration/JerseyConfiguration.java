package com.jax.jersey.JerseyProject.configuration;

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
    }
}