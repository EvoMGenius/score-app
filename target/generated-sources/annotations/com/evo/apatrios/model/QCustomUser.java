package com.evo.apatrios.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCustomUser is a Querydsl query type for CustomUser
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCustomUser extends EntityPathBase<CustomUser> {

    private static final long serialVersionUID = -1501293835L;

    public static final QCustomUser customUser = new QCustomUser("customUser");

    public final ListPath<Award, QAward> awards = this.<Award, QAward>createList("awards", Award.class, QAward.class, PathInits.DIRECT2);

    public final StringPath email = createString("email");

    public final StringPath faculty = createString("faculty");

    public final StringPath fullName = createString("fullName");

    public final ComparablePath<java.util.UUID> id = createComparable("id", java.util.UUID.class);

    public final StringPath password = createString("password");

    public final EnumPath<Role> role = createEnum("role", Role.class);

    public final NumberPath<Long> score = createNumber("score", Long.class);

    public final StringPath studyGroup = createString("studyGroup");

    public QCustomUser(String variable) {
        super(CustomUser.class, forVariable(variable));
    }

    public QCustomUser(Path<? extends CustomUser> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCustomUser(PathMetadata metadata) {
        super(CustomUser.class, metadata);
    }

}

