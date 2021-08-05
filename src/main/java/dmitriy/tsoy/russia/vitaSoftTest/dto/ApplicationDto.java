package dmitriy.tsoy.russia.vitaSoftTest.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@RequiredArgsConstructor
public class ApplicationDto {

  private long id;
  private String text;
  private String status;
  private LocalDate date;

  public static ApplicationDto.Builder newBuilder(){
    return new ApplicationDto().new Builder();
  }

  public class Builder {
    public Builder() {
    }

    public ApplicationDto.Builder id(long id) {
      ApplicationDto.this.id = id;
      return this;
    }

    public ApplicationDto.Builder text(String text) {
      ApplicationDto.this.text = text;
      return this;
    }

    public ApplicationDto.Builder status(String status) {
      ApplicationDto.this.status = status;
      return this;
    }

    public ApplicationDto.Builder date(LocalDate date) {
      ApplicationDto.this.date = date;
      return this;
    }

    public ApplicationDto build() {
      return ApplicationDto.this;
    }
  }
}
