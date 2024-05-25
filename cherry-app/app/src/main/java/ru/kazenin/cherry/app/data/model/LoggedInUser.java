package ru.kazenin.cherry.app.data.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoggedInUser {

    private String username;
    private String auth;

}