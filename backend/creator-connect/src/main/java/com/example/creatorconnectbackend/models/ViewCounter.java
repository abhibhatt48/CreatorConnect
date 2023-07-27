package com.example.creatorconnectbackend.models;

import java.util.Date;

public class ViewCounter {

    private Long InfluencerID;
    private Long OrgId;
    private Date Date;

    public Long getInfluencerId() {
        return InfluencerID;
    }

    public void setInfluencerId(Long InfluencerID) {
        this.InfluencerID = InfluencerID;
    }

    public Long getOrgId() {
        return OrgId;
    }

    public void setOrgId(Long OrgId) {
        this.OrgId = OrgId;
    }

    public Date getDate() {
        return Date;
    }

    public void setDate(Date Date) {
        this.Date = Date;
    }
}
