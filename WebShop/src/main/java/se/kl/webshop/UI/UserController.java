package se.kl.webshop.UI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import se.kl.webshop.Entitys.User;
import se.kl.webshop.Entitys.UserType;
import se.kl.webshop.Service.*;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/start")
    public String showStartPage() {
        return "start";
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("login")
    public String loginUser(Model model,
                            @RequestParam String username,
                            @RequestParam String password) {
        User user = userService.loginUser(username, password);
        if (user != null) {
            if (user.getUsertype().getId() == 2) {
                return "redirect:/admin";
            } else {
                return "redirect:/homepage";
            }
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
    }

    @GetMapping("/signup")
    public String showSignupForm() {
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(Model model,
                         @RequestParam("email") String email,
                         @RequestParam("username") String username,
                         @RequestParam("password") String password) {

        if (!email.contains("@")) {
            model.addAttribute("error", "Invalid email address");
            return "redirect:/signup";
        }

        if (username.length() <= 5) {
            model.addAttribute("error", "Username must be longer than 5 characters");
            return "redirect:/signup";
        }

        if (password.length() <= 5) {
            model.addAttribute("error", "Password must be longer than 5 characters");
            return "redirect:/signup";
        }

        if (!password.matches(".*[A-Z].*")) {
            model.addAttribute("error", "Password must contain at least one capital letter");
            return "redirect:/signup";
        }

        if (!username.matches(".*[A-Z].*")) {
            model.addAttribute("error", "Username must contain at least one capital letter");
            return "redirect:/signup";
        }

        UserType userType = userService.getUserTypeById(1);
        if (userType == null) {
            model.addAttribute("error", "User type not found");
            return "redirect:/signup";
        }

        User newUser = new User();
        newUser.setEmail(email);
        newUser.setUsername(username);
        newUser.setPassword(password);
        newUser.setUsertype(userType);

        userService.createUser(newUser);

        return "redirect:/start";
    }
}