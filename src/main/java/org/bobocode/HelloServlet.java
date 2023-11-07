package org.bobocode;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = "/hello")
public class HelloServlet extends HttpServlet {
    private final CustomSession session = new CustomSession();
    private static final String HELLO = "Hello";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        var writer = new PrintWriter(resp.getOutputStream());
        var currentSession = session.createOrGetSession(req, resp);
        writer.println(getMessage(currentSession));
        writer.flush();
    }

    private String getMessage(Session currentSession) {
        if (currentSession.getName() != null) {
            return """
                    %s %s""".formatted(HELLO, currentSession.getName());
        }
        return HELLO;
    }
}
