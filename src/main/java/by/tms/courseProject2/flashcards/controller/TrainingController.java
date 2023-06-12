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



public class TrainingController extends HttpServlet {
//
//    public static final String URL_PASS = "/training";
//    private FlashCardThemeService flashCardThemeService;
//    private FlashCardService flashCardService;
//
//    @Override
//    public void init() throws ServletException {
//        ServletContext context = getServletContext();
//        flashCardThemeService = (FlashCardThemeService) context.getAttribute("flashCardThemeService");
//        flashCardService = (FlashCardService) context.getAttribute("flashCardService");
//
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        long flashCardThemeId = Long.parseLong(req.getParameter("flashCardThemeId"));
//        long flashCardId = Long.parseLong(req.getParameter("flashCardId"));
//        resp.setContentType("text/plain");
//        resp.setCharacterEncoding("utf-8");
//        resp.setStatus(HttpServletResponse.SC_OK);
//        resp.getWriter().println(flashCardService.getNextFlashCard(flashCardThemeId,flashCardId));
//
//    }
}
