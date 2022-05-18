package com.example.shoppinglistservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "products")
public class Product {
    @Id
    private String id;

    private String productId;

    public Product(String productId) {
        this.productId = productId;
    }
}
