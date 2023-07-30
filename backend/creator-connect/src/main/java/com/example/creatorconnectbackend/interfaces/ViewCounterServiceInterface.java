/**
 * -----------------------------------------------------------------------------
 *              View Counter Service Interface
 * -----------------------------------------------------------------------------
 * Purpose:
 * The 'ViewCounterServiceInterface' provides a structured approach for 
 * services linked to view counting functionalities within the 
 * 'com.example.creatorconnectbackend' application. It defines standardized 
 * operations to record, retrieve, and analyze profile views for influencers 
 * and organizations.
 *
 * Key Features:
 * 1. Add View: Records a view for an influencer's or organization's profile.
 * 2. Views by Influencer: Allows retrieval of the total number of views 
 *    received by a specific influencer, identified by their unique ID.
 * 3. Views by Company Type: Enables analysis and aggregation of profile views 
 *    based on the type of company.
 *
 *
 * Data Models:
 * - ViewCounter: Represents the structure of a view count entry, capturing 
 *   details like the viewer, viewed profile, timestamp, and other relevant 
 *   attributes.
 *
 * Usage:
 * This interface can be especially beneficial for analytic tools or 
 * dashboards within the application that aim to offer insights on user 
 * engagement, profile popularity, or marketing effectiveness.
 * -----------------------------------------------------------------------------
 */

package com.example.creatorconnectbackend.interfaces;

import com.example.creatorconnectbackend.models.ViewCounter;

import java.util.Map;

public interface ViewCounterServiceInterface {

    
    ViewCounter addView(ViewCounter viewCounter);
    
    
    Map<Long, Integer> getViewsByInfluencerID(Long id);


    Map<String, Integer> getProfileViewsByCompanyType(Long id);
}
