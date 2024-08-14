package se.kl.webshop.UI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import se.kl.webshop.Entitys.Product;
import se.kl.webshop.Service.ProductService;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/product/{typeId}")
    public String getProductsByType(Model model, @PathVariable int typeId) {
        List<Product> products = productService.getProductsByTypeId(typeId);
        model.addAttribute("products", products);
        return "product";
    }
}