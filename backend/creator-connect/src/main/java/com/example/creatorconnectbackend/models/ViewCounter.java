package com.example.creatorconnectbackend.models;

import java.util.Date;

/**
 * -----------------------------------------------------------------------------
 *                            ViewCounter Class
 * -----------------------------------------------------------------------------
 * Purpose of Class:
 *  Represents a view counter entity that tracks the number of views for an influencer
 *  by an organization in the Creator Connect system. It encapsulates the data related to
 *  views and interactions between influencers and organizations.
 *
 * Functions:
 *  - Getters & Setters for:
 *    - InfluencerID: To get and set the unique identifier of the influencer.
 *    - OrgId: To get and set the unique identifier of the organization.
 *    - Date: To get and set the date when the interaction occurred.
 *
 * -----------------------------------------------------------------------------
 */
public class ViewCounter {


    private Long InfluencerID;

    private Long OrgId;

    private Date Date;

    // Getters and Setters

    /**
     * @return the unique identifier of the influencer.
     */
    public Long getInfluencerId() {
        return InfluencerID;
    }

    /**
     * @param InfluencerID the unique identifier to set for the influencer.
     */
    public void setInfluencerId(Long InfluencerID) {
        this.InfluencerID = InfluencerID;
    }

    /**
     * @return the unique identifier of the organization.
     */
    public Long getOrgId() {
        return OrgId;
    }

    /**
     * @param OrgId the unique identifier to set for the organization.
     */
    public void setOrgId(Long OrgId) {
        this.OrgId = OrgId;
    }

    /**
     * @return the date when the interaction occurred.
     */
    public Date getDate() {
        return Date;
    }

    /**
     * @param Date the date to set for when the interaction occurred.
     */
    public void setDate(Date Date) {
        this.Date = Date;
    }
}
