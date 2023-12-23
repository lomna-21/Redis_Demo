package com.Redis.Model;

import lombok.*;
import java.io.Serializable;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Person implements Serializable {


    private String id;

    private  String name;

    private Integer age;

    private Address address;


}
