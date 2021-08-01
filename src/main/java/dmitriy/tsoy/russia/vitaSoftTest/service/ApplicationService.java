package dmitriy.tsoy.russia.vitaSoftTest.service;

import dmitriy.tsoy.russia.vitaSoftTest.model.Application;
import dmitriy.tsoy.russia.vitaSoftTest.repository.ApplicationRepo;
import dmitriy.tsoy.russia.vitaSoftTest.repository.UserRepo;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class ApplicationService {

  ApplicationRepo applicationRepo;
  UserRepo userRepo;

  public Optional<Application> getApplicationById(long id) {
    return applicationRepo.findById(id);
  }

  public List<Application> getApplicationsForUser(long id) {
    return applicationRepo.getApplicationsForUser(id);
  }

  public List<Application> getSentApplications() {
    return applicationRepo.getSentApplications();
  }

  public void createApplication(long id, Application application) {
    applicationRepo.save(
        application.setStatus("draft")
            .setUser(userRepo.getById(id))
            .setDate(LocalDate.now()));
  }

  public void updateApplication(long id, String text, String status) {
    if (status.equals(""))
      status = applicationRepo.getById(id).getStatus();
    applicationRepo.updateApplication(id, text, status);
  }

  public void updateApplicationStatus(long id, String status) {
    if (status.equals(""))
      status = applicationRepo.getById(id).getStatus();
    applicationRepo.updateApplicationStatus(id, status);
  }
}
