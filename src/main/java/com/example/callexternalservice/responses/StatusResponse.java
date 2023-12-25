package com.example.callexternalservice.responses;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

@Data
public class StatusResponse {
    private Result result;
    @Data
    public class Result {
        private Long id;
        private String state;
        private String subState;
        private String code;
        @SerializedName("final")
        private String finalValue;
        private String trans;
        @SerializedName("server_time")
        private String serverTime;
        @SerializedName("process_time")
        private String processTime;
        private List<Attribute> attribute;

        @Data
        public class Attribute {
            private String name;
            private String value;
        }
    }
}
