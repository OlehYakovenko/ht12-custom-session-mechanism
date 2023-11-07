package org.bobocode;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class CustomSession {

    private final Map<String, Session> sessionStore = new ConcurrentHashMap<>();

    public Session createOrGetSession(HttpServletRequest request, HttpServletResponse response) {
        if (getSessionId(request) == null) {
            var session = createCustomSession(request);
            response.addCookie(new Cookie(Session.SESSION_ID, session.getSessionId()));
            return session;
        } else {
            return getCustomSession(request);
        }
    }

    private Session getCustomSession(HttpServletRequest request) {
        var sessionId = getSessionId(request);
        if (sessionId == null) {
            return null;
        } else {
            var session = sessionStore.get(sessionId);
            if (!request.getParameter("name").equals(session.getName())) {
                session.setName(request.getParameter("name"));
                sessionStore.put(sessionId, session);
            }
            return session;
        }
    }

    private static String getSessionId(HttpServletRequest request) {
        var cookies = request.getCookies();
        if(cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(Session.SESSION_ID)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    private Session createCustomSession(HttpServletRequest request) {
        var sessionId = UUID.randomUUID().toString();
        String userName = null;
        if (request.getParameter("name") != null) {
            if (!request.getParameter("name").isEmpty()) {
                userName = request.getParameter("name");
            }
        }
        var session = new Session(sessionId, userName);
        sessionStore.put(sessionId, session);
        return session;
    }
}
