package com.restaurante.feedback.service;

import com.restaurante.feedback.dao.DishDAO;
import com.restaurante.feedback.dao.FeedbackDAO;
import com.restaurante.feedback.entities.Dish;
import com.restaurante.feedback.entities.Feedback;
import com.restaurante.feedback.exception.FeedbackException;

import java.util.List;
import java.util.Optional;

public class FeedbackService {

    private final DishDAO dishDAO = new DishDAO();
    private final FeedbackDAO feedbackDAO = new FeedbackDAO();

    public Dish createDish(String name, String desc) throws FeedbackException {
        Dish d = new Dish(name, desc);
        dishDAO.save(d);
        return d;
    }

    public Feedback addFeedback(int dishId, String user, int rating, String comment) throws FeedbackException {
        Optional<Dish> dish = dishDAO.findById(dishId);
        if (dish.isEmpty()) throw new FeedbackException("Dish não encontrado para ID=" + dishId);

        Feedback fb = new Feedback(dishId, user, rating, comment);
        feedbackDAO.save(fb);
        return fb;
    }

    public List<Feedback> listAllFeedbacks() throws FeedbackException {
        return feedbackDAO.loadAll();
    }

    public double averageRatingForDish(int dishId) throws FeedbackException {
        List<Feedback> list = feedbackDAO.findByDishId(dishId);
        if (list.isEmpty()) return 0.0;
        return list.stream().mapToInt(Feedback::getRating).average().orElse(0.0);
    }
}
