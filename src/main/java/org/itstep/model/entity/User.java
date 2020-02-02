package org.itstep.model.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class User {
    private int id;
    private String login;
    private String password;

    public enum ROLE {
        USER, ADMIN, UNKNOWN
    }

    private ROLE role;
}
