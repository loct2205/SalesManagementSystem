package com.salesmanagementsystem.salesmanagementsystem.Controller;

import com.salesmanagementsystem.salesmanagementsystem.Model.data;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class MainInterfaceController implements Initializable {

    @FXML
    private Button customers_btn;

    @FXML
    private Button dashboard_btn;

    @FXML
    private Button inventory_addbnt;

    @FXML
    private Button inventory_btn;

    @FXML
    private Button inventory_clearbtn;

    @FXML
    private TableColumn<?, ?> inventory_col_date;

    @FXML
    private TableColumn<?, ?> inventory_col_idproduct;

    @FXML
    private TableColumn<?, ?> inventory_col_price;

    @FXML
    private TableColumn<?, ?> inventory_col_productname;

    @FXML
    private TableColumn<?, ?> inventory_col_status;

    @FXML
    private TableColumn<?, ?> inventory_col_stock;

    @FXML
    private TableColumn<?, ?> inventory_col_type;

    @FXML
    private Button inventory_deletebtn;

    @FXML
    private AnchorPane inventory_form;

    @FXML
    private ImageView inventory_imageview;

    @FXML
    private Button inventory_importbtn;

    @FXML
    private TableView<?> inventory_tableview;

    @FXML
    private Button inventory_updatebtn;

    @FXML
    private AnchorPane mainForm;

    @FXML
    private Button menu_btn;

    @FXML
    private Button signout_bnt;

    @FXML
    private Label username;

    public void displayUsername() {
        String user = data.username;
        user = user.substring(0, 1).toUpperCase() + user.substring(1);

        username.setText(user);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
