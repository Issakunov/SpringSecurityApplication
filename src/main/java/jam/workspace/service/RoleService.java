package jam.workspace.service;

import jam.workspace.model.Role;

import java.util.List;

public interface RoleService {
    List<Role> getAllRoles();
    List<String> getNameOfRoles();
    Role getRoleByName(String name);
}
