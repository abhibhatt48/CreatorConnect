/**
 * -----------------------------------------------------------------------------
 *           Influencer Service Interface
 * -----------------------------------------------------------------------------
 * Purpose:
 * The 'InfluencerServiceInterface' delineates a standardized contract for 
 * the management and manipulation of influencers within the 
 * 'com.example.creatorconnectbackend' application. This interface is crucial
 * for ensuring a uniform approach to influencer operations, such as registration, 
 * retrieval, updating, listing, and deletion.
 *
 * Key Features:
 * 1. Registration: Facilitates the addition of a new influencer tied to a user ID.
 * 2. Retrieval: Offers methods to fetch influencers either individually by ID 
 *    or as an entire list.
 * 3. Update: Enables the modification of existing influencer data.
 * 4. Deletion: Grants the ability to remove an influencer based on their unique ID.
 *
 * Data Models:
 * - Influencer: Represents the influencer model, encapsulating properties 
 *   like their profile, metrics, niche, and any other relevant attributes.
 * -----------------------------------------------------------------------------
 */

package com.example.creatorconnectbackend.interfaces;

import java.util.List;
import com.example.creatorconnectbackend.models.Influencer;

public interface InfluencerServiceInterface {


    Influencer register(Influencer influencer, Long userId);


    Influencer getById(Long id);

    Influencer update(Long id, Influencer updatedInfluencer);

    List<Influencer> getAll();

    void deleteById(Long id);
}
