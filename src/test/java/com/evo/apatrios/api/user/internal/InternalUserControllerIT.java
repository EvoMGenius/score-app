package com.evo.apatrios.api.user.internal;

import com.evo.apatrios.api.user.external.dto.output.UserDto;
import com.evo.apatrios.api.user.internal.dto.UpdateUserDto;
import com.evo.apatrios.util.ResourceUtils;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.jupiter.tools.spring.test.postgres.annotation.meta.EnablePostgresIntegrationTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.web.reactive.server.WebTestClient;
import ru.themikhailz.security.CustomUserDetailsImpl;
import ru.themikhailz.security.Role;
import ru.themikhailz.service.JwtService;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static com.evo.apatrios.model.Role.ADMIN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@EnablePostgresIntegrationTest
class InternalUserControllerIT {

    @Autowired
    private WebTestClient client;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private UserDetailsService userDetailsService;

    private final UserDetails userDetails = CustomUserDetailsImpl.builder()
                                                                 .username("username")
                                                                 .password("")
                                                                 .role(Role.ADMIN)
                                                                 .build();

    private final String token = "Bearer token";

    @BeforeEach
    public void before() {
        when(jwtService.extractId(any())).thenReturn(UUID.fromString("00000000-0000-0000-0000-000000000000"));
        when(jwtService.extractUsername(any())).thenReturn("username");
        when(jwtService.getRoles(any())).thenReturn(List.of(ADMIN.name()));
        when(userDetailsService.loadUserByUsername(any())).thenReturn(userDetails);
    }

    @Test
    @DataSet(value = "/datasets/api/user/update.json", cleanAfter = true, cleanBefore = true)
    @ExpectedDataSet(value = "/datasets/api/user/update__expected.json")
    void update() throws IOException {
        // Arrange
        UUID userId = UUID.fromString("00000000-0000-0000-0000-000000000000");
        UpdateUserDto dto = ResourceUtils.parseJson("/json/api/user/update.json", UpdateUserDto.class);

        // Act
        UserDto response = client.put()
                                 .uri("/api/v1/internal/user/update/{id}", userId)
                                 .header("Authorization", token)
                                 .bodyValue(dto)
                                 .exchange()
                                 .expectStatus()
                                 .isOk()
                                 .expectBody(UserDto.class)
                                 .returnResult()
                                 .getResponseBody();

        // Assert
        UserDto expectedResponse = ResourceUtils.parseJson("/json/api/user/update__expected.json", UserDto.class);

        assertThat(response).usingRecursiveComparison()
                            .withStrictTypeChecking()
                            .isEqualTo(expectedResponse);
    }

    @Test
    @DataSet(value = "/datasets/api/user/update-score.json", cleanAfter = true, cleanBefore = true)
    @ExpectedDataSet(value = "/datasets/api/user/update-score__expected.json")
    void updateScore() throws IOException {
        // Arrange
        UUID userId = UUID.fromString("00000000-0000-0000-0000-000000000000");
        Long additionalScore = 50L;

        // Act
        UserDto response = client.post()
                                 .uri("/api/v1/internal/user/update/score/{userId}/{additionalScore}", userId, additionalScore)
                                 .header("Authorization", token)
                                 .exchange()
                                 .expectStatus()
                                 .isOk()
                                 .expectBody(UserDto.class)
                                 .returnResult()
                                 .getResponseBody();

        // Assert
        UserDto expectedResponse = ResourceUtils.parseJson("/json/api/user/update-score__expected.json", UserDto.class);

        assertThat(response).usingRecursiveComparison()
                            .withStrictTypeChecking()
                            .isEqualTo(expectedResponse);
    }

    @Test
    @DataSet(value = "/datasets/api/user/received.json", cleanAfter = true, cleanBefore = true)
    @ExpectedDataSet(value = "/datasets/api/user/received__expected.json")
    void received() {
        // Arrange
        UUID userId = UUID.fromString("00000000-0000-0000-0000-000000000000");
        UUID userAwardId = UUID.fromString("00000000-0000-0000-0000-000000000001");

        // Act
        client.post()
              .uri("/api/v1/internal/user/{userId}/received/{userAwardId}", userId, userAwardId)
              .header("Authorization", token)
              .exchange()
              // Assert
              .expectStatus()
              .isOk();

    }
}