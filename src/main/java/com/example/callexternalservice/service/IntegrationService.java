package com.example.callexternalservice.service;


import com.example.callexternalservice.requests.CheckRequest;
import com.example.callexternalservice.requests.PayRequest;
import com.example.callexternalservice.requests.StatusRequest;
import com.example.callexternalservice.responses.CheckResponse;
import com.example.callexternalservice.responses.PayResponse;
import com.example.callexternalservice.responses.StatusResponse;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface IntegrationService {
    StatusResponse sendStatusRequest(StatusRequest statusRequest) throws JsonProcessingException;
    PayResponse sendPayRequest(PayRequest payRequest);
    CheckResponse sendCheckRequest(CheckRequest checkRequest);

}
