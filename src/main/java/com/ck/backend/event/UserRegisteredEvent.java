package com.ck.backend.event;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegisteredEvent {
    private String email;

    public UserRegisteredEvent(String email) {
        this.email = email;
    }
}
