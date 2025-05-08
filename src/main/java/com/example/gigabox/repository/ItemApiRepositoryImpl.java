package com.example.gigabox.repository;

import com.example.gigabox.dto.Item;
import com.example.gigabox.dto.QItem;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ItemApiRepositoryImpl implements ItemApiRepositoryCustom{
    private final EntityManager em;
    private JPAQueryFactory queryFactory;
    @Override
    public List<Item> findByCategory(String category) {
        if(queryFactory == null){
            queryFactory = new JPAQueryFactory(em);
        }
        return queryFactory.selectFrom(QItem.item)
                .where(QItem.item.productId.eq(category)).fetch();
    }
}
