package com.piv.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter @Setter @AllArgsConstructor
public class Dog {
    private String name;
    private Date dateOfBirth;
    private Double height, weight;
}
