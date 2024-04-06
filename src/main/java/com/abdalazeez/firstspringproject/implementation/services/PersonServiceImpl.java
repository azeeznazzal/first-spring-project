package com.abdalazeez.firstspringproject.implementation.services;

import com.abdalazeez.firstspringproject.customExceptions.PersonNotFoundException;
import com.abdalazeez.firstspringproject.model.Person;
import com.abdalazeez.firstspringproject.services.PersonService;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class PersonServiceImpl implements PersonService {

    private final Person builderTest = Person.builder()
            .id(11)
            .name("foulan")
            .city("york")
            .email("foulan@mail.com")
            .Phone("0798745684")
            .build();

    private final DataSource dataSource;

    public PersonServiceImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Person fetchById(int id) throws PersonNotFoundException {

        Person person = null;
        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT * FROM person WHERE id = " + id;
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        person = new Person(resultSet.getInt(1),
                                resultSet.getString(2),
                                resultSet.getString(3),
                                resultSet.getString(4),
                                resultSet.getString(5));

                    }
                }
            }

        } catch (SQLException e) {
            try {
                throw new SQLException(e.getMessage());
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
        if (Objects.isNull(person))
            throw new PersonNotFoundException("Could no find Person With ID: " + id);

        return person;
    }

    public List<Person> fetchAll() throws SQLException {

        List<Person> personList = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            String sql = "select * from person";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    //System.out.println("----------\n"+resultSet+"\n----------");
                    while (resultSet.next()) {

                        Person person = new Person(resultSet.getInt(1),
                                resultSet.getString(2),
                                resultSet.getString(3),
                                resultSet.getString(4),
                                resultSet.getString(5));

                        //System.out.println("----------\n"+person.toString()+"\n----------");
                        personList.add(person);
                    }
                }
            }
        }
        return personList;
    }

    public Person addPerson(Person newperson) throws SQLException {

        try (Connection connection = dataSource.getConnection()) {
            String sql = "INSERT INTO person VALUES (?, ?, ?, ?, ?)";
            //String sql = "insert into person values (" + newperson.getId() + ", '" + newperson.getName() + "', '" + newperson.getCity() + "', '" + newperson.getEmail() + "', '" + newperson.getPhone() + "' )";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, newperson.getId());
                preparedStatement.setString(2, newperson.getName());
                preparedStatement.setString(3, newperson.getCity());
                preparedStatement.setString(4, newperson.getEmail());
                preparedStatement.setString(5, newperson.getPhone());
                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    return newperson;
                } else {
                    throw new SQLException("Person insertion failed.");
                }
            }

        }
    }

    public Person updatePerson(int id, Person newPerson) throws SQLException{

        try(Connection connection = dataSource.getConnection()){
            String sql = "update person set name = ?, city = ?, email = ?, phone = ?  where id = ?";
            try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
                preparedStatement.setString(1, newPerson.getName());
                preparedStatement.setString(2, newPerson.getCity());
                preparedStatement.setString(3, newPerson.getEmail());
                preparedStatement.setString(4, newPerson.getPhone());
                preparedStatement.setInt(5,id);

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    return fetchById(id);
                } else {
                    throw new SQLException("Person insertion failed.");
                }

            } catch (PersonNotFoundException e) {
                throw new RuntimeException(e);
            }

        }

    }

    @SneakyThrows
    public void deletePerson(int id){

        try(Connection connection = dataSource.getConnection()){
            String sql = "delete person where id = ?";
            try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
                preparedStatement.setInt(1,id);
                int rowAffected = preparedStatement.executeUpdate();

                if(rowAffected == 0){
                    throw new SQLException("No column affected");
                }
            }
        }
    }


}