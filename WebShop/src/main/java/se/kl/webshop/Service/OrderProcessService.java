package se.kl.webshop.Service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.kl.webshop.Database.OrderProcessRepo;
import se.kl.webshop.Entitys.OrderProcess;
import se.kl.webshop.Entitys.Product;
import se.kl.webshop.Entitys.User;

import java.util.List;

@Service
public class OrderProcessService {

    @Autowired
    private OrderProcessRepo orderProcessRepo;
    @Autowired
    private OrderCompleteService orderCompleteService;

    public List<OrderProcess> getCartItemsByUser(User user) {
        return orderProcessRepo.findByUser(user);
    }

    public void addToCart(User user, Product product) {
        OrderProcess orderProcess = new OrderProcess();
        orderProcess.setUser(user);
        orderProcess.setProduct(product);
        orderProcess.setUserId(user.getId());
        orderProcess.setProductId(product.getId());
        try {
            orderProcessRepo.save(orderProcess);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeFromCart(User user, Product product) {
        List<OrderProcess> orderProcesses = orderProcessRepo.findByUserAndProduct(user, product);
        if (!orderProcesses.isEmpty()) {
            OrderProcess orderProcessToRemove = orderProcesses.get(0);
            for (OrderProcess orderProcess : orderProcesses) {
                if (orderProcess.getId() > orderProcessToRemove.getId()) {
                    orderProcessToRemove = orderProcess;
                }
            }
            orderProcessRepo.delete(orderProcessToRemove);
        }
    }

    @Transactional
    public void completeOrder(int orderId) {
        OrderProcess orderProcess = orderProcessRepo.findById(orderId).orElse(null);
        if (orderProcess != null) {
            User user = new User();
            user.setId(orderProcess.getUserId());
            orderCompleteService.checkout(user);
            orderProcessRepo.delete(orderProcess);
        }
    }

    @Transactional
    public void completeOrdersForUser(int userId) {
        List<OrderProcess> orderProcesses = orderProcessRepo.findByUserId(userId);

        for (OrderProcess orderProcess : orderProcesses) {
            completeOrder(orderProcess.getId());
        }
    }

    public List<OrderProcess> getAllProcessingOrders() {
        return orderProcessRepo.findAll();
    }
}
