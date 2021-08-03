package dmitriy.tsoy.russia.vitaSoftTest.repository;

import dmitriy.tsoy.russia.vitaSoftTest.model.Role;
import dmitriy.tsoy.russia.vitaSoftTest.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {

    Optional<Role> findByRoleName(String roleName);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO usr_roles (user_id, roles_id) VALUES (:id, 2)", nativeQuery = true)
    void updateUserToOperator(@Param("id") long id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM usr_roles WHERE user_id = :id AND roles_id = :role_id", nativeQuery = true)
    void updateOperatorToUser(@Param("id") long id, @Param("role_id") long roleId);
}
