package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import lk.ijse.model.Employee;
import lk.ijse.model.tm.CustomerTm;
import lk.ijse.model.tm.EmployeeTm;
import lk.ijse.repository.EmployeeRepo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeFormController {

    @FXML
    private Text Employee;

    @FXML
    private Button btnClear;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> colE_Name;

    @FXML
    private TableColumn<?, ?> colE_contact;

    @FXML
    private TableColumn<?, ?> colE_id;

    @FXML
    private TableColumn<?, ?> colE_time;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<EmployeeTm> tblEmployee;

    @FXML
    private TextField txtContactNumber;

    @FXML
    private TextField txtEmployeeId;

    @FXML
    private TextField txtEmployeeName;

    @FXML
    private TextField txtEmployeeTime;

    private List<lk.ijse.model.Employee> employeeList = new ArrayList<>();
    public void initialize() {
        this.employeeList=getAllEmployee();
        setCellValueFactory();
        loadEmployeeTable();
    }

    private void loadEmployeeTable() {
        ObservableList<EmployeeTm> tmList = FXCollections.observableArrayList();

        for (Employee employee : employeeList) {
            EmployeeTm employeeTm = new EmployeeTm(
                    employee.getE_id(),
                    employee.getE_name(),
                    employee.getE_Contact(),
                    employee.getE_time()
            );

            tmList.add(employeeTm);
        }
        tblEmployee.setItems(tmList);
        Object selectedItem = tblEmployee.getSelectionModel().getSelectedItem();
        System.out.println("selectedItem = " + selectedItem);

    }

    private List<Employee> getAllEmployee() {
        List<Employee> employeeList = null;
        try {
            employeeList = EmployeeRepo.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return employeeList;
    }

    private void setCellValueFactory() {
        colE_id.setCellValueFactory(new PropertyValueFactory<>("E_id"));
        colE_Name.setCellValueFactory(new PropertyValueFactory<>("E_name"));
        colE_contact.setCellValueFactory(new PropertyValueFactory<>("E_contact"));
        colE_time.setCellValueFactory(new PropertyValueFactory<>("E_time"));
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String E_id = txtEmployeeId.getText();
        String E_name = txtEmployeeName.getText();
        String E_contact = txtContactNumber.getText();
        String E_time = txtEmployeeTime.getText();

        Employee employee = new Employee(E_id, E_name, E_contact, E_time);

        try {
            boolean isUpdated = EmployeeRepo.update(employee);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "employee updated!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String E_id = txtEmployeeId.getText();

        try {
            boolean isDeleted = EmployeeRepo.delete(E_id);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "employee deleted!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        String E_id = txtEmployeeId.getText();
        String E_name = txtEmployeeName.getText();
        String E_contact = txtContactNumber.getText();
        String E_time = txtEmployeeTime.getText();

        Employee employee = new Employee(E_id, E_name, E_contact, E_time);

        try {
            boolean isSaved = EmployeeRepo.save(employee);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "employee saved!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        clearFields();
    }

    private void clearFields() {
        txtEmployeeId.setText("");
        txtEmployeeName.setText("");
        txtContactNumber.setText("");
        txtEmployeeTime.setText("");

    }

    public void txtSearchOnAction(ActionEvent actionEvent) {
        String E_id = txtEmployeeId.getText();

        try {
            Employee employee = EmployeeRepo.searchById(E_id);

            if (employee != null) {
                txtEmployeeId.setText(employee.getE_id());
                txtEmployeeName.setText(employee.getE_name());
                txtContactNumber.setText(employee.getE_Contact());
                txtEmployeeTime.setText(employee.getE_time());
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
}
