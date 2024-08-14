package se.kl.webshop.UI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import se.kl.webshop.Entitys.OrderComplete;
import se.kl.webshop.Entitys.User;
import se.kl.webshop.Service.OrderCompleteService;
import se.kl.webshop.Service.UserService;

@Controller
public class OrderController {

    @Autowired
    private OrderCompleteService orderCompleteService;
    @Autowired
    private UserService userService;

    @GetMapping("/order")
    public String showOrder(Model model) {
        User currentUser = userService.getCurrentUser();
        if (currentUser == null) {
            return "redirect:/login";
        }

        OrderComplete orderComplete = orderCompleteService.getLastOrder(currentUser);
        if (orderComplete == null) {
            return "noOrder";
        }

        model.addAttribute("order", orderComplete);
        model.addAttribute("message", "Thank you for your purchase. Buy from us again!");

        return "order";
    }
}
