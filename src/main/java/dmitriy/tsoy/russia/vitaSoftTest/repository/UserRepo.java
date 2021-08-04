package dmitriy.tsoy.russia.vitaSoftTest.repository;

import dmitriy.tsoy.russia.vitaSoftTest.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

  User findByUsername(String username);
}
