package se.kl.webshop.Mail;
import org.springframework.stereotype.Component;
import se.kl.webshop.Entitys.OrderComplete;
import se.kl.webshop.Entitys.OrderItem;
import se.kl.webshop.Entitys.User;

@Component
public class SmtpMailSender {

    public void sendOrderConfirmationEmail(User user, OrderComplete order) {

        String subject = "Order Confirmation";
        StringBuilder bodyBuilder = new StringBuilder();
        bodyBuilder.append("Your order has been placed successfully!<br/><br/>")
                .append("Order ID: ").append(order.getId()).append("<br/>")
                .append("Items:<br/>");

        for (OrderItem item : order.getOrderItems()) {
            bodyBuilder.append("- ").append(item.getProduct().getName()).append("<br/>");
        }

        String body = bodyBuilder.toString();

        GEmailSender emailSender = new GEmailSender();
        String to = user.getEmail();
        String from = "baltzar1998@gmail.com";
        emailSender.sendEmail(to, from, subject, body);
    }
}

