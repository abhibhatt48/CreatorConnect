package com.example.creatorconnectbackend.services;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import java.util.AbstractMap;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.creatorconnectbackend.models.ViewCounter;

 /* ViewCounterServiceTest
 * 
 * A test class for the ViewCounterService. The purpose is to test various functionalities 
 * associated with the view counting operations such as retrieving views by influencer ID,
 * and views categorized by the company type.
 * 
 * Functions:
 * 1. setUp(): Sets up the mock and service for testing.
 * 2. testGetViewsByInfluencerIDException(): Tests the scenario where no views are present 
 *    for a given influencer ID.
 * 3. testGetViewsByInfluencerID(): Tests the scenario where views exist for a given influencer ID.
 * 4. testGetProfileViewsByCompanyType(): Tests profile views retrieval based on the company type.
 * 
 * Note: Always ensure that the test suite is run after making any changes to the associated methods 
 * to validate that the functionalities are consistent.
 * 
 */

public class ViewCounterServiceTest {

    private ViewCounterService viewCounterService;

    @Mock
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        viewCounterService = new ViewCounterService(jdbcTemplate);
    }

    /**
     * Test scenario when there are no views for the given influencer ID.
     * The method should handle the EmptyResultDataAccessException and return an empty map.
     */
    @Test
    void testGetViewsByInfluencerIDException() {
        Long influencerId = 1L;

        when(jdbcTemplate.query(any(String.class), eq(new Object[]{influencerId}), any(RowMapper.class)))
                .thenThrow(new EmptyResultDataAccessException(1));

        Map<Long, Integer> result = viewCounterService.getViewsByInfluencerID(influencerId);
        assertNotNull(result);
        assertTrue(result.containsKey(influencerId));
        assertEquals(Integer.valueOf(0), result.get(influencerId));

        verify(jdbcTemplate, times(1)).query(any(String.class), eq(new Object[]{influencerId}), any(RowMapper.class));
    }

    /**
     * Test scenario when there are views for the given influencer ID.
     * The method should return a map with the influencer ID and the corresponding view count.
     */
    @Test
    void testGetViewsByInfluencerID() {
        Long influencerId = 1L;

        when(jdbcTemplate.query(any(String.class), eq(new Object[]{influencerId}), any(RowMapper.class)))
                .thenReturn(Collections.singletonList(new AbstractMap.SimpleEntry<>(influencerId, 10)));

        Map<Long, Integer> result = viewCounterService.getViewsByInfluencerID(influencerId);
        assertNotNull(result);
        assertTrue(result.containsKey(influencerId));
        assertEquals(Integer.valueOf(10), result.get(influencerId));

        verify(jdbcTemplate, times(1)).query(any(String.class), eq(new Object[]{influencerId}), any(RowMapper.class));
    }

    /**
     * Test scenario when there are profile views for the given influencer ID and company type.
     * The method should return a map with the company type and the corresponding profile view count.
     */
    @Test
    void testGetProfileViewsByCompanyType() {
        Long influencerId = 1L;
        String companyType = "Tech";
        
        when(jdbcTemplate.query(any(String.class), eq(new Object[]{influencerId}), any(RowMapper.class)))
                .thenReturn(Collections.singletonList(new AbstractMap.SimpleEntry<>(companyType, 10)));

        Map<String, Integer> result = viewCounterService.getProfileViewsByCompanyType(influencerId);
        assertNotNull(result);
        assertTrue(result.containsKey(companyType));
        assertEquals(Integer.valueOf(10), result.get(companyType));

        verify(jdbcTemplate, times(1)).query(any(String.class), eq(new Object[]{influencerId}), any(RowMapper.class));
    }
}
