package com.example.callexternalservice.service;

import com.example.callexternalservice.config.CertConfig;
import com.example.callexternalservice.requests.CheckRequest;
import com.example.callexternalservice.requests.PayRequest;
import com.example.callexternalservice.requests.StatusRequest;
import com.example.callexternalservice.responses.CheckResponse;
import com.example.callexternalservice.responses.PayResponse;

import com.example.callexternalservice.responses.StatusResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@Service
public class IntegrationServiceImpl implements IntegrationService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CertConfig.class);

    @Autowired
    @Qualifier("certRestTemplate")
    private RestTemplate certRestTemplate;

    @Value("${integration.url}")
    private String url;

    @Value("${credentials.point}")
    private String point;

    private final XmlMapper xmlMapper = new XmlMapper();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Gson gson = new Gson();
    @Override
    public CheckResponse sendCheckRequest(CheckRequest checkRequest) {
        CheckResponse checkResponse;
        try {
            checkRequest.setPoint(point);
            String response = sendRequest(checkRequest.getRequestXml());
            System.out.println(response);
            JsonNode jsonNode = xmlMapper.readTree(response);
            String json = objectMapper.writeValueAsString(jsonNode);

            checkResponse = gson.fromJson(json, CheckResponse.class);
            return checkResponse;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public PayResponse sendPayRequest(PayRequest payRequest) {
        PayResponse payResponse;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
            dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+0600"));
            String date = dateFormat.format(new Date());
            payRequest.setDate(date);
            payRequest.setPoint(point);
            String response = sendRequest(payRequest.getRequestXml());

            JsonNode jsonNode = xmlMapper.readTree(response);
            String json = objectMapper.writeValueAsString(jsonNode);

            payResponse = gson.fromJson(json, PayResponse.class);
            return payResponse;
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public StatusResponse sendStatusRequest(StatusRequest statusRequest)  {
        StatusResponse statusResponse;
        try {
            statusRequest.setPoint(point);
            String response = sendRequest(statusRequest.getRequestXml());

            JsonNode jsonNode = xmlMapper.readTree(response);
            String json = objectMapper.writeValueAsString(jsonNode);

            statusResponse = gson.fromJson(json, StatusResponse.class);
            return statusResponse;
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
    public String sendRequest(String requestBody) {
        try {
            System.out.println(requestBody);
            LOGGER.info("Sending check request");
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_XML);
            HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
            ResponseEntity<String> responseEntity = certRestTemplate.postForEntity(url, request, String.class);
            LOGGER.info("Received auth response httpCode: {} body: {}", responseEntity.getStatusCode(), responseEntity.getBody());
            return responseEntity.getBody();

        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            throw e;
        }
    }
}
