package com.abdalazeez.firstspringproject.services;

import com.abdalazeez.firstspringproject.customExceptions.PersonNotFoundException;
import com.abdalazeez.firstspringproject.model.Person;

import java.sql.SQLException;
import java.util.List;

public interface PersonService {
    Person fetchById(int id) throws PersonNotFoundException;

    List<Person> fetchAll() throws SQLException;

    Person addPerson(Person newperson) throws SQLException;

    Person updatePerson(int id, Person newPerson) throws SQLException;

    void deletePerson(int id) throws SQLException;
}
