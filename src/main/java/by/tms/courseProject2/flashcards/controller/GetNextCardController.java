//package by.tms.courseProject2.flashcards.controller;
//
//import by.tms.courseProject2.flashcards.models.FlashCards;
//import by.tms.courseProject2.flashcards.service.FlashCardService;
//import jakarta.servlet.ServletContext;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//import java.io.IOException;
//import java.util.Optional;
//
//@WebServlet(urlPatterns = "training/next")
//public class GetNextCardController extends HttpServlet {
//    private FlashCardService flashCardService;
//
//
//    @Override
//    public void init() throws ServletException {
//        ServletContext context = getServletContext();
//        flashCardService = (FlashCardService) context.getAttribute("flashCardService");
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        long flashCardId = Long.parseLong(req.getParameter("flashCardId"));
//        Optional<FlashCards> flashCard = flashCardService.getNextFlashCard(flashCardId, flashCardId);
//        String respData = flashCard.toString();
//        resp.setContentType("text/plain");
//        resp.setCharacterEncoding("utf-8");
//        resp.setStatus(HttpServletResponse.SC_OK);
//        resp.getWriter().println(respData);
//
//
//
//    }
//}
