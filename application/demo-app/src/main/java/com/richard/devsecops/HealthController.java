package com.richard.devsecops;

import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @GetMapping("/api/info")
    public Map<String, String> info() {
        return Map.of(
            "application", "GitLab DevSecOps Demo App",
            "version", "1.0.0",
            "status", "running"
        );
    }

    @GetMapping("/health")
    public Map<String, String> health() {
        return Map.of(
            "status", "UP",
            "application", "GitLab DevSecOps Demo App"
        );
    }
}
