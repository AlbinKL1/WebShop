package se.kl.webshop.Database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.kl.webshop.Entitys.OrderProcess;
import se.kl.webshop.Entitys.Product;
import se.kl.webshop.Entitys.User;

import java.util.List;

@Repository
public interface OrderProcessRepo extends JpaRepository<OrderProcess, Integer> {
    List<OrderProcess> findByUser(User user);
    List<OrderProcess> findByUserAndProduct(User user, Product product);
    List<OrderProcess> findByUserId(int userId);
}
