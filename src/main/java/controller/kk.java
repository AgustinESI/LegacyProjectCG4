package main.java.controller;

import main.java.model.User;

import java.util.List;

public class kk {

    public static void main(String[] args) {
        UserController uc = new UserController();
        List<User> list = uc.getAllUsers();

        System.out.println(list);
    }
}
