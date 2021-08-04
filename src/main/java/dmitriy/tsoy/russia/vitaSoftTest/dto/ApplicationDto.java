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
}
