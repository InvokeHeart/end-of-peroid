package org.stop.eop.util;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SessionContextUtils {
    private static SessionContextUtils instance;
    private final Map<String, HttpSession> sessionMap;

    private SessionContextUtils() {
        sessionMap = new ConcurrentHashMap<>();
    }

    public static SessionContextUtils getInstance() {
        if (instance == null) {
            instance = new SessionContextUtils();
        }
        return instance;
    }

    public synchronized void addSession(HttpSession session) {
        if (session != null) {
            sessionMap.put(session.getId(), session);
        }
    }

    public synchronized void delSession(HttpSession session) {
        if (session != null) {
            sessionMap.remove(session.getId());
        }
    }

    public synchronized HttpSession getSession(String sessionID) {
        if (sessionID == null) {
            return null;
        }
        return sessionMap.get(sessionID);
    }
}
