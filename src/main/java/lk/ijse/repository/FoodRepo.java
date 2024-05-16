package lk.ijse.repository;

import lk.ijse.db.DbConnection;
import lk.ijse.model.Equipment;
import lk.ijse.model.Food;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FoodRepo {
    public static boolean delete(String F_id) throws SQLException {
        String sql = "DELETE FROM food WHERE F_id = ?";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, F_id);

        return pstm.executeUpdate() > 0;
    }

    public static boolean save(Food food) throws SQLException {
        String sql = "INSERT INTO food VALUES(?, ?, ?, ?)";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, food.getF_id());
        pstm.setObject(2, food.getF_qty());
        pstm.setObject(3, food.getDescription());

        return pstm.executeUpdate() > 0;
    }

    public static boolean update(Food food) throws SQLException {
        String sql = "UPDATE food SET Description = ?, F_qty = ? WHERE F_id = ?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, food.getF_id());
        pstm.setObject(2, food.getF_qty());
        pstm.setObject(3, food.getDescription());

        return pstm.executeUpdate() > 0;

    }

    public static Food searchById(String F_id) throws SQLException {
        String sql = "SELECT * FROM food WHERE F_id = ?";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, F_id);
        ResultSet resultSet = pstm.executeQuery();

        Food food = null;

        if (resultSet.next()) {
            String id = resultSet.getString(1);
            String F_qty = resultSet.getString(2);
            String Description = resultSet.getString(3);

            food = new Food(id, F_qty, Description);
        }
        return food;
    }

    public static List<Food> getAll() throws SQLException {
        String sql = "SELECT * FROM food";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<Food> foodList = new ArrayList<>();
        while (resultSet.next()) {
            String F_id = resultSet.getString(1);
            String F_qty = resultSet.getString(2);
            String Description = resultSet.getString(3);

            Food food = new Food(F_id, F_qty, Description);
            foodList.add(food);
        }
        return foodList;
    }
}
