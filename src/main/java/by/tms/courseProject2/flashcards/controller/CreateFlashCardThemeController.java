package by.tms.courseProject2.flashcards.controller;

import by.tms.courseProject2.flashcards.service.FlashCardThemeService;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
@WebServlet(urlPatterns = "/create-theme")
public class CreateFlashCardThemeController extends HttpServlet {

    private FlashCardThemeService flashCardThemeService;

    @Override
    public void init() throws ServletException {
        ServletContext context = getServletContext();
        flashCardThemeService = (FlashCardThemeService) context.getAttribute("flashCardThemeService");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            String name = req.getParameter("name");
            flashCardThemeService.addNewTheme(name);
            resp.sendRedirect(req.getContextPath() + "/themes");
    }
}
