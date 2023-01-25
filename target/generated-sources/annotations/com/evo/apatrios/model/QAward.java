package com.evo.apatrios.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QAward is a Querydsl query type for Award
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAward extends EntityPathBase<Award> {

    private static final long serialVersionUID = 576439972L;

    public static final QAward award = new QAward("award");

    public final NumberPath<Long> cost = createNumber("cost", Long.class);

    public final ComparablePath<java.util.UUID> id = createComparable("id", java.util.UUID.class);

    public final StringPath name = createString("name");

    public QAward(String variable) {
        super(Award.class, forVariable(variable));
    }

    public QAward(Path<? extends Award> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAward(PathMetadata metadata) {
        super(Award.class, metadata);
    }

}

