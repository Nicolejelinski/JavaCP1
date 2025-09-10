package com.restaurante.feedback.dao;

import com.restaurante.feedback.entities.Dish;
import com.restaurante.feedback.exception.FeedbackException;

import java.sql.*;
import java.util.Optional;

public class DishDAO {

    public void save(Dish dish) throws FeedbackException {
        String sql = "INSERT INTO DISH (NAME, DESCRIPTION) VALUES (?, ?)";
        try (Connection conn = Database.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, dish.getName());
            ps.setString(2, dish.getDescription());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    dish.setId(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new FeedbackException("Erro ao salvar prato", e);
        }
    }

    public Optional<Dish> findById(int id) throws FeedbackException {
        String sql = "SELECT ID, NAME, DESCRIPTION FROM DISH WHERE ID = ?";
        try (Connection conn = Database.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new Dish(
                            rs.getInt("ID"),
                            rs.getString("NAME"),
                            rs.getString("DESCRIPTION")
                    ));
                }
            }
        } catch (SQLException e) {
            throw new FeedbackException("Erro ao buscar prato", e);
        }
        return Optional.empty();
    }
}
