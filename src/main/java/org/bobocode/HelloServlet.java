package org.bobocode;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = "/hello")
public class HelloServlet extends HttpServlet {
    private static final String NAME = "name";
    private static final String HELLO = "Hello";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        var writer = new PrintWriter(resp.getOutputStream());
        writer.println(getMessage(req));
        writer.flush();
    }

    private String getMessage(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (request.getParameter(NAME) == null && session.getAttribute(NAME) != null) {
            return getMessageFormatted(session);
        } else if (request.getParameter(NAME) != null) {
            session.setAttribute(NAME, request.getParameter(NAME));
            return getMessageFormatted(session);
        }
        return HELLO;
    }

    private static String getMessageFormatted(HttpSession session) {
        return """
                %s %s!""".formatted(HELLO, session.getAttribute(NAME));
    }
}
