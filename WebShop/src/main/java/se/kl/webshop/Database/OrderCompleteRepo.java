package se.kl.webshop.Database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.kl.webshop.Entitys.OrderComplete;

import java.util.List;

@Repository
public interface OrderCompleteRepo extends JpaRepository<OrderComplete, Integer> {
    List<OrderComplete> findByUserIdOrderByCreatedAtDesc(int userId);
}
