package com.example.gigabox.dto;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMovie is a Querydsl query type for Movie
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMovie extends EntityPathBase<Movie> {

    private static final long serialVersionUID = -942082959L;

    public static final QMovie movie = new QMovie("movie");

    public final StringPath actors = createString("actors");

    public final StringPath age = createString("age");

    public final StringPath date = createString("date");

    public final StringPath detailUrl = createString("detailUrl");

    public final StringPath dInfo = createString("dInfo");

    public final StringPath director = createString("director");

    public final StringPath dTitle = createString("dTitle");

    public final StringPath genre = createString("genre");

    public final NumberPath<Double> gpa = createNumber("gpa", Double.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> likes = createNumber("likes", Integer.class);

    public final StringPath movieCode = createString("movieCode");

    public final StringPath posterUrl = createString("posterUrl");

    public final StringPath ranking = createString("ranking");

    public final StringPath rate = createString("rate");

    public final ListPath<Review, QReview> reviews = this.<Review, QReview>createList("reviews", Review.class, QReview.class, PathInits.DIRECT2);

    public final StringPath story = createString("story");

    public final StringPath title = createString("title");

    public QMovie(String variable) {
        super(Movie.class, forVariable(variable));
    }

    public QMovie(Path<? extends Movie> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMovie(PathMetadata metadata) {
        super(Movie.class, metadata);
    }

}

