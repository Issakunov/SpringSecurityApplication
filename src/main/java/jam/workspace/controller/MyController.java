package jam.workspace.controller;

import jam.workspace.model.User;
import jam.workspace.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api")
public class MyController {

    @Autowired
    private UserService userService;

    @GetMapping()
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "all-users-page";
    }
    @GetMapping("delete/{id}")
    public String deleteUserById(@PathVariable("id") int id) {
        userService.deleteUserById(id);
        return "redirect:/api";
    }
    @GetMapping("/{id}/edit")
     public String getUserByIdForEditUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
         return "edit-users";
     }

     @PostMapping("/{id}")
     public String edit(@ModelAttribute("user") User user, @PathVariable("id") int id) {
        userService.updateUserById(user, id);
        return "redirect:/api";
     }

    @GetMapping("/new")
     public String getUserByIdForCreateUser(@ModelAttribute("users") User user) {
        return "new-user";
     }

    @PostMapping()
    public String createUser(@ModelAttribute("users") User user) {
        userService.saveUser(user);
        return "redirect:/api";
    }
}