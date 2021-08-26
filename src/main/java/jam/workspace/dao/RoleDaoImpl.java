package jam.workspace.dao;

import jam.workspace.model.Role;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {

    private SessionFactory sessionFactory;

    public RoleDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Role> getAllRoles() {
        return sessionFactory.getCurrentSession().createQuery("from Role").getResultList();
    }

    @Override
    public List<String> getNamesOfRoles() {
        return sessionFactory.getCurrentSession().createQuery("select role from Role").getResultList();
    }

    @Override
    public Role getRoleByName(String name) {
        Query query = sessionFactory.getCurrentSession().createQuery("select role from Role role where role.role =: role");
        query.setParameter("role", name);
        return (Role) query.getSingleResult();
    }
}
