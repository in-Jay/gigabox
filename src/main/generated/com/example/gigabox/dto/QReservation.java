package com.example.gigabox.dto;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReservation is a Querydsl query type for Reservation
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReservation extends EntityPathBase<Reservation> {

    private static final long serialVersionUID = -808238003L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReservation reservation = new QReservation("reservation");

    public final StringPath area = createString("area");

    public final NumberPath<Integer> count = createNumber("count", Integer.class);

    public final StringPath date = createString("date");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath resmovie = createString("resmovie");

    public final NumberPath<Long> resmovieId = createNumber("resmovieId", Long.class);

    public final StringPath resmovieImage = createString("resmovieImage");

    public final StringPath restheater = createString("restheater");

    public final NumberPath<Long> restheaterId = createNumber("restheaterId", Long.class);

    public final ListPath<ReservationSeat, QReservationSeat> seats = this.<ReservationSeat, QReservationSeat>createList("seats", ReservationSeat.class, QReservationSeat.class, PathInits.DIRECT2);

    public final StringPath time = createString("time");

    public final NumberPath<Integer> totalPrice = createNumber("totalPrice", Integer.class);

    public final com.example.gigabox.users.QGigaUser user;

    public QReservation(String variable) {
        this(Reservation.class, forVariable(variable), INITS);
    }

    public QReservation(Path<? extends Reservation> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReservation(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReservation(PathMetadata metadata, PathInits inits) {
        this(Reservation.class, metadata, inits);
    }

    public QReservation(Class<? extends Reservation> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new com.example.gigabox.users.QGigaUser(forProperty("user")) : null;
    }

}

