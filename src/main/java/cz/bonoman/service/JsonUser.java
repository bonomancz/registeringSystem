package cz.bonoman.service;

import org.springframework.stereotype.Service;

@Service
public class JsonUser {
    private String id, name, surname, personID;

    public JsonUser(){}

    public String getId() {return this.id;}
    public String getName() {return this.name;}
    public String getSurname() {return this.surname;}
    public String getPersonID() {return personID;}
}
