package com.example.callexternalservice.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class CheckRequest {
    private String point;
    private String service;
    private String function;
    private String attrName;
    private String attrValue;
    private String attrName2;
    private String attrValue2;

    public String getRequestXml() {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                "<request point=\""+ point +"\">" +
                    "<advanced service=\"" + service + "\" function =\"" + function + "\">" +
                        "<attribute name=\""+ attrName +"\" value=\""+ attrValue +"\"/>" +
                        "<attribute name=\""+ attrName2 +"\" value=\""+ attrValue2 +"\"/>" +
                    "</advanced>" +
                "</request>";
    }
}


