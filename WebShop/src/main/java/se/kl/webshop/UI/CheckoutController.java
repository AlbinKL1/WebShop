package se.kl.webshop.UI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import se.kl.webshop.Entitys.OrderComplete;
import se.kl.webshop.Entitys.User;
import se.kl.webshop.Service.OrderCompleteService;
import se.kl.webshop.Mail.SmtpMailSender;
import se.kl.webshop.Service.UserService;


@Controller
public class CheckoutController {

    @Autowired
    private OrderCompleteService orderCompleteService;
    @Autowired
    private UserService userService;

    @PostMapping("/checkout")
    @ResponseBody
    public ModelAndView checkout() {
        ModelAndView modelAndView = new ModelAndView();

        User currentUser = userService.getCurrentUser();
        if (currentUser == null) {
            modelAndView.setViewName("redirect:/login");
            return modelAndView;
        }

        OrderComplete order = orderCompleteService.checkout(currentUser);

        SmtpMailSender smtpMailSender = new SmtpMailSender();
        smtpMailSender.sendOrderConfirmationEmail(currentUser, order);

        modelAndView.addObject("message", "Checkout successful!");
        modelAndView.setViewName("redirect:/order");
        return modelAndView;
    }
}

