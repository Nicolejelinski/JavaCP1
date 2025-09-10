package com.restaurante.feedback;

import com.restaurante.feedback.entities.Dish;
import com.restaurante.feedback.entities.Feedback;
import com.restaurante.feedback.exception.FeedbackException;
import com.restaurante.feedback.service.FeedbackService;
import com.restaurante.feedback.util.Utils;

import java.util.List;

public class Main {

    private static final FeedbackService service = new FeedbackService();

    public static void main(String[] args) {
        System.out.println("=== Restaurante Feedback - Pratos ===");

        while (true) {
            System.out.println("\nMenu:\n1 - Cadastrar prato\n2 - Registrar feedback\n3 - Listar todos os feedbacks\n4 - Média por prato\n0 - Sair");
            int opt = Utils.readInt("Escolha: ");

            try {
                if (opt == 0) break;

                else if (opt == 1) {
                    String name = Utils.readLine("Nome do prato: ");
                    String desc = Utils.readLine("Descrição: ");
                    Dish dish = service.createDish(name, desc);
                    System.out.println("Dish criado com ID=" + dish.getId());
                }

                else if (opt == 2) {
                    int dishId = Utils.readInt("ID do Dish: ");
                    String user = Utils.readLine("Usuário: ");
                    int rating = Utils.readInt("Nota (1-5): ");
                    String comment = Utils.readLine("Comentário: ");
                    Feedback fb = service.addFeedback(dishId, user, rating, comment);
                    System.out.println("Feedback registrado com ID=" + fb.getId());
                }

                else if (opt == 3) {
                    List<Feedback> all = service.listAllFeedbacks();
                    if (all.isEmpty()) System.out.println("Nenhum feedback registrado.");
                    for (Feedback f : all) System.out.println(f);
                }

                else if (opt == 4) {
                    int dishId = Utils.readInt("ID do Dish: ");
                    double avg = service.averageRatingForDish(dishId);
                    System.out.printf("Média de avaliação do prato: %.2f estrelas%n", avg);
                }

                else System.out.println("Opção inválida");

            } catch (FeedbackException e) {
                System.out.println("Erro: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Erro inesperado: " + e.getMessage());
            }
        }
    }
}
