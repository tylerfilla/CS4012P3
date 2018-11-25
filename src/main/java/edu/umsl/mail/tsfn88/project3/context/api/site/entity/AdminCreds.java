/*
 * Tyler Filla
 * CS 4012
 * Project 3
 */

package edu.umsl.mail.tsfn88.project3.context.api.site.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AdminCreds {

    @JsonProperty
    public String adminusername;

    @JsonProperty
    public String adminpassword;

}
