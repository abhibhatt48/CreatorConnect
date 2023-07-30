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

// Marking this class as a Spring Service bean
@Service
public class ViewCounterService implements ViewCounterServiceInterface {
    // JDBC template for database operations
    private final JdbcTemplate jdbcTemplate;

    // Constructor-based Dependency Injection for JdbcTemplate
    public ViewCounterService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // RowMapper to convert a row in the 'View_counter' table to a ViewCounter object
    private RowMapper<ViewCounter> rowMapper = (rs, rowNum) -> {
        ViewCounter viewCounter = new ViewCounter();
        viewCounter.setOrgId(rs.getLong("OrgID"));
        viewCounter.setInfluencerId(rs.getLong("InfluencerID"));
        viewCounter.setDate(rs.getDate("Date"));
        return viewCounter;
    };

    // Add a view record to the 'View_counter' table
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

    // Get the number of views for a specific influencer based on their ID
    public Map<Long, Integer> getViewsByInfluencerID(Long id) {
        String query = "SELECT COUNT(*) AS profile_views FROM View_counter WHERE InfluencerID = ?";
        try {
            // Use jdbcTemplate to execute the query and return a map of influencer ID and view count
            return jdbcTemplate.query(query, new Object[]{id}, (rs, rowNum) -> {
                int viewCount = rs.getInt("profile_views");
                return new AbstractMap.SimpleEntry<>(id, viewCount);
            }).stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        } catch (EmptyResultDataAccessException e) {
            // If the query returns no results, return a map with influencer ID and view count as 0
            Map<Long, Integer> map = new HashMap<>();
            map.put(id, 0);
            return map;
        }
    }

    // Get the number of views for a specific influencer grouped by company type of the organizations viewing them
    public Map<String, Integer> getProfileViewsByCompanyType(Long id) {
        String sql = "SELECT o.CompanyType, COUNT(*) AS view_count " +
                "FROM View_counter v " +
                "JOIN organizations o ON v.OrgID = o.OrgID " +
                "WHERE v.InfluencerID = ? " +
                "GROUP BY o.CompanyType";

        try {
            // Use jdbcTemplate to execute the query and return a map of company type and view count
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
