package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.model.Food;
import lk.ijse.model.tm.FoodTm;
import lk.ijse.repository.FoodRepo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FoodController {

    @FXML
    private Button btnClear;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colF_id;

    @FXML
    private TableColumn<?, ?> colF_qty;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<FoodTm> tblFood;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtFoodQty;

    @FXML
    private TextField txtfoodId;

    private List<Food> foodList = new ArrayList<>();

    public void initialize() {
        this.foodList=getAllFood();
        setCellValueFactory();
        loadEquipmentTable();
    }

    private void loadEquipmentTable() {
        ObservableList<FoodTm> tmList = FXCollections.observableArrayList();

        for (Food food : foodList) {
            FoodTm foodTm = new FoodTm(
                    food.getF_id(),
                    food.getF_qty(),
                    food.getDescription()

            );

            tmList.add(foodTm);
        }
        tblFood.setItems(tmList);
        Object selectedItem = tblFood.getSelectionModel().getSelectedItem();
        System.out.println("selectedItem = " + selectedItem);
    }

    private void setCellValueFactory() {
        colF_id.setCellValueFactory(new PropertyValueFactory<>("F_id"));
        colF_qty.setCellValueFactory(new PropertyValueFactory<>("F_qty"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("Description"));

    }

    private List<Food> getAllFood() {
        List<Food> foodList = null;
        try {
            foodList = FoodRepo.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return foodList;

    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();

    }

    private void clearFields() {
        txtfoodId.setText("");
        txtFoodQty.setText("");
        txtDescription.setText("");

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String F_id = txtfoodId.getText();

        try {
            boolean isDeleted = FoodRepo.delete(F_id);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "food deleted!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String F_id = txtfoodId.getText();
        String F_qty = txtFoodQty.getText();
        String Description = txtDescription.getText();

        Food food = new Food(F_id, F_qty, Description);

        try {
            boolean isSaved = FoodRepo.save(food);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "food saved!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String F_id = txtfoodId.getText();
        String F_qty= txtFoodQty.getText();
        String Description = txtDescription.getText();

        Food food = new Food(F_id, F_qty, Description);

        try {
            boolean isUpdated = FoodRepo.update(food);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "equipment updated!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }


    @FXML
    void txtSearchOnaction(ActionEvent event) {
        String F_id = txtfoodId.getText();

        try {
            Food food = FoodRepo.searchById(F_id);

            if (food != null) {
                txtfoodId.setText(food.getF_id());
                txtFoodQty.setText(food.getF_qty());
                txtDescription.setText(food.getDescription());

            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    @FXML
    void colAddressOnAction(ActionEvent event) {

    }

}
