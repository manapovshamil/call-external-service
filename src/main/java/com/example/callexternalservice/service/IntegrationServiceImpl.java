package com.example.callexternalservice.service;

import com.example.callexternalservice.config.CertConfig;
import com.example.callexternalservice.requests.CheckRequest;
import com.example.callexternalservice.requests.PayRequest;
import com.example.callexternalservice.requests.StatusRequest;
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

    @Override
    public String sendCheckRequest(CheckRequest checkRequest) {
        checkRequest.setPoint(point);
        return sendRequest(checkRequest.getRequestXml());
    }

    @Override
    public String sendPayRequest(PayRequest payRequest) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+0600"));
        String date = dateFormat.format(new Date());
        payRequest.setDate(date);
        payRequest.setPoint(point);
        return sendRequest(payRequest.getRequestXml());
    }

    @Override
    public String sendStatusRequest(StatusRequest statusRequest){
        statusRequest.setPoint(point);
        return sendRequest(statusRequest.getRequestXml());
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
