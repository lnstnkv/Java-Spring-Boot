package ru.tsu.hits.springdb1.exception;

public class TaskNotFoundException  extends RuntimeException{
    public TaskNotFoundException(String message) {
        super(message);
    }
}

