/**
 * -----------------------------------------------------------------------------
 *           Connection Request Service Interface
 * -----------------------------------------------------------------------------
 * Purpose:
 * The 'ConnectionRequestServiceInterface' provides a clear contract for managing 
 * connection requests within the 'com.example.creatorconnectbackend' application.
 * It outlines essential CRUD operations along with custom methods tailored to 
 * specific functionalities, like updating the request status or modifying the message.
 *
 * Key Features:
 * 1. Creation: Allows creation of a new connection request.
 * 2. Retrieval: Fetches a specific connection request by its unique ID.
 * 3. Update: Provides flexibility in updating the status or the message of a request.
 * 4. Deletion: Deletes a connection request by its unique ID.
 * 
 * Data Models:
 * - ConnectionRequest: Represents a connection request, including information 
 *   like the sender, recipient, status, and associated message.
 * - RequestStatus: Enumerates possible states for a connection request (e.g., 
 *   PENDING, ACCEPTED, DECLINED).
 *
 * -----------------------------------------------------------------------------
 */

package com.example.creatorconnectbackend.interfaces;

import com.example.creatorconnectbackend.models.ConnectionRequest;
import com.example.creatorconnectbackend.models.RequestStatus;

import java.util.Map;

public interface ConnectionRequestServiceInterface {

    ConnectionRequest createRequest(ConnectionRequest connectionRequest);

    
    ConnectionRequest getConnectionRequestByID(Long requestID);

    
    ConnectionRequest updateStatus(Long id, RequestStatus newStatus);

    
    ConnectionRequest updateMessage(Long id, Map<String, String> message);

    
    void deleteByID(Long id);
}
