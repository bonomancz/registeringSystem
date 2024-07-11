package cz.bonoman.service;

import cz.bonoman.registeringSystem.RegisteringSystemException;
import cz.bonoman.registeringSystem.RegisteringSystemInterface;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

@Service
public class Operator implements RegisteringSystemInterface {
    private ArrayList<User> users;
    private FileSystem fs;
    private DBService db;

    public Operator(){
        this.fs = new FileSystem();
        this.db = new DBService();
        this.users = new ArrayList<>();
    }

    @Override
    public void addUser(String name, String surname) throws RegisteringSystemException {
        try{
            String insertUserQuery;
            User newUser = new User(0, name, surname, this.getPersonID(), this.getUUID().toString());
            insertUserQuery = "insert into registeringSystem.users " +
                    "(`Name`, `Surname`, `PersonID`, `Uuid`) values " +
                    "('" + newUser.getName() + "', '" + newUser.getSurname() + "', '" + newUser.getPersonID() + "', '" + newUser.getUuid() + "');";
            db.execUpdate(insertUserQuery);
        }catch (Exception e){
            throw new RegisteringSystemException("addUser(): " + e.getMessage());
        }
    }

    @Override
    public void deleteUser(String personID) throws RegisteringSystemException{
        try{
            String deleteUserQuery;
            deleteUserQuery = "delete from registeringSystem.users where PersonID like '" + personID + "';";
            db.execUpdate(deleteUserQuery);
        }catch (DatabaseException e){
            throw new RegisteringSystemException("deleteUser(): " + e.getMessage());
        }
    }

    @Override
    public void updateUser(String name, String surName, String personID) throws RegisteringSystemException{
        try{
            String updateUserQuery;
            updateUserQuery = "update registeringSystem.users set Name = '" + name + "', Surname = '" + surName + "' where PersonID like '" + personID + "';";
            db.execUpdate(updateUserQuery);
        }catch (DatabaseException e){
            throw new RegisteringSystemException("updateUser(): " + e.getMessage());
        }
    }

    private long get64MostSignificantBitsForVersion1() {
        final long currentTimeMillis = System.currentTimeMillis();
        final long time_low = (currentTimeMillis & 0x0000_0000_FFFF_FFFFL) << 32;
        final long time_mid = ((currentTimeMillis >> 32) & 0xFFFF) << 16;
        final long version = 1 << 12;
        final long time_hi = ((currentTimeMillis >> 48) & 0x0FFF);
        return time_low | time_mid | version | time_hi;
    }

    private long get64LeastSignificantBitsForVersion1() {
        Random random = new Random();
        long random63BitLong = random.nextLong() & 0x3FFFFFFFFFFFFFFFL;
        long variant3BitFlag = 0x8000000000000000L;
        return random63BitLong | variant3BitFlag;
    }

    private UUID getUUID() {
        long most64SigBits = get64MostSignificantBitsForVersion1();
        long least64SigBits = get64LeastSignificantBitsForVersion1();
        return new UUID(most64SigBits, least64SigBits);
    }

    public String getPersonID() throws StorageDataException {
        String personID;
        boolean isIdAvailable = false;
        try {
            personID = "PERSONID";
            List<String> pIds = new ArrayList<>(this.fs.getFilesContentList());
            for (String pId : pIds) {
                if (!this.isPersonIDUsed(pId)) {
                    personID = pId;
                    isIdAvailable = true;
                    break;
                }
            }
            if(!isIdAvailable){
                throw new StorageDataException("getPersonID(): No more free PersonID on CA available.");
            }
        }catch(DatabaseException e){
            throw new StorageDataException("getPersonID(): " + e.getMessage());
        }
        return personID;
    }

    @Override
    public void connectDatabase() throws RegisteringSystemException {
        try {
            if (!db.dbConnect()) {
                System.err.println("Database connection failed.");
            }
        }catch (SQLException e){
            throw new RegisteringSystemException("connectDatabase(): " + e.getMessage());
        }
    }

    @Override
    public void closeDatabase() throws RegisteringSystemException{
        try{
            if(!db.dbClose()){
                System.err.println("Closing database failed.");
            }
        }catch(SQLException e){
            throw new RegisteringSystemException("closeDatabase(): " + e.getMessage());
        }
    }

    public boolean isPersonIDUsed(String input) throws DatabaseException {
        boolean isPersonIDUsed = false;
        try{
            String sqlQuery = "select personID from users order by personID;";
            List<Map<String, Object>> sqlTable = new ArrayList<>(this.db.execQuery(sqlQuery));
            for(Map<String, Object> row : sqlTable){
                for(Map.Entry<String, Object> entry : row.entrySet()){
                    String dbPersonID = entry.getValue().toString();
                    if(dbPersonID.equals(input)){
                        isPersonIDUsed = true;
                    }
                }
            }
        }catch (DatabaseException e){
            throw new DatabaseException("isPersonIdUsed(): " + e.getMessage());
        }
        return isPersonIDUsed;
    }

    @Override
    public List<User> getUsersData(boolean details, String PersonID) throws RegisteringSystemException {
        List<User> usersList = new ArrayList<>();
        try{
            String sqlQuery = "select ID, Name, Surname, PersonID, Uuid from users order by ID asc;";
            if(PersonID != null) {
                sqlQuery = "select ID, Name, Surname, PersonID, Uuid from users where PersonID like '" + PersonID + "' order by ID asc;";
            }
            List<Map<String, Object>> sqlTable = new ArrayList<>(this.db.execQuery(sqlQuery));
            String dbPersonID = "", dbName = "", dbSurName = "", dbUuid = "";
            int dbId = 0;
            for(Map<String, Object> row : sqlTable){
                for(Map.Entry<String, Object> entry : row.entrySet()){
                    if(entry.getKey().equals("ID")){dbId = Integer.parseInt(entry.getValue().toString());}
                    if(entry.getKey().equals("Name")){dbName = entry.getValue().toString();}
                    if(entry.getKey().equals("Surname")){dbSurName = entry.getValue().toString();}
                    if(entry.getKey().equals("PersonID")){dbPersonID = entry.getValue().toString();}
                    if(entry.getKey().equals("Uuid")){dbUuid = entry.getValue().toString();}
                }
                if(details) {
                    usersList.add(new User(dbId, dbName, dbSurName, dbPersonID, dbUuid));
                }else{
                    usersList.add(new User(dbId, dbName, dbSurName));
                }
            }
        }catch (DatabaseException e){
            throw new RegisteringSystemException("getUsers(): " + e.getMessage());
        }
        return usersList;
    }

    public ArrayList<User> getUsers(){
        return new ArrayList<>(this.users);
    }

}
