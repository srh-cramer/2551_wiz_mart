package com.cramer._551_shop_vorlage.controller;

import com.cramer._551_shop_vorlage.model.CartItem;
import com.cramer._551_shop_vorlage.model.Product;
import com.cramer._551_shop_vorlage.service.ShopService;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

import java.util.List;

public class ShopController {
    @FXML
    private FlowPane productPane;

    @FXML
    private ListView<CartItem> cartListView;

    @FXML
    private Label totalLabel;

    @FXML
    private Label cartCountLabel;

    @FXML
    private Label productCountLabel;

    @FXML
    private TextField searchField;

    @FXML
    private ComboBox<String> categoryComboBox;

    @FXML
    private ComboBox<String> sortComboBox;

    private ShopService shopService;

    @FXML
    public void initialize() {
        shopService = new ShopService();

        setupCategories();
        setupSorting();

        categoryComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            displayProducts();
        });


        displayProducts();
        updateCartView();
    }

    // Füge dem Kategorie-Dropdown die Einträge hinzu
    private void setupCategories() {
        categoryComboBox.getItems().add("All");
        categoryComboBox.getItems().add("Potion");
        categoryComboBox.getItems().add("Cloak");
        categoryComboBox.getItems().add("Wand");
        categoryComboBox.getItems().add("Spellbook");

        categoryComboBox.setValue("All");
    }

    // Füge dem Sortierungs-Dropdown die Einträge hinzu
    private void setupSorting() {
        sortComboBox.getItems().add("Name");
        sortComboBox.getItems().add("Price: Low to High");
        sortComboBox.getItems().add("Price: High to Low");

        sortComboBox.setValue("Name");
    }

    // Zeige nur die Produkte an, nach denen gefiltert/gesucht wurde
    // Standard: Zeige alle an
    // TODO: Sortierung berücksichtigen
    private void displayProducts() {

        productPane.getChildren().clear();

        String searchText = searchField.getText().toLowerCase();
        String selectedCategory = categoryComboBox.getValue();
        String selectedSort = sortComboBox.getValue();

        List<Product> products = shopService.getProducts();

        for (Product product : products) {

            if (!searchText.isEmpty()
                    && !product.getName().toLowerCase().contains(searchText)) {
                continue;
            }

            if (!selectedCategory.equals("All")
                    && !product.getCategory().equals(selectedCategory)) {
                continue;
            }

            productPane.getChildren().add(createProductCard(product));
        }

        productCountLabel.setText(
                "Products: " + productPane.getChildren().size()
        );
    }

    // Erstelle eine VBox, die ein einzelnes Produkt anzeigt
    private VBox createProductCard(Product product) {

        VBox card = new VBox(8);
        card.setPadding(new Insets(12));
        card.setPrefWidth(220);

        Label nameLabel = new Label(product.getName());
        Label categoryLabel = new Label(product.getCategory());
        Label priceLabel = new Label(
                String.format("%.2f Gold", product.getPrice())
        );
        Label descriptionLabel = new Label(product.getDescription());

        descriptionLabel.setWrapText(true);

        Label stockLabel = new Label(
                "Stock: " + product.getStock()
        );

        Button addButton = new Button("Add to cart");

        addButton.setOnAction(event -> {
            shopService.addToCart(product);
            updateCartView();
        });

        card.getChildren().addAll(
                nameLabel,
                categoryLabel,
                priceLabel,
                descriptionLabel,
                stockLabel,
                addButton
        );

        return card;
    }

    private void updateCartView() {

        cartListView.getItems().clear();

        cartListView.getItems().addAll(
                shopService.getCart().getItems()
        );

        totalLabel.setText(
                String.format(
                        "Total: %.2f Gold",
                        shopService.getCart().getTotalPrice()
                )
        );

        cartCountLabel.setText(
                "Items: " + shopService.getCart().getItemCount()
        );
    }

    @FXML
    private void handleSearch(){
        displayProducts();
    }

    @FXML
    private void handleClearCart() {
        shopService.clearCart();
        updateCartView();
    }

    @FXML
    private void handleCheckout() {

        if (shopService.getCart().getItems().isEmpty()) {
            showMessage(
                    "Empty Cart",
                    "Your cart is empty."
            );
            return;
        }

        showMessage(
                "Checkout",
                "Order completed!"
        );

        shopService.clearCart();
        updateCartView();
    }

    private void showMessage(String title, String message) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
