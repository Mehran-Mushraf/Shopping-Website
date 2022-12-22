package com.example.ecommerce;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginPageController {

    @FXML
    TextField email;

    @FXML
    PasswordField password;

    @FXML
    public void login(MouseEvent e) throws IOException, SQLException {
        String query = String.format("Select * from user where emailID = '%s' AND pass = '%s'",email.getText(),password.getText());
        ResultSet res = HelloApplication.connection.executeQuery(query);

        if(res.next()){
            HelloApplication.emailId = res.getString(("emailID"));
            String userType = res.getString("usertype");
            if(userType.equals("Seller"))
            {
                AnchorPane sellerPage = FXMLLoader.load((getClass().getResource("sellerPage.fxml")));
                HelloApplication.root.getChildren().add(sellerPage);
            }
            else
            {
                System.out.println("We are Logged in as buyer");
                ProductPage productPage = new ProductPage();

                Header header = new Header();
                AnchorPane productPane = new AnchorPane();
                productPane.getChildren().add(productPage.products());
                productPane.setLayoutX(10);
                productPane.setLayoutY(50);
                HelloApplication.root.getChildren().clear();
                HelloApplication.root.getChildren().addAll(header.root,productPane);


            }
            System.out.println("The user is present in the user table");
        }
        else {
            Dialog<String> dialog = new Dialog<>();
            dialog.setTitle("Login");
            ButtonType type = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().add(type);
            dialog.setContentText("Login failed, Please check userName/Password and try again");
            dialog.showAndWait();
        }
    }
}
