package com.jax.jersey.JerseyProject.service;

import com.jax.jersey.JerseyProject.database.DatabaseClass;
import com.jax.jersey.JerseyProject.model.Message;
import com.jax.jersey.JerseyProject.model.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class ProfileService {
    private Map<String, Profile> profileMap = DatabaseClass.getProfiles();

    public ProfileService() {
        profileMap.put("Developer", new Profile(1, "Developer", "Fuad", "Aliyev"));
    }

    public List<Profile> getAllProfiles() {
        return new ArrayList<Profile>(profileMap.values());
    }

    public Profile getProfile(String profileName) {
        return profileMap.get(profileName);
    }

    public Profile addProfile(Profile profile) {
        profile.setId(profileMap.size() + 1);
        profileMap.put(profile.getProfileName(), profile);
        return profile;
    }

    public Profile updateProfile(Profile profile) {
        if(profile.getId() <= 0)
            return null;
        profileMap.put(profile.getProfileName(), profile);
        return profile;
    }

    public Profile removeProfile(String profileName) {
        return profileMap.remove(profileName);
    }
}
