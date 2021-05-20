package com.example.demo.controller;


import com.example.demo.dao.PersonDao;
import com.example.demo.dao.RelationshipDao;
import com.example.demo.exceptions.NoPersonException;
import com.example.demo.exceptions.NoRelationshipException;
import com.example.demo.graphs.GraphsAlgorithms;
import com.example.demo.model.Person;
import com.example.demo.model.Relationship;
import org.apache.commons.lang3.tuple.MutablePair;
import org.springframework.cache.annotation.CachePut;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Person getPerson(@PathVariable("id") int id) throws NoPersonException {
        PersonDao personDao = new PersonDao();
        try {
            Person person = personDao.getPersonById(id);
            return person;
        } catch (NoPersonException e) {
            e.printStackTrace();
        }
        throw new NoPersonException();
    }

    @GetMapping("/persons/important")
    public List<Person> getImportantPersons() throws NoPersonException {
        PersonDao personDao = new PersonDao();
        RelationshipDao relationshipDao = new RelationshipDao();
        try {
            List<MutablePair<Integer, Integer>> pairList= personDao.getAllPersonsIds();
            int [][] friendshipMatrix = new int[pairList.size()+1][pairList.size()+1];
            Map<Integer,List<Relationship>> relationshipMap = new HashMap<>();
            for (int i = 0; i < pairList.size(); i++) {
                int id = pairList.get(i).left;
                List<Relationship> listRelationships = relationshipDao.getPersonRelationShipsById(id);
                relationshipMap.put(id,listRelationships);
            }
            for (int i = 0; i < pairList.size(); i++) {
                int id = pairList.get(i).left;
                for (Relationship rel: relationshipMap.get(id)) {
                    if(rel.getIdPrimaPersoana() != id) {
                        friendshipMatrix[id][rel.getIdPrimaPersoana()] = 1;
                        friendshipMatrix[rel.getIdPrimaPersoana()][id] = 1;
                    }
                    else {
                        friendshipMatrix[rel.getIdADouaPersoana()][id] = 1;
                        friendshipMatrix[id][rel.getIdADouaPersoana()] = 1;
                    }
                }
            }
            List<Integer> disconnetingIds = new ArrayList<>();
            for (int i = 0; i < pairList.size(); i++) {
                int[] connected = new int[pairList.size()];
                int [][] auxMatrix = new int[pairList.size()][pairList.size()];
                int start = 0;
                for (int j = 0; j < pairList.size(); j++)
                    for (int k = 0; k < pairList.size(); k++)
                        if(pairList.get(j).left != pairList.get(i).left && pairList.get(k).left != pairList.get(i).left && friendshipMatrix[pairList.get(j).left][pairList.get(k).left] != 0) {
                            auxMatrix[pairList.get(j).left][pairList.get(k).left] = 1;
                            auxMatrix[pairList.get(k).left][pairList.get(j).left] = 1;
                            start = pairList.get(j).left;
                        }
                GraphsAlgorithms.executeDfs(start, auxMatrix, pairList.size(), connected, 1);
                boolean stillConnected = true;
                for (int j = 0; j < pairList.size(); j++)
                    if(pairList.get(j).left != pairList.get(i).left)
                        stillConnected = stillConnected && (connected[pairList.get(j).left] == 1);
                if(!stillConnected)
                    disconnetingIds.add(pairList.get(i).left);
            }
            List<Person> listImportant = new ArrayList<>();
            for (int i = 0; i < disconnetingIds.size(); i++) {
                Person importantPerson = personDao.getPersonById(disconnetingIds.get(i));
                listImportant.add(importantPerson);
            }
            return listImportant;
        }
        catch (NoPersonException | NoRelationshipException e) {
            e.printStackTrace();
        }
        throw new NoPersonException();
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
