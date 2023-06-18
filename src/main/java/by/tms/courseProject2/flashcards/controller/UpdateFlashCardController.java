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

@WebServlet(urlPatterns = "/update")
public class UpdateFlashCardController extends HttpServlet {
    private FlashCardService flashCardService;

    @Override
    public void init() throws ServletException {
        ServletContext context = getServletContext();
        flashCardService = (FlashCardService) context.getAttribute("flashCardService");


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long flashCardId = Long.parseLong(req.getParameter("flashCardId"));
        boolean isLearned = Boolean.parseBoolean(req.getParameter("isLearned"));
        FlashCards updatedFlashCard = flashCardService.setStatusOfKnowledge(flashCardId, isLearned);
        resp.sendRedirect(req.getContextPath() + "/flashcards" + "?themeId?" + updatedFlashCard.getTheme_id());

    }
}
