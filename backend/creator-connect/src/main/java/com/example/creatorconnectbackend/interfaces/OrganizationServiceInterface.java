package com.example.creatorconnectbackend.interfaces;

import java.util.List;
import com.example.creatorconnectbackend.models.Organization;

// defining the contract for services related to organizations.
public interface OrganizationServiceInterface {
    
    // Register a new organization associated with a user ID and return the registered organization.
    Organization register(Organization organization, Long userId);

    // Retrieve an organization based on its unique ID.
    Organization getById(Long id);

    // Update the details of an existing organization based on its unique ID and return the updated organization.
    Organization update(Long id, Organization updatedOrganization);

    // Fetch a list of all organizations present in the database.
    List<Organization> getAll();

    // Delete an organization based on its unique ID.
    void deleteById(Long id);
}
