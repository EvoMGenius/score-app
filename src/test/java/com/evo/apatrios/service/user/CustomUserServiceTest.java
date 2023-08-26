package com.evo.apatrios.service.user;

import com.evo.apatrios.model.CustomUser;
import com.evo.apatrios.model.CustomUserBoughtAward;
import com.evo.apatrios.model.Role;
import com.evo.apatrios.repository.CustomUserRepository;
import com.evo.apatrios.service.user.argument.CreateUserArgument;
import com.evo.apatrios.service.user.argument.SearchUserArgument;
import com.evo.apatrios.service.user.argument.UpdateUserArgument;
import com.querydsl.core.types.Predicate;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class CustomUserServiceTest {

    private final CustomUserRepository repository = Mockito.mock(CustomUserRepository.class);

    private final CustomUserServiceImpl userService = new CustomUserServiceImpl(repository);

    @Test
    void getExisting() {
        // Arrange
        UUID id = UUID.randomUUID();
        CustomUser user = mock(CustomUser.class);

        when(repository.findById(id)).thenReturn(Optional.of(user));

        // Act
        CustomUser actual = userService.getExisting(id);

        // Assert
        ArgumentCaptor<UUID> idArgumentCaptor = ArgumentCaptor.forClass(UUID.class);

        verify(repository).findById(idArgumentCaptor.capture());

        assertThat(idArgumentCaptor.getValue()).isEqualTo(id);
        assertThat(actual).isEqualTo(actual);
    }

    @Test
    void getList() {
        // Arrange
        SearchUserArgument argument = SearchUserArgument.builder()
                                                        .email("email")
                                                        .faculty("faculty")
                                                        .fullName("fullname")
                                                        .studyGroup("group")
                                                        .build();
        Sort sort = mock(Sort.class);
        List<CustomUser> expectedList = new ArrayList<>();

        when(repository.findAll(any(Predicate.class), any(Sort.class))).thenReturn(expectedList);

        // Act
        List<CustomUser> list = userService.getList(argument, sort);

        // Assert
        ArgumentCaptor<Predicate> predicateArgumentCaptor = ArgumentCaptor.forClass(Predicate.class);

        verify(repository).findAll(predicateArgumentCaptor.capture(), any(Sort.class));
        Predicate capturedPredicate = predicateArgumentCaptor.getValue();

        assertThat(capturedPredicate.toString()).isEqualTo("containsIc(customUser.fullName,fullname) &&" +
                                                           " containsIc(customUser.email,email) &&" +
                                                           " containsIc(customUser.faculty,faculty) &&" +
                                                           " containsIc(customUser.studyGroup,group)");

        assertThat(list).isEqualTo(expectedList);
    }

    @Test
    void create() {
        // Arrange
        CreateUserArgument argument = CreateUserArgument.builder()
                                                        .email("email")
                                                        .password("password")
                                                        .fullName("name")
                                                        .studyGroup("group")
                                                        .faculty("faculty")
                                                        .build();

        CustomUser expectedUser = mock(CustomUser.class);
        when(repository.save(any())).thenReturn(expectedUser);

        // Act
        CustomUser actual = userService.create(argument);

        // Assert
        ArgumentCaptor<CustomUser> customUserArgumentCaptor = ArgumentCaptor.forClass(CustomUser.class);

        verify(repository).save(customUserArgumentCaptor.capture());
        CustomUser capturedUser = customUserArgumentCaptor.getValue();

        CustomUser expectedCapturedUser = CustomUser.builder()
                                                    .email(argument.getEmail())
                                                    .password(argument.getPassword())
                                                    .fullName(argument.getFullName())
                                                    .studyGroup(argument.getStudyGroup())
                                                    .faculty(argument.getFaculty())
                                                    .role(Role.USER)
                                                    .score(0L)
                                                    .awards(new ArrayList<>())
                                                    .build();

        assertThat(capturedUser).isEqualTo(expectedCapturedUser);

        assertThat(actual).isEqualTo(expectedUser);
    }

    @Test
    void update() {
        // Arrange
        UUID id = UUID.randomUUID();
        List<CustomUserBoughtAward> awardList = mock(List.class);
        UpdateUserArgument argument = UpdateUserArgument.builder()
                                                        .email("email")
                                                        .score(100L)
                                                        .awards(awardList)
                                                        .fullName("name")
                                                        .studyGroup("group")
                                                        .faculty("faculty")
                                                        .build();

        CustomUser expectedUser = mock(CustomUser.class);
        when(repository.findById(id)).thenReturn(Optional.of(CustomUser.builder()
                                                                       .id(id)
                                                                       .build()));
        when(repository.save(any())).thenReturn(expectedUser);

        // Act
        CustomUser actual = userService.update(argument, id);

        // Assert
        ArgumentCaptor<CustomUser> customUserArgumentCaptor = ArgumentCaptor.forClass(CustomUser.class);

        verify(repository).save(customUserArgumentCaptor.capture());
        CustomUser capturedUser = customUserArgumentCaptor.getValue();

        CustomUser expectedCapturedUser = CustomUser.builder()
                                                    .id(id)
                                                    .email(argument.getEmail())
                                                    .password(actual.getPassword())
                                                    .fullName(argument.getFullName())
                                                    .studyGroup(argument.getStudyGroup())
                                                    .faculty(argument.getFaculty())
                                                    .role(Role.USER)
                                                    .score(argument.getScore())
                                                    .awards(argument.getAwards())
                                                    .build();

        assertThat(capturedUser).isEqualTo(expectedCapturedUser);
        assertThat(actual).isEqualTo(expectedUser);
    }

    @Test
    void deleteById() {
        // Arrange
        UUID id = UUID.randomUUID();

        // Act
        userService.deleteById(id);

        // Assert
        verify(repository).deleteById(id);
    }

    @Test
    void findByEmail() {
        // Arrange
        String email = "email";
        CustomUser user = mock(CustomUser.class);

        when(repository.findByEmail(email)).thenReturn(Optional.of(user));

        // Act
        CustomUser actual = userService.findByEmail(email);

        // Assert
        ArgumentCaptor<String> emailArgumentCaptor = ArgumentCaptor.forClass(String.class);

        verify(repository).findByEmail(emailArgumentCaptor.capture());

        assertThat(emailArgumentCaptor.getValue()).isEqualTo(email);
        assertThat(actual).isEqualTo(actual);
    }

    @Test
    void updateScore() {
        // Arrange
        UUID userId = UUID.randomUUID();
        Long additionalScore = 100L;

        CustomUser expectedUser = mock(CustomUser.class);
        CustomUser existingUser = CustomUser.builder()
                                            .id(userId)
                                            .score(0L)
                                            .build();
        when(repository.findById(userId)).thenReturn(Optional.of(existingUser));
        when(repository.save(any())).thenReturn(expectedUser);
        // Act
        CustomUser actual = userService.updateScore(userId, additionalScore);

        // Assert
        ArgumentCaptor<CustomUser> customUserArgumentCaptor = ArgumentCaptor.forClass(CustomUser.class);

        verify(repository).save(customUserArgumentCaptor.capture());
        CustomUser capturedUser = customUserArgumentCaptor.getValue();

        CustomUser expectedCapturedUser = CustomUser.builder()
                                                    .id(userId)
                                                    .score(existingUser.getScore() + additionalScore)
                                                    .build();
        assertThat(capturedUser).isEqualTo(expectedCapturedUser);

        assertThat(actual).isEqualTo(expectedUser);
    }
}