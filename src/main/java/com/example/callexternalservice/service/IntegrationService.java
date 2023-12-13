package com.example.callexternalservice.service;


import com.example.callexternalservice.requests.CheckRequest;
import com.example.callexternalservice.requests.PayRequest;
import com.example.callexternalservice.requests.StatusRequest;

public interface IntegrationService {
    String sendStatusRequest(StatusRequest statusRequest);
    String sendPayRequest(PayRequest payRequest);
    String sendCheckRequest(CheckRequest checkRequest);

}
