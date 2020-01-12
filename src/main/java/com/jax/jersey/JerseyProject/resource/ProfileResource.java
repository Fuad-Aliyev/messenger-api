package com.jax.jersey.JerseyProject.resource;

import com.jax.jersey.JerseyProject.model.Message;
import com.jax.jersey.JerseyProject.model.Profile;
import com.jax.jersey.JerseyProject.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/profiles")
public class ProfileResource {
    @Autowired
    ProfileService profileService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Profile> getProfiles() {
        return profileService.getAllProfiles();
    }

    @GET
    @Path("/{profileName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Profile getProfile(@PathParam("profileName") String profileName) {
        return profileService.getProfile(profileName);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Profile addMessage(Profile profile) {
        return profileService.addProfile(profile);
    }

    @PUT
    @Path("/{profileName}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Profile updateMessage(@PathParam("profileName") String profileName, Profile profile) {
        profile.setProfileName(profileName);
        return profileService.updateProfile(profile);
    }

    @DELETE
    @Path("/{profileName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Profile deleteMesssage(@PathParam("profileName") String profileName) {
        return profileService.removeProfile(profileName);
    }
}
