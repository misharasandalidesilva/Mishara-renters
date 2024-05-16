package lk.ijse.repository;

import lk.ijse.db.DbConnection;
import lk.ijse.model.Employee;
import lk.ijse.model.Equipment;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EquipmentRepo {
    public static boolean delete(String Eq_id) throws SQLException {
        String sql = "DELETE FROM equipment WHERE Eq_id = ?";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, Eq_id);

        return pstm.executeUpdate() > 0;
    }

    public static boolean save(Equipment equipment) throws SQLException {
        String sql = "INSERT INTO equipment VALUES(?, ?, ?, ?)";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, equipment.getEq_id());
        pstm.setObject(2, equipment.getEq_name());
        pstm.setObject(3, equipment.getEq_qty());

        return pstm.executeUpdate() > 0;

    }

    public static Equipment searchById(String Eq_id) throws SQLException {
        String sql = "SELECT * FROM equipment WHERE Eq_id = ?";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, Eq_id);
        ResultSet resultSet = pstm.executeQuery();

        Equipment equipment = null;

        if (resultSet.next()) {
            String id = resultSet.getString(1);
            String Eq_name = resultSet.getString(2);
            String Eq_qty = resultSet.getString(3);

            equipment = new Equipment(id, Eq_name, Eq_qty);
        }
        return equipment;
        
    }

    public static List<Equipment> getAll() throws SQLException {
        String sql = "SELECT * FROM equipment";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<Equipment> employeeList = new ArrayList<>();
        while (resultSet.next()) {
            String Eq_id = resultSet.getString(1);
            String Eq_name = resultSet.getString(2);
            String Eq_qty = resultSet.getString(3);

            Equipment equipment = new Equipment(Eq_id, Eq_name, Eq_qty);
            employeeList.add(equipment);
        }
        return employeeList;
    }

    public static boolean update(Equipment equipment) throws SQLException {
        String sql = "UPDATE equipment SET Eq_name = ?, E_qty = ? WHERE E_id = ?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, equipment.getEq_id());
        pstm.setObject(2, equipment.getEq_name());
        pstm.setObject(3, equipment.getEq_qty());

        return pstm.executeUpdate() > 0;

    }
}
