package com.restaurante.feedback.dao;

import com.restaurante.feedback.entities.Feedback;
import com.restaurante.feedback.exception.FeedbackException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FeedbackDAO {

    public void save(Feedback feedback) throws FeedbackException {
        String sql = "INSERT INTO FEEDBACK (DISH_ID, USERNAME, RATING, COMMENT_TEXT) VALUES (?, ?, ?, ?)";
        try (Connection conn = Database.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, new String[]{"ID"})) {

            ps.setInt(1, feedback.getDishId());
            ps.setString(2, feedback.getUser());
            ps.setInt(3, feedback.getRating());
            ps.setString(4, feedback.getComment());

            ps.executeUpdate();

            // pegar ID gerado automaticamente
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    feedback.setId(rs.getInt(1));
                }
            }

        } catch (SQLException e) {
            throw new FeedbackException("Erro ao salvar feedback", e);
        }
    }

    public List<Feedback> findByDishId(int dishId) throws FeedbackException {
        List<Feedback> list = new ArrayList<>();
        String sql = "SELECT ID, DISH_ID, USERNAME, RATING, COMMENT_TEXT FROM FEEDBACK WHERE DISH_ID = ?";
        try (Connection conn = Database.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, dishId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Feedback f = new Feedback(
                            rs.getInt("ID"),
                            rs.getInt("DISH_ID"),
                            rs.getString("USERNAME"),
                            rs.getInt("RATING"),
                            rs.getString("COMMENT_TEXT")
                    );
                    list.add(f);
                }
            }

        } catch (SQLException e) {
            throw new FeedbackException("Erro ao buscar feedbacks do Dish " + dishId, e);
        }
        return list;
    }

    public List<Feedback> loadAll() throws FeedbackException {
        List<Feedback> list = new ArrayList<>();
        String sql = "SELECT ID, DISH_ID, USERNAME, RATING, COMMENT_TEXT FROM FEEDBACK";
        try (Connection conn = Database.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Feedback f = new Feedback(
                        rs.getInt("ID"),
                        rs.getInt("DISH_ID"),
                        rs.getString("USERNAME"),
                        rs.getInt("RATING"),
                        rs.getString("COMMENT_TEXT")
                );
                list.add(f);
            }

        } catch (SQLException e) {
            throw new FeedbackException("Erro ao carregar feedbacks", e);
        }
        return list;
    }
}
