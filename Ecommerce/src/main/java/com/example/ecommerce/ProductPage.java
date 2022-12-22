package com.example.ecommerce;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductPage {
    ListView<HBox> products;
    ListView<HBox> productsbySearch(String search) throws SQLException {
        products = new ListView<>();

//        products.setPrefHeight(80);
        ObservableList<HBox> productList = FXCollections.observableArrayList();
        ResultSet res = HelloApplication.connection.executeQuery("Select * from product");
        while(res.next()) {
            if(res.getString("productName").toLowerCase().contains(search.toLowerCase())) {
                Label name = new Label();
                Label productId = new Label();
                Label price = new Label();
                Button Buy = new Button();

                name.setMinWidth(150);
                productId.setMinWidth(50);
                price.setMinWidth(190);
                Buy.setStyle("-fx-background-color: green ");
                Buy.setText("Buy");
                Buy.setPrefWidth(70);

                HBox productDetails = new HBox();

                Buy.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        if (HelloApplication.emailId.equals("")) {
                            Dialog<String> dialog = new Dialog<>();
                            dialog.setTitle("Login");
                            ButtonType type = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                            dialog.getDialogPane().getButtonTypes().add(type);
                            dialog.setContentText("Please login first to order");
                            dialog.showAndWait();
                        } else {
                            System.out.println("You are logged in with " + HelloApplication.emailId);
                            Order order = new Order();
                            try {
                                order.placeOrder(productId.getText());
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                        System.out.println("Buy button is getting clicked");
                    }
                });
                name.setText(res.getString("ProductName"));
                price.setText(res.getString("Price"));
                productId.setText(res.getString("ProductID"));
                productDetails.getChildren().addAll(productId, name, price, Buy);
                productList.add(productDetails);
            }
        }
        products.setPrefWidth(490);
        products.setItems(productList);
        return products;
    }

    ListView<HBox> products() throws SQLException {
        products = new ListView<>();
        ObservableList<HBox> productList = FXCollections.observableArrayList();
        ResultSet res = HelloApplication.connection.executeQuery("Select * from product");
        while(res.next()) {
            Label name = new Label();
            Label productId = new Label();
            Label price = new Label();
            Button Buy = new Button();
            name.setMinWidth(150);
            productId.setMinWidth(50);
            price.setMinWidth(190);
            Buy.setStyle("-fx-background-color: green ");
            Buy.setText("Buy");
            Buy.setPrefWidth(70);

            HBox productDetails = new HBox();

            Buy.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    if (HelloApplication.emailId.equals("")) {
                        Dialog<String> dialog = new Dialog<>();
                        dialog.setTitle("Login");
                        ButtonType type = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                        dialog.getDialogPane().getButtonTypes().add(type);
                        dialog.setContentText("Please login first to order");
                        dialog.showAndWait();
                        System.out.println("Please Login first");
                    } else {
                        System.out.println("You are logged in with " + HelloApplication.emailId);
                        Order order = new Order();
                        try {
                            order.placeOrder(productId.getText());
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("Buy button is getting clicked");
                }
            });
            name.setText(res.getString("ProductName"));
            ;
            price.setText(res.getString("Price"));
            productId.setText(res.getString("ProductID"));
            productDetails.getChildren().addAll(productId, name, price, Buy);
            productList.add(productDetails);

        }
        products.setPrefWidth(490);
        products.setItems(productList);
        return products;
    }
}
