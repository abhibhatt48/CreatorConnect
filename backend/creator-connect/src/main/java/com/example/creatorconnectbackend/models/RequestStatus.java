package com.example.creatorconnectbackend.models;

/**
 * Represents the possible statuses of a requests.
 */
public enum RequestStatus {

    /** Indicates the request is awaiting review or decision. */
    Pending,

    /** Indicates the request has been approved or accepted. */
    Accepted,

    /** Indicates the request has been rejected or denied. */
    Denied
}
