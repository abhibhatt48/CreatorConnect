package com.example.creatorconnectbackend.services;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.AbstractMap;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.creatorconnectbackend.models.ViewCounter;

public class ViewCounterServiceTest {

    private ViewCounterService viewCounterService;

    @Mock
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        viewCounterService = new ViewCounterService(jdbcTemplate);
    }

//    @Test
//    void testAddView() {
//        ViewCounter viewCounter = new ViewCounter();
//        viewCounter.setInfluencerId(1L);
//        viewCounter.setOrgId(2L);
//        viewCounter.setDate(java.sql.Date.valueOf("2023-07-26"));
//
//        when(jdbcTemplate.update(any(String.class), anyLong(), anyLong(), any(java.sql.Date.class))).thenReturn(1);
//
//        ViewCounter result = viewCounterService.addView(viewCounter);
//        assertNotNull(result);
//
//        verify(jdbcTemplate, times(1)).update(any(String.class), eq(1L), eq(2L), eq(java.sql.Date.valueOf("2023-07-26")));
//    }
//
//    @Test
//    void testAddViewException() {
//        ViewCounter viewCounter = new ViewCounter();
//        viewCounter.setInfluencerId(1L);
//        viewCounter.setOrgId(2L);
//        viewCounter.setDate(java.sql.Date.valueOf("2023-07-26"));
//
//        when(jdbcTemplate.update(any(String.class), anyLong(), anyLong(), any(java.sql.Date.class))).thenThrow(new RuntimeException());
//
//        ViewCounter result = viewCounterService.addView(viewCounter);
//        assertNull(result);
//
//        verify(jdbcTemplate, times(1)).update(any(String.class), eq(1L), eq(2L), eq(java.sql.Date.valueOf("2023-07-26")));
//    }
//
//    @Test
//    void testAddViewNotUpdated() {
//        ViewCounter viewCounter = new ViewCounter();
//        viewCounter.setInfluencerId(1L);
//        viewCounter.setOrgId(2L);
//        viewCounter.setDate(java.sql.Date.valueOf("2023-07-26"));
//
//        when(jdbcTemplate.update(any(String.class), anyLong(), anyLong(), any(java.sql.Date.class))).thenReturn(0);
//
//        ViewCounter result = viewCounterService.addView(viewCounter);
//        assertNull(result);
//
//        verify(jdbcTemplate, times(1)).update(any(String.class), eq(1L), eq(2L), eq(java.sql.Date.valueOf("2023-07-26")));
//    }

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