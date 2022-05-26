package org.stop.eop.util;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionIdListener;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {
    private final SessionContextUtils sessionContext= SessionContextUtils.getInstance();


    @Override
    public void sessionCreated(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        sessionContext.addSession(session);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        sessionContext.delSession(session);
    }
}
