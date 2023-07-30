package com.example.creatorconnectbackend.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.creatorconnectbackend.models.ViewCounter;
import com.example.creatorconnectbackend.services.ViewCounterService;

public class ViewCounterControllerTest {

    private ViewCounterController viewCounterController;

    @Mock
    private ViewCounterService viewCounterService;

    @BeforeEach
    public void setUp() {
        // Initialize a mock ViewCounterService and ViewCounterController
        MockitoAnnotations.openMocks(this);
        viewCounterController = new ViewCounterController(viewCounterService);
    }

    @Test
    public void testAddView() {
        // Prepare test data
        ViewCounter viewCounter = new ViewCounter();
        viewCounter.setInfluencerId(1L);
        viewCounter.setOrgId(1L);
        viewCounter.setDate(new Date());

        // Mock the behavior of the ViewCounterService
        when(viewCounterService.addView(viewCounter)).thenReturn(viewCounter);

        // Execute the controller method
        ResponseEntity<ViewCounter> response = viewCounterController.addView(viewCounter);

        // Verify the result
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(viewCounter, response.getBody());
    }

    @Test
    public void testGetViewsByInfluencerID() {
        // Prepare test data
        Long influencerId = 1L;
        Map<Long, Integer> views = new HashMap<>();
        views.put(influencerId, 100);

        // Mock the behavior of the ViewCounterService
        when(viewCounterService.getViewsByInfluencerID(influencerId)).thenReturn(views);

        // Execute the controller method
        ResponseEntity<Map<Long, Integer>> response = viewCounterController.getViewsByInfluencerID(influencerId);

        // Verify the result
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(views, response.getBody());
    }

    @Test
    public void testGetProfileViewsByCompanyType() {
        // Prepare test data
        Long influencerId = 1L;
        Map<String, Integer> views = new HashMap<>();
        views.put("IT", 100);

        // Mock the behavior of the ViewCounterService
        when(viewCounterService.getProfileViewsByCompanyType(influencerId)).thenReturn(views);

        // Execute the controller method
        ResponseEntity<Map<String, Integer>> response = viewCounterController.getProfileViewsByCompanyType(influencerId);

        // Verify the result
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(views, response.getBody());
    }
}
