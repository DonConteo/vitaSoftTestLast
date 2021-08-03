package dmitriy.tsoy.russia.vitaSoftTest.repository;

import dmitriy.tsoy.russia.vitaSoftTest.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

  User findByUsername(String username);
}
