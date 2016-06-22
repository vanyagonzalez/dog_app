package com.piv.model;


import lombok.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

import java.util.Date;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @RequiredArgsConstructor
@EqualsAndHashCode(of = "dogId")
public class Dog {
    private Long dogId;
    @NonNull
    private String name;
    @NonNull
    private Date dateOfBirth;
    @NonNull
    private Double height;
    @NonNull
    private Double weight;

    public static Dog random() {
        return new Dog(
                RandomStringUtils.randomAscii(RandomUtils.nextInt(1, 10))
                , new Date()
                , RandomUtils.nextDouble(0, Double.MAX_VALUE)
                , RandomUtils.nextDouble(0, Double.MAX_VALUE)
        );
    }
}
