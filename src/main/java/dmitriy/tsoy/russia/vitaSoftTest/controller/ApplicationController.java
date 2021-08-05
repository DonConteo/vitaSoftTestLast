package dmitriy.tsoy.russia.vitaSoftTest.controller;

import dmitriy.tsoy.russia.vitaSoftTest.dto.ApplicationDto;
import dmitriy.tsoy.russia.vitaSoftTest.model.Application;
import dmitriy.tsoy.russia.vitaSoftTest.model.User;
import dmitriy.tsoy.russia.vitaSoftTest.service.ApplicationService;
import dmitriy.tsoy.russia.vitaSoftTest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ApplicationController {

  @Autowired
  ApplicationService applicationService;
  @Autowired
  UserService userService;

  @GetMapping("application")
  public ResponseEntity getApplicationsForUser(@AuthenticationPrincipal User user) {
    List<ApplicationDto> applications = applicationService.getApplicationsForUser(user.getId());
    return applications.isEmpty()
            ? new ResponseEntity<>("There is no applications", HttpStatus.NOT_FOUND)
            : new ResponseEntity<>(applications, HttpStatus.OK);
  }

  @PostMapping("application")
  public ResponseEntity<String> createApplication(@AuthenticationPrincipal User user,
                                                  @RequestBody Application app) {
    applicationService.createApplication(user.getId(), app);
    return new ResponseEntity<>("Application successfully added", HttpStatus.CREATED);
  }

  @GetMapping("application/{appId}")
  public ResponseEntity getApplicationById(@AuthenticationPrincipal User user,
                                           @PathVariable(value="appId") long appId) {
    return applicationService.getUserApplicationById(user.getId(), appId).isPresent()
            ? new ResponseEntity<>(applicationService.getUserApplicationById(user.getId(), appId), HttpStatus.OK)
            : new ResponseEntity(HttpStatus.NOT_FOUND);
  }

  @PutMapping("application/{appId}")
  public ResponseEntity<String> updateApplication(@AuthenticationPrincipal User user,
                                                  @PathVariable(value="appId") long appId,
                                                  @RequestParam(value="status", required = false, defaultValue = "") String status,
                                                  @RequestBody Application app) {
    if(applicationService.getUserApplicationById(user.getId(), appId).isPresent()) {
      if(status.equalsIgnoreCase("sent") || status.equals("")) {
        applicationService.updateApplication(appId, app, status);
        return new ResponseEntity<>("Application successfully updated", HttpStatus.OK);
      }
      return new ResponseEntity<>("STATUS must be SENT (for change DRAFT to SENT) or empty (to left STATUS as DRAFT)", HttpStatus.EXPECTATION_FAILED);
    }
    return new ResponseEntity<>("Application with this ID doesn't exists", HttpStatus.NOT_FOUND);
  }

  @GetMapping("sentApplication")
  public ResponseEntity getSentApplications() {
    List<ApplicationDto> applications = applicationService.getSentApplications();
    return applications.isEmpty()
            ? new ResponseEntity<>("There is no applications", HttpStatus.NOT_FOUND)
            : new ResponseEntity<>(applications, HttpStatus.OK);
  }

  @GetMapping("sentApplication/{appId}")
  public ResponseEntity getApplicationForOperator(@PathVariable(value="appId") long appId) {
    return applicationService.getSentApplicationById(appId) != null
            ? new ResponseEntity<>(applicationService.getSentApplicationById(appId), HttpStatus.OK)
            : new ResponseEntity(HttpStatus.NOT_FOUND);
  }

  @PutMapping("sentApplication/{appId}")
  public ResponseEntity updateApplicationStatus(@PathVariable(value="appId") long appId,
                                                @RequestParam(value="status", required = false, defaultValue = "") String status) {
    if (applicationService.getSentApplicationById(appId) != null) {
      if (!applicationService.getApplicationById(appId).get().getStatus().equals("sent")) {
        return new ResponseEntity<>("You can't see this application", HttpStatus.EXPECTATION_FAILED);
      } else if(status.equalsIgnoreCase("confirmed") || status.equals("refused") || status.equals("")) {
        applicationService.updateApplicationStatus(appId, status);
        return new ResponseEntity<>("Application successfully updated", HttpStatus.OK);
      }
      return new ResponseEntity<>("STATUS must be CONFIRMED, REFUSED or empty", HttpStatus.EXPECTATION_FAILED);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
}

