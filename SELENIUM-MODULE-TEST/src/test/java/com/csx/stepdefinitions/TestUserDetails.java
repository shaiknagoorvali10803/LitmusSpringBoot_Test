package com.csx.stepdefinitions;

import org.springframework.stereotype.Component;
@Component
public class TestUserDetails {
    protected UserDetails userDetails;

    public UserDetails getUserDetails() {
        return userDetails;
    }
    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }
}
