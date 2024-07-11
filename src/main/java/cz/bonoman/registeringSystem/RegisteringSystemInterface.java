package cz.bonoman.registeringSystem;

import cz.bonoman.service.User;

import java.util.List;

public interface RegisteringSystemInterface {
    public void connectDatabase() throws RegisteringSystemException;
    public void closeDatabase() throws RegisteringSystemException;
    public void addUser(String name, String surname) throws RegisteringSystemException;
    public void deleteUser(String PersonID) throws RegisteringSystemException;
    public void updateUser(String name, String surName, String personID) throws RegisteringSystemException;
    public List<User> getUsersData(boolean details, String PersonID) throws RegisteringSystemException;
}
