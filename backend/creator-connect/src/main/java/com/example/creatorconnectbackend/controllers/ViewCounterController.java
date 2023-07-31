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
