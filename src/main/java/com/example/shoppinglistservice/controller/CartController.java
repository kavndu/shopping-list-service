package com.example.shoppinglistservice.controller;

import com.example.shoppinglistservice.model.Cart;
import com.example.shoppinglistservice.model.Product;
import com.example.shoppinglistservice.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/d")
public class CartController {

  @Autowired
  CartRepository cartRepository;

  @GetMapping("/carts")
  public ResponseEntity<List<Cart>> getAllCarts() {
    try {
      List<Cart> carts = new ArrayList<Cart>();

        cartRepository.findAll().forEach(carts::add);

      if (carts.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(carts, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/cart/{id}")
  public ResponseEntity<Cart> getTutorialById(@PathVariable("id") String id) {
    Optional<Cart> cartData = cartRepository.findById(id);

    if (cartData.isPresent()) {
      return new ResponseEntity<>(cartData.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/cart/product")
  public ResponseEntity<?> createTutorial(@RequestBody Cart cart) {
    try {
      Cart _cart = cartRepository.save(new Cart(cart.getUserName(), cart.getCart()));
      return new ResponseEntity<>("Successfully Added.", HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping("/carts/{id}")
  public ResponseEntity<Cart> updateTutorial(@PathVariable("id") String id, @RequestBody Cart cart) {
    Optional<Cart> cartData = cartRepository.findById(id);

    if (cartData.isPresent()) {
      Cart _cart = cartData.get();
      _cart.setUserName(cart.getUserName());
      _cart.setCart(cart.getCart());
      return new ResponseEntity<>(cartRepository.save(_cart), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/cart/{id}")
  public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") String id) {
    try {
      cartRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
