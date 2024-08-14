package se.kl.webshop.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.kl.webshop.Database.ProductRepo;
import se.kl.webshop.Database.ProductTypeRepo;
import se.kl.webshop.Entitys.Product;
import se.kl.webshop.Entitys.ProductType;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private ProductTypeRepo productTypeRepo;
    @Autowired
    private ProductTypeService productTypeService;

    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    public List<ProductType> getAllProductTypes(){
        return  productTypeRepo.findAll();
    }

    public List<Product> getProductsByTypeId(int typeId) {
        return productRepo.findByProductTypeId(typeId);
    }

    public Product getProductById(int productId) {
        return productRepo.findById(productId).orElse(null);
    }

    public void addProduct(String productName, int productPrice, int productTypeId) {
        ProductType productType = productTypeService.getProductTypeById(productTypeId);
        if (productType != null) {
            Product product = new Product(productName, productType);
            product.setPrice(productPrice);
            productRepo.save(product);
        }
    }
}
