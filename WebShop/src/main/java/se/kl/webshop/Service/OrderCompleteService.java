package se.kl.webshop.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.kl.webshop.Database.OrderCompleteRepo;
import se.kl.webshop.Database.OrderItemRepo;
import se.kl.webshop.Database.OrderProcessRepo;
import se.kl.webshop.Database.ProductRepo;
import se.kl.webshop.Entitys.*;

import java.util.*;

@Service
public class OrderCompleteService {

    @Autowired
    private OrderProcessRepo orderProcessRepo;
    @Autowired
    private OrderCompleteRepo orderCompleteRepo;
    @Autowired
    private ProductRepo productRepo;

    public OrderComplete checkout(User user) {
        List<OrderProcess> cartItems = orderProcessRepo.findByUser(user);

        OrderComplete orderComplete = new OrderComplete();
        orderComplete.setUserId(user.getId());
        orderComplete.setCreatedAt(new Date());

        Map<Integer, Integer> productQuantityMap = new HashMap<>();

        for (OrderProcess item : cartItems) {
            int productId = item.getProduct().getId();

            productQuantityMap.put(productId, productQuantityMap.getOrDefault(productId, 0) + 1);
        }

        for (Map.Entry<Integer, Integer> entry : productQuantityMap.entrySet()) {
            OrderItem orderItem = new OrderItem();
            OrderItemKey orderItemKey = new OrderItemKey();
            orderItemKey.setOrderId(orderComplete.getId());
            orderItemKey.setProductId(entry.getKey());

            orderItem.setId(orderItemKey);
            orderItem.setOrder(orderComplete);
            orderItem.setProduct(productRepo.findById(entry.getKey()).orElse(null));
            orderItem.setQuantity(entry.getValue());

            orderComplete.getOrderItems().add(orderItem);
        }

        orderCompleteRepo.save(orderComplete);
        orderProcessRepo.deleteAll(cartItems);

        return orderComplete;
    }

    public OrderComplete getLastOrder(User user) {
        List<OrderComplete> orders = orderCompleteRepo.findByUserIdOrderByCreatedAtDesc(user.getId());
        return orders.isEmpty() ? null : orders.get(0);
    }

    public List<OrderComplete> getAllCompletedOrders() {
        return orderCompleteRepo.findAll();
    }
}
