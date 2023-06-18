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


@WebServlet(urlPatterns = "/delete-card")
public class DeleteFlashCardController extends HttpServlet {
    private FlashCardService flashCardService;

    @Override
    public void init() throws ServletException {
        ServletContext context = getServletContext();
        flashCardService = (FlashCardService) context.getAttribute("flashCardService");


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long flashCardId = Long.parseLong(req.getParameter("flashCardId"));
        FlashCards deletedCard = flashCardService.deleteCard(flashCardId);
        resp.sendRedirect(req.getContextPath()+ "/flashcards" +"?themeId" + deletedCard);
    }
}
