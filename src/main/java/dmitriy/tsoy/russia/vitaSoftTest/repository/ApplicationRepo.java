package dmitriy.tsoy.russia.vitaSoftTest.repository;

import dmitriy.tsoy.russia.vitaSoftTest.model.Application;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ApplicationRepo extends JpaRepository<Application, Long> {

  @Modifying
  @Transactional
  @Query(value = "select * from application where user_id =:id order by date", nativeQuery = true)
  List<Application> getApplicationsForUser(@Param("id") long id);

  @Modifying
  @Transactional
  @Query(value = "update application set text = :text, status = :status where id = :id", nativeQuery = true)
  void updateApplication(@Param("id") long id,
                         @Param("text") String text,
                         @Param("status") String status);

  @Modifying
  @Transactional
  @Query(value = "select * from application where status ilike 'sent' order by date", nativeQuery = true)
  List<Application> getSentApplications();

  @Modifying
  @Transactional
  @Query(value = "update application set status = :status where id = :id", nativeQuery = true)
  void updateApplicationStatus(@Param("id") long id,
                               @Param("status") String status);
}
