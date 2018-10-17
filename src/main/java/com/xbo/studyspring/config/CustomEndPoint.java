package com.xbo.studyspring.config;


import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@Endpoint(id = "xbo1")
public class CustomEndPoint {
    @ReadOperation
    public Map<String, Object> endpoint() {
        Map<String, Object> map = new HashMap<>(16);
        map.put("author", "xbo");
        map.put("mp", "xbo1.com");
        return map;
    }
}