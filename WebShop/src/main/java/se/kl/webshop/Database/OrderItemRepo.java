package se.kl.webshop.Database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.kl.webshop.Entitys.OrderItem;

@Repository
public interface OrderItemRepo extends JpaRepository<OrderItem,Integer> {
}
