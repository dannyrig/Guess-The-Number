package com.sg.guessthenumber.controller;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
@RestController
// This class handles catching specific exceptions and displaying custom messages
// to prevent Postman from returning its long, default message used for when
// an error happens.
public class GuessTheNumberErrorController extends ResponseEntityExceptionHandler {
    // Create a message to be displayed when a NullPointerException occurs.
    private static final String NULL_MESSAGE = "Invalid response, please try again";
    // Create a message to be displayed when an array is out of bounds, while searching
    // for a non-existent index.
    private static final String ARRAY_MESSAGE = "Array is out of bounds, try again.";
    // Default message for when the guessing number method, when a guess is entered that
    // exceeds the constraints.
    private static final String MESSAGE = "Too long a guess";

    // Create a method for handling NullPointerException (a value does not exist)
    // Create an annotation to point to the NullPointerException class.
    @ExceptionHandler({NullPointerException.class})

    // The first parameter is the error itself, the second parameter
    // is the HTTP request error that is found.
    public final ResponseEntity<Error> handleNullException(
            NullPointerException ex,
            WebRequest request) {

        // Create a new variable of type Error (from the Error class)
        // and set the message equal to the global null message variable.
        Error err = new Error();
        err.setMessage(NULL_MESSAGE);
        // Return the response to be displayed.
        return new ResponseEntity<>(err, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    // Create a method for handling ArrayIndexOutOfBoundsException (an index that does not exist)
    // Create an annotation to point to the ArrayIndexOutOfBoundsException class.

    @ExceptionHandler({ArrayIndexOutOfBoundsException.class})
    public final ResponseEntity<Error> handleArrayException(
            ArrayIndexOutOfBoundsException ex,
            WebRequest request) {

        // Create a new variable of type Error (from the Error class)
        // and set the message equal to the global array message variable.
        Error err = new Error();
        err.setMessage(ARRAY_MESSAGE);

        // Return the response to be displayed.
        return new ResponseEntity<>(err, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Create a method for handling a DataIntegrityViolationException (a value is entered that is not allowed)
    // Create an annotation to point to the DataIntegrityViolationException class.

    @ExceptionHandler({DataIntegrityViolationException.class})
    public final ResponseEntity<Error> handleDataException(
            DataIntegrityViolationException ex,
            WebRequest request) {

        // Create a new variable of type Error (from the Error class)
        // and set the message equal to the global default message variable.
        Error err = new Error();
        err.setMessage(MESSAGE);

        // Return the response to be displayed.
        return new ResponseEntity<>(err, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
