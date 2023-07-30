/**
 * -----------------------------------------------------------------------------
 *                     Influencer Controller
 * -----------------------------------------------------------------------------
 * Purpose:
 * The 'InfluencerController' class is a primary controller of the 'com.example.creatorconnectbackend.controllers'
 * package. It manages the operations and functionalities related to influencers in the application.
 *
 * Key Features:
 * - Registration of influencers based on user IDs.
 * - Retrieval, updating, and deletion of influencers.
 * - Specialized endpoints to fetch influencer details based on influencer ID and retrieve all influencers.
 *
 * Annotations:
 * - @RestController: Marks the class as a RESTful web service controller.
 * - @CrossOrigin: Allows cross-origin requests, useful for frontend-backend separation during development.
 * - @RequestMapping: All endpoints in this controller will start with "/api/influencers".
 *
 * Dependencies:
 * - InfluencerService: A service layer component that provides logic and operations related to influencers.
 *
 * Core Endpoints:
 * - /register/{userId}: Registers a new influencer using a specified user ID.
 * - /{id}: Retrieves or updates influencer details using influencer ID.
 * - (GET) /: Fetches a list of all influencers.
 * - /{id} (DELETE): Deletes an influencer using influencer ID.
 *
 */

package com.example.creatorconnectbackend.controllers;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import com.example.creatorconnectbackend.models.Influencer;
import com.example.creatorconnectbackend.services.InfluencerService;


@RestController

@CrossOrigin
@RequestMapping("/api/influencers")
public class InfluencerController {

    private final InfluencerService influencerService;

    private final Logger logger = LoggerFactory.getLogger(InfluencerController.class);

    
    @Autowired
    public InfluencerController(InfluencerService influencerService) {
        this.influencerService = influencerService;
    }

    
    @PostMapping("/register/{userId}")
    public ResponseEntity<?> registerInfluencer(@PathVariable Long userId, @RequestBody Influencer influencer, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            logger.error("Validation errors while registering influencer by user with ID: {}", userId);

            // Convert validation errors into a map for a more structured response.
            Map<String, String> errors = bindingResult.getFieldErrors().stream()
                    .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));

            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        logger.info("Attempt to register new influencer by user with ID: {}", userId);
        Influencer registeredInfluencer = influencerService.register(influencer, userId);
        logger.info("Influencer registered successfully with ID: {}", registeredInfluencer.getInfluencerID());
        return new ResponseEntity<>(registeredInfluencer, HttpStatus.CREATED);
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<Influencer> getInfluencerById(@PathVariable("id") Long id) {
        logger.info("Request to get influencer with ID: {}", id);
        Influencer influencer = influencerService.getById(id);
        return new ResponseEntity<>(influencer, HttpStatus.OK);
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateInfluencer(@PathVariable("id") Long id, @RequestBody Influencer updatedInfluencer, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            logger.error("Validation errors while updating influencer with ID: {}", id);

            // Convert validation errors into a map for a more structured response.
            Map<String, String> errors = bindingResult.getFieldErrors().stream()
                    .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));

            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        logger.info("Attempt to update influencer with ID: {}", id);
        Influencer influencer = influencerService.update(id, updatedInfluencer);
        logger.info("Influencer with ID: {} updated successfully", id);
        return new ResponseEntity<>(influencer, HttpStatus.OK);
    }

    
    @GetMapping
    public ResponseEntity<List<Influencer>> getAllInfluencers() {
        logger.info("Request to get all influencers");
        List<Influencer> influencers = influencerService.getAll();
        return new ResponseEntity<>(influencers, HttpStatus.OK);
    }

    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteInfluencerById(@PathVariable("id") Long id) {
        logger.info("Attempt to delete influencer with ID: {}", id);
        influencerService.deleteById(id);
        logger.info("Influencer with ID: {} deleted successfully", id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
