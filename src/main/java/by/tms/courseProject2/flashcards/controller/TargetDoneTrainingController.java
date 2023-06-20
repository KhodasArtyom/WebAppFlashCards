package by.tms.courseProject2.flashcards.controller;

import by.tms.courseProject2.flashcards.service.FlashCardService;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/training/mark-complete")
public class TargetDoneTrainingController extends HttpServlet {
    private FlashCardService trainingService;

    @Override
    public void init() throws ServletException {
        ServletContext context = getServletContext();
        trainingService = (FlashCardService) context.getAttribute("trainingService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long cardId = Long.parseLong(req.getParameter("cardId"));
        trainingService.markIsDone(cardId);
        resp.sendRedirect(req.getContextPath() + "?previousCardId=" + cardId);
    }
}

