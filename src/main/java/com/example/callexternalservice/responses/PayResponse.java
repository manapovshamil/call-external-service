package com.example.callexternalservice.responses;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class PayResponse {
    private Result result;

    @Data
    public class Result{
        private Long id;
        private String state;
        @SerializedName("substate")
        private String subState;
        private String code;
        @SerializedName("final")
        private String finalValue;
        private String trans;
        @SerializedName("process_time")
        private String processTime;
    }
}
