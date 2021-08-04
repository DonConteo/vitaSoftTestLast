package dmitriy.tsoy.russia.vitaSoftTest.service;

import dmitriy.tsoy.russia.vitaSoftTest.dto.ApplicationDto;
import dmitriy.tsoy.russia.vitaSoftTest.model.Application;
import dmitriy.tsoy.russia.vitaSoftTest.repository.ApplicationRepo;
import dmitriy.tsoy.russia.vitaSoftTest.repository.UserRepo;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicationService {

  @Autowired
  ApplicationRepo applicationRepo;
  @Autowired
  UserRepo userRepo;

  public Optional<Application> getApplicationById(long id) {
    return applicationRepo.findById(id);
  }

  public Optional<Application> getUserApplicationById(long id, long appId) {
    return applicationRepo.findApplicationForUserById(id, appId);
  }

  public void createApplication(long id, Application app) {
    applicationRepo.save(
        app.setStatus("draft")
            .setUser(userRepo.getById(id))
            .setDate(LocalDate.now()));
  }

  // for USER
  public List<ApplicationDto> getApplicationsForUser(long id) {
    List<Application> applications = applicationRepo.getApplicationsForUser(id);
    List<ApplicationDto> list = new ArrayList<>();
    for(Application application : applications) {
      ApplicationDto applicationDto = new ApplicationDto();
      applicationDto.setId(application.getId())
              .setStatus(application.getStatus())
              .setText(application.getText())
              .setDate(application.getDate());
      list.add(applicationDto);
    }
    return list;
  }

  public void updateApplication(long id, Application app, String status) {
    if (status.equals(""))
      status = applicationRepo.getById(id).getStatus();
    applicationRepo.updateApplication(id, app.getText(), status);
  }

  // for OPERATOR
  public void updateApplicationStatus(long id, String status) {
    if (status.equals(""))
      status = applicationRepo.getById(id).getStatus();
    applicationRepo.updateApplicationStatus(id, status);
  }

  public List<ApplicationDto> getSentApplications() {
    List<Application> applications = applicationRepo.getSentApplications();
    List<ApplicationDto> list = new ArrayList<>();
    for(Application app : applications) {
      String appText = app.getText();
      StringBuilder outputText = new StringBuilder();
      for(char c : appText.toCharArray()) {
        outputText.append(c).append("-");
      }
      ApplicationDto applicationDto = new ApplicationDto();
      applicationDto.setId(app.getId())
              .setStatus(app.getStatus())
              .setDate(app.getDate())
              .setText(outputText.toString());
      list.add(applicationDto);
    }
    return list;
  }

  public ApplicationDto getSentApplicationById(long id) {
    Application app = applicationRepo.getSentApplicationById(id);
    ApplicationDto applicationDto = new ApplicationDto();
    String appText = app.getText();
    StringBuilder outputText = new StringBuilder();
    for(char c : appText.toCharArray()) {
      outputText.append(c).append("-");
    }
    applicationDto.setId(app.getId())
            .setStatus(app.getStatus())
            .setDate(app.getDate())
            .setText(outputText.toString());
    return applicationDto;
  }
}
