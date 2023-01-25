package com.evo.apatrios.service.award;

import com.evo.apatrios.model.Award;
import com.evo.apatrios.service.award.argument.CreateAwardArgument;
import com.evo.apatrios.service.award.argument.SearchAwardArgument;
import com.evo.apatrios.service.award.argument.UpdateAwardArgument;
import lombok.NonNull;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.UUID;

public interface AwardService {

    Award getExisting(@NonNull UUID awardId);

    List<Award> getList(SearchAwardArgument argument, Sort sort);

    Award create(@NonNull CreateAwardArgument argument);

    Award update(@NonNull UpdateAwardArgument argument, @NonNull UUID id);
}
