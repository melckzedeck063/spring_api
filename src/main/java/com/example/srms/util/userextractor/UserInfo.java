package com.example.srms.util.userextractor;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {

    private Long id;
    private String email;
    private String firstName;
    private String lastName;

}
