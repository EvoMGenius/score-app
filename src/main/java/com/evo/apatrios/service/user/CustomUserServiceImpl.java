package com.evo.apatrios.service.user;

import com.evo.apatrios.exception.UserNotFoundException;
import com.evo.apatrios.model.CustomUser;
import com.evo.apatrios.model.QCustomUser;
import com.evo.apatrios.model.Role;
import com.evo.apatrios.repository.CustomUserRepository;
import com.evo.apatrios.service.user.argument.CreateUserArgument;
import com.evo.apatrios.service.user.argument.SearchUserArgument;
import com.evo.apatrios.service.user.argument.UpdateUserArgument;
import com.evo.apatrios.service.utils.QPredicates;
import com.google.common.collect.Lists;
import com.querydsl.core.types.Predicate;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomUserServiceImpl implements CustomUserService {

    private final CustomUserRepository repository;

    private final QCustomUser qUser = QCustomUser.customUser;

    @Override
    @Transactional(readOnly = true)
    public CustomUser getExisting(@NonNull UUID id) {
        return repository.findById(id).orElseThrow(() -> new UserNotFoundException("User with this id is not found", id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<CustomUser> getList(SearchUserArgument argument, Sort sort) {
        Predicate predicate = buildPredicate(argument);

        return Lists.newArrayList(repository.findAll(predicate, sort));
    }

    @Override
    @Transactional
    public CustomUser create(@NonNull CreateUserArgument argument) {
        return repository.save(CustomUser.builder()
                                         .fullName(argument.getFullName())
                                         .faculty(argument.getFaculty())
                                         .studyGroup(argument.getStudyGroup())
                                         .email(argument.getEmail())
                                         .role(Role.USER)
                                         .score(0L)
                                         .password(argument.getPassword())
                                         .awards(new ArrayList<>()).build());
    }

    @Override
    @Transactional
    public CustomUser update(@NonNull UpdateUserArgument argument, @NonNull UUID id) {
        CustomUser user = getExisting(id);

        user.setFullName(argument.getFullName());
        user.setFaculty(argument.getFaculty());
        user.setStudyGroup(argument.getStudyGroup());
        user.setEmail(argument.getEmail());
        user.setScore(argument.getScore());
        user.setPassword(argument.getPassword());
        user.setRole(argument.getRole());
        user.setAwards(argument.getAwards());

        return repository.save(user);
    }

    @Override
    public void deleteById(@NonNull UUID id) {
        repository.deleteById(id);
    }

    private Predicate buildPredicate(SearchUserArgument argument) {
        return QPredicates.builder()
                          .add(argument.getFullName(), qUser.fullName::containsIgnoreCase)
                          .add(argument.getEmail(), qUser.email::containsIgnoreCase)
                          .add(argument.getFaculty(), qUser.faculty::containsIgnoreCase)
                          .add(argument.getStudyGroup(), qUser.studyGroup::containsIgnoreCase)
                          .buildAnd();
    }
}