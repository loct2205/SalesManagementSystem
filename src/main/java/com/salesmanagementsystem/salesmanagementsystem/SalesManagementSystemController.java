package com.salesmanagementsystem.salesmanagementsystem.Controller;

import com.salesmanagementsystem.salesmanagementsystem.Model.database;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.sql.Connection;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.ResourceBundle;

public class SalesManagementSystemController implements Initializable {
    @FXML
    private Button Createacc_btn;

    @FXML
    private Button Login_btn;

    @FXML
    private Label _forgotrpassword;

    @FXML
    private PasswordField _password;

    @FXML
    private TextField _username;

    @FXML
    private Button alreadyAccount_btn;

    @FXML
    private Button cf_back_btn;

    @FXML
    private PasswordField cf_cfPassword;

    @FXML
    private Button cf_changePass_btn;

    @FXML
    private TextField cf_newPassword;

    @FXML
    private AnchorPane confirm_form;

    @FXML
    private Button fg_back_btn;

    @FXML
    private PasswordField fg_phone;

    @FXML
    private Button fg_proceed_btn;

    @FXML
    private TextField fg_username;

    @FXML
    private AnchorPane forgot_form;

    @FXML
    private AnchorPane loginForm;

    @FXML
    private TextField semail;

    @FXML
    private TextField sfullname;

    @FXML
    private AnchorPane sideForm;

    @FXML
    private AnchorPane signUpForm;

    @FXML
    private Button signUp_btn;

    @FXML
    private TextField spassword;

    @FXML
    private TextField sphone;

    @FXML
    private TextField susername;

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    private Alert alert;

    public void loginBtn() {
        if(_username.getText().isEmpty() || _password.getText().isEmpty()){
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Incorrect Username/Password");
            alert.showAndWait();
        }else{

            String selectData = "SELECT username, password FROM employee WHERE username = ? and password = ?";

            connect = database.connectDB();

            try {

                prepare = connect.prepareStatement(selectData);
                prepare.setString(1, _username.getText());
                prepare.setString(2, _password.getText());

                result = prepare.executeQuery();
                // IF SUCCSESSFULLY LOGIN, THEN PROCEED TO ANOTHER FORM WHICH IS MAIN FROM
                if(result.next()){
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Succesfull Login");
                    alert.showAndWait();

                    //LINK TO MAIN INTERFACE

                    Parent root = FXMLLoader.load(getClass().getResource("mainInterface.fxml"));

                    Stage stage = new Stage();
                    Scene scene = new Scene(root);

                    stage.setTitle("Book & Records Shop Management System");
                    stage.setMinWidth(1100);
                    stage.setMinHeight(600);

                    stage.setScene(scene);
                    stage.show();

                    Login_btn.getScene().getWindow().hide();

                }else{//IF NOT, THEN THE ERROR MESSAGE WILL APPEAR
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Incorrect Username/Password");
                    alert.showAndWait();
                }

            } catch (Exception e) {e.printStackTrace();}
        }
    }


    public void regBtn() {
        if (susername.getText().isEmpty() || spassword.getText().isEmpty()
            || sfullname.getText().isEmpty() || semail.getText().isEmpty()
                || sphone.getText().isEmpty()){
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();
        }else {
            String regData = "INSERT INTO employee (username, password, fullname, email, phone, date)"
                    + "VALUES(?,?,?,?,?,?)";
            connect = database.connectDB();
            try{
                String checkUsername = "SELECT username FROM employee WHERE username = '"
                        + susername + "'";
                prepare = connect.prepareStatement(checkUsername);
                result = prepare.executeQuery();

                if(result.next()){
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText(susername.getText() + " is already taken");
                    alert.showAndWait();
                } else if(spassword.getText().length() < 8) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Invalid Password, atlesst 8 length are needed");
                    alert.showAndWait();
                } else {
                    prepare = connect.prepareStatement(regData);
                    prepare.setString(1, susername.getText());
                    prepare.setString(2, spassword.getText());
                    prepare.setString(3, sfullname.getText());
                    prepare.setString(4, semail.getText());
                    prepare.setString(5, sphone.getText());

                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                    prepare.setString(6, String.valueOf(sqlDate));

                    prepare.executeUpdate();
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully registered Account");
                    alert.showAndWait();

                    susername.setText("");
                    spassword.setText("");
                    sfullname.setText("");
                    semail.setText("");
                    sphone.setText("");

                    TranslateTransition slider = new TranslateTransition();

                    slider.setNode(loginForm);
                    slider.setToX(0);
                    slider.setDuration(Duration.seconds(.5));

                    slider.setOnFinished ((ActionEvent e) -> {
                        alreadyAccount_btn.setVisible(false);
                        Createacc_btn.setVisible((true));
                    });

                    slider.play();

                }

            }catch (Exception e){e.printStackTrace();}
        }
    }

    public void switchForgotPass(){
        forgot_form.setVisible(true);
        loginForm.setVisible(false);
        signUpForm.setVisible(false);

    }

    public void proceedBtn() {
        if(fg_username.getText().isEmpty() || fg_phone.getText().isEmpty()){

            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();
        }else {
            String selectData = "SELECT username, phone FROM employee WHERE username = ? AND phone = ?";
            connect = database.connectDB();

            try {

                prepare = connect.prepareStatement(selectData);
                prepare.setString(1, fg_username.getText());
                prepare.setString(2, fg_phone.getText());

                result = prepare.executeQuery();

                if(result.next()){
                    confirm_form.setVisible(true);
                    forgot_form.setVisible(false);
                }else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Incorrect Information");
                    alert.showAndWait();
                }

            } catch (Exception e) {e.printStackTrace();}
        }
    }

    public void changePassBtn(){
        if(cf_newPassword.getText().isEmpty() || cf_cfPassword.getText().isEmpty()){
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();
        }else {

            if(cf_newPassword.getText().equals(cf_cfPassword.getText())){
                String getDate = "SELECT date FROM employee WHERE username = '" + fg_username.getText() + "'";

                try{
                    prepare = connect.prepareStatement(getDate);
                    result = prepare.executeQuery();

                    String date = "";
                    if (result.next()){
                        date = result.getString("date");
                    }

                    String updatePass = "UPDATE employee SET password = '"
                            + cf_newPassword.getText() + "', date = '" + date + "' WHERE username = '"
                            + fg_username.getText() + "'";

                    prepare = connect.prepareStatement(updatePass);
                    prepare.executeUpdate();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully changed Password");
                    alert.showAndWait();

                    loginForm.setVisible(true);
                    confirm_form.setVisible(false);

                    //TO CLEAR FIELDS
                    cf_newPassword.setText("");
                    cf_cfPassword.setText("");
                    fg_username.setText("");
                    fg_phone.setText("");

                }catch (Exception e){e.printStackTrace();}
            }else {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Not match");
                alert.showAndWait();
            }

        }
    }

    public void backToLoginForm() {
        loginForm.setVisible(true);
        forgot_form.setVisible(false);
    }

    public void  backToForgotPassForm() {
        confirm_form.setVisible(false);
        forgot_form.setVisible(true);
    }

    public void switchForm(ActionEvent event){

        TranslateTransition slider = new TranslateTransition();

        if(event.getSource() == Createacc_btn){
            slider.setNode(loginForm);
            slider.setToX(500);
            slider.setDuration(Duration.seconds(1));

            slider.setOnFinished ((ActionEvent e) -> {
                alreadyAccount_btn.setVisible(true);
                Createacc_btn.setVisible((false));
                forgot_form.setVisible(false);
                confirm_form.setVisible(false);
                signUpForm.setVisible(true);
            });

            slider.play();
        } else if(event.getSource() == alreadyAccount_btn) {
            slider.setNode(loginForm);
            slider.setToX(0);
            slider.setDuration(Duration.seconds(.5));

            slider.setOnFinished ((ActionEvent e) -> {
                alreadyAccount_btn.setVisible(false);
                Createacc_btn.setVisible((true));
                forgot_form.setVisible(false);
                confirm_form.setVisible(false);
                loginForm.setVisible(true);
            });

            slider.play();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}