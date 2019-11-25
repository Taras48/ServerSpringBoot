package com.server.controler;

import com.server.model.JsonUser;
import com.server.model.User;
import com.server.service.RoleService;
import com.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
    public @ResponseBody List<User> getAllUsers() {
        List<User> list = userService.findAll();
        return list;
    }

    @PostMapping(value = "/add")
    public void postAdd( @RequestBody JsonUser jsonUser) {

        System.out.println(jsonUser.getName());
        System.out.println(jsonUser.getName());
        //user.setRoles(roleService.findAllByRole(role));
       // userService.saveUser(jsonUser);
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
