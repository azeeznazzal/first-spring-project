package com.abdalazeez.firstspringproject.services;

import com.abdalazeez.firstspringproject.customExceptions.UserNotFoundException;
import com.abdalazeez.firstspringproject.model.User;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {


    private static List<User> userList;


//    public UserService() {
//        userList = new ArrayList<>();
//
//        User user1 = new User(1,"Ida", 32, "ida@mail.com");
//        User user2 = new User(2,"Hans", 26, "hans@mail.com");
//        User user3 = new User(3,"Lars", 45, "lars@mail.com");
//        User user4 = new User(4,"Ben", 32, "ben@mail.com");
//        User user5 = new User(5,"Eva", 59, "eva@mail.com");
//
//        userList.addAll(Arrays.asList(user1,user2,user3,user4,user5));
//    }

    @PostConstruct
    private void init() {
        userList = new ArrayList<>();

        User user1 = new User(1, "Ida", 32, "ida@mail.com");
        User user2 = new User(2, "Hans", 26, "hans@mail.com");
        User user3 = new User(3, "Lars", 45, "lars@mail.com");
        User user4 = new User(4, "Ben", 32, "ben@mail.com");
        User user5 = new User(5, "Eva", 59, "eva@mail.com");

        userList.addAll(Arrays.asList(user1, user2, user3, user4, user5));
    }

    public List<User> gerAll() {
        return userList;
    }

    public User getUser(int id) {
        for (User user : userList) {
            if (id == user.getId()) {
                return user;
            }
        }
        return null;
    }

    public Optional<User> addUser(User user) {
        if (userList.stream().anyMatch(u -> u.getId() == user.getId())) {
            return Optional.empty();
        } else {
            userList.add(user);
            return Optional.of(user);
        }

    }


    public User updateUser(int id, User user) throws UserNotFoundException {

        User updatedUser = userList.stream().filter(u -> u.getId() == id).findFirst().orElseThrow(() -> new UserNotFoundException("User cannot be found with ID: " + id));

        updatedUser.setEmail(user.getEmail());
        updatedUser.setAge(user.getAge());
        updatedUser.setName(user.getName());
        userList = userList.stream()
                .map(u -> u.getId() == id ? updatedUser : u)
                .toList();

        return updatedUser;
    }

    public void deleteUser(int id) throws UserNotFoundException {
       User detetedUser = userList.stream().filter(u -> u.getId() == id).findFirst().orElseThrow(() -> new UserNotFoundException("User cannot be found with ID: " + id));
       userList.remove(detetedUser);

    }

}
