package org.securitydemo.exception;

public class StudentAlreadyExistException extends Exception {
    public StudentAlreadyExistException(String message) {
        super(message);
    }
}
