package com.alta.exception;

public class NoEntityException extends Exception {

    public NoEntityException(int id) {
        super("Object {" + id + "} not found. Check the request details");
    }
}
