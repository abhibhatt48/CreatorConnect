package com.example.creatorconnectbackend.services;

import com.example.creatorconnectbackend.interfaces.ViewCounterServiceInterface;
import com.example.creatorconnectbackend.models.ViewCounter;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ViewCounterService implements ViewCounterServiceInterface {

    private final JdbcTemplate jdbcTemplate;

    public ViewCounterService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<ViewCounter> rowMapper = (rs, rowNum) -> {
        ViewCounter viewCounter = new ViewCounter();
        viewCounter.setOrgId(rs.getLong("orgID"));
        viewCounter.setInfluencerId(rs.getLong("influencerID"));
        viewCounter.setDate(rs.getDate("Date"));
        return viewCounter;
    };

    public ViewCounter addView(ViewCounter viewCounter) {
        String query = "INSERT INTO View_counter (influencer_id, org_id, date) VALUES (?, ?, ?)";
        try {
            int updated = jdbcTemplate.update(query, viewCounter.getInfluencerId(), viewCounter.getOrgId(), viewCounter.getDate());
            if (updated == 0) {
                // Add Logger
                return null;
            }
            return viewCounter;
        }
        catch (Exception e) {
            // Add Logger
            return null;
        }
    }

    public Map<Long, Integer> getViewsByInfluencerID(Long id) {
        String query = "SELECT COUNT(*) AS profile_views FROM View_counter WHERE influencerID = ?";
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
        String sql = "SELECT o.companyType, COUNT(*) AS view_count " +
                "FROM View_counter v " +
                "JOIN organizations o ON v.orgID = o.orgID " +
                "WHERE v.influencerID = ? " +
                "GROUP BY o.companyType";

        try {
            return jdbcTemplate.query(sql, new Object[]{id}, (rs, rowNum) -> {
                String companyType = rs.getString("companyType");
                int viewCount = rs.getInt("view_count");
                return new AbstractMap.SimpleEntry<>(companyType, viewCount);
            }).stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        } catch (Exception e) {
            return new HashMap<>();
        }
    }

}
