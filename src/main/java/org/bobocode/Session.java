package org.bobocode;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Session {
    public final static String SESSION_ID = "CUSTOMSESSIONID";
    @Setter
    private String name;
    private final String sessionId;

    public Session(String sessionId, String name) {
        this.sessionId = sessionId;
        this.name = name;
    }
}
