package com.Redis.Model;


import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Address implements Serializable {

    private String city;

    private String street;

    private String state;

    private String country;

}
