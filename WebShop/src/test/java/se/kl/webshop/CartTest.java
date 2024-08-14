package se.kl.webshop;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import se.kl.webshop.Database.*;
import se.kl.webshop.Entitys.OrderProcess;
import se.kl.webshop.Entitys.Product;
import se.kl.webshop.Entitys.User;
import se.kl.webshop.Service.OrderProcessService;
import se.kl.webshop.Service.ProductService;
import se.kl.webshop.Service.UserService;
import se.kl.webshop.UI.CartController;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CartTest {
    @Mock
    private OrderProcessService orderProcessService;
    @Mock
    private UserService userService;
    @Mock
    private ProductService productService;

    @InjectMocks
    private CartController cartController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testAddToCart() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getHeader("Referer")).thenReturn("/some-page");

        when(userService.getCurrentUser()).thenReturn(new User());

        Product product = new Product();
        when(productService.getProductById(anyInt())).thenReturn(product);

        String viewName = cartController.addToCart(1, request);

        assertEquals("redirect:/some-page", viewName);
    }

    @Test
    void testViewCart() {
        when(userService.getCurrentUser()).thenReturn(new User());

        OrderProcess orderProcess1 = new OrderProcess();
        orderProcess1.setProduct(new Product());
        orderProcess1.getProduct().setPrice((int) 10.0);

        List<OrderProcess> cartItems = List.of(orderProcess1);
        when(orderProcessService.getCartItemsByUser(any())).thenReturn(cartItems);

        when(productService.getProductById(anyInt())).thenReturn(new Product());

        Model model = mock(Model.class);

        String viewName = cartController.viewCart(model);
        assertEquals("cart", viewName);
    }
}
