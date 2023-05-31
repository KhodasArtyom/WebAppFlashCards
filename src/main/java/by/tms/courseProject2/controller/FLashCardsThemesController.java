package by.tms.courseProject2.controller;

import by.tms.courseProject2.FlashCards.service.FlashCardThemeService;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

@WebServlet(urlPatterns = "/themes")
public class FLashCardsThemesController extends HttpServlet {

    private FlashCardThemeService flashCardThemeService;

    @Override
    public void init() throws ServletException {
        ServletContext context = getServletContext();
        flashCardThemeService = (FlashCardThemeService) context.getAttribute("flashCardsThemeService");

    }
}
