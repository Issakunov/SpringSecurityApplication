package jam.workspace.controller;

import jam.workspace.model.User;
import jam.workspace.service.RoleService;
import jam.workspace.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
public class MyController {

    private final UserService userService;
    private final RoleService roleService;

    public MyController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public String mainPage() {
        return "/main-page";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "/login-page";
    }

    @GetMapping("/admin")
    public String getAllUsers(Model model) {
        model.addAttribute("user1", userService.getAllUsers());
        return "all-users-page";
    }

    @GetMapping("delete/{id}")
    public String deleteUserById(@PathVariable("id") int id) {
        userService.deleteUserById(id);
        return "redirect:/admin";
    }

    @GetMapping("{id}/edit")
    public String getUserByIdForEditUser2(@PathVariable("id") int id, Model model) {
        model.addAttribute("user2", userService.getUserById(id));
        model.addAttribute("roles2", roleService.getAllRoles());
        return "edit-users";
    }
    @PostMapping("/edit/{id}")
    public String editUser(@ModelAttribute("user2") User user, @RequestParam Map<String, String> form, @PathVariable("id") int id) {
        List<String> roles = roleService.getNameOfRoles();
        Set<String> strings = new HashSet<>(roles);
        user.getRoles().clear();
        for (String key : form.keySet()) {
            if (strings.contains(key)) {
                user.getRoles().add(roleService.getRoleByName(key));
            }
        }
        userService.updateUserById(user, id);
        return "redirect:/admin";
    }

    @GetMapping("/user")
    public String getUserMethod(Principal principal, Model model) {
        model.addAttribute("user4", userService.getUserMethod(principal.getName()));
        return "user-page";
    }

    @GetMapping("/new")
    public String setUserForCreationUser(@ModelAttribute("user3") User user, Model model) {
        model.addAttribute("role3", roleService.getAllRoles());
        return "new-user";
    }

    @PostMapping("/")
    public String createNewUser(User user,@RequestParam Map<String, String> form) {
        List<String> roles = roleService.getNameOfRoles();
        Set<String> strings = new HashSet<>(roles);
        user.getRoles().clear();
        for (String key : form.keySet()) {
            if (strings.contains(key)) {
                user.getRoles().add(roleService.getRoleByName(key));
            }
        }
        userService.saveUser(user);
        return "redirect:/admin";
    }
}