package dmitriy.tsoy.russia.vitaSoftTest.service;

import dmitriy.tsoy.russia.vitaSoftTest.model.Role;
import dmitriy.tsoy.russia.vitaSoftTest.model.User;
import dmitriy.tsoy.russia.vitaSoftTest.repository.RoleRepo;
import dmitriy.tsoy.russia.vitaSoftTest.repository.UserRepo;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

@Service
public class UserService {

  UserRepo userRepo;
  RoleRepo roleRepo;

  public Optional<User> getUserById(long id) {
    return userRepo.findById(id);
  }

  public List<User> getAllUsers() {
    return userRepo.findAll();
  }

  public String changeUserStatus(long id, String role) {
    Optional<User> user = userRepo.findById(id);
    if(role.equals("operator")) {
      if(user.get().getRoles().contains(roleRepo.getById(2L))) {
        return "User is already OPERATOR";
      } else
        roleRepo.updateUserToOperator(id);
        return "User has OPERATOR's rights now";
    }
    if(role.equals("user")) {
      if (user.get().getRoles().contains(roleRepo.getById(2L))) {
        roleRepo.updateOperatorToUser(id);
        return "User is just USER now";
      }
      return "User is already just USER";
    }
    return "User status has been left without changes";
  }




//      }
//      user.getRoles().add(roleRepo.getById(2L));
//      userRepo.updateUserRole(id, user.getRoles());
//    }
//  }

  //    @Override
  //    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
  //        return (UserDetails) userRepo.findByUsername(username);
  //    }
}
