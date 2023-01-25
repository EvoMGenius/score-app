package com.evo.apatrios.service.award;

import com.evo.apatrios.exception.AwardNotFoundException;
import com.evo.apatrios.model.Award;
import com.evo.apatrios.model.QAward;
import com.evo.apatrios.repository.AwardRepository;
import com.evo.apatrios.service.award.argument.CreateAwardArgument;
import com.evo.apatrios.service.award.argument.SearchAwardArgument;
import com.evo.apatrios.service.award.argument.UpdateAwardArgument;
import com.evo.apatrios.service.utils.QPredicates;
import com.google.common.collect.Lists;
import com.querydsl.core.types.Predicate;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AwardServiceImpl implements AwardService {

    private final AwardRepository repository;

    private final QAward qAward = QAward.award;

    @Override
    public Award getExisting(@NonNull UUID awardId) {
        return repository.findById(awardId).orElseThrow(() -> new AwardNotFoundException("This award is not found", awardId));
    }

    @Override
    public List<Award> getList(SearchAwardArgument argument, Sort sort) {
        Predicate predicate = buildPredicate(argument);
        return Lists.newArrayList(repository.findAll(predicate, sort));
    }

    @Override
    public Award create(@NonNull CreateAwardArgument argument) {
        return Award.builder()
                    .name(argument.getName())
                    .cost(argument.getCost())
                    .build();
    }

    @Override
    public Award update(@NonNull UpdateAwardArgument argument, @NonNull UUID id) {
        Award award = getExisting(id);

        award.setName(argument.getName());
        award.setCost(argument.getCost());

        return repository.save(award);
    }

    private Predicate buildPredicate(SearchAwardArgument argument) {
        return QPredicates.builder()
                          .add(argument.getName(), qAward.name::containsIgnoreCase)
                          .add(argument.getCost(), qAward.cost::eq)
                          .buildAnd();
    }
}
