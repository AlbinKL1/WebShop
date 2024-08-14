package se.kl.webshop.Database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.kl.webshop.Entitys.ProductType;

@Repository
public interface ProductTypeRepo extends JpaRepository<ProductType,Integer> {
}
