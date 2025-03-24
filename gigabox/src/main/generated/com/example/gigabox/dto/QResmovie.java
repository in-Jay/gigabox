package com.example.gigabox.dto;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QResmovie is a Querydsl query type for Resmovie
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QResmovie extends EntityPathBase<Resmovie> {

    private static final long serialVersionUID = 1775083951L;

    public static final QResmovie resmovie = new QResmovie("resmovie");

    public final StringPath age = createString("age");

    public final StringPath date = createString("date");

    public final DatePath<java.time.LocalDate> endDate = createDate("endDate", java.time.LocalDate.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath movieCode = createString("movieCode");

    public final StringPath posterUrl = createString("posterUrl");

    public final ListPath<Showtime, QShowtime> showtime = this.<Showtime, QShowtime>createList("showtime", Showtime.class, QShowtime.class, PathInits.DIRECT2);

    public final DatePath<java.time.LocalDate> startDate = createDate("startDate", java.time.LocalDate.class);

    public final StringPath title = createString("title");

    public QResmovie(String variable) {
        super(Resmovie.class, forVariable(variable));
    }

    public QResmovie(Path<? extends Resmovie> path) {
        super(path.getType(), path.getMetadata());
    }

    public QResmovie(PathMetadata metadata) {
        super(Resmovie.class, metadata);
    }

}

