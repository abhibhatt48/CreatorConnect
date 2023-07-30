/**
 * -----------------------------------------------------------------------------
 *                     Connection Request Model Class
 * -----------------------------------------------------------------------------
 * 
 * Purpose:
 * Represents a request for a connection between an organization and an influencer.
 * Typically, an organization may want to collaborate with an influencer for 
 * promotional activities. This class acts as the data structure for such 
 * collaboration requests, capturing essential information regarding the 
 * originator, recipient, and the status of the request.
 * 
 * Key Attributes:
 * - requestID: A unique identifier for the connection request.
 * - orgID: The ID corresponding to the organization that initiates the request.
 * - influencerID: The ID corresponding to the influencer being approached for collaboration.
 * - requestMessage: An optional message detailing the purpose or context of the connection request.
 * - requestStatus: The current status of the request (e.g., Pending, Accepted, Declined).
 * 
 * Constraints:
 * - requestMessage: The length should not exceed 500 characters to maintain consistency and avoid excessive verbosity.
 * - requestStatus: Should always be provided when creating a new connection request instance.
 * 
 * Usage:
 * This model can be used in conjunction with other models such as 'Influencer' 
 * and 'Organization' to facilitate collaborations and partnerships within the 
 * 'com.example.creatorconnectbackend' application. A typical workflow may involve 
 * an organization sending a connection request to an influencer, awaiting their 
 * response, and subsequently collaborating upon approval.
 * 
 * Enhancements (Future Consideration):
 * - Include timestamps to track when the request was made and when it was last updated.
 * - Incorporate a mechanism for influencers to respond with counter-offers or feedback.
 * - Enable notifications or alerts for both parties when there are updates to a connection request.
 * 
 * -----------------------------------------------------------------------------
 */

package com.example.creatorconnectbackend.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ConnectionRequest {

    private Long requestID;

    private Long orgID;

    private Long influencerID;

    @Size(max = 500)
    private String requestMessage;

    @NotNull
    private RequestStatus requestStatus;

    public Long getRequestID() {
        return requestID;
    }

    public void setRequestID(Long requestID) {
        this.requestID = requestID;
    }

    public Long getOrgID() {
        return orgID;
    }

    public void setOrgID(Long orgID) {
        this.orgID = orgID;
    }

    public Long getInfluencerID() {
        return influencerID;
    }

    public void setInfluencerID(Long influencerID) {
        this.influencerID = influencerID;
    }

    public String getRequestMessage() {
        return requestMessage;
    }

    public void setRequestMessage(String requestMessage) {
        this.requestMessage = requestMessage;
    }

    public RequestStatus getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(RequestStatus requestStatus) {
        this.requestStatus = requestStatus;
    }    
}
