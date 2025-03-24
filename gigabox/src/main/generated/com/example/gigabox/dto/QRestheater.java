package com.example.gigabox.dto;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRestheater is a Querydsl query type for Restheater
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRestheater extends EntityPathBase<Restheater> {

    private static final long serialVersionUID = -1840073008L;

    public static final QRestheater restheater = new QRestheater("restheater");

    public final StringPath area = createString("area");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<Showtime, QShowtime> showTimes = this.<Showtime, QShowtime>createList("showTimes", Showtime.class, QShowtime.class, PathInits.DIRECT2);

    public final StringPath theater = createString("theater");

    public QRestheater(String variable) {
        super(Restheater.class, forVariable(variable));
    }

    public QRestheater(Path<? extends Restheater> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRestheater(PathMetadata metadata) {
        super(Restheater.class, metadata);
    }

}

