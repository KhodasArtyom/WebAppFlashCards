package by.tms.courseProject2.flashcards.controller;


import by.tms.courseProject2.flashcards.models.FlashCards;
import by.tms.courseProject2.flashcards.service.FlashCardService;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

@WebServlet(urlPatterns = "/training/next")
public class TrainingNextCardController extends HttpServlet {

private FlashCardService trainingService;

    @Override
    public void init() throws ServletException {
        ServletContext context = getServletContext();
        trainingService = (FlashCardService) context.getAttribute("trainingService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long previousCardId = Long.parseLong(req.getParameter("previousCardId"));
        Optional<FlashCards> cardOpt = trainingService.getNextFlashCard(previousCardId);
        if (cardOpt.isPresent()) {
            FlashCards card = cardOpt.orElseThrow();
            String respBody = card.toString();

            resp.setStatus(HttpServletResponse.SC_OK);
            resp.setContentType("text/plain");
            resp.setCharacterEncoding("utf-8");
            resp.getWriter().println(respBody);

        } else {
            resp.sendRedirect(req.getContextPath() + "/themes");
        }
    }
    }

