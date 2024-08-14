package se.kl.webshop.UI;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import se.kl.webshop.Entitys.OrderProcess;
import se.kl.webshop.Entitys.Product;
import se.kl.webshop.Entitys.User;
import se.kl.webshop.Service.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CartController {

    @Autowired
    private  OrderProcessService orderProcessService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;

    @GetMapping("/cart")
    public String viewCart(Model model) {
        User currentUser = userService.getCurrentUser();
        if (currentUser == null) {
            return "redirect:/login";
        }

        List<OrderProcess> cartItems = orderProcessService.getCartItemsByUser(currentUser);

        Map<Product, Integer> productQuantities = new HashMap<>();
        for (OrderProcess item : cartItems) {
            Product product = item.getProduct();
            productQuantities.put(product, productQuantities.getOrDefault(product, 0) + 1);
        }

        model.addAttribute("productQuantities", productQuantities);

        double totalCost = 0;
        for (OrderProcess item : cartItems) {
            totalCost += item.getProduct().getPrice();
        }

        model.addAttribute("totalCost", totalCost);

        return "cart";
    }

    @PostMapping("/addToCart")
    public String addToCart(@RequestParam("productId") int productId,
                            HttpServletRequest request) {

        User currentUser = userService.getCurrentUser();
        if (currentUser == null) {
            return "redirect:/login";
        }

        Product product = productService.getProductById(productId);
        if (product == null) {
            return "redirect:/homepage";
        }

        orderProcessService.addToCart(currentUser, product);

        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }


    @PostMapping("/removeFromCart")
    @ResponseBody
    public ModelAndView removeFromCart(@RequestParam("productId") int productId) {
        ModelAndView modelAndView = new ModelAndView();

        User currentUser = userService.getCurrentUser();
        if (currentUser == null) {
            return modelAndView;
        }

        Product product = productService.getProductById(productId);
        if (product == null) {
            modelAndView.addObject("message", "Product not found");
            modelAndView.setViewName("homepage");
            return modelAndView;
        }

        orderProcessService.removeFromCart(currentUser, product);

        modelAndView.addObject("message", "Product removed from cart!");
        modelAndView.setViewName("redirect:/cart");
        return modelAndView;
    }
}
