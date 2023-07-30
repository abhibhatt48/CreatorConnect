/**
 * -----------------------------------------------------------------------------
 *           Organization Service Interface
 * -----------------------------------------------------------------------------
 * Purpose:
 * The 'OrganizationServiceInterface' delineates a standardized contract for 
 * the management and manipulation of organizations within the 
 * 'com.example.creatorconnectbackend' application. By establishing this contract, 
 * the application ensures a consistent approach to organization operations,
 * such as registration, retrieval, updating, listing, and deletion.
 *
 * Key Features:
 * 1. Registration: Allows for the addition of a new organization, associated 
 *    with a specific user ID.
 * 2. Retrieval: Provides methods to fetch organizations either individually 
 *    by their unique ID or as an entire collection.
 * 3. Update: Allows for the modification of an existing organization's details.
 * 4. Deletion: Provides the ability to remove an organization based on its ID.
 *
 * Data Models:
 * - Organization: Represents the model of an organization, encompassing 
 *   attributes like name, type, members, and other relevant details.
 * -----------------------------------------------------------------------------
 */

package com.example.creatorconnectbackend.interfaces;

import java.util.List;
import com.example.creatorconnectbackend.models.Organization;

public interface OrganizationServiceInterface {


    Organization register(Organization organization, Long userId);
    Organization getById(Long id);
    Organization update(Long id, Organization updatedOrganization);
    List<Organization> getAll();
    void deleteById(Long id);
}
