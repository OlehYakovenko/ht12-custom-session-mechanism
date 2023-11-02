package org.bobocode;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

@WebServlet(value = "/hello")
public class HelloServlet extends HttpServlet {
    private final CustomSession session = new CustomSession();
    private static final String NAME = "name";
    private static final String HELLO = "Hello";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        var writer = new PrintWriter(resp.getOutputStream());
        writer.println(getMessage(req));
        writer.flush();
    }

    private String getMessage(HttpServletRequest request) {
        if (request.getParameter(NAME) == null && session.getAttribute(request) != null) {
            return getMessageFormatted(session, request);
        } else if (request.getParameter(NAME) != null) {
            session.setAttribute(request);
            return getMessageFormatted(session, request);
        }
        return HELLO;
    }

    private static String getMessageFormatted(CustomSession session, HttpServletRequest req) {
        return """
                %s %s !""".formatted(HELLO, session.getAttribute(req));
    }
}
