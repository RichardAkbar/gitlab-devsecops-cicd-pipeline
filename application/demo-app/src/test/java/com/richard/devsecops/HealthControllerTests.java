package com.richard.devsecops;

import org.junit.jupiter.api.Test;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HealthControllerTests {

    private final HealthController controller = new HealthController();

    @Test
    void infoEndpointShouldReturnApplicationInformation() {
        Map<String, String> response = controller.info();

        assertEquals("GitLab DevSecOps Demo App", response.get("application"));
        assertEquals("1.0.0", response.get("version"));
        assertEquals("running", response.get("status"));
    }

    @Test
    void healthEndpointShouldReturnUp() {
        Map<String, String> response = controller.health();

        assertEquals("UP", response.get("status"));
        assertEquals("GitLab DevSecOps Demo App", response.get("application"));
    }
}
