package com.ims.controller;

import com.ims.entity.Role;
import com.ims.service.RoleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {
    private final RoleService roleService;
    public RoleController(RoleService roleService){
        this.roleService=roleService;
    }
    @PostMapping
    public Role addRole (@RequestBody Role role){
        return roleService.addRole(role);
    }
    @PutMapping
    public Role updateRole (@RequestBody Role role){
        return roleService.updateRole(role);
    }
    @DeleteMapping("/{id}")
    void deleteRole (@PathVariable int id){
        roleService.deleteRole(id);
    }
    @GetMapping
    List<Role> getRoles(){
        return roleService.getRoles();
    }
    @GetMapping("/{id}")
    public Role getRoleById(@PathVariable int id){
        return roleService.getRoleById(id);
    }
}
