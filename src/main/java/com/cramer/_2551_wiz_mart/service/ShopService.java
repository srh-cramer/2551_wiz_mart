package com.cramer._2551_wiz_mart.service;

import com.cramer._2551_wiz_mart.data.ProductData;
import com.cramer._2551_wiz_mart.model.Product;
import com.cramer._2551_wiz_mart.model.ShoppingCart;


import java.util.List;

public class ShopService {

    private List<Product> products;
    private ShoppingCart cart;

    public ShopService() {
        products = ProductData.createProducts();
        cart = new ShoppingCart();
    }

    public List<Product> getProducts() {
        return products;
    }

    public ShoppingCart getCart() {
        return cart;
    }

    public void addToCart(Product product) {
        cart.addProduct(product);
    }

    public void removeFromCart(Product product) {
        cart.removeProduct(product);
    }

    public void clearCart() {
        cart.clear();
    }
}
