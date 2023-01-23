package com.ims.service;

import com.ims.entity.Role;

import java.util.List;

public interface RoleService {
    Role addRole (Role role);
    Role updateRole (Role role);
    void deleteRole(int id);
    List<Role> getRoles();
    Role getRoleById(int id);
}
