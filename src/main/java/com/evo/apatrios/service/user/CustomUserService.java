package com.evo.apatrios.service.user;

import com.evo.apatrios.model.CustomUser;
import com.evo.apatrios.service.user.argument.CreateUserArgument;
import com.evo.apatrios.service.user.argument.SearchUserArgument;
import com.evo.apatrios.service.user.argument.UpdateUserArgument;
import lombok.NonNull;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.UUID;

public interface CustomUserService {

    CustomUser getExisting(@NonNull UUID id);

    List<CustomUser> getList(SearchUserArgument argument, Sort sort);

    CustomUser create(@NonNull CreateUserArgument argument);

    CustomUser update(@NonNull UpdateUserArgument argument, @NonNull UUID id);

    void deleteById(@NonNull UUID id);

    CustomUser findByEmail(@NonNull String email);

    CustomUser updateScore(@NonNull UUID id, @NonNull Long additionalScore);
}
