package cz.bonoman.service;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {
    private int id;
    private String name, surname, personID, uuid;

    public User(){}

    public User(int id, String name, String surname){
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    public User(int id, String name, String surname, String personID, String uuid){
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.personID = personID;
        this.uuid = uuid;
    }

    public int getId() {return id;}
    public String getName() {return name;}
    public String getSurname() {return surname;}
    public String getPersonID() {return personID;}
    public String getUuid() {return uuid;}
}
