package io.sm.exceptions.exceptions;

public class PersonNotFound extends RuntimeException {
    public PersonNotFound(String msg){
        super(msg);
    }
}
