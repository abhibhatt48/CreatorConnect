package com.example.creatorconnectbackend.interfaces;

import java.util.List;
import com.example.creatorconnectbackend.models.Influencer;

//defining the contract for services related to influencers.
public interface InfluencerServiceInterface {
    
    // Register a new influencer associated with a user ID and return the registered influencer.
    Influencer register(Influencer influencer, Long userId);

    // Retrieve an influencer based on its unique ID.
    Influencer getById(Long id);

    // Update the details of an existing influencer based on its unique ID and return the updated influencer.
    Influencer update(Long id, Influencer updatedInfluencer);

    // Fetch a list of all influencers present in the database.
    List<Influencer> getAll();

    // Delete an influencer based on its unique ID.
    void deleteById(Long id);
}
