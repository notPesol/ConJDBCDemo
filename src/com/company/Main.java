package com.company;

import com.company.db.Datasource;
import com.company.model.Product;
import com.company.model.ProductView;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        Datasource datasource = Datasource.getInstance();
        if (datasource.open()) {
            System.out.println("Database connected");
        }

        List<Product> products = datasource.queryAllProducts(Datasource.ORDER_BY.ASC);
        printProducts(products);

        products = datasource.queryProductByName("7j05");
        printProducts(products);

        List<ProductView> productViews = datasource.queryAllProductView(Datasource.ORDER_BY.ASC);
        productViews.forEach(productView -> {
            System.out.println("name: " + productView.getName() +
                    ", stock: " + productView.getStock() +
                    ", brand: " + productView.getBrand() +
                    ", category: " + productView.getCategory());
        });


        datasource.insertProduct("mg9009", 7, "aerosoft", "shoes");


        if (datasource.close()) {
            System.out.println("Database closed");
        }
    }

    private static void printProducts(List<Product> products) {
        if (products != null) {
            products.forEach(product -> System.out.println("name: " + product.getName() +
                    ", stock: " + product.getStock()));
        }
    }
}
