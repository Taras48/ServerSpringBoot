package com.server.controler;

import com.server.model.JsonUser;
import com.server.model.Role;
import com.server.model.User;
import com.server.service.RoleService;
import com.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/admin")
public class UserRestController {

    private UserService userService;
    private RoleService roleService;

    @Autowired
    public UserRestController(UserService service, RoleService roleService) {
        this.userService = service;
        this.roleService = roleService;
    }

    @GetMapping(value = "/all")
    public @ResponseBody
    List<User> getAllUsers() {
        List<User> list = userService.findAll();
        return list;
    }

    @PostMapping(value = "/add")
    public void postAdd(@RequestBody User user) {
        Set<Role> roles = new HashSet<>();
        roles.add(roleService.findAllByRole(user.getRoles().iterator().next().getRole()));
        user.setRoles(roles);
        userService.saveUser(user);
    }


    @PutMapping(value = "/update")
    public void putUpdateUser(@RequestBody User user) {
        User upUser = userService.getUserById(user.getId());
        upUser.setName(user.getName());
        upUser.setPassword(user.getPassword());
        // upUser.getRoles().add(roleService.findAllByRole(user.getRoles()));
        upUser.setMessage(user.getMessage());
        userService.updateUser(upUser);
    }

    @DeleteMapping(value = "/delete")
    public void deleteUser(@RequestBody Long id) {
        userService.deleteUser(id);
    }
}
