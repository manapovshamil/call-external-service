package com.example.callexternalservice.requests;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PayRequest {

    private Long id;
    private Integer sum;
    private Double sumComm;
    private String check;
    private String service;
    private String account;
    private String date;
    private String point;
    private String attrName;
    private String attrValue;

    public String getRequestXml() {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                "<request point =\"" + point + "\">" +
                    "<payment " +
                        " id=\"" + id + "\"" +
                        " sum=\"" + sum + "\"" +
                        " sumComm=\"" + sumComm + "\"" +
                        " check=\"" + check + "\"" +
                        " service=\"" + service + "\"" +
                        " account=\"" + account + "\"" +
                        " date=\"" + date + "\">" +
                        "<attribute name=\""+ attrName +"\" value=\"" + attrValue + "\"/>" +
                    "</payment>" +
                "</request>";
    }
}
