package se.kl.webshop.Database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import se.kl.webshop.Entitys.Product;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product,Integer> {
    @Query("SELECT p FROM Product p WHERE p.productType.id = :typeId")
    List<Product> findByProductTypeId(@Param("typeId") int typeId);
}
