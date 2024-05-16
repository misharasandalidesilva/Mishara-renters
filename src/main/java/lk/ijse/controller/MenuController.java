package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class MenuController {

    @FXML
    private Button btnClear;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colF_id;

    @FXML
    private TableColumn<?, ?> colM_code;

    @FXML
    private TableColumn<?, ?> colM_name;

    @FXML
    private TableColumn<?, ?> colO_id;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<?> tblMenu;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtFoodId;

    @FXML
    private TextField txtMenuCode;

    @FXML
    private TextField txtMenuName;

    @FXML
    private TextField txtOrderId;

    @FXML
    void btnClearOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

    @FXML
    void txtSearchOnAction(ActionEvent event) {

    }

}
