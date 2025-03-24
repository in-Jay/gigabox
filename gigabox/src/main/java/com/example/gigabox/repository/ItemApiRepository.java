package com.example.gigabox.repository;

import com.example.gigabox.dto.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemApiRepository extends JpaRepository<Item, Long>, ItemApiRepositoryCustom{

}
