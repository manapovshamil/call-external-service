package com.example.callexternalservice.responses;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

@Data
public class CheckResponse {
    private Result result;

    @Data
    public class Result{
       private String code;
       private String service;
       private Dataa data;
    }
    @Data
    public static class Dataa{
        private List<Input> input;
        private Nested nested;
    }
    @Data
    public static class Input{
        private String key;
        private String keyTitle;
        private String value;
        private String valueTitle;
    }
    @Data
    public static class Nested{
        private String id;
    }
}
