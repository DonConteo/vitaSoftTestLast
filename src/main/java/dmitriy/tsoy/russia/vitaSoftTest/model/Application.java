package dmitriy.tsoy.russia.vitaSoftTest.model;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.IDENTITY;

import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.jetbrains.annotations.Contract;

@Entity
@Getter
@ToString
@Setter
@RequiredArgsConstructor
public class Application {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  Long id;
  String text;
  String status;
  LocalDate date;
  @ManyToOne(fetch = EAGER)
  User user;

  @Override
  @Contract(value = "null -> false", pure = true)
  public boolean equals(Object o) {
    return this == o || o != null
                            && Hibernate.getClass(this) == Hibernate.getClass(o)
                            && Objects.equals(id, ((Application) o).id);
  }

  @Override
  public int hashCode() {
    return 85634910;
  }
}
