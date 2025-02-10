package spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import spring.models.User;
import spring.services.UserService;

import javax.validation.Valid;


@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("users", userService.index());
        return "users/index";
    }


    @GetMapping("/show")

    public String show(@RequestParam("id") int id, Model model) {
        for (User user : userService.index()) {
            if (user.getId() == id) {
                model.addAttribute("user", user);
            }
        }
        return "users/show";
    }


    @GetMapping("/new")

    public String newPerson(Model model) {
        model.addAttribute("user", new User());
        return "users/new";

    }


    @PostMapping("/new")

    public String save(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors())
            return "users/new";
        userService.save(user);
        return "redirect:/users";
    }

    @GetMapping("/edit")

    public String edit(Model model, @RequestParam("id") int id) {
        model.addAttribute("user", userService.show(id));
        return "users/edit";
    }

    @PostMapping("/update")

    public String update(@ModelAttribute("user") @Valid User user,
                         BindingResult bindingResult,
                         @RequestParam("id") int id) {
        if (bindingResult.hasErrors()) {
            return "users/edit";
        }
        userService.update(id, user);
        return "redirect:/users";
    }


    @PostMapping("/delete")

    public String delete(@RequestParam("id") int id) {
        userService.delete(id);
        return "redirect:/users";
    }
}

