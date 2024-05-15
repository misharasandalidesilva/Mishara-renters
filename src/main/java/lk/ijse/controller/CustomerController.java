package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import lk.ijse.model.Customer;
import lk.ijse.repository.CustomerRepo;

import java.sql.SQLException;
import java.util.List;

public class CustomerController {

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
    private TableColumn<?, ?> colContactNum;

    @FXML
    private TableColumn<?, ?> colCusID;

    @FXML
    private TableColumn<?, ?> colCusName;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<?> tblCustomer;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtContactNumber;

    @FXML
    private TextField txtCustomerId;

    @FXML
    private TextField txtCustomerName;

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();

    }
    public void initialize() {
       getAllCustomers();
        setCellValueFactory();
        loadCustomerTable();
    }

    private void loadCustomerTable() {

    }
private void setCellValueFactory(){

}
    private List<Customer> getAllCustomers() {
        List<Customer> customerList = null;
        try {
            customerList = CustomerRepo.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customerList;
    }
    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String C_id = txtCustomerId.getText();

        try {
            boolean isDeleted = CustomerRepo.delete(C_id);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "customer deleted!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String C_id = txtCustomerId.getText();
        String C_name = txtCustomerName.getText();
        String C_contact = txtContactNumber.getText();
        String C_address = txtAddress.getText();

        Customer customer = new Customer(C_id, C_name, C_contact, C_address);

        try {
            boolean isSaved = CustomerRepo.save(customer);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "customer saved!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

//        now we should persist our customer model

    }

    private void clearFields() {
        txtCustomerId.setText("");
        txtCustomerName.setText("");
        txtContactNumber.setText("");
        txtAddress.setText("");

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String C_id = txtCustomerId.getText();
        String C_name = txtCustomerId.getText();
        String C_contact = txtContactNumber.getText();
        String C_address = txtAddress.getText();

        Customer customer = new Customer(C_id, C_name, C_contact, C_address);

        try {
            boolean isUpdated = CustomerRepo.update(customer);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "customer updated!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }
    @FXML
    void txtSearchOnAction(ActionEvent event) {
        String id = txtCustomerId.getText();

        try {
            Customer customer = CustomerRepo.searchById(id);

            if (customer != null) {
                txtCustomerId.setText(customer.getC_id());
                txtCustomerName.setText(customer.getC_name());
                txtContactNumber.setText(customer.getC_contact());
                txtAddress.setText(customer.getC_address());
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void txtEmployeeNameOnAction(ActionEvent actionEvent) {
        
    }

    public void txtEmployeeIdOnAction(ActionEvent actionEvent) {
    }

    public void txtEmployeeTimeOnAction(ActionEvent actionEvent) {
    }

    public void tblEmployeeOnAction(SortEvent<TableView> tableViewSortEvent) {
    }

    public void colEmployeeIDOnAction(TableColumn.CellEditEvent cellEditEvent) {
    }

    public void colEmployeeNameOnAction(TableColumn.CellEditEvent cellEditEvent) {
    }

    public void colContactNumOnAction(TableColumn.CellEditEvent<?,?> cellEditEvent) {
    }

    public void colEmployeeTimeOnAction(TableColumn.CellEditEvent cellEditEvent) {
    }

    public void txtContactNumberOnAction(ActionEvent actionEvent) {
    }
}


