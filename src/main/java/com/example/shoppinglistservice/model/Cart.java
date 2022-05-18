package com.example.shoppinglistservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "cart")
public class Cart {
  @Id
  private String id;

  private String userName;
  private List<Product> cart;

  public Cart(String userName, List<Product> cart) {
    this.userName = userName;
    this.cart = cart;
  }

}
