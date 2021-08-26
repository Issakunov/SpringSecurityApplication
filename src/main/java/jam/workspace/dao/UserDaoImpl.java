package jam.workspace.dao;

import jam.workspace.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<User> getAllUsers() {
        return sessionFactory.getCurrentSession().createQuery("from User").getResultList();
    }

    @Override
    public User getUserById(int id) {
        return sessionFactory.getCurrentSession().get(User.class, id);
    }

    @Override
    public void updateUserById(User user, int id) {
        User user1 = sessionFactory.getCurrentSession().get(User.class, id);
        user1.setId(user.getId());
        user1.setName(user.getName());
        user1.setName(user.getName());
        user1.setDepartment(user.getDepartment());
        user1.setRoles(user.getRoles());
        user1.setSalary(user.getSalary());
        saveUser(user1);
    }

    @Override
    public void deleteUserById(int id) {
        User user = sessionFactory.getCurrentSession().get(User.class, id);
        sessionFactory.getCurrentSession().delete(user);
    }

    @Override
    public void saveUser(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public User findByUserName(String name) {
        List<User> users = sessionFactory.getCurrentSession().createQuery("from User").getResultList();
        for (User user : users) {
            if (user.getName().equals(name)) {
                return user;
            }
        }
        return new User();
    }
}
