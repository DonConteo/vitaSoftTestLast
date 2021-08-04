package dmitriy.tsoy.russia.vitaSoftTest.model;

import java.util.Objects;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Role implements GrantedAuthority {

  @Id
  long id;
  String roleName;
  @Transient
  @ManyToMany(mappedBy = "roles")
  @ToString.Exclude
  Set<User> users;

  @Override
  public boolean equals(Object o) {
    return this == o || o != null
                            && Hibernate.getClass(this) == Hibernate.getClass(o)
                            && Objects.equals(id, ((Role) o).id);
  }

  @Override
  public int hashCode() {
    return 1179619963;
  }

  @Override
  public String getAuthority() {
    return getRoleName();
  }
}
