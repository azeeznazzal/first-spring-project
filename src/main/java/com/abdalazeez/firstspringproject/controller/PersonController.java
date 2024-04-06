package com.abdalazeez.firstspringproject.controller;

import com.abdalazeez.firstspringproject.customExceptions.PersonNotFoundException;
import com.abdalazeez.firstspringproject.model.Person;
import com.abdalazeez.firstspringproject.services.PersonService;
import jakarta.validation.Valid;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/person")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }


    @GetMapping("")
    public ResponseEntity<List<Person>> fetchAll() throws SQLException {
        List<Person> personList = personService.fetchAll();
        return ResponseEntity.status(HttpStatus.OK).body(personList);
    }

    @GetMapping(value = "{id}" /*,
            consumes = MediaType.ALL_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE*/)
    public ResponseEntity<Person> getPerson(@PathVariable int id) throws PersonNotFoundException {
        Person person = personService.fetchById(id);
        return ResponseEntity.status(HttpStatus.OK).body(person);
    }

    @PostMapping("addPerson")
    public ResponseEntity<Person> addPerson(@RequestBody Person newPerson) throws SQLException {
        //System.out.println("\n\n\n\n Debugging line --------- \n\n");
        Person addedPerson = personService.addPerson(newPerson);
        return ResponseEntity.status(HttpStatus.OK).body(addedPerson);
    }

    @PutMapping("updatePerson")
    public ResponseEntity<Person> updatePerson(@Valid @RequestParam int id, @RequestBody Person newPerson) throws SQLException {
        Person updatedPerson = personService.updatePerson(id, newPerson);
        return ResponseEntity.status(HttpStatus.OK).body(updatedPerson);
    }

    @DeleteMapping("deletePerson/{id}")
    @SneakyThrows
    public ResponseEntity<Object> deletePerson(@PathVariable int id){
        personService.deletePerson(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
