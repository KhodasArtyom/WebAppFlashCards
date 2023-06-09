package by.tms.courseProject2.flashcards.controller;

import by.tms.courseProject2.flashcards.service.FlashCardService;
import by.tms.courseProject2.flashcards.service.FlashCardThemeService;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/training")
public class TrainingController extends HttpServlet {

    private FlashCardThemeService flashCardThemeService;
    private FlashCardService flashCardService;

    @Override
    public void init()   {
        ServletContext context = getServletContext();
        flashCardService = (FlashCardService) context.getAttribute("flashCardService");
        flashCardThemeService = (FlashCardThemeService) context.getAttribute("flashCardThemeService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long themeId = Long.parseLong(req.getParameter("themeId"));
        long nextFlashCard = Long.parseLong(req.getParameter("nextCardId"));
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("utf-8");
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().println(flashCardService.getNextFlashCard(themeId,nextFlashCard));
    }
}
