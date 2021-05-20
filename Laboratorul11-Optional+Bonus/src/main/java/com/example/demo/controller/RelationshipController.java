package com.example.demo.controller;

import com.example.demo.dao.PersonDao;
import com.example.demo.dao.RelationshipDao;
import com.example.demo.exceptions.NoPersonException;
import com.example.demo.exceptions.NoRelationshipException;
import com.example.demo.model.Person;
import com.example.demo.model.Relationship;
import org.apache.commons.lang3.tuple.MutablePair;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@RestController
public class RelationshipController {

    @GetMapping("/relationships")
    public List<Relationship> getRelationships() {
        RelationshipDao relationshipDao = new RelationshipDao();
        try {
            List<Relationship> relationships = relationshipDao.getAllRelationShips();
            return relationships;
        } catch (NoRelationshipException e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("relationships/popular/most")
    public List<Person> mostPopular(@RequestParam int count) {
        RelationshipDao relationshipDao = new RelationshipDao();
        PersonDao personDao = new PersonDao();
        List<Person> listaPersoane = new ArrayList<>();
        try {
            List<MutablePair<Integer, Integer>> pairList= personDao.getAllPersonsIds();
            pairList = relationshipDao.getAllCountRelationshipPersonById(pairList);
            pairList.sort((th,ta) -> ta.right - th.right);
            for(int i=0; i < pairList.size() && i < count; i++) {
                Person person = personDao.getPersonById(pairList.get(i).left);
                listaPersoane.add(person);
            }
            return listaPersoane;

        } catch (NoRelationshipException | NoPersonException e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("relationships/popular/least")
    public List<Person> LeastPopular(@RequestParam int count) {
        RelationshipDao relationshipDao = new RelationshipDao();
        PersonDao personDao = new PersonDao();
        List<Person> listaPersoane = new ArrayList<>();
        try {
            List<MutablePair<Integer, Integer>> pairList= personDao.getAllPersonsIds();
            pairList = relationshipDao.getAllCountRelationshipPersonById(pairList);
            pairList.sort(Comparator.comparingInt(th -> th.right));
            for (int i = 0; i < pairList.size(); i++) {
                System.out.println(pairList.get(i).left + " ");
            }
            for(int i=0; i < pairList.size() && i < count; i++) {

                Person person = personDao.getPersonById(pairList.get(i).left);
                listaPersoane.add(person);
            }
            return listaPersoane;

        } catch (NoRelationshipException | NoPersonException e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/relationships/for/{id}")
    public List<Relationship> getRelationshipForId(@PathVariable("id") int id) {
        RelationshipDao relationshipDao = new RelationshipDao();
        try {
            List<Relationship> relationships = relationshipDao.getPersonRelationShipsById(id);
            return relationships;
        } catch (NoRelationshipException e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/relationships/{id}")
    public Relationship getRelationship(@PathVariable("id") int id) throws NoRelationshipException{
        RelationshipDao relationshipDao = new RelationshipDao();
        try {
            Relationship relationship = relationshipDao.getRelationshipById(id);
            return relationship;
        } catch (NoRelationshipException e) {
            e.printStackTrace();
        }
        throw new NoRelationshipException();
    }

    @PostMapping(value = "/relationships/add", consumes = "application/json")
    public ResponseEntity<String> addRelationship(@RequestBody Relationship relationship) {
        RelationshipDao relationshipDao = new RelationshipDao();
        try {
            relationshipDao.insertRelationship(relationship);
            return new ResponseEntity<>("Relationship added successfully!", HttpStatus.CREATED);
        } catch (NoPersonException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Invalid data for Relationship!", HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @DeleteMapping(value="/relationships/delete/{id}")
    public ResponseEntity<String> deleteRelationship(@PathVariable int id) {
        RelationshipDao relationshipDao = new RelationshipDao();
        try {
            relationshipDao.deleteRelationshipById(id);
        } catch (NoRelationshipException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Relationship not found!",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Relationship removed!", HttpStatus.OK);
    }
    @PutMapping("relationships/update/{id}")
    public ResponseEntity<String> updatePerson(@PathVariable int id, @RequestParam String newType) {
        RelationshipDao relationshipDao = new RelationshipDao();
        try {
            relationshipDao.updateRelationshipTypeById(id,newType);
        } catch (NoRelationshipException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Relationship not found!",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Relationship modified!", HttpStatus.OK);
    }
}
