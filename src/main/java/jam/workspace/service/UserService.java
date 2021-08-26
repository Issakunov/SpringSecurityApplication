package jam.workspace.service;

import jam.workspace.model.User;
import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(int id);
    void saveUser(User user);
    void updateUserById(User user, int id);
    void deleteUserById(int id);
    User getUserMethod(String name);

}
