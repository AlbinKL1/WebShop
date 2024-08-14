package se.kl.webshop.UI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import se.kl.webshop.Entitys.OrderComplete;
import se.kl.webshop.Entitys.OrderProcess;
import se.kl.webshop.Entitys.Product;
import se.kl.webshop.Entitys.ProductType;
import se.kl.webshop.Service.OrderCompleteService;
import se.kl.webshop.Service.OrderProcessService;
import se.kl.webshop.Service.ProductService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class AdminController {

    @Autowired
    private OrderCompleteService orderCompleteService;
    @Autowired
    private OrderProcessService orderProcessService;
    @Autowired
    private ProductService productService;

    @GetMapping("/admin")

    public String adminDashboard(Model model) {
        List<OrderComplete> completedOrders = orderCompleteService.getAllCompletedOrders();
        List<OrderProcess> processingOrders = orderProcessService.getAllProcessingOrders();
        Map<Integer, List<OrderProcess>> userOrders = processingOrders.stream()
                .collect(Collectors.groupingBy(OrderProcess::getUserId));
        List<Product> products = productService.getAllProducts();
        List<ProductType> productTypes = productService.getAllProductTypes();

        model.addAttribute("completedOrders", completedOrders);
        model.addAttribute("userOrders", userOrders);
        model.addAttribute("products", products);
        model.addAttribute("productTypes", productTypes);

        return "admin";
    }

    @PostMapping("/admin/addProduct")
    public String addProduct(@RequestParam("productName") String productName,
                             @RequestParam("productPrice") int productPrice,
                             @RequestParam("productType") int productTypeId,
                             Model model) {

        productService.addProduct(productName, productPrice, productTypeId);
        return "redirect:/admin";
    }

    @PostMapping("/admin/completeOrders")
    public String completeOrders(@RequestParam("userIds") int[] userIds) {
        for (int userId : userIds) {
            orderProcessService.completeOrdersForUser(userId);
        }
        return "redirect:/admin";
    }
}