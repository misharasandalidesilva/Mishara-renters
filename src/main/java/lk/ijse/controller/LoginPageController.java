package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.db.DbConnection;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.sun.javafx.scene.control.skin.Utils.getResource;

public class LoginPageController {

    public AnchorPane rootNode;
    @FXML
    private Button btnLogin;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUserName;
    private URL getClass;

    @FXML
    void btnLoginOnAction(ActionEvent event) throws SQLException, IOException {
        String User_name = txtUserName.getText();
        String Password = txtPassword.getText();

        checkCredential(User_name, Password);

    }

    private void checkCredential(String userId, String pw) throws SQLException, IOException {
        String sql = "Select User_name, Password FROM User WHERE U_id =?";
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setObject(1, userId);

        ResultSet resultSet;
        resultSet = pstm.executeQuery();
        if(resultSet.next()) {
            String dbPW = resultSet.getString(2);

            if (dbPW.equals(pw)) {

                navigateToTheDashboard();
            } else {
                new Alert(Alert.AlertType.ERROR,
                        "Password is incorrect!").show();

            }
        }
else {
                new Alert(Alert.AlertType. INFORMATION,

                        "user name  not found!").show();
            }

        }


    private void navigateToTheDashboard() throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/dashboard.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) this.rootNode.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Dashboard Form");

    }

    @FXML
    void txtPasswordOnAction(ActionEvent event) {

    }

    @FXML
    void txtUserNameOnAction(ActionEvent event) {

    }

    public void RegisterOnAction(MouseEvent mouseEvent) throws IOException {

    }

    public void RegisterOnActions(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/register.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) this.rootNode.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Register Form");
    }
}
