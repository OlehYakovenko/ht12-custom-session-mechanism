package org.bobocode;

import jakarta.servlet.http.HttpServletRequest;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CustomSession {

    private final Map<String, Session> sessionStore = new ConcurrentHashMap<>();


    public String getAttribute(HttpServletRequest request){
        var sessionId = request.getRequestedSessionId();
        if(sessionStore.get(sessionId) == null){
            return null;
        }
        return sessionStore.get(sessionId).userName();
    }

    public void setAttribute(HttpServletRequest request){
        var userName = request.getParameter("name");
        String sessionId = request.getRequestedSessionId();
        sessionStore.put(sessionId, new Session(sessionId, userName));
    }

    record Session(String sessionId, String userName){}
}
