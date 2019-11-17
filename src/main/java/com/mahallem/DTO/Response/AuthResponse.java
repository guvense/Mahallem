package com.mahallem.DTO.Response;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthResponse {

    private String userName;

    private String firstName;

    private String lastName;

    private String _id;

    private String token;
}
