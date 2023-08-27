package com.evo.apatrios.api.award.internal;

import com.evo.apatrios.api.award.external.dto.output.AwardDto;
import com.evo.apatrios.api.award.internal.dto.CreateAwardDto;
import com.evo.apatrios.api.award.internal.dto.UpdateAwardDto;
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
class InternalAwardControllerIT {

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
    @DataSet(value = "/datasets/api/award/create.json", cleanAfter = true, cleanBefore = true)
    @ExpectedDataSet(value = "/datasets/api/award/create__expected.json")
    void create() throws IOException {
        // Arrange
        CreateAwardDto dto = ResourceUtils.parseJson("/json/api/award/create.json", CreateAwardDto.class);

        // Act
        AwardDto response = client.post()
                                  .uri("/api/v1/internal/award/create")
                                  .bodyValue(dto)
                                  .header("Authorization", token)
                                  .exchange()
                                  .expectStatus()
                                  .isOk()
                                  .expectBody(AwardDto.class)
                                  .returnResult()
                                  .getResponseBody();

        // Assert
        AwardDto expectedResponse = ResourceUtils.parseJson("/json/api/award/create__expected.json", AwardDto.class);

        assertThat(response).usingRecursiveComparison()
                            .withStrictTypeChecking()
                            .ignoringFields("id")
                            .isEqualTo(expectedResponse);
    }

    @Test
    @DataSet(value = "/datasets/api/award/update.json", cleanAfter = true, cleanBefore = true)
    @ExpectedDataSet(value = "/datasets/api/award/update__expected.json")
    void update() throws IOException {
        // Arrange
        UUID id = UUID.fromString("00000000-0000-0000-0000-000000000000");
        UpdateAwardDto dto = ResourceUtils.parseJson("/json/api/award/update.json", UpdateAwardDto.class);

        // Act
        AwardDto response = client.put()
                                  .uri("/api/v1/internal/award/update/"+id)
                                  .bodyValue(dto)
                                  .header("Authorization", token)
                                  .exchange()
                                  .expectStatus()
                                  .isOk()
                                  .expectBody(AwardDto.class)
                                  .returnResult()
                                  .getResponseBody();

        // Assert
        AwardDto expectedResponse = ResourceUtils.parseJson("/json/api/award/update__expected.json", AwardDto.class);

        assertThat(response).usingRecursiveComparison()
                            .withStrictTypeChecking()
                            .isEqualTo(expectedResponse);
    }
}