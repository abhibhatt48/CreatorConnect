package com.example.creatorconnectbackend.interfaces;

import com.example.creatorconnectbackend.models.ViewCounter;

import java.util.Map;

//defining the contract for services related to view counting functionalities.
public interface ViewCounterServiceInterface {

    // Record a new view entry for a specific content or influencer and return the updated ViewCounter object.
    ViewCounter addView(ViewCounter viewCounter);
    
    // Fetch the total number of views for a given influencer by their ID. 
    // The returned map associates each content ID with its respective view count.
    Map<Long, Integer> getViewsByInfluencerID(Long id);

    // Fetch the total number of profile views for a given company by their type.
    // The returned map associates each company type with its respective view count.
    Map<String, Integer> getProfileViewsByCompanyType(Long id);
}
