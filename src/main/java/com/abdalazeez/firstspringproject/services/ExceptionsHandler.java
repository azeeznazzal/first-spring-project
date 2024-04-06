package com.abdalazeez.firstspringproject.services;

import com.abdalazeez.firstspringproject.customExceptions.NullPersonIdException;
import com.abdalazeez.firstspringproject.customExceptions.PersonNotFoundException;
import com.abdalazeez.firstspringproject.customExceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionsHandler {
    //centralized configuration and management of cross-cutting concerns
    //such as exception handling, global model attributes,
    //and data binding across multiple controllers.



    @ModelAttribute("commonData")
    public String addCommonDataToModel() {
        // This method provides common data that will be added to the model for all controller methods.
        return "This is common data shared across all views.";

        // No need to explicitly call addCommonDataToModel method
        // The common data will be automatically added to the model
        // by the @ControllerAdvice class
        //<!-- Displaying the common data provided by the @ModelAttribute method inside html tags-->
        //<p th:text="${commonData}"></p>
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException ex) {
        String errorMessage = ex.getMessage();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }

    @ExceptionHandler(NullPersonIdException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleNullPersonIdException(UserNotFoundException ex){
        String errorMessage = ex.getMessage();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }

    @ExceptionHandler(PersonNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleNullPersonIdException(PersonNotFoundException ex){
        String errorMessage = ex.getMessage();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }

}
