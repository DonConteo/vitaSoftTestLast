package dmitriy.tsoy.russia.vitaSoftTest.controller;

import dmitriy.tsoy.russia.vitaSoftTest.model.User;
import dmitriy.tsoy.russia.vitaSoftTest.service.UserService;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {

  @Autowired
  UserService userService;

  @GetMapping()
  public ResponseEntity<List<User>> getAllUsers() {
    List<User> users = userService.getAllUsers();
    return users.isEmpty()
               ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
               : new ResponseEntity<>(users, HttpStatus.OK);
  }

  @GetMapping("{id}")
  public ResponseEntity getUser(@PathVariable(value = "id") long id) {
    return userService.getUserById(id).isPresent()
               ? new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK)
               : new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @PutMapping("{id}")
  public ResponseEntity changeUserStatus(@PathVariable(value="id") long id,
                                         @RequestParam(value="role", required = false, defaultValue = "") String role) {
    if(role.equalsIgnoreCase("user") ||
            role.equalsIgnoreCase("operator") ||
            role.equalsIgnoreCase("admin") ||
            role.equalsIgnoreCase("demoteUser") ||
            role.equalsIgnoreCase("demoteOperator") ||
            role.equalsIgnoreCase("demoteAdmin") ||
            role.equalsIgnoreCase("")) {
      return new ResponseEntity(userService.changeUserStatus(id, role.toLowerCase(Locale.ROOT)), HttpStatus.OK);
    }
    return new ResponseEntity("Select the right ROLE or leave role empty", HttpStatus.EXPECTATION_FAILED);
  }
}
