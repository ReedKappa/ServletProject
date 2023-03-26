package service;

import model.UserProfile;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class AccountService {
    private static Map<String, UserProfile> logins = new HashMap<>();
    private static Map<UserProfile, HttpSession> sessions = new HashMap<>();

    public void addUser(UserProfile user){
        logins.put(user.getLogin(), user);
    }

    public UserProfile getUserByLogin(String login) {
        return logins.get(login);
    }

    public HttpSession getUserBySession(UserProfile session) {
        return sessions.get(session);
    }
    public void addSession(UserProfile user, HttpSession session){
        if (!sessions.containsKey(user))
            sessions.put(user, session);
    }

    public void removeSession(UserProfile user){
        sessions.remove(user);
    }

    public boolean checkUser(String login, String password){
        UserProfile user = logins.get(login);
        return user != null && user.getPassword().equals(password);
    }


    public UserProfile getBySession(String sessionId){
        for (UserProfile user : sessions.keySet()) {
            if (Objects.equals(sessions.get(user).getId(), sessionId)){
                return user;
            }
        }

        return null;
    }

    public boolean hasActiveSession() {
        return sessions.isEmpty();
    }
}
