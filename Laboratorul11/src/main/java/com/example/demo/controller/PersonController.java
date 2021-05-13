package com.example.demo.controller;


import com.example.demo.dao.PersonDao;
import com.example.demo.exceptions.NoPersonException;
import com.example.demo.model.Person;
import org.springframework.cache.annotation.CachePut;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {

    @GetMapping("/persons")
    public List<Person> getPersons() {
        PersonDao personDao = new PersonDao();
        try {
            List<Person> person = personDao.getAllPersons();
            return person;
        } catch (NoPersonException e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/persons/{id}")
    public Person getPerson(@PathVariable("id") int id) {
        PersonDao personDao = new PersonDao();
        try {
            Person person = personDao.getPersonById(id);
            return person;
        } catch (NoPersonException e) {
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping(value = "/persons/add", consumes = "application/json")
    public ResponseEntity<String> addPerson(@RequestBody Person person) {
        PersonDao personDao = new PersonDao();
        try {
            personDao.insertPerson(person);
            return new ResponseEntity<>("Person added successfully!", HttpStatus.CREATED);
        } catch (NoPersonException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Invalid data for person!", HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @DeleteMapping(value="/persons/delete/{name}")
    public ResponseEntity<String> deletePerson(@PathVariable String name) {
        PersonDao personDao = new PersonDao();
        try {
            personDao.deletePersonByName(name);
        } catch (NoPersonException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Person not found!",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Person removed!", HttpStatus.OK);
    }
    @PutMapping("persons/update/{oldName}")
    public ResponseEntity<String> updatePerson(@PathVariable String oldName, @RequestParam String newName) {
        PersonDao personDao = new PersonDao();
        try {
            personDao.updatePersonByName(oldName,newName);
        } catch (NoPersonException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Person not found!",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Person modified!", HttpStatus.OK);
    }

}
