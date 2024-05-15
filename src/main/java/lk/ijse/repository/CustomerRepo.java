package lk.ijse.repository;

import lk.ijse.db.DbConnection;
import lk.ijse.model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepo {
    public static boolean save(Customer customer) throws SQLException {
//        In here you can now save your customer
        String sql = "INSERT INTO customer VALUES(?, ?, ?, ?)";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, customer.getC_id());
        pstm.setObject(2, customer.getC_name());
        pstm.setObject(3, customer.getC_contact());
        pstm.setObject(4, customer.getC_address());

        return pstm.executeUpdate() > 0;

        /*int affectedRows = pstm.executeUpdate();
        if (affectedRows > 0) {
            return true;
        } else {
            return false;
        }*/
    }

    public static boolean update(Customer customer) throws SQLException {
        String sql = "UPDATE customer SET C_name = ?, C_address = ?, C_contact = ? WHERE C_id = ?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, customer.getC_id());
        pstm.setObject(2, customer.getC_name());
        pstm.setObject(3, customer.getC_contact());
        pstm.setObject(4, customer.getC_address());

        return pstm.executeUpdate() > 0;
    }

    public static Customer searchById(String C_id) throws SQLException {
        String sql = "SELECT * FROM customer WHERE C_id = ?";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, C_id);
        ResultSet resultSet = pstm.executeQuery();

        Customer customer = null;

        if (resultSet.next()) {
            C_id = resultSet.getString(1);
            String C_name = resultSet.getString(2);
            String C_contact = resultSet.getString(3);
            String C_address = resultSet.getString(4);

            customer = new Customer(C_id, C_name, C_contact, C_address);
        }
        return customer;
    }

    public static boolean delete(String C_id) throws SQLException {
        String sql = "DELETE FROM customer WHERE C_id = ?";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, C_id);

        return pstm.executeUpdate() > 0;
    }

    public static List<Customer> getAll() throws SQLException {
        String sql = "SELECT * FROM customer";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<Customer> customerList = new ArrayList<>();
        while (resultSet.next()) {
            String C_id = resultSet.getString(1);
            String C_name = resultSet.getString(2);
            String C_contact = resultSet.getString(3);
            String C_address = resultSet.getString(4);

            Customer customer = new Customer(C_id, C_name, C_contact, C_address);
            customerList.add(customer);
        }
        return customerList;
    }

    public static List<String> getIds() throws SQLException {
        String sql = "SELECT C_id FROM customer";

        Connection connection = DbConnection.getInstance().getConnection();
        ResultSet resultSet = connection.prepareStatement(sql).executeQuery();

        List<String> idList = new ArrayList<>();

        while (resultSet.next()) {
            idList.add(resultSet.getString(1));
        }
        return idList;
    }
}

