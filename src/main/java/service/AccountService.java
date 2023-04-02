package service;

import SQL.DataBaseHandler;
import SQL.UsersDAO;
import SQL.UsersDAOImplementation;
import model.UserProfile;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class AccountService {
    private static UsersDAO dao = new UsersDAOImplementation();
    private static Map<UserProfile, HttpSession> sessions = new HashMap<>();

    DataBaseHandler db = new DataBaseHandler();

    public void addUser(UserProfile user){
        dao.add(user);
    }

    public void addSession(UserProfile user, HttpSession session){
        if (!sessions.containsKey(user))
            sessions.put(user, session);
    }

    public void removeSession(UserProfile user){
        sessions.remove(user);
    }

    public boolean checkUser(String login, String password){
        if (dao.getAll() == null) {
            return false;
        }
        for (UserProfile user : dao.getAll()) {
            if (user.getLogin().equals(login)) {
                return user.getPassword().equals(password);
            }
        }
        return false;
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
