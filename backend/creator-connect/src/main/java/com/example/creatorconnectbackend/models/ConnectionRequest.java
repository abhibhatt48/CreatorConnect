package com.example.creatorconnectbackend.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

// This class represents  connection requests, presumably between an organization and an influencer.
public class ConnectionRequest {

    // Unique identifier for each connection request
    private Long requestID;

    // The ID of the organization sending or receiving the connection request
    private Long orgID;

    // The ID of the influencer sending or receiving the connection request
    private Long influencerID;

    // A message accompanying the connection request. Its size is constrained to be 500 characters at most.
    @Size(max = 500)
    private String requestMessage;

    // Status of the connection request (e.g., pending, accepted, declined). The status cannot be null.
    @NotNull
    private RequestStatus requestStatus;

    // Getter for requestID
    public Long getRequestID() {
		return requestID;
	}

    // Setter for requestID
	public void setRequestID(Long requestID) {
		this.requestID = requestID;
	}

    // Getter for orgID
	public Long getOrgID() {
		return orgID;
	}

    // Setter for orgID
	public void setOrgID(Long orgID) {
		this.orgID = orgID;
	}

    // Getter for influencerID
	public Long getInfluencerID() {
		return influencerID;
	}

    // Setter for influencerID
	public void setInfluencerID(Long influencerID) {
		this.influencerID = influencerID;
	}

    // Getter for requestMessage
	public String getRequestMessage() {
		return requestMessage;
	}

    // Setter for requestMessage
	public void setRequestMessage(String requestMessage) {
		this.requestMessage = requestMessage;
	}

    // Getter for requestStatus
	public RequestStatus getRequestStatus() {
		return requestStatus;
	}

    // Setter for requestStatus
	public void setRequestStatus(RequestStatus requestStatus) {
		this.requestStatus = requestStatus;
	}    
}
