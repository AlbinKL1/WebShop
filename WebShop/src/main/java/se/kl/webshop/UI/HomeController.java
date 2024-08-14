package se.kl.webshop.UI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import se.kl.webshop.Service.ProductService;
import se.kl.webshop.Entitys.ProductType;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ProductService productService;

    @GetMapping("/homepage")
    public String showHomePage(Model model) {
        List<ProductType> productTypes = productService.getAllProductTypes();
        model.addAttribute("productTypes", productTypes);
        return "homepage";
    }
}
