package com.evo.apatrios.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCustomUserBoughtAward is a Querydsl query type for CustomUserBoughtAward
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCustomUserBoughtAward extends EntityPathBase<CustomUserBoughtAward> {

    private static final long serialVersionUID = -560750083L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCustomUserBoughtAward customUserBoughtAward = new QCustomUserBoughtAward("customUserBoughtAward");

    public final QAward award;

    public final ComparablePath<java.util.UUID> id = createComparable("id", java.util.UUID.class);

    public final BooleanPath isReceived = createBoolean("isReceived");

    public QCustomUserBoughtAward(String variable) {
        this(CustomUserBoughtAward.class, forVariable(variable), INITS);
    }

    public QCustomUserBoughtAward(Path<? extends CustomUserBoughtAward> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCustomUserBoughtAward(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCustomUserBoughtAward(PathMetadata metadata, PathInits inits) {
        this(CustomUserBoughtAward.class, metadata, inits);
    }

    public QCustomUserBoughtAward(Class<? extends CustomUserBoughtAward> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.award = inits.isInitialized("award") ? new QAward(forProperty("award")) : null;
    }

}

