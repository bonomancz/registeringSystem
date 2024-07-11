package cz.bonoman.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import cz.bonoman.service.ErrorResponse;
import cz.bonoman.service.JsonUser;
import cz.bonoman.service.Operator;
import cz.bonoman.registeringSystem.RegisteringSystemException;
import cz.bonoman.service.User;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {

    @Autowired
    private Operator operator;

    @PostConstruct
    public void init(){
        try {
            operator.connectDatabase();
        }catch(RegisteringSystemException e){
            System.err.println("Controller: init(): " + e.getMessage());
        }
    }

    @GetMapping("/api/v1/user/{ID}")
    public List<User> getUsersData(@PathVariable(value = "ID") String ID,
                                   @RequestParam(name = "detail", required = false) boolean DETAIL){
        List<User> usersData = new ArrayList<>();
        try {
            usersData = new ArrayList<>(operator.getUsersData(DETAIL, ID));
        }catch(RegisteringSystemException e){
            System.err.println("Controller: getUsersData(): " + e.getMessage());
        }
        return usersData;
    }

    @GetMapping("/api/v1/users")
    public List<User> getUsersData(@RequestParam(name = "detail", required = false) boolean DETAIL){
        List<User> usersData = new ArrayList<>();
        try {
            usersData = new ArrayList<>(operator.getUsersData(DETAIL, null));
        }catch(RegisteringSystemException e){
            System.err.println("Controller: getUsersData(): " + e.getMessage());
        }
        return usersData;
    }

    @DeleteMapping("/api/v1/user/{ID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable(value = "ID") String ID){
        try {
            operator.deleteUser(ID);
        }catch(RegisteringSystemException e){
            System.err.println("Controller: deleteUser(): " + e.getMessage());
        }
    }

    @PutMapping("/api/v1/user")
    public void updateUser(@RequestBody JsonUser user){
        try {
            operator.updateUser(user.getName(), user.getSurname(), user.getId());
        }catch(RegisteringSystemException e){
            System.err.println("Controller: updateUser(): " + e.getMessage());
        }
    }

    @PostMapping("/api/v1/user")
    public void addUser(@RequestBody JsonUser user){
        try {
            operator.addUser(user.getName(), user.getSurname());
        }catch(RegisteringSystemException e){
            System.err.println("Controller: addUser(): " + e.getMessage());
        }
    }

    @GetMapping("/listusers")
    public void webListUsers(HttpServletResponse response) throws IOException {
        response.sendRedirect("/?listusers");
    }

    @GetMapping("/listuserdetail")
    public void webListUserDetail(HttpServletResponse response) throws IOException {
        response.sendRedirect("/?listuserdetail");
    }

    @GetMapping("/adduser")
    public void webAddUser(HttpServletResponse response) throws IOException {
        response.sendRedirect("/?adduser");
    }

    @PostMapping("/adduserformsent")
    public void webAddFormSent(HttpServletResponse response, @RequestParam("name") String name, @RequestParam("surname") String surname) throws IOException {
        try {
            operator.addUser(name, surname);
        }catch(RegisteringSystemException e){
            System.err.println("Controller: webAddFormSent(): " + e.getMessage());
        }
        response.sendRedirect("/?listusers");
    }

    @PostMapping("/updateuserformsent")
    public void webUpdateFormSent(HttpServletResponse response, @RequestParam("name") String name, @RequestParam("surname") String surname, @RequestParam("id") String personid) throws IOException {
        try {
            operator.updateUser(name, surname, personid);
        }catch(RegisteringSystemException e){
            System.err.println("Controller: webUpdateFormSent(): " + e.getMessage());
        }
        response.sendRedirect("/?listusers");
    }

    @PostMapping("/listuserdetailformsent")
    public void webListUserDetailFormSent(HttpServletResponse response, @RequestParam("personid") String id) throws IOException {
        response.sendRedirect("/?listuserdetailform&id=" + id);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleException(Exception e){
        e.printStackTrace();
        return new ErrorResponse(
                e.getMessage(), e.getStackTrace(), LocalDateTime.now());
    }
}
