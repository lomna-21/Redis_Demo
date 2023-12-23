package com.Redis.DTO;

import com.Redis.Model.Address;
import com.Redis.Model.Person;
import lombok.*;

import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreatePersonRequest {

    private  String name;

    private Integer age;

    private Address address;

    public Person to(){

        return Person.builder().name(this.name).age(this.age).address(this.address).id(UUID.randomUUID().toString()).build();
    }
}
