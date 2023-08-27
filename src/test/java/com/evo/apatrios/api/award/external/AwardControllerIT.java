package com.evo.apatrios.api.award.external;

import com.evo.apatrios.api.award.external.dto.output.AwardDto;
import com.evo.apatrios.util.ResourceUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.github.database.rider.core.api.dataset.DataSet;
import com.google.common.collect.Lists;
import com.jupiter.tools.spring.test.postgres.annotation.meta.EnablePostgresIntegrationTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.web.reactive.server.WebTestClient;
import ru.themikhailz.security.CustomUserDetailsImpl;
import ru.themikhailz.security.Role;
import ru.themikhailz.service.JwtService;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static com.evo.apatrios.model.Role.USER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@EnablePostgresIntegrationTest
class AwardControllerIT {

    @Autowired
    private WebTestClient client;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private UserDetailsService userDetailsService;

    private final UserDetails userDetails = CustomUserDetailsImpl.builder()
                                                                 .username("username")
                                                                 .password("")
                                                                 .role(Role.USER)
                                                                 .build();

    private final String token = "Bearer token";

    @BeforeEach
    public void before() {
        when(jwtService.extractId(any())).thenReturn(UUID.fromString("00000000-0000-0000-0000-000000000000"));
        when(jwtService.extractUsername(any())).thenReturn("username");
        when(jwtService.getRoles(any())).thenReturn(List.of(USER.name()));
        when(userDetailsService.loadUserByUsername(any())).thenReturn(userDetails);
    }


    @ParameterizedTest
    @MethodSource("list")
    @DataSet(value = "/datasets/api/award/list.json", cleanBefore = true, cleanAfter = true)
    void getList(String paramName,
                 Object paramValue,
                 String expectedResponseFile) throws IOException {
        // Arrange
        List<AwardDto> expectedResponse = ResourceUtils.parseJson("/json/api/award/list/" + expectedResponseFile,
                                                                  new TypeReference<List<AwardDto>>() {});

        // Act
        List<AwardDto> response = client.get()
                                        .uri(uriBuilder -> uriBuilder.path("/api/v1/award/list")
                                                                     .queryParam(paramName, paramValue)
                                                                     .build())
                                        .header("Authorization", token)
                                        .exchange()
                                        .expectStatus()
                                        .isOk()
                                        .expectBody(new ParameterizedTypeReference<List<AwardDto>>() {})
                                        .returnResult()
                                        .getResponseBody();

        // Assert
        assertThat(response).usingRecursiveComparison()
                            .withStrictTypeChecking()
                            .ignoringCollectionOrder()
                            .isEqualTo(expectedResponse);
    }

    private static List<Arguments> list() {
        return Lists.newArrayList(
                Arguments.of("", "", "list_all.json"),
                Arguments.of("name", "Супер пупер награда", "list_name.json"),
                Arguments.of("cost", "133", "list_cost.json"));
    }
}