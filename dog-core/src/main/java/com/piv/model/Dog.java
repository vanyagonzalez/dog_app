package com.piv.model;


import lombok.*;

import java.util.Date;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@EqualsAndHashCode
public class Dog {
    private String name;
    private Date dateOfBirth;
    private Double height, weight;
}
