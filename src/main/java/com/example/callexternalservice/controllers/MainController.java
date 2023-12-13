package com.example.callexternalservice.controllers;

import com.example.callexternalservice.requests.CheckRequest;
import com.example.callexternalservice.requests.PayRequest;
import com.example.callexternalservice.requests.StatusRequest;
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
    public ResponseEntity<String> check(@RequestBody CheckRequest checkRequest){
        String result = integrationService.sendCheckRequest(checkRequest);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/pay")
    public ResponseEntity<String> pay(@RequestBody PayRequest payRequest) {
        String result = integrationService.sendPayRequest(payRequest);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/status")
    public ResponseEntity<String> getStatus(@RequestBody StatusRequest statusRequest){
        String result = integrationService.sendStatusRequest(statusRequest);
        return ResponseEntity.ok(result);
    }
}
