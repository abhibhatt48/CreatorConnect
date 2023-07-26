
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.creatorconnectbackend.models.Organization;
import com.example.creatorconnectbackend.services.OrganizationService;

@RestController
@RequestMapping("/api/organizations")
public class OrganizationController {
    private final OrganizationService organizationService;
    private final Logger logger = LoggerFactory.getLogger(OrganizationController.class);

    @Autowired
    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @PostMapping("/register/{userId}")
    public ResponseEntity<?> registerOrganization(@PathVariable Long userId,@RequestBody Organization organization, BindingResult bindingResult) {
    	if (bindingResult.hasErrors()) {
            logger.info("Validation errors while registering organization");
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
            logger.info("Validation errors while updating organization");
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