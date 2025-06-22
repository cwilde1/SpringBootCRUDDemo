package com.example.demo.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.HashMap;

@RestController
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public ResponseEntity<Map<String, Object>> handleError(HttpServletRequest request) {
        Object statusCode = request.getAttribute("javax.servlet.error.status_code");
        Object message = request.getAttribute("javax.servlet.error.message");

        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("status", statusCode);
        errorResponse.put("message", message != null ? message : "Unexpected error");

        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf((Integer) statusCode));
    }

    // Optional: to silence deprecated warning in newer Spring versions
    public String getErrorPath() {
        return "/error";
    }
}
