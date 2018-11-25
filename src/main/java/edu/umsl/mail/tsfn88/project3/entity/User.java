/*
 * Tyler Filla
 * CS 4012
 * Project 3
 */

package edu.umsl.mail.tsfn88.project3.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * A registered user.
 */
@Entity
@Table(name = "user")
public class User {

    private static final long serialVersionUID = 1;

    /**
     * The user's unique ID.
     */
    private long id;

    /**
     * The user's username.
     */
    private String username;

    /**
     * The user's password.
     */
    private String password;

    /**
     * The user's first name.
     */
    private String fname;

    /**
     * The user's last name.
     */
    private String lname;

    /**
     * The user's last modified time.
     */
    private Timestamp modified;

    /**
     * @return The user ID
     */
    @Id
    @GeneratedValue
    public long getId() {
        return id;
    }

    /**
     * @param id The user ID
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return The user's username
     */
    @Column(unique = true)
    public String getUsername() {
        return username;
    }

    /**
     * @param username The user's username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return The user's plaintext password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password The user's plaintext password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return The user's first name.
     */
    public String getFname() {
        return fname;
    }

    /**
     * @param fname The user's first name.
     */
    public void setFname(String fname) {
        this.fname = fname;
    }

    /**
     * @return The user's last name.
     */
    public String getLname() {
        return lname;
    }

    /**
     * @param lname The user's last name.
     */
    public void setLname(String lname) {
        this.lname = lname;
    }

    /**
     * @return The user's last modified time
     */
    public Timestamp getModified() {
        return modified;
    }

    /**
     * @param modified The user's last modified time
     */
    public void setModified(Timestamp modified) {
        this.modified = modified;
    }

}
