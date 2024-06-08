package ru.kazenin.cashezavr.app.data.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoggedInUser {

    private String username;
    private String auth;

}