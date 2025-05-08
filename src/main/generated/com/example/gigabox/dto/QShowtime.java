package com.example.gigabox.dto;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QShowtime is a Querydsl query type for Showtime
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QShowtime extends EntityPathBase<Showtime> {

    private static final long serialVersionUID = 1780293225L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QShowtime showtime = new QShowtime("showtime");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QResmovie resmovie;

    public final QRestheater restheater;

    public final DateTimePath<java.time.LocalDateTime> showTime = createDateTime("showTime", java.time.LocalDateTime.class);

    public QShowtime(String variable) {
        this(Showtime.class, forVariable(variable), INITS);
    }

    public QShowtime(Path<? extends Showtime> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QShowtime(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QShowtime(PathMetadata metadata, PathInits inits) {
        this(Showtime.class, metadata, inits);
    }

    public QShowtime(Class<? extends Showtime> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.resmovie = inits.isInitialized("resmovie") ? new QResmovie(forProperty("resmovie")) : null;
        this.restheater = inits.isInitialized("restheater") ? new QRestheater(forProperty("restheater")) : null;
    }

}

