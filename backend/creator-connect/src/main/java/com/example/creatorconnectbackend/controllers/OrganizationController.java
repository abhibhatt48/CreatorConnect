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

// Declare this class as a REST Controller
@RestController
// Allow cross-origin requests from different domains or ports
@CrossOrigin
// Base URL for all the endpoints in this controller
@RequestMapping("/api/organizations")
public class OrganizationController {
    // Inject the organizationService for performing CRUD operations
    private final OrganizationService organizationService;
    // Create a logger instance to log messages
    private final Logger logger = LoggerFactory.getLogger(OrganizationController.class);

    // Constructor-based dependency injection
    @Autowired
    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    // Endpoint to register a new Organization
    @PostMapping("/register/{userId}")
    public ResponseEntity<?> registerOrganization(@PathVariable Long userId, @RequestBody Organization organization, BindingResult bindingResult) {
    	if (bindingResult.hasErrors()) {
            // Log if there are validation errors during registration
            logger.info("Validation errors while registering organization");
            // Convert validation errors to a list of error messages
            List<String> errors = bindingResult.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList());
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
    	logger.info("Attempt to register new organization by user with ID: {}", userId);
        Organization registeredOrganization = organizationService.register(organization, userId);
        logger.info("Organization registered successfully with ID: {}", registeredOrganization.getOrgID());
        return new ResponseEntity<>(registeredOrganization, HttpStatus.CREATED);
    }

    // Endpoint to get details of an Organization by its ID
    @GetMapping("/{id}")
    public ResponseEntity<Organization> getOrganizationById(@PathVariable("id") Long id) {
    	logger.info("Request to get organization with ID: {}", id);
        Organization organization = organizationService.getById(id);
        return new ResponseEntity<>(organization, HttpStatus.OK);
    }

    // Endpoint to update an Organization's details
    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrganization(@PathVariable("id") Long id, @RequestBody Organization updatedOrganization, BindingResult bindingResult) {
    	if (bindingResult.hasErrors()) {
            // Log if there are validation errors during update
            logger.info("Validation errors while updating organization");
            // Convert validation errors to a list of error messages
            List<String> errors = bindingResult.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList());
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
    	logger.info("Attempt to update organization with ID: {}", id);
        Organization organization = organizationService.update(id, updatedOrganization);
        logger.info("Organization with ID: {} updated successfully", id);
        return new ResponseEntity<>(organization, HttpStatus.OK);
    }

    // Endpoint to fetch all registered Organizations
    @GetMapping
    public ResponseEntity<List<Organization>> getAllOrganizations() {
    	logger.info("Request to get all organizations");
        List<Organization> organizations = organizationService.getAll();
        return new ResponseEntity<>(organizations, HttpStatus.OK);
    }

    // Endpoint to delete a specific Organization by its ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrganizationById(@PathVariable("id") Long id) {
    	logger.info("Attempt to delete organization with ID: {}", id);
        organizationService.deleteById(id);
        logger.info("Organization with ID: {} deleted successfully", id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
