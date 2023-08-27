package com.evo.apatrios.api.user.external;

import com.evo.apatrios.api.user.external.dto.output.UserBuyAwardDto;
import com.evo.apatrios.api.user.external.dto.output.UserDto;
import com.evo.apatrios.api.user.external.dto.output.UserListDto;
import com.evo.apatrios.util.ResourceUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.google.common.collect.Lists;
import com.jupiter.tools.spring.test.postgres.annotation.meta.EnablePostgresIntegrationTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
import ru.themikhailz.service.AuthService;
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
class CustomUserControllerIT {

    @Autowired
    private WebTestClient client;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private AuthService authService;

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
    @DataSet(value = "/datasets/api/user/list.json", cleanBefore = true, cleanAfter = true)
    void getList(String paramName,
                 Object paramValue,
                 String expectedResponseFile) throws IOException {
        // Arrange
        List<UserListDto> expectedResponse = ResourceUtils.parseJson("/json/api/user/list/" + expectedResponseFile,
                                                                     new TypeReference<List<UserListDto>>() {});

        // Act
        List<UserListDto> response = client.get()
                                           .uri(uriBuilder -> uriBuilder.path("/api/v1/user/list")
                                                                        .queryParam(paramName, paramValue)
                                                                        .build())
                                           .header("Authorization", token)
                                           .exchange()
                                           .expectStatus()
                                           .isOk()
                                           .expectBody(new ParameterizedTypeReference<List<UserListDto>>() {})
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
                Arguments.of("fullName", "Фамилия Имя Отчество", "list_fullname.json"),
                Arguments.of("faculty", "Факультет писателей", "list_faculty.json"),
                Arguments.of("studyGroup", "Боец кидец", "list_group.json"),
                Arguments.of("email", "test@test.test", "list_email.json"));
    }

    @Test
    @DataSet(value = "/datasets/api/user/buy-award.json", cleanBefore = true, cleanAfter = true)
    @ExpectedDataSet(value = "/datasets/api/user/buy-award__expected.json")
    void buyAward() throws IOException {
        // Arrange
        UUID awardId = UUID.fromString("00000000-0000-0000-0000-000000000000");

        when(authService.getAuthorizedUserId()).thenReturn(UUID.fromString("00000000-0000-0000-0000-000000000000"));

        // Act
        UserBuyAwardDto responseBody = client.post()
                                             .uri("/api/v1/user/{id}/buy", awardId)
                                             .header("Authorization", token)
                                             .exchange()
                                             .expectStatus()
                                             .isOk()
                                             .expectBody(UserBuyAwardDto.class)
                                             .returnResult()
                                             .getResponseBody();

        // Assert

        UserBuyAwardDto expectedResponse = ResourceUtils.parseJson("/json/api/user/buy-award__expected.json", UserBuyAwardDto.class);

        assertThat(responseBody).usingRecursiveComparison()
                                .withStrictTypeChecking()
                                .isEqualTo(expectedResponse);
    }

    @Test
    @DataSet(value = "/datasets/api/user/get-by-id.json", cleanBefore = true, cleanAfter = true)
    void getById() throws IOException {
        // Arrange
        UUID userId = UUID.fromString("00000000-0000-0000-0000-000000000000");

        // Act
        UserDto response = client.get()
                                 .uri("/api/v1/user/{id}/user", userId)
                                 .header("Authorization", token)
                                 .exchange()
                                 .expectStatus()
                                 .isOk()
                                 .expectBody(UserDto.class)
                                 .returnResult()
                                 .getResponseBody();

        // Assert
        UserDto expectedResponse = ResourceUtils.parseJson("/json/api/user/get-by-id__expected.json", UserDto.class);

        assertThat(response).usingRecursiveComparison()
                            .withStrictTypeChecking()
                            .isEqualTo(expectedResponse);
    }

    @Test
    @DataSet(value = "/datasets/api/user/me.json", cleanBefore = true, cleanAfter = true)
    void me() throws IOException {
        when(authService.getAuthorizedUserId()).thenReturn(UUID.fromString("00000000-0000-0000-0000-000000000000"));

        // Act
        UserDto response = client.get()
                                 .uri("/api/v1/user/me")
                                 .header("Authorization", token)
                                 .exchange()
                                 .expectStatus()
                                 .isOk()
                                 .expectBody(UserDto.class)
                                 .returnResult()
                                 .getResponseBody();

        // Assert
        UserDto expectedResponse = ResourceUtils.parseJson("/json/api/user/me__expected.json", UserDto.class);

        assertThat(response).usingRecursiveComparison()
                            .withStrictTypeChecking()
                            .isEqualTo(expectedResponse);
    }
}