package com.sbnz.sbnz.facts;

import com.sbnz.sbnz.enums.Role;
import com.sbnz.sbnz.model.AppUser;
import com.sbnz.sbnz.model.Rating;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoggedInUser {

    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private Role role;
    private List<Rating> ratings;
    private List<UserPurchase> purchases;

    public LoggedInUser(AppUser appUser) {
        this.id = appUser.getId();
        this.username = appUser.getUsername();
        this.firstName = appUser.getFirstName();
        this.lastName = appUser.getLastName();
        this.role = appUser.getRole();
        this.ratings = appUser.getRatings();
    }
}
