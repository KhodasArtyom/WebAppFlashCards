package by.tms.courseProject2.flashcards.controller;

import by.tms.courseProject2.flashcards.service.FlashCardThemeService;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet(urlPatterns = "/delete-theme")
public class DeleteFlashCardThemeController extends HttpServlet {

    private FlashCardThemeService flashCardThemeService;

    @Override
    public void init() throws ServletException {
        ServletContext context  = getServletContext();
        flashCardThemeService = (FlashCardThemeService) context.getAttribute("flashCardThemeService");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long themeId = Long.parseLong(req.getParameter("themeId"));
        flashCardThemeService.removeTheme(themeId);
        resp.sendRedirect(req.getContextPath() + "themes");
    }
}
