package dmitriy.tsoy.russia.vitaSoftTest.controller;

import dmitriy.tsoy.russia.vitaSoftTest.service.ApplicationService;
import dmitriy.tsoy.russia.vitaSoftTest.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("application")
public class ApplicationController {

  //    @Autowired
  ApplicationService applicationService;
  //    @Autowired
  UserService userService;

  //    @GetMapping()
  //    public ResponseEntity getApplicationsForUser(@AuthenticationPrincipal User user) {
  //        List<Application> applications = applicationService.getApplicationsForUser(user.getId());
  //        return applications.isEmpty()
  //                ? new ResponseEntity<>("There is no applications", HttpStatus.NOT_FOUND)
  //                : new ResponseEntity<>(applications, HttpStatus.OK);
  //    }

  //    @PostMapping()
  //    public ResponseEntity<String> createApplication(@AuthenticationPrincipal User user,
  //                                                     @RequestBody Application application) {
  //        applicationService.createApplication(user.getId(), application);
  //        return applicationService.getApplicationById(application.getId()).isPresent()
  //                ? new ResponseEntity<>("Application successfully added", HttpStatus.CREATED)
  //                : new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
  //    }
  //
//    @GetMapping("{appId}")
//    public ResponseEntity getApplicationById(@AuthenticationPrincipal User user,
//                                              @PathVariable(value="appId") long appId) {
//        return applicationService.getApplicationById(appId).isPresent()
//                ? new ResponseEntity<>(applicationService.getApplicationById(appId), HttpStatus.OK)
//                : new ResponseEntity(HttpStatus.NOT_FOUND);
//    }
//
//    @PutMapping("{appId}")
//    public ResponseEntity<String> updateApplication(@AuthenticationPrincipal User user,
//                                                    @PathVariable(value="appId") long appId,
//                                                    @RequestParam(value="status", required = false, defaultValue = "") String status,
//                                                    @RequestBody String text) {
//        if (applicationService.getApplicationById(appId).isPresent()) {
//            applicationService.updateApplication(appId, text, status);
//            return applicationService.getApplicationById(appId).get().getText().equals(text)
//                    ? new ResponseEntity<>("Application successfully updated", HttpStatus.OK)
//                    : new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
//
////        getCurrentStatus = appId.getStatus
////        newStatus = status
////        app = getAppById(appId)
////        if (currentStatus.getFromStatus.contains(user.getRole) && newStatus.getToStatus.contains(user.getRole)){
////              updateApp1Status(newStatus)
////        }
//
//    @GetMapping("sentApplication")
//    public ResponseEntity getSentApplications(@AuthenticationPrincipal User user) {
//        List<Application> applications = applicationService.getSentApplications();
//        return applications.isEmpty()
//                ? new ResponseEntity<>("There is no applications", HttpStatus.NOT_FOUND)
//                : new ResponseEntity<>(applications, HttpStatus.OK);
//    }
//
//    @GetMapping("sentApplication/{appId}")
//    public ResponseEntity getApplicationForOperator(@AuthenticationPrincipal User user,
//                                                  @PathVariable(value="appId") long appId) {
//        return applicationService.getApplicationById(appId).isPresent()
//                ? new ResponseEntity<>(applicationService.getApplicationById(appId), HttpStatus.OK)
//                : new ResponseEntity(HttpStatus.NOT_FOUND);
//    }
//
//    @PutMapping("sentApplication/{appId}")
//    public ResponseEntity updateApplicationStatus(@AuthenticationPrincipal User user,
//                                                  @PathVariable(value="appId") long appId,
//                                                  @RequestParam(value="status", required = false, defaultValue = "") String status) {
//        if (applicationService.getApplicationById(appId).isPresent()) {
//            if (!applicationService.getApplicationById(appId).get().getStatus().equals("sent")) {
//                return new ResponseEntity<>("You can't see this application", HttpStatus.EXPECTATION_FAILED);
//            }
//            applicationService.updateApplicationStatus(appId, status);
//            return applicationService.getApplicationById(appId).get().getStatus().equals(status)
//                    ? new ResponseEntity<>("Application successfully updated", HttpStatus.OK)
//                    : new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
}
