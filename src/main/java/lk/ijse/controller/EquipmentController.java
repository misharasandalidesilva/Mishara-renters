package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.model.Equipment;
import lk.ijse.model.tm.EquipmentTm;
import lk.ijse.repository.EquipmentRepo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class EquipmentController {

    @FXML
    private Button btnClear;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> colEq_id;

    @FXML
    private TableColumn<?, ?> colEq_name;

    @FXML
    private TableColumn<?, ?> colEq_qty;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<EquipmentTm> tblEquipment;

    @FXML
    private TextField txtEquipmentId;

    @FXML
    private TextField txtEquipmentName;

    @FXML
    private TextField txtEquipmentQty;



    private List<lk.ijse.model.Equipment> equipmentList = new ArrayList<>();

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();

    }

    public void initialize() {
        this.equipmentList=getAllEquipment();
        setCellValueFactory();
        loadEquipmentTable();
    }

    private void loadEquipmentTable() {
        ObservableList<EquipmentTm> tmList = FXCollections.observableArrayList();

        for (Equipment equipment : equipmentList) {
            EquipmentTm equipmentTm = new EquipmentTm(
                    equipment.getEq_id(),
                    equipment.getEq_name(),
                    equipment.getEq_qty()

            );

            tmList.add(equipmentTm);
        }
        tblEquipment.setItems(tmList);
        Object selectedItem = tblEquipment.getSelectionModel().getSelectedItem();
        System.out.println("selectedItem = " + selectedItem);
    }

    private void setCellValueFactory() {
        colEq_id.setCellValueFactory(new PropertyValueFactory<>("Eq_id"));
        colEq_name.setCellValueFactory(new PropertyValueFactory<>("Eq_name"));
        colEq_qty.setCellValueFactory(new PropertyValueFactory<>("Eq_qty"));

    }

    private List<Equipment> getAllEquipment() {
        List<Equipment> equipmentList = null;
        try {
            equipmentList = EquipmentRepo.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return equipmentList;
    }

    private void clearFields() {
        txtEquipmentId.setText("");
        txtEquipmentName.setText("");
        txtEquipmentQty.setText("");

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String Eq_id = txtEquipmentId.getText();

        try {
            boolean isDeleted = EquipmentRepo.delete(Eq_id);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "equipment deleted!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String Eq_id = txtEquipmentId.getText();
        String Eq_name = txtEquipmentName.getText();
        String Eq_qty = txtEquipmentQty.getText();

        Equipment equipment = new Equipment(Eq_id, Eq_name, Eq_qty);

        try {
            boolean isSaved = EquipmentRepo.save(equipment);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "equipment saved!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String Eq_id = txtEquipmentId.getText();
        String Eq_name= txtEquipmentName.getText();
        String Eq_qty = txtEquipmentQty.getText();

        Equipment equipment = new Equipment(Eq_id, Eq_name, Eq_qty);

        try {
            boolean isUpdated = EquipmentRepo.update(equipment);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "equipment updated!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    @FXML
    void colEquipmentIDOnAction(ActionEvent event) {

    }

    @FXML
    void colEquipmentNameOnAction(ActionEvent event) {

    }

    @FXML
    void colEquipmentQtyOnAction(ActionEvent event) {

    }

    @FXML
    void tblEquipmentOnAction(ActionEvent event) {

    }

    @FXML
    void txtEquipmentIdOnAction(ActionEvent event) {

    }

    @FXML
    void txtEquipmentNameOnAction(ActionEvent event) {

    }

    @FXML
    void txtEquipmentQtyOnAction(ActionEvent event) {

    }

    public void txtSearchOnAction(ActionEvent actionEvent) {
        String id = txtEquipmentId.getText();

        try {
            Equipment equipment = EquipmentRepo.searchById(id);

            if (equipment != null) {
                txtEquipmentId.setText(equipment.getEq_id());
                txtEquipmentName.setText(equipment.getEq_name());
                txtEquipmentQty.setText(equipment.getEq_qty());

            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
}
