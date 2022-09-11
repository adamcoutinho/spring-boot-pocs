package com.main.sqs.endpoint;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.UUID;

public class UserRequest {

    @JsonProperty("id_external")
    private UUID idExternal;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("years_old")
    private Integer yearsOld;
    @JsonProperty("created_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd")
    private LocalDate createdAt;

    public String getFirstName() {
        return firstName;
    }

    public Integer getYearsOld() {
        return yearsOld;
    }

    public UUID getIdExternal() {
        return idExternal;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "UserRequest{" +
                "idExternal=" + idExternal +
                ", firstName='" + firstName + '\'' +
                ", yearsOld=" + yearsOld +
                ", createdAt=" + createdAt +
                '}';
    }
}
