package com.Redis.Service;


import com.Redis.DAO.PersonRepository;
import com.Redis.Model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PersonService {


    @Autowired
    PersonRepository personRepository;

    public void create(Person person){

        personRepository.set(person);
    }


    public Person get(String personId) {

        return personRepository.getById(personId);

    }

    public List<Person> getAll() {

        Set<String> keys=personRepository.get();
        return keys.stream().
                map(k -> personRepository.getByKey(k)).
                collect(Collectors.toList());

    }

    public void lPush(Person person){
        personRepository.lpush(person);
    }

    public void rPush(Person person){
        personRepository.rpush(person);
    }

    public List<Person> lPop(int count){
        return personRepository.lPop(count);
    }

    public List<Person> rPop(int count){
        return personRepository.rPop(count);
    }

    public List<Person> lRange(int start, int end){
        return personRepository.lRange(start, end);
    }

    public void setPersonHash(Person person){

        personRepository.hmSet(person);
    }

    public Person getPersonHash(String personId){

        return personRepository.hmGetAll(personId);
    }


}
