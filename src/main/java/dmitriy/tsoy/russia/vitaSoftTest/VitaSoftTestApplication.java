package dmitriy.tsoy.russia.vitaSoftTest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan("dmitriy.tsoy.russia.vitaSoftTest.model")
@SpringBootApplication(scanBasePackages = "dmitriy.tsoy.russia.vitaSoftTest")
public class VitaSoftTestApplication {

  public static void main(String[] args) {
    SpringApplication.run(VitaSoftTestApplication.class, args);
  }
}
