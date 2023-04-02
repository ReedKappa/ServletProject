package SQL;

import model.UserProfile;

import java.util.List;

public interface UsersDAO {
    UserProfile get(long id);
    List<UserProfile> getAll();
    void add(UserProfile dataSet);
}
