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

@WebServlet(urlPatterns = "/training/theFirst")
public class TrainingWithFirstFlashCardController extends HttpServlet {
    private FlashCardService trainingService;

    @Override
    public void init() throws ServletException {
        ServletContext context = getServletContext();
        trainingService = (FlashCardService) context.getAttribute("trainingService");

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        long themeId = Long.parseLong(req.getParameter("themeId"));
        FlashCards flashCard = trainingService.getTheFirstFlashCard(themeId);
        String respBody = flashCard.toString();

        resp.setStatus(HttpServletResponse.SC_OK);
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("utf-8");
        resp.getWriter().println(respBody);


    }
}
