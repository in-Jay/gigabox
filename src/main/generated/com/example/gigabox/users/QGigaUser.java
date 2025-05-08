package com.example.gigabox.users;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QGigaUser is a Querydsl query type for GigaUser
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QGigaUser extends EntityPathBase<GigaUser> {

    private static final long serialVersionUID = 1670505533L;

    public static final QGigaUser gigaUser = new QGigaUser("gigaUser");

    public final DatePath<java.time.LocalDate> birthdate = createDate("birthdate", java.time.LocalDate.class);

    public final StringPath email = createString("email");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath password = createString("password");

    public final DateTimePath<java.time.LocalDateTime> passwordToken = createDateTime("passwordToken", java.time.LocalDateTime.class);

    public final StringPath realname = createString("realname");

    public final ListPath<com.example.gigabox.dto.Reservation, com.example.gigabox.dto.QReservation> reservations = this.<com.example.gigabox.dto.Reservation, com.example.gigabox.dto.QReservation>createList("reservations", com.example.gigabox.dto.Reservation.class, com.example.gigabox.dto.QReservation.class, PathInits.DIRECT2);

    public final StringPath username = createString("username");

    public QGigaUser(String variable) {
        super(GigaUser.class, forVariable(variable));
    }

    public QGigaUser(Path<? extends GigaUser> path) {
        super(path.getType(), path.getMetadata());
    }

    public QGigaUser(PathMetadata metadata) {
        super(GigaUser.class, metadata);
    }

}

