package se.kl.webshop.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.kl.webshop.Database.ProductTypeRepo;
import se.kl.webshop.Entitys.ProductType;

@Service
public class ProductTypeService {

    @Autowired
    private ProductTypeRepo productTypeRepo;

    public ProductType getProductTypeById(int productTypeId) {
        return productTypeRepo.findById(productTypeId).orElse(null);
    }
}
