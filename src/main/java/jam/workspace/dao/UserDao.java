package jam.workspace.dao;

import jam.workspace.model.User;
import java.util.List;

public interface UserDao {
    List<User> getAllUsers();
    User getUserById(int id);
    void updateUserById(User user, int id);
    void deleteUserById(int id);
    void saveUser(User user);
}
