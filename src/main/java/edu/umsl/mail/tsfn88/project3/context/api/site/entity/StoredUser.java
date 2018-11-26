/*
 * Tyler Filla
 * CS 4012
 * Project 3
 */

package edu.umsl.mail.tsfn88.project3.context.api.site.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StoredUser {

    @JsonProperty
    public long id;

    @JsonProperty
    public String username;

    @JsonProperty
    public String password;

    @JsonProperty
    public String firstname;

    @JsonProperty
    public String lastname;

    @JsonProperty
    public Long modified;

}
