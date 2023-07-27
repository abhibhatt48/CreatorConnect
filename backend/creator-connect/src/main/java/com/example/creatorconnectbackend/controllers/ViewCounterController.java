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

    @Autowired
    public ViewCounterController(ViewCounterService viewCounterService) {
        this.viewCounterService = viewCounterService;
    }

    @PostMapping("/addView")
    public ResponseEntity<ViewCounter> addView(@Valid @RequestBody ViewCounter viewCounter) {
        ViewCounter vc = viewCounterService.addView(viewCounter);
        return ResponseEntity.ok(vc);
    }

    @GetMapping("/getByID/{id}")
    public ResponseEntity<Map<Long, Integer>> getViewsByInfluencerID(@PathVariable("id") Long id) {
        Map<Long, Integer> map = viewCounterService.getViewsByInfluencerID(id);
        return ResponseEntity.ok(map);
    }

    @GetMapping("/getByCompanyType/{id}")
    public ResponseEntity<Map<String, Integer>> getProfileViewsByCompanyType(@PathVariable("id") Long id) {
        Map<String, Integer> map = viewCounterService.getProfileViewsByCompanyType(id);
        return ResponseEntity.ok(map);
    }
}
