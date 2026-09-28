package com.cramer._551_shop_vorlage.service;

import com.cramer._551_shop_vorlage.data.ProductData;
import com.cramer._551_shop_vorlage.model.Product;
import com.cramer._551_shop_vorlage.model.ShoppingCart;


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
