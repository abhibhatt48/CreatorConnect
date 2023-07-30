/**
 * -----------------------------------------------------------------------------
 *                 Organization Controller
 * -----------------------------------------------------------------------------
 * Purpose:
 * The 'OrganizationController' class is a vital controller within the 
 * 'com.example.creatorconnectbackend.controllers' package. Its primary role is
 * to manage the operations associated with organizations in the application.
 *
 * Key Features:
 * - Registration of new organizations based on user IDs.
 * - Retrieval, updating, and deletion of organizations.
 * - Specialized endpoints to fetch organization details based on organization ID 
 *   and retrieve all organizations in the system.
 *
 * Annotations:
 * - @RestController: Specifies that this class provides RESTful web service endpoints.
 * - @CrossOrigin: Enables cross-origin requests, allowing for frontend-backend integration.
 * - @RequestMapping: Indicates that the endpoints in this controller will have a prefix of "/api/organizations".
 *
 * Dependencies:
 * - OrganizationService: This service layer component offers logic and operations 
 *   specific to organizations.
 *
 * Core Endpoints:
 * - /register/{userId}: Facilitates the registration of a new organization linked to 
 *   a specific user ID.
 * - /{id}: Allows for the retrieval or updating of organization details via the 
 *   organization's ID.
 * - (GET) /: Provides a list of all existing organizations.
 * - /{id} (DELETE): Handles the removal of an organization, identified by its ID.
 *
 */


package com.example.creatorconnectbackend.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import com.example.creatorconnectbackend.models.Organization;
import com.example.creatorconnectbackend.services.OrganizationService;


@RestController

@CrossOrigin

@RequestMapping("/api/organizations")
public class OrganizationController {
    private final OrganizationService organizationService;

    private final Logger logger = LoggerFactory.getLogger(OrganizationController.class);

    @Autowired
    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    /**
     * Endpoint to register a new Organization.
     */
    @PostMapping("/register/{userId}")
    public ResponseEntity<?> registerOrganization(@PathVariable Long userId, @RequestBody Organization organization, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // Log if there are validation errors during registration.
            logger.info("Validation errors while registering organization");
            // Convert validation errors to a list of error messages.
            List<String> errors = bindingResult.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList());
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        logger.info("Attempt to register new organization by user with ID: {}", userId);
        Organization registeredOrganization = organizationService.register(organization, userId);
        logger.info("Organization registered successfully with ID: {}", registeredOrganization.getOrgID());
        return new ResponseEntity<>(registeredOrganization, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Organization> getOrganizationById(@PathVariable("id") Long id) {
        logger.info("Request to get organization with ID: {}", id);
        Organization organization = organizationService.getById(id);
        return new ResponseEntity<>(organization, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrganization(@PathVariable("id") Long id, @RequestBody Organization updatedOrganization, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // Log if there are validation errors during update.
            logger.info("Validation errors while updating organization");
            // Convert validation errors to a list of error messages.
            List<String> errors = bindingResult.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList());
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        logger.info("Attempt to update organization with ID: {}", id);
        Organization organization = organizationService.update(id, updatedOrganization);
        logger.info("Organization with ID: {} updated successfully", id);
        return new ResponseEntity<>(organization, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Organization>> getAllOrganizations() {
        logger.info("Request to get all organizations");
        List<Organization> organizations = organizationService.getAll();
        return new ResponseEntity<>(organizations, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrganizationById(@PathVariable("id") Long id) {
        logger.info("Attempt to delete organization with ID: {}", id);
        organizationService.deleteById(id);
        logger.info("Organization with ID: {} deleted successfully", id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
