package com.bank.sbnz.auth;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    public String firstName;
    public String lastName;
    public String username;
    private String password;
}
