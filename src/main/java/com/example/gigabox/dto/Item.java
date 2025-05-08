package com.example.gigabox.dto;

import com.querydsl.core.annotations.QueryEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.json.JSONObject;
import lombok.Getter;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String link;
    private String image;
    private int lprice;
    private String productId;
    private String category3;


    public Item(JSONObject itemJson){
        this.title = itemJson.getString("title");
        this.link = itemJson.getString("link");
        this.image = itemJson.getString("image");
        this.lprice = itemJson.getInt("lprice");
        this.productId = itemJson.getString("productId");
        this.category3 = itemJson.getString("category3");
    }

}
