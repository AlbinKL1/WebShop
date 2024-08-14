package se.kl.webshop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import se.kl.webshop.Entitys.User;
import se.kl.webshop.Entitys.UserType;
import se.kl.webshop.Service.UserService;
import se.kl.webshop.UI.UserController;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class UserTest {
    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testLoginUser() {
        Model model = mock(Model.class);
        User user = new User();
        user.setUsertype(new UserType());
        when(userService.loginUser(anyString(), anyString())).thenReturn(user);

        String viewName = userController.loginUser(model, "testuser", "testpassword");
        assertEquals("redirect:/homepage", viewName);
    }

    @Test
    void testLoginUserInvalidCredentials() {
        Model model = mock(Model.class);
        when(userService.loginUser(anyString(), anyString())).thenReturn(null);

        String viewName = userController.loginUser(model, "invaliduser", "invalidpassword");
        assertEquals("login", viewName);
    }

    @Test
    void testSignup() {
        UserType userType = new UserType();
        userType.setId(1);

        when(userService.getUserTypeById(anyInt())).thenReturn(userType);

        String viewName = userController.signup(mock(Model.class), "test@example.com", "TestUser", "TestPassword123");
        assertEquals("redirect:/start", viewName);
    }

    @Test
    void testSignupInvalidEmail() {
        Model model = mock(Model.class);
        String viewName = userController.signup(model, "invalidEmail", "TestUser", "TestPassword123");
        assertEquals("redirect:/signup", viewName);
    }

    @Test
    void testSignupShortUsername() {
        Model model = mock(Model.class);
        String viewName = userController.signup(model, "test@example.com", "short", "TestPassword123");
        assertEquals("redirect:/signup", viewName);
    }

    @Test
    void testSignupShortPassword() {
        Model model = mock(Model.class);
        String viewName = userController.signup(model, "test@example.com", "TestUser", "short");
        assertEquals("redirect:/signup", viewName);
    }

    @Test
    void testSignupPasswordWithoutCapital() {
        Model model = mock(Model.class);
        String viewName = userController.signup(model, "test@example.com", "TestUser", "testpassword123");
        assertEquals("redirect:/signup", viewName);
    }

    @Test
    void testSignupUsernameWithoutCapital() {
        Model model = mock(Model.class);
        String viewName = userController.signup(model, "test@example.com", "testuser", "TestPassword123");
        assertEquals("redirect:/signup", viewName);
    }
}

