package com.example.callexternalservice.controllers;

import com.example.callexternalservice.requests.CheckRequest;
import com.example.callexternalservice.requests.PayRequest;
import com.example.callexternalservice.requests.StatusRequest;
import com.example.callexternalservice.responses.CheckResponse;
import com.example.callexternalservice.responses.PayResponse;
import com.example.callexternalservice.responses.StatusResponse;
import com.example.callexternalservice.service.IntegrationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/paydala")
public class MainController {
    @Autowired
    IntegrationServiceImpl integrationService;

    @PostMapping("/check")
    public ResponseEntity<CheckResponse> check(@RequestBody CheckRequest checkRequest)  {
        CheckResponse result = integrationService.sendCheckRequest(checkRequest);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/pay")
    public ResponseEntity<PayResponse> pay(@RequestBody PayRequest payRequest) {
        PayResponse result = integrationService.sendPayRequest(payRequest);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/status")
    public ResponseEntity<StatusResponse> getStatus(@RequestBody StatusRequest statusRequest){
        StatusResponse result = integrationService.sendStatusRequest(statusRequest);
        return ResponseEntity.ok(result);
    }
}
