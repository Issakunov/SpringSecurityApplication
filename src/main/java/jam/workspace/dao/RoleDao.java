package jam.workspace.dao;

import jam.workspace.model.Role;

import java.util.List;

public interface RoleDao {
    List<Role> getAllRoles();
    List<String> getNamesOfRoles();
    Role getRoleByName(String name);
}
