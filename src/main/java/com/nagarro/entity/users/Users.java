package com.nagarro.entity.users;

import com.nagarro.entity.images.Images;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "users",
uniqueConstraints = {
        @UniqueConstraint(columnNames = "userName"),
        @UniqueConstraint(columnNames = "emailID")})
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String userName;
    private String emailID;
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Images> images = new ArrayList<>();

    public Users() {

    }

    public Users(String name, String userName, String emailID, String password) {
        this.name = name;
        this.userName = userName;
        this.emailID = emailID;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmailID() {
        return emailID;
    }

    public String getPassword() {
        return password;
    }

    public List<Images> getImages() {
        return Collections.unmodifiableList(images);
    }

    public void addImage(Images img) {
        images.add(img);
    }

}
