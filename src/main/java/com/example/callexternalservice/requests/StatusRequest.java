package com.example.callexternalservice.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class StatusRequest {
    private Long id;
    private String point;

    public String getRequestXml() {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                "<request point=\"" + point + "\">" +
                    "<status id=\""+ id +"\"/>" +
                "</request>";
    }
}
