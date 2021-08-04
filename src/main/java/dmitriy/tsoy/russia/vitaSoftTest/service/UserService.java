package dmitriy.tsoy.russia.vitaSoftTest.service;

import dmitriy.tsoy.russia.vitaSoftTest.model.Role;
import dmitriy.tsoy.russia.vitaSoftTest.model.User;
import dmitriy.tsoy.russia.vitaSoftTest.repository.RoleRepo;
import dmitriy.tsoy.russia.vitaSoftTest.repository.UserRepo;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

  @Autowired
  UserRepo userRepo;
  @Autowired
  RoleRepo roleRepo;

  public Optional<User> getUserById(long id) {
    return userRepo.findById(id);
  }

  public List<User> getAllUsers() {
    return userRepo.findAll();
  }

  public String changeUserStatus(long id, String role) {
    Optional<User> user = userRepo.findById(id);
    if(role.equalsIgnoreCase("operator")) {
      if(user.get().getRoles().contains(roleRepo.getById(2L))) {
        return "User is already OPERATOR";
      } else
        roleRepo.updateUserToOperator(id);
        return "User has OPERATOR's rights now";
    }
    if(role.equalsIgnoreCase("user")) {
      if (user.get().getRoles().contains(roleRepo.getById(2L))) {
        roleRepo.updateOperatorToUser(id, 2L);
        return "User is just USER now";
      }
      return "User is already just USER";
    }
    return "User status has been left without changes";
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepo.findByUsername(username);
    if (user == null) {
      throw new UsernameNotFoundException("User not found");
    }
    return user;
  }
}
