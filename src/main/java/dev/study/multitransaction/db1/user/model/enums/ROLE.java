package dev.study.multitransaction.db1.user.model.enums;

import lombok.Getter;

@Getter
public enum ROLE {
    ADMIN("ADMIN"),
    USER("USER");

    private final String name;

    ROLE(String name) {
        this.name = name;
    }


}
