module com.cramer._551_shop_vorlage {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.cramer._551_shop_vorlage to javafx.fxml;
    exports com.cramer._551_shop_vorlage;
}