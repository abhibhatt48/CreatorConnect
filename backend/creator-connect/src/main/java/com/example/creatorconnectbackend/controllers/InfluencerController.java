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

// Define the class as a REST controller
@RestController
// Enable Cross-Origin requests for the entire controller
@CrossOrigin
// Base URL for all endpoints in this controller
@RequestMapping("/api/influencers")
public class InfluencerController {

    // Service to manage Influencer related operations
	private final InfluencerService influencerService;
    // Logger for logging messages related to operations in this class
	private final Logger logger = LoggerFactory.getLogger(InfluencerController.class);

    // Constructor-based dependency injection for the InfluencerService
    @Autowired
    public InfluencerController(InfluencerService influencerService) {
        this.influencerService = influencerService;
    }

    // Endpoint to register a new Influencer
    @PostMapping("/register/{userId}")
    public ResponseEntity<?> registerInfluencer(@PathVariable Long userId, @RequestBody Influencer influencer, BindingResult bindingResult) {
    	if (bindingResult.hasErrors()) {
            logger.error("Validation errors while registering influencer by user with ID: {}", userId);

            // Convert validation errors into a map for a more structured response
            Map<String, String> errors = bindingResult.getFieldErrors().stream()
                    .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
    	logger.info("Attempt to register new influencer by user with ID: {}", userId);
        Influencer registeredInfluencer = influencerService.register(influencer, userId);
        logger.info("Influencer registered successfully with ID: {}", registeredInfluencer.getInfluencerID());
        return new ResponseEntity<>(registeredInfluencer, HttpStatus.CREATED);       
    }

    // Endpoint to fetch a specific Influencer by their ID
    @GetMapping("/{id}")
    public ResponseEntity<Influencer> getInfluencerById(@PathVariable("id") Long id) {
    	logger.info("Request to get influencer with ID: {}", id);
        Influencer influencer = influencerService.getById(id);
        return new ResponseEntity<>(influencer, HttpStatus.OK);
    }

    // Endpoint to update details of an existing Influencer
    @PutMapping("/{id}")
    public ResponseEntity<?> updateInfluencer(@PathVariable("id") Long id, @RequestBody Influencer updatedInfluencer, BindingResult bindingResult) {
    	if (bindingResult.hasErrors()) {
            logger.error("Validation errors while updating influencer with ID: {}", id);

            // Convert validation errors into a map for a more structured response
            Map<String, String> errors = bindingResult.getFieldErrors().stream()
                    .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));

            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
    	logger.info("Attempt to update influencer with ID: {}", id);
        Influencer influencer = influencerService.update(id, updatedInfluencer);
        logger.info("Influencer with ID: {} updated successfully", id);
        return new ResponseEntity<>(influencer, HttpStatus.OK);
    }

    // Endpoint to fetch all registered Influencers
    @GetMapping
    public ResponseEntity<List<Influencer>> getAllInfluencers() {
    	logger.info("Request to get all influencers");
        List<Influencer> influencers = influencerService.getAll();
        return new ResponseEntity<>(influencers, HttpStatus.OK);
    }

    // Endpoint to delete a specific Influencer by their ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteInfluencerById(@PathVariable("id") Long id) {
    	logger.info("Attempt to delete influencer with ID: {}", id);
        influencerService.deleteById(id);
        logger.info("Influencer with ID: {} deleted successfully", id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
