package com.example.callexternalservice.config;


import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.apache.hc.client5.http.io.HttpClientConnectionManager;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactory;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactoryBuilder;
import org.apache.hc.core5.ssl.SSLContexts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.ResourceUtils;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;

@Configuration
public class CertConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(CertConfig.class);

    @Value("${integration.trust-store-path}")
    private String trustStorePath;

    @Value("${integration.trust-store-password}")
    private String trustStorePassword;

    @Value("${integration.key-store-path}")
    private String keyStorePath;

    @Value("${integration.key-store-password}")
    private String keyStorePassword;

    @Value("${integration.key-password}")
    private String keyPassword;

    @Bean(name = "certRestTemplate")
    public RestTemplate certRestTemplate() throws Exception {
        RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory());
        LOGGER.info("Created RestTemplate with SSL Context");
        return restTemplate;
    }

    private ClientHttpRequestFactory clientHttpRequestFactory() throws Exception {
        return new HttpComponentsClientHttpRequestFactory(httpClient());
    }

    private HttpClient httpClient() throws Exception {
        SSLContext sslcontext = SSLContexts.custom()
                .loadTrustMaterial(ResourceUtils.getFile(trustStorePath), trustStorePassword.toCharArray())
                .loadKeyMaterial(ResourceUtils.getFile(keyStorePath), keyStorePassword.toCharArray(), keyPassword.toCharArray())
                .build();

        final SSLConnectionSocketFactory sslSocketFactory = SSLConnectionSocketFactoryBuilder.create()
                .setSslContext(sslcontext)
                .build();

        final HttpClientConnectionManager cm = PoolingHttpClientConnectionManagerBuilder.create()
                .setSSLSocketFactory(sslSocketFactory)
                .build();

        return HttpClients.custom().setConnectionManager(cm).build();
    }

}
