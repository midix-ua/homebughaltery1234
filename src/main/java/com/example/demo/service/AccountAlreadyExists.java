package com.example.demo.service;

public class AccountAlreadyExists extends RuntimeException {
    public AccountAlreadyExists(String message) {
        super(message);
    }
}
