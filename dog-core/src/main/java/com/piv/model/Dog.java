package com.piv.model;


import lombok.*;

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
}
