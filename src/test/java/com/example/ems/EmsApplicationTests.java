package com.example.ems;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Sanity check: verifies the full Spring application context starts up
 * correctly with all beans wired together. Requires a reachable MySQL
 * instance matching application.properties, since ddl-auto needs a
 * live connection to validate/update the schema.
 */
@SpringBootTest
class EmsApplicationTests {

    @Test
    void contextLoads() {
        // If the application context fails to start, this test fails.
    }
}
