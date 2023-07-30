package com.example.creatorconnectbackend.services;

import com.example.creatorconnectbackend.interfaces.ViewCounterServiceInterface;
import com.example.creatorconnectbackend.models.ViewCounter;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * ViewCounterService class provides functions for managing view counts in the system.
 * It interacts with the database using JdbcTemplate and SimpleJdbcInsert for adding view records and querying view counts.
 *
 * Functions:
 * 1. addView: Adds a view record to the 'View_counter' table.
 *    - Parameters:
 *        - viewCounter (ViewCounter): The ViewCounter object containing information about the view.
 *    - Returns:
 *        - ViewCounter: The updated ViewCounter object with the view record added, or null if an exception occurs.
 *
 * 2. getViewsByInfluencerID: Retrieves the number of views for a specific influencer based on their ID.
 *    - Parameters:
 *        - id (Long): The ID of the influencer.
 *    - Returns:
 *        - Map<Long, Integer>: A map containing the influencer ID as the key and the view count as the value.
 *
 * 3. getProfileViewsByCompanyType: Retrieves the number of views for a specific influencer grouped by the company type of the organizations viewing them.
 *    - Parameters:
 *        - id (Long): The ID of the influencer.
 *    - Returns:
 *        - Map<String, Integer>: A map containing the company type as the key and the corresponding view count as the value.
 *
 * Dependencies:
 * - JdbcTemplate: Used for querying the database and mapping rows to ViewCounter objects.
 * - RowMapper: Used for converting a row in the 'View_counter' table to a ViewCounter object.
 * - SimpleJdbcInsert: Used for adding view records to the 'View_counter' table.
 * - ViewCounter: Model class representing the view counter data.
 */@Service
public class ViewCounterService implements ViewCounterServiceInterface {
    private final JdbcTemplate jdbcTemplate;

    public ViewCounterService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<ViewCounter> rowMapper = (rs, rowNum) -> {
        ViewCounter viewCounter = new ViewCounter();
        viewCounter.setOrgId(rs.getLong("OrgID"));
        viewCounter.setInfluencerId(rs.getLong("InfluencerID"));
        viewCounter.setDate(rs.getDate("Date"));
        return viewCounter;
    };

    public ViewCounter addView(ViewCounter viewCounter) {
        try {
            SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
            jdbcInsert.withTableName("View_counter");

            Map<String, Object> parameters = new HashMap<String, Object>();
            parameters.put("InfluencerID", viewCounter.getInfluencerId());
            parameters.put("OrgID", viewCounter.getOrgId());
            parameters.put("Date", viewCounter.getDate());

            jdbcInsert.execute(parameters);
            return viewCounter;
        } catch (Exception e) {
            // Return null if an exception occurs during the view addition
            return null;
        }
    }

    public Map<Long, Integer> getViewsByInfluencerID(Long id) {
        String query = "SELECT COUNT(*) AS profile_views FROM View_counter WHERE InfluencerID = ?";
        try {
            return jdbcTemplate.query(query, new Object[]{id}, (rs, rowNum) -> {
                int viewCount = rs.getInt("profile_views");
                return new AbstractMap.SimpleEntry<>(id, viewCount);
            }).stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        } catch (EmptyResultDataAccessException e) {
            Map<Long, Integer> map = new HashMap<>();
            map.put(id, 0);
            return map;
        }
    }

    public Map<String, Integer> getProfileViewsByCompanyType(Long id) {
        String sql = "SELECT o.CompanyType, COUNT(*) AS view_count " +
                "FROM View_counter v " +
                "JOIN organizations o ON v.OrgID = o.OrgID " +
                "WHERE v.InfluencerID = ? " +
                "GROUP BY o.CompanyType";

        try {
            return jdbcTemplate.query(sql, new Object[]{id}, (rs, rowNum) -> {
                String companyType = rs.getString("companyType");
                int viewCount = rs.getInt("view_count");
                System.out.println(viewCount + " AA");
                return new AbstractMap.SimpleEntry<>(companyType, viewCount);
            }).stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        } catch (Exception e) {
            // If an exception occurs during the query, return an empty map
            return new HashMap<>();
        }
    }
}
