package com.Redis.Controller;


import com.Redis.DTO.CreatePersonRequest;
import com.Redis.Model.Person;
import com.Redis.Service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    PersonService personService;


    @PostMapping("/set")
    public void createPerson(@RequestBody(required = true) @Valid CreatePersonRequest createPersonRequest){

        personService.create(createPersonRequest.to());
    }

    @GetMapping("/get")
    public Person getPerson(@RequestParam("id")String personId){

        return personService.get(personId);
    }

    @GetMapping("/all")
    public List<Person> getAll(){

        return personService.getAll();
    }

    /*------------------------------------------------LISTS-----------------------------------------------------------*/
    @PostMapping("lpush")
    public void lPush(@RequestBody @Valid CreatePersonRequest createPersonRequest){

        personService.lPush(createPersonRequest.to());
    }

    @PostMapping("rPush")
    public void  rPush(@RequestBody @Valid CreatePersonRequest createPersonRequest) {

        personService.rPush(createPersonRequest.to());
    }

    @DeleteMapping("/lPop")
    public  List<Person> lPop(@RequestParam(value = "count",required = false,defaultValue = "1")int count){

        return personService.lPop(count);
    }

    @DeleteMapping("/rPop")
    public  List<Person> rPop(@RequestParam(value = "count",required = false,defaultValue = "1")int count){

        return personService.rPop(count);
    }

    @GetMapping("/lRange")
    public  List<Person> lRange(@RequestParam  (value = "start",required = false,defaultValue = "0") int start
                                ,@RequestParam (value = "end",required = false,defaultValue = "-1") int end){

        return personService.lRange(start,end );
    }

    /*------------------------------------------------HASH------------------------------------------------------------*/

    @PostMapping("/hash/create")
    public void createHash(@RequestBody @Valid CreatePersonRequest createPersonRequest){

        personService.setPersonHash(createPersonRequest.to());
    }

    @GetMapping("/hash/{personId}")
    public Person getPersonHash(@PathVariable("personId")String personId){

        return personService.getPersonHash(personId);
    }

}