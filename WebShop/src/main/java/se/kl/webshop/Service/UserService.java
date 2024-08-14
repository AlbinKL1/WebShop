package se.kl.webshop.Service;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.kl.webshop.Database.UserRepo;
import se.kl.webshop.Database.UserTypeRepo;
import se.kl.webshop.Entitys.User;
import se.kl.webshop.Entitys.UserType;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepository;
    @Autowired
    private UserTypeRepo userTypeRepository;
    @Autowired
    private HttpSession httpSession;

    public void createUser(User user) {
        userRepository.save(user);
    }

    public User loginUser(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, password);
        if (user != null) {
            if (user.getUsertype().getId() == 2) {
                httpSession.setAttribute("username", username);
                return user;
            } else {
                httpSession.setAttribute("username", username);
                return user;
            }
        }
        return null;
    }

    public UserType getUserTypeById(int id) {
        return userTypeRepository.findById(id).orElse(null);
    }

    public User getCurrentUser() {
        String username = (String) httpSession.getAttribute("username");
        if (username == null) {
            return null;
        }

        return userRepository.findByUsername(username);
    }
}