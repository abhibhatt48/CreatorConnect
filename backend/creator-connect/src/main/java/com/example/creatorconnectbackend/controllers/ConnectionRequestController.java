package com.example.creatorconnectbackend.controllers;

import com.example.creatorconnectbackend.models.ConnectionRequest;
import com.example.creatorconnectbackend.models.RequestStatus;
import com.example.creatorconnectbackend.services.ConnectionRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/connectionReq")
public class ConnectionRequestController {

    private final ConnectionRequestService connectionRequestService;

    @Autowired
    public ConnectionRequestController(ConnectionRequestService connectionRequestService) {
        this.connectionRequestService = connectionRequestService;
    }

    @PostMapping("/create")
    public ResponseEntity<ConnectionRequest> createRequest(@Valid @RequestBody ConnectionRequest connectionRequest) {
        ConnectionRequest createdRequest = connectionRequestService.createRequest(connectionRequest);
        return new ResponseEntity<>(createdRequest, HttpStatus.CREATED);
    }

    @GetMapping("/getByRequestID/{requestId}")
    public ResponseEntity<ConnectionRequest> getConnectionById(@PathVariable("requestId") Long requestId) {
        ConnectionRequest connectionRequest = connectionRequestService.getConnectionRequestByID(requestId);
        return ResponseEntity.ok(connectionRequest);
    }

    @PutMapping("/update/{requestId}")
    public ResponseEntity<String> updateConnectionRequestStatus(
            @PathVariable Long requestId,
            @RequestBody Map<String, String> payload) {

        String requestStatus = payload.get("requestStatus");

        if (requestStatus != null) {
            connectionRequestService.updateStatus(requestId, RequestStatus.valueOf(requestStatus));
            return ResponseEntity.ok("Connection request updated successfully");
        } else {
            return ResponseEntity.badRequest().body("Invalid request payload");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteByID(@PathVariable("id") Long id) {
        connectionRequestService.deleteByID(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/updateMessage/{id}")
    public ResponseEntity<ConnectionRequest> updateRequestMessage(@PathVariable("id") Long requestId, @RequestBody Map<String, String> map) {
        ConnectionRequest updatedRequest = connectionRequestService.updateMessage(requestId, map);
        return ResponseEntity.ok(updatedRequest);
    }

}

