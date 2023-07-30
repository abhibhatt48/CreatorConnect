package com.example.creatorconnectbackend.models;

/**
 * -----------------------------------------------------------------------------
 *                           RequestStatus Enum
 * -----------------------------------------------------------------------------
 * 
 * Purpose:
 * The `RequestStatus` enum defines the various potential statuses that a request 
 * (like a connection request) can have within the 'com.example.creatorconnectbackend' application.
 * 
 * Enum Values:
 * - Pending: Indicates that the request has been made but has not yet been acted upon.
 * - Accepted: Indicates that the request has been approved or accepted by the recipient.
 * - Denied: Indicates that the request has been declined or denied by the recipient.
 * 
 * Usage:
 * This enum can be used to track and manage the state of various requests in the system. 
 * It can be associated with models like 'ConnectionRequest' to signify the status of 
 * a connection attempt between an organization and an influencer.
 * 
 * Benefits:
 * - Provides a standardized way to represent request outcomes.
 * - Ensures data consistency when recording request statuses.
 * -----------------------------------------------------------------------------
 */

public enum RequestStatus {

    Pending,

    Accepted,

    Denied
}
