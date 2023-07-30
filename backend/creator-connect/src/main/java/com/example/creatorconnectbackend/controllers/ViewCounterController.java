/**
 * -----------------------------------------------------------------------------
 *               View Counter Controller
 * -----------------------------------------------------------------------------
 * Purpose:
 * The 'ViewCounterController' class is a specialized controller within the 
 * 'com.example.creatorconnectbackend.controllers' package. Its primary role is 
 * to manage operations related to view counters in the application. This is useful 
 * for tracking and analyzing the number of views received by specific entities 
 * like influencers or based on certain categorizations such as company types.
 *
 * Key Features:
 * - View Addition: Records and adds a new view for an entity.
 * - Fetch by Influencer ID: Retrieves the number of views for a specific influencer by ID.
 * - Fetch by Company Type: Gathers view counts based on company types.
 *
 * Annotations:
 * - @RestController: Specifies that this class offers RESTful web service endpoints.
 * - @CrossOrigin: Permits cross-origin requests, facilitating frontend-backend communication.
 * - @RequestMapping: Ensures that the endpoints in this controller start with "/api/viewCounters".
 *
 * Dependencies:
 * - ViewCounterService: A service layer component holding logic and operations 
 *   related to view counter functionalities.
 *
 * Core Endpoints:
 * - /addView: Registers a new view event and returns the updated count.
 * - /getByID/{id}: Fetches view counts by the specified influencer's ID.
 * - /getByCompanyType/{id}: Returns views segmented by company type, useful for categorization.
 * -----------------------------------------------------------------------------
 */

package com.example.creatorconnectbackend.controllers;

import com.example.creatorconnectbackend.models.ViewCounter;
import com.example.creatorconnectbackend.services.ViewCounterService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;


@RestController

@CrossOrigin

@RequestMapping("/api/viewCounters")
public class ViewCounterController {

    private final ViewCounterService viewCounterService;

    /**
     * Constructor-based dependency injection for ViewCounterService.
     */
    @Autowired
    public ViewCounterController(ViewCounterService viewCounterService) {
        this.viewCounterService = viewCounterService;
    }

    /**
     * Endpoint to add a new view record.
     */
    @PostMapping("/addView")
    public ResponseEntity<ViewCounter> addView(@Valid @RequestBody ViewCounter viewCounter) {
        // Call service to add a view and store the resultant updated view count.
        ViewCounter vc = viewCounterService.addView(viewCounter);
        // Return the resultant view count.
        return ResponseEntity.ok(vc);
    }

    /**
     * Endpoint to get the number of views by a particular influencer identified by ID.
     */
    @GetMapping("/getByID/{id}")
    public ResponseEntity<Map<Long, Integer>> getViewsByInfluencerID(@PathVariable("id") Long id) {
        // Call service to fetch views by influencer ID.
        Map<Long, Integer> map = viewCounterService.getViewsByInfluencerID(id);
        // Return the map of influencer ID to view count.
        return ResponseEntity.ok(map);
    }

    /**
     * Endpoint to get profile views based on a particular company type, possibly for categorizing views.
     */
    @GetMapping("/getByCompanyType/{id}")
    public ResponseEntity<Map<String, Integer>> getProfileViewsByCompanyType(@PathVariable("id") Long id) {
        // Call service to fetch views by company type.
        Map<String, Integer> map = viewCounterService.getProfileViewsByCompanyType(id);
        // Return the map of company types to view count.
        return ResponseEntity.ok(map);
    }
}
