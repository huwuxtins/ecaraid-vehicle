package com.ecaraid.intern.vehicle.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class MessageResponse {
    public static ResponseEntity<Object> returnResponse(Object data, String status, HttpStatus httpStatus) {
        Map<String, Object> result = new HashMap<>();
        result.put("data", data);
        result.put("status", status);
        return new ResponseEntity<>(result, httpStatus);
    }
}
