package se.kl.webshop.Database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.kl.webshop.Entitys.UserType;

@Repository
public interface UserTypeRepo extends JpaRepository<UserType, Integer> {
}
