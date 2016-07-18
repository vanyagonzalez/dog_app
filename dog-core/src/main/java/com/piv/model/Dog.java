package com.piv.model;


import lombok.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

import javax.validation.constraints.Min;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Calendar;
import java.util.Date;

@Getter @Setter @AllArgsConstructor @RequiredArgsConstructor
@EqualsAndHashCode(of = "dogId")
public class Dog {

    private Long dogId;

    public static final String NAME_MSG = "name - 1-100 symbols";
    public static final int MAX_LEN = 100;
    @Size(min = 1, max = MAX_LEN, message = NAME_MSG)
    private String name;

    public static final String DATE_OF_BIRTH_MSG = "must be before NOW";
    @Past(message = DATE_OF_BIRTH_MSG)
    private Date dateOfBirth;

    public static final String HEIGHT_MSG = "must be greater than 0";
    @Min(value = 0, message = HEIGHT_MSG)
    private Double height;

    public static final String WEIGHT_MSG = "must be greater than 0";
    @Min(value = 0, message = WEIGHT_MSG)
    private Double weight;

    public static Dog random() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -1 * RandomUtils.nextInt(1, 100));
        return new Dog(
                null
                , RandomStringUtils.randomAscii(RandomUtils.nextInt(1, 10))
                , calendar.getTime()
                , RandomUtils.nextDouble(0, Double.MAX_VALUE)
                , RandomUtils.nextDouble(0, Double.MAX_VALUE)
        );
    }
}
