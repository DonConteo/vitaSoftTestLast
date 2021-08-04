package dmitriy.tsoy.russia.vitaSoftTest.repository;

import dmitriy.tsoy.russia.vitaSoftTest.model.Application;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ApplicationRepo extends JpaRepository<Application, Long> {

  @Transactional
  @Query(value = "SELECT * FROM application WHERE user_id = :id ORDER BY date", nativeQuery = true)
  List<Application> getApplicationsForUser(@Param("id") long id);

  @Transactional
  @Query(value = "SELECT * FROM application WHERE id = :appId AND user_id = :user_id", nativeQuery = true)
  Optional<Application> findApplicationForUserById(@Param("user_id") long id,
                                                   @Param("appId") long appId);

  @Modifying
  @Transactional
  @Query(value = "UPDATE application SET text = :text, status = :status WHERE id = :id", nativeQuery = true)
  void updateApplication(@Param("id") long id,
                         @Param("text") String text,
                         @Param("status") String status);

  @Transactional
  @Query(value = "SELECT * FROM application WHERE status ILIKE 'sent' ORDER BY date", nativeQuery = true)
  List<Application> getSentApplications();

  @Modifying
  @Transactional
  @Query(value = "UPDATE application SET status = :status WHERE id = :id", nativeQuery = true)
  void updateApplicationStatus(@Param("id") long id,
                               @Param("status") String status);

  @Transactional
  @Query(value = "SELECT * FROM application WHERE id = :id AND status ILIKE 'sent' ORDER BY date", nativeQuery = true)
  Application getSentApplicationById(@Param("id") long id);
}
