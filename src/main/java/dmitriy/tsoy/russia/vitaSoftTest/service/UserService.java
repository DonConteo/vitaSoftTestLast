package dmitriy.tsoy.russia.vitaSoftTest.service;

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
    switch (role) {
      case ("user"):
        if(user.get().getRoles().contains(roleRepo.getById(1L))) {
          return "User is already USER";
        }
        roleRepo.giveRole(id, 1);
        return "User has USER's rights now";
      case ("operator"):
        if(user.get().getRoles().contains(roleRepo.getById(2L))) {
          return "User is already OPERATOR";
        }
        roleRepo.giveRole(id, 2);
        return "User has OPERATOR's rights now";
      case ("admin"):
        if(user.get().getRoles().contains(roleRepo.getById(3L))) {
          return "User is already ADMIN";
        }
        roleRepo.giveRole(id, 3);
        return "User has ADMIN's rights now";
      case ("demoteuser"):
        if (user.get().getRoles().contains(roleRepo.getById(1L))) {
          roleRepo.takeAwayRole(id, 1);
          return "USER demoted";
        }
        return "User don't has this ROLE";
      case ("demoteoperator"):
        if (user.get().getRoles().contains(roleRepo.getById(2L))) {
          roleRepo.takeAwayRole(id, 2);
          return "OPERATOR demoted";
        }
        return "User don't has this ROLE";
      case ("demoteadmin"):
        if (user.get().getRoles().contains(roleRepo.getById(3L))) {
          roleRepo.takeAwayRole(id, 3);
          return "ADMIN demoted";
        }
        return "User don't has this ROLE";
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
