package org.bobocode;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

public class Session {
    public final static String SESSION_ID = "CUSTOMSESSIONID";
    @Setter
    @Getter
    private String name;
    @Getter
    private final String sessionId;

    public Session(String sessionId, String name) {
        this.sessionId = sessionId;
        this.name = name;
    }
}
