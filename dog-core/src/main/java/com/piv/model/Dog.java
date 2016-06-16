package com.piv.model;


import lombok.*;

import java.util.Date;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@EqualsAndHashCode(of = "dogId")
public class Dog {
    private Integer dogId;
    private String name;
    private Date dateOfBirth;
    private Double height;
    private Double weight;
}
