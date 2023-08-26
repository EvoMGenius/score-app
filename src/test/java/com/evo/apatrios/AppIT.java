package com.evo.apatrios;

import com.jupiter.tools.spring.test.postgres.annotation.meta.EnablePostgresIntegrationTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@EnablePostgresIntegrationTest
class AppIT {

    @Test
    @DisplayName("Интеграционный тест подъема контекста")
    void contextRunTest() {}

}