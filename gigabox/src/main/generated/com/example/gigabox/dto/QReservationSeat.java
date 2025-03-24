package com.example.gigabox.dto;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReservationSeat is a Querydsl query type for ReservationSeat
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReservationSeat extends EntityPathBase<ReservationSeat> {

    private static final long serialVersionUID = 1895143410L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReservationSeat reservationSeat = new QReservationSeat("reservationSeat");

    public final NumberPath<Integer> adultnum = createNumber("adultnum", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QReservation reservation;

    public final StringPath seat = createString("seat");

    public final NumberPath<Integer> youthnum = createNumber("youthnum", Integer.class);

    public QReservationSeat(String variable) {
        this(ReservationSeat.class, forVariable(variable), INITS);
    }

    public QReservationSeat(Path<? extends ReservationSeat> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReservationSeat(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReservationSeat(PathMetadata metadata, PathInits inits) {
        this(ReservationSeat.class, metadata, inits);
    }

    public QReservationSeat(Class<? extends ReservationSeat> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.reservation = inits.isInitialized("reservation") ? new QReservation(forProperty("reservation"), inits.get("reservation")) : null;
    }

}

