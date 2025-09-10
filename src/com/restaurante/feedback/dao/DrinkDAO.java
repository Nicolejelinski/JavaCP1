package com.restaurante.feedback.dao;

import com.restaurante.feedback.entities.Drink;
import com.restaurante.feedback.exception.FeedbackException;

import java.sql.*;
import java.util.Optional;

public class DrinkDAO {

    public Drink save(Drink drink) throws FeedbackException {
        String sql = "INSERT INTO DRINK (NAME, DESCRIPTION) VALUES (?, ?)";
        try (Connection conn = Database.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, drink.getName());
            ps.setString(2, drink.getDescription());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    drink.setId(rs.getInt(1));
                }
            }

        } catch (SQLException e) {
            throw new FeedbackException("Erro ao salvar Drink", e);
        }
        return drink;
    }

    public Optional<Drink> findById(int id) throws FeedbackException {
        String sql = "SELECT ID, NAME, DESCRIPTION FROM DRINK WHERE ID = ?";
        try (Connection conn = Database.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new Drink(rs.getInt("ID"), rs.getString("NAME"), rs.getString("DESCRIPTION")));
                }
            }

        } catch (SQLException e) {
            throw new FeedbackException("Erro ao buscar Drink por id=" + id, e);
        }
        return Optional.empty();
    }
}
