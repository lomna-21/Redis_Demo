package com.Redis.DAO;


import com.Redis.Model.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Repository

public class PersonRepository {

    @Autowired
    RedisTemplate<String,Person>redisTemplate;

    @Autowired
    ObjectMapper objectMapper;

    private static final String PERSON_LIST_KEY="person_list";
    private static final  String PERSON_KEY_PREFIX="person::";
    private static final Integer PERSON_VALUE_EXPIRY=1;
    private static final  String PERSON_HASH_KEY_PREFIX="person_hash::";


    public void set(Person person){

        this.redisTemplate.opsForValue().set(getKey(String.valueOf(person.getId())),person,PERSON_VALUE_EXPIRY, TimeUnit.DAYS);
    }

    public Person getById(String personId){

        return this.redisTemplate.opsForValue().get(getKey(personId));
    }

    public Set<String> get(){

        return this.redisTemplate.keys(PERSON_KEY_PREFIX+"*");
    }

    public Person getByKey(String key){

        return this.redisTemplate.opsForValue().get(key);
    }

    private String getKey(String personId){

        return PERSON_KEY_PREFIX+personId;
    }

    public void lpush(Person person) {

        redisTemplate.opsForList().leftPush(PERSON_LIST_KEY,person);
    }

    public void rpush(Person person) {

        redisTemplate.opsForList().rightPush(PERSON_LIST_KEY,person);
    }

    public List<Person> lPop(int counter) {

      return   redisTemplate.opsForList().leftPop(PERSON_LIST_KEY,counter);
    }

    public List<Person> rPop(int counter) {

        return redisTemplate.opsForList().rightPop(PERSON_LIST_KEY,counter);
    }

    public List<Person> lRange(int start, int end){

        return this.redisTemplate.opsForList().range(PERSON_LIST_KEY,start,end);
    }

    public void hmSet(Person person){

        Map map=objectMapper.convertValue(person, Map.class);
        this.redisTemplate.opsForHash().putAll(getKeyForHash(person.getId()),map);
    }

    public Person hmGetAll(String personId){

        Map map= this.redisTemplate.opsForHash().entries(getKeyForHash(personId));
        return objectMapper.convertValue(map, Person.class);
    }

    public String getKeyForHash(String personId){

        return PERSON_HASH_KEY_PREFIX+personId;
    }

}
