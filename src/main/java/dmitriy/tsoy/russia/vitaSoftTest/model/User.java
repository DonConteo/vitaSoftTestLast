package dmitriy.tsoy.russia.vitaSoftTest.model;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.IDENTITY;

import java.util.Objects;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "usr")
public class User {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  long id;
  String username;
  String password;

  @ManyToMany(fetch = EAGER)
  Set<Role> roles;

  @Override
  public boolean equals(Object o) {
    return this == o || o != null
                            && Hibernate.getClass(this) == Hibernate.getClass(o)
                            && Objects.equals(id, ((User) o).id);
  }

  @Override
  public int hashCode() {
    return 562048007;
  }
}
