package com.main.aws.app.entities;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import org.springframework.data.annotation.Id;

@DynamoDBTable(tableName = "user")
public class User implements Serializable {

    private static final long serialVersionUID = -4282005207341771716L;

    @Id
    @DynamoDBHashKey(attributeName = "id")
    @DynamoDBAutoGeneratedKey
    @JsonIgnore
    private String id;

    @DynamoDBAttribute(attributeName = "login")
    @JsonProperty("login")
    private String login;

    @DynamoDBAttribute(attributeName = "email")
    @JsonProperty("password")
    private String password;

    @DynamoDBAttribute(attributeName = "phone_number")
    @JsonProperty("phone_number")
    private String phoneNumber;

    @DynamoDBAttribute(attributeName = "active")
    @JsonProperty("active")
    private Boolean active;

    public User() {
    }

    public User(
                String login,
                String password,
                String phoneNumber,
                Boolean active
    ) {
        this.login = login;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.active = active;
    }

    //Getters and Setters...
}