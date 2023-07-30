package com.example.creatorconnectbackend.interfaces;

import com.example.creatorconnectbackend.models.ConnectionRequest;
import com.example.creatorconnectbackend.models.RequestStatus;

import java.util.Map;

// defining the contract for services related to connection requests.
public interface ConnectionRequestServiceInterface {

    // Create a new connection request and return the created request.
    ConnectionRequest createRequest(ConnectionRequest connectionRequest);

    // Retrieve a connection request based on its unique ID.
    ConnectionRequest getConnectionRequestByID(Long requestID);

    // Update the status of a connection request based on its unique ID and return the updated request.
    ConnectionRequest updateStatus(Long id, RequestStatus newStatus);

    // Update the message for a connection request based on its unique ID and return the updated request.
    ConnectionRequest updateMessage(Long id, Map<String, String> message);

    // Delete a connection request based on its unique ID.
    void deleteByID(Long id);
}
