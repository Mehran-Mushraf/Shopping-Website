package com.example.ecommerce;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.SQLException;

public class headerController {
    @FXML
    public void initialize(){
        if(!HelloApplication.emailId.equals("")){
            loginButton.setOpacity(0);
            loginButton.setDisable(true);
            email.setText(HelloApplication.emailId);
        }
    }
    @FXML
    Button loginButton,logoutButton;
    @FXML
    Label email;
    @FXML
    TextField searchtext;
    @FXML
    public void login(MouseEvent e) throws IOException {
        AnchorPane loginpage = FXMLLoader.load((getClass().getResource("loginPage.fxml")));
        HelloApplication.root.getChildren().add(loginpage);
        
    }
    @FXML
    public void search(MouseEvent e)throws IOException , SQLException
    {
        ProductPage productPage = new ProductPage();

        Header header = new Header();
        AnchorPane productPane = new AnchorPane();
        productPane.getChildren().add(productPage.productsbySearch(searchtext.getText()));
        productPane.setLayoutX(10);
        productPane.setLayoutY(50);


        HelloApplication.root.getChildren().clear();
        HelloApplication.root.getChildren().addAll(header.root,productPane);
    }
    @FXML
    public void logoutAppear(MouseEvent e)
    {
        if(logoutButton.getOpacity() == 0)
        {
            logoutButton.setOpacity(1);
        }
        else {
            logoutButton.setOpacity(0);
        }
    }
    @FXML
    public void logout(MouseEvent e) throws IOException {
        if(logoutButton.getOpacity() ==1 ) {
            HelloApplication.emailId = "";
            logoutButton.setOpacity(0);
            Header header = new Header();
            HelloApplication.root.getChildren().add(header.root);
        }
    }
}
